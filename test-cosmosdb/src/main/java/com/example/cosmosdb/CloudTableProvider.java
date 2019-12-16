package com.example.cosmosdb;

import com.microsoft.azure.storage.StorageCredentialsSharedAccessSignature;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author xboat date 2019-12-16
 */
public class CloudTableProvider {
    private  CloudTableClient cloudTableClient;

    public CloudTableProvider(CloudStorageConfiguration configuration) throws URISyntaxException {
        StorageCredentialsSharedAccessSignature storageCredentials= new StorageCredentialsSharedAccessSignature(configuration.getSasToken());
        // Create the table client.
        cloudTableClient = new CloudTableClient(new URI(configuration.getResourceUri()),storageCredentials);
    }

    public CloudTable createTable(String tableName) throws StorageException, URISyntaxException {
        // Create the table if it doesn't exist.
        CloudTable cloudTable = cloudTableClient.getTableReference(tableName);
        return cloudTable;
    }

}
