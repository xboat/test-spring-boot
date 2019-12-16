package com.example.cosmosdb;

import com.microsoft.azure.storage.StorageException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;

/**
 * @author xboat date 2019-12-14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CosmosdbApplication.class)
public class CloudStorageManagerTest {

    private CloudStorageConfiguration _configuration;

    private CloudStorageManager _cloudStorageManager;

    public CloudStorageManagerTest() throws URISyntaxException, StorageException {
        CloudStorageConfiguration configuration = new CloudStorageConfiguration();
        configuration.setSasToken("3D&tn=packages");
        configuration.setResourceUri("https://xcontroltestdw.table.core.windows.net");
        configuration.setTableName("packages");
        _cloudStorageManager = new CloudStorageManager(configuration);
    }


    @Test
    public void get() throws StorageException {
        CustomerEntity customerEntity = _cloudStorageManager.get("DefaultPartitionKey","101");
        System.out.println(customerEntity.getPartitionKey() +
                " " + customerEntity.getRowKey() +
                "\t" + customerEntity.getCtnNo() +
                "\t" + customerEntity.getConfirmBatchNo());
        Assert.assertNotNull(customerEntity);
    }

    @Test
    public void getList() {
        Iterable<CustomerEntity> entities = _cloudStorageManager.getList("CTNNo","A23125");
        // entities.iterator().hasNext();
        for (CustomerEntity entity : entities) {
            System.out.println(entity.getPartitionKey() +
                    " " + entity.getRowKey() +
                    "\t" + entity.getCtnNo() +
                    "\t" + entity.getConfirmBatchNo());
        }
        Assert.assertNotNull(entities);
    }

    @Test
    public void delete() throws StorageException {
        Object obj = _cloudStorageManager.delete("DefaultPartitionKey","101");

        Assert.assertNotNull(obj);
    }


}