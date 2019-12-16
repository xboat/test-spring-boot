package com.example.cosmosdb;

/**
 * @author xboat date 2019-12-14
 */
public class CloudStorageConfiguration {

    private String sasToken;

    private String resourceUri;

    private String tableName;

    public String getSasToken() {
        return sasToken;
    }

    public void setSasToken(String sasToken) {
        this.sasToken = sasToken;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
