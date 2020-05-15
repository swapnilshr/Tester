
import model.BatchTransfer;
import model.ScannedDocumentTransfer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Map;

public class Application {

    private static final String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    //The JDBC connection URL which allows for Windows authentication is defined below.
   private static final String jdbcURL = "jdbc:sqlserver://sacsqldev14.cm.fdielt.com:1433;databaseName=SquashQA;integratedSecurity=true";
   //private static final String jdbcURL = "jdbc:sqlserver://sacsqldev13.cm.fdielt.com:1433;databaseName=CMSPerformance3;integratedSecurity=true";

    public static void main(String[] args) throws SQLException {
        System.out.println("Program started");
        try
        {
            Class.forName(jdbcDriver).newInstance();
            System.out.println("JDBC driver loaded");
        }
        catch (Exception err)
        {
            System.err.println("Error loading JDBC driver");
            err.printStackTrace(System.err);
            System.exit(0);
        }

        Connection databaseConnection= null;
        try
        {
            //Connect to the database
            databaseConnection = DriverManager.getConnection(jdbcURL);
            System.out.println("Connected to the database");
        }
        catch (SQLException err)
        {
            System.err.println("Error connecting to the database");
            err.printStackTrace(System.err);
            System.exit(0);
        }
        try
        {
            //declare the statement object
            Statement sqlStatement = databaseConnection.createStatement();

            execCommand(5, sqlStatement);

            System.out.println("Closing database connection");

            //close the database connection
            databaseConnection.close();
        }
        catch (SQLException err)
        {
            System.err.println("SQL Error");
            err.printStackTrace(System.err);
            System.exit(0);
        }
        System.out.println("Program finished");
    }

    private static void execCommand(int count, Statement sqlStatement) throws SQLException {
        if (count > 0 && count < 100 && sqlStatement != null) {
            System.out.println("************************************");
            System.out.println(" Batch Number  | Manufacturer Id");
            Map<BatchTransfer, ScannedDocumentTransfer> values = DataGenerator.getData(count, "WA",
                    "1", "HONDA");
            sqlStatement.execute("BEGIN TRANSACTION;");
            for (BatchTransfer batchTransfer: values.keySet()) {
                ScannedDocumentTransfer documentTransfer = values.get(batchTransfer);
                //execute the command using the execute method
                sqlStatement.execute(prepareBatchTransferCmd(batchTransfer));
                sqlStatement.execute(prepareScannedDocumentTransferCmd(documentTransfer));
                System.out.println(" " + batchTransfer.getBatchNumber() + "  | " + documentTransfer.getManufacturerId() + "  | " + batchTransfer.getState() +
                        "  | " + batchTransfer.getDocumentTypeIdentifier() + "  | " + batchTransfer.getClientShortName());
            }
            sqlStatement.execute("COMMIT TRANSACTION;");
            sqlStatement.execute("BEGIN TRANSACTION;");

            sqlStatement.execute("update SCHEDULE SET NEXT_SCHEDULED = '" +
                            LocalDateTime.now().plusSeconds(5).toString() +
                            "',IS_ENABLED=1 where SCHEDULE_ID = 1080");
            sqlStatement.execute("COMMIT TRANSACTION;");
            System.out.println("************************************");
        }
    }
    private static String prepareBatchTransferCmd(BatchTransfer batchTransfer) {
        StringBuilder output = new StringBuilder();
        if (batchTransfer != null && batchTransfer.getBatchNumber() != null) {
            output.append("INSERT INTO BATCH_TRANSFER (BATCH_NUMBER, CLIENT_SHORT_NAME," +
                    "STATE, LEGACY_STATUS, CREATED_DATE_TIME, TRACKING_ID," +
                    "LAST_MOD_DATE_TIME, BATCH_CONTROL_NUMBER, COMMIT_USERNAME, BATCH_RECEIVE_DATE," +
                    "LEGACY_DOCUMENT_TYPE, DOCUMENT_TYPE_IDENTIFIER, TOTAL_SCANNED_COUNT," +
                    "IS_ORIGINAL, LOCATION_ID) values ('");
            output.append(batchTransfer.getBatchNumber()).append("', '");
            output.append(batchTransfer.getClientShortName()).append("', '");
            output.append(batchTransfer.getState()).append("', '");
            output.append(batchTransfer.getLegacyStatus()).append("', '");
//            output.append("2010-03-23T11:30:19','03 TRIAD ','2010-03-23T11:30:19','1815079','Reader Auto-Commit'," +
//                    "'2010-03-23T00:00:00','T','1','1','1','1')");
            output.append(batchTransfer.getCreatedDateTime()).append("', '");
            output.append(batchTransfer.getTrackingId()).append("', '");
            output.append(batchTransfer.getLastModDateTime()).append("', '");
            output.append(batchTransfer.getBatchControlNumber()).append("', '");
            output.append(batchTransfer.getCommitUsername()).append("', '");
            output.append(batchTransfer.getBatchReceiveDate()).append("', '");
            output.append(batchTransfer.getLegacyDocumentType()).append("', '");
            output.append(batchTransfer.getDocumentTypeIdentifier()).append("', '");
            output.append(batchTransfer.getTotalScannedCount()).append("', '");
            output.append(batchTransfer.getIsOriginal()).append("', '");
            output.append(batchTransfer.getLocationId()).append("')");
        }
        return output.toString();
    }

