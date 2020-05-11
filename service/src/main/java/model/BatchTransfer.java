package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BATCH_TRANSFER")
public class BatchTransfer {
    @Id
    @Column(name = "BATCH_NUMBER", nullable = false)
    private String batchNumber;
    @Column(name = "CLIENT_SHORT_NAME", nullable = false)
    private String clientShortName ;
    @Column(name = "STATE", nullable = false)
    private String state ;
    @Column(name = "LEGACY_STATUS", nullable = false)
    private String legacyStatus ;
    @Column(name = "CREATED_DATE_TIME", nullable = false)
    private String createdDateTime ;
    @Column(name = "TRACKING_ID", nullable = false)
    private String trackingId ;
    @Column(name = "LAST_MOD_DATE_TIME", nullable = false)
    private String lastModDateTime ;
    @Column(name = "BATCH_CONTROL_NUMBER", nullable = false)
    private String batchControlNumber ;
    @Column(name = "COMMIT_USERNAME", nullable = false)
    private String commitUsername ;
    @Column(name = "BATCH_RECEIVE_DATE", nullable = false)
    private String batchReceiveDate ;
    @Column(name = "LEGACY_DOCUMENT_TYPE", nullable = false)
    private String legacyDocumentType ;
    @Column(name = "DOCUMENT_TYPE_IDENTIFIER", nullable = false)
    private String documentTypeIdentifier ;
    @Column(name = "TOTAL_SCANNED_COUNT", nullable = false)
    private String totalScannedCount ;
    @Column(name = "IS_ORIGINAL", nullable = false)
    private String isOriginal;
    @Column(name = "LOCATION_ID", nullable = false)
    private String locationId;

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getClientShortName() {
        return clientShortName;
    }

    public void setClientShortName(String clientShortName) {
        this.clientShortName = clientShortName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLegacyStatus() {
        return legacyStatus;
    }

    public void setLegacyStatus(String legacyStatus) {
        this.legacyStatus = legacyStatus;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getLastModDateTime() {
        return lastModDateTime;
    }

    public void setLastModDateTime(String lastModDateTime) {
        this.lastModDateTime = lastModDateTime;
    }

    public String getBatchControlNumber() {
        return batchControlNumber;
    }

    public void setBatchControlNumber(String batchControlNumber) {
        this.batchControlNumber = batchControlNumber;
    }

    public String getCommitUsername() {
        return commitUsername;
    }

    public void setCommitUsername(String commitUsername) {
        this.commitUsername = commitUsername;
    }

    public String getBatchReceiveDate() {
        return batchReceiveDate;
    }

    public void setBatchReceiveDate(String batchReceiveDate) {
        this.batchReceiveDate = batchReceiveDate;
    }

    public String getLegacyDocumentType() {
        return legacyDocumentType;
    }

    public void setLegacyDocumentType(String legacyDocumentType) {
        this.legacyDocumentType = legacyDocumentType;
    }

    public String getDocumentTypeIdentifier() {
        return documentTypeIdentifier;
    }

    public void setDocumentTypeIdentifier(String documentTypeIdentifier) {
        this.documentTypeIdentifier = documentTypeIdentifier;
    }

    public String getTotalScannedCount() {
        return totalScannedCount;
    }

    public void setTotalScannedCount(String totalScannedCount) {
        this.totalScannedCount = totalScannedCount;
    }

    public String getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(String isOriginal) {
        this.isOriginal = isOriginal;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
