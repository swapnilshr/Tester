import model.BatchTransfer;
import model.ScannedDocumentTransfer;
import vin.decoder.VIN;
import vin.encoder.VinGeneratorUtils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class DataGenerator {
    private static String[] STATES = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA",
                              "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA",
                              "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY",
                              "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX",
                              "UT", "VT", "VA", "WA", "WV", "WI", "WY" };
    private static String[] CLIENTS = {"HONDA", "PNC", "RETAILHAFC", "PINELLAS"};
//    private static String[] LIEN = {"DRIVE FINANCIAL SERVICES", "HSBC AUTO FINANCE INC", "NAVY FEDERAL CREDIT UNION",
//                                    "KINETIC FEDERAL CREDIT UNION", "AMERICAN HONDA FINANCE CORP"};
    private static String[] LIEN = {"DAIMLER", "CAPONE", "HONDA", "BKWEST", "CHASE"};

    private static String[] MAKE = {"HOND", "ACUR", "NISS", "MITS", "SUZI", "AUDI", "FORD", "JEEP"};

    public static Map<BatchTransfer, ScannedDocumentTransfer> getData(int count) {

        SecureRandom random = new SecureRandom();
        Map<BatchTransfer, ScannedDocumentTransfer> output = new LinkedHashMap<>();

        for(int i=0; i<count; i++) {
            String batchNumber = generateBatchNumber();
            String vin = VinGeneratorUtils.getRandomVin();
            VIN vinDecoded = new VIN(vin);

            BatchTransfer batchTransfer = new BatchTransfer();
            batchTransfer.setBatchNumber(batchNumber);
            batchTransfer.setClientShortName(CLIENTS[random.nextInt(CLIENTS.length)]);
            batchTransfer.setState(STATES[random.nextInt(STATES.length)]);
            batchTransfer.setLegacyStatus("I");
            LocalDateTime dateTime = LocalDateTime.now()
                    .withYear(vinDecoded.getYear());
            String dateTimeStr = dateTime.toString().substring(0, dateTime.toString().length() - 4);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a");

            batchTransfer.setCreatedDateTime(dateTimeStr);
            batchTransfer.setTrackingId("03 TRIAD");
            batchTransfer.setLastModDateTime(dateTimeStr);
            batchTransfer.setBatchControlNumber(generateBatchControlNumber());
            batchTransfer.setCommitUsername("Reader Auto-Commit");
            batchTransfer.setBatchReceiveDate(dateTimeStr);
            batchTransfer.setLegacyDocumentType("T");
            batchTransfer.setDocumentTypeIdentifier("1");
            batchTransfer.setTotalScannedCount("1");
            batchTransfer.setIsOriginal("1");
            batchTransfer.setLocationId("1");

            ScannedDocumentTransfer documentTransfer = new ScannedDocumentTransfer();
            documentTransfer.setBatchNumber(batchTransfer.getBatchNumber());
            documentTransfer.setManufacturerId(vin);
            documentTransfer.setRawOwnerData("SShrivastava");
            documentTransfer.setOdometerReadingData("");
            documentTransfer.setLienholderName(LIEN[random.nextInt(LIEN.length)]);
            documentTransfer.setMake(MAKE[random.nextInt(MAKE.length)]);
            documentTransfer.setYear(Integer.valueOf(vinDecoded.getYear()).toString());
            documentTransfer.setCreatedDateTime(dateTime.format(formatter));
            documentTransfer.setImageUncFullPath("z:\\teleformimages\\0" + batchTransfer.getBatchNumber() +
                    "\\" + randomString(6, true) + "0000.tif");
            documentTransfer.setDocumentIdentifier("0" + batchTransfer.getBatchNumber() + " - " + 1);
            documentTransfer.setVerifierUsername("khousto");
            documentTransfer.setFormId("14327299");
            documentTransfer.setBatchDirectory("u:\\tf\\bat\\00200118");
            documentTransfer.setTotalScannedCount(Integer.valueOf(random.nextInt(10)).toString());
            documentTransfer.setBatchReceiveDate(documentTransfer.getCreatedDateTime().substring(0, 10));
            documentTransfer.setScanningMachineName("khousto");
            documentTransfer.setTrackingId(batchTransfer.getTrackingId());
            documentTransfer.setBatchSequence("0");
            documentTransfer.setTitleNumber("");
            documentTransfer.setIssuanceDateData(documentTransfer.getBatchReceiveDate());
            documentTransfer.setDocumentId(UUID.randomUUID().toString());
            output.put(batchTransfer, documentTransfer);
        }
        return output;
    }

    private static String generateBatchNumber() {
        long leftLimit = Long.parseLong("100000000000");
        long rightLimit = Long.parseLong("1000000000000");
        Long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        return generatedLong.toString();
    }

    private static String generateBatchControlNumber() {
        int min = 1100000;
        int max = 10000000;
        Integer generated = new SecureRandom().nextInt((max - min) + 1) + min;
        return generated.toString();
    }

    private static String randomString(int length, boolean withNum) {
        StringBuilder output = new StringBuilder();
        SecureRandom random = new SecureRandom();
        String alpha = "ABCDEFGHJKLMNPRSTUVWXYZ";
        for (int i=0; i< length; i++ ) {
            if (withNum && random.nextInt(10) > 5) {
                output.append(random.nextInt(10));
            }
            else {
                output.append(alpha.charAt(random.nextInt(alpha.length())));
            }
        }
        return output.toString();
    }



}