    private static String prepareScannedDocumentTransferCmd(ScannedDocumentTransfer documentTransfer) {
        StringBuilder output = new StringBuilder();
        if (documentTransfer != null && documentTransfer.getBatchNumber() != null) {
            output.append("INSERT INTO SCANNED_DOCUMENT_TRANSFER (BATCH_NUMBER, MANUFACTURER_ID, " +
                    "RAW_OWNER_DATA, ODOMETER_READING_DATA, LIENHOLDER_NAME, MAKE, YEAR, " +
                    "CREATED_DATE_TIME, IMAGE_UNC_FULL_PATH, DOCUMENT_IDENTIFIER, VERIFIER_USERNAME, " +
                    "FORM_ID, BATCH_DIRECTORY, TOTAL_SCANNED_COUNT, BATCH_RECEIVE_DATE, " +
                    "SCANNING_MACHINE_NAME, TRACKING_ID, BATCH_SEQUENCE, TITLE_NUMBER, " +
                    "ISSUANCE_DATE_DATA, DOCUMENT_ID ) values ('");
            output.append(documentTransfer.getBatchNumber()).append("', '");
            output.append(documentTransfer.getManufacturerId()).append("', '");
            output.append(documentTransfer.getRawOwnerData()).append("', '");
            output.append(documentTransfer.getOdometerReadingData()).append("', '");
            output.append(documentTransfer.getLienholderName()).append("', '");
//            output.append("','DBPO12LNID','AUDI','','08/23/2006 11:28:24 AM','\\\\dorado\\Images\\Image0013.tif'," +
//                    "'00200118 - 1','khousto','14327299','u:\\tf\\bat\\00200118','3','08/23/2006','khousto'," +
//                    "'03 TRIAD','0','','07/29/06',NEWID())");
            output.append(documentTransfer.getMake()).append("', '");
            output.append(documentTransfer.getYear()).append("', '");
            output.append(documentTransfer.getCreatedDateTime()).append("', '");
            output.append(documentTransfer.getImageUncFullPath()).append("', '");
            output.append(documentTransfer.getDocumentIdentifier()).append("', '");
            output.append(documentTransfer.getVerifierUsername()).append("', '");
            output.append(documentTransfer.getFormId()).append("', '");
            output.append(documentTransfer.getBatchDirectory()).append("', '");
            output.append(documentTransfer.getTotalScannedCount()).append("', '");
            output.append(documentTransfer.getBatchReceiveDate()).append("', '");
            output.append(documentTransfer.getScanningMachineName()).append("', '");
            output.append(documentTransfer.getTrackingId()).append("', '");
            output.append(documentTransfer.getBatchSequence()).append("', '");
            output.append(documentTransfer.getTitleNumber()).append("', '");
            output.append(documentTransfer.getIssuanceDateData()).append("', ");
            output.append("NEWID()").append(")");
        }
        return output.toString();
    }

}
