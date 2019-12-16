package com.example.cosmosdb;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * @author xboat date 2019-12-14
 */
public class CloudStorageProvider {


    private CloudStorageAccount storageAccount;


    public CloudStorageProvider(CloudStorageConfiguration configuration){
        try
        {
            // Retrieve storage account from connection-string.
//            CloudStorageAccount storageAccount =  CloudStorageAccount.parse("DefaultEndpointsProtocol=https;AccountName=testName;AccountKey=testKey;EndpointSuffix=core.windows.net");
            StorageCredentialsSharedAccessSignature storageCredentials= new StorageCredentialsSharedAccessSignature(configuration.getSasToken());

            //tableEndpoint
            storageAccount = new CloudStorageAccount(storageCredentials, new URI(configuration.getResourceUri()),
                   new URI(configuration.getResourceUri()),
                   new URI(configuration.getResourceUri()));
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
    }

    public CloudTable createTable(String tableName) throws StorageException, URISyntaxException {

        // Create the table client.
        CloudTableClient tableClient = storageAccount.createCloudTableClient();
//        for (String table : tableClient.listTables())
//        {
//            // Output each table name.
//            System.out.println(table);
//        }

        // Create the table if it doesn't exist.
        CloudTable cloudTable = tableClient.getTableReference(tableName);
       // Boolean bool = cloudTable.createIfNotExists();
        return cloudTable;
    }


}
