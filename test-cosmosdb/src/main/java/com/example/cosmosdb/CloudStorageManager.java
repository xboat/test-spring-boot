package com.example.cosmosdb;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.*;

import java.net.URISyntaxException;
import java.util.*;

/**
 * @author xboat date 2019-12-14
 */
public class CloudStorageManager {

    private final CloudTable _cloudTable;

    private CloudStorageProvider _storageAccountProvider;

    public CloudStorageManager(CloudStorageConfiguration configuration) throws URISyntaxException, StorageException {

        _storageAccountProvider = new CloudStorageProvider(configuration);
        this._cloudTable = _storageAccountProvider.createTable(configuration.getTableName());
    }


    public CustomerEntity get(String partitionKey, String rowKey) throws StorageException {
        TableOperation retrieveSmithJeff = TableOperation.retrieve(partitionKey, rowKey, CustomerEntity.class);
        return _cloudTable.execute(retrieveSmithJeff).getResultAsType();
    }


    public void insert(CustomerEntity customer) throws StorageException {
        try {
            // Create an operation to add the new customer to the people table.
            TableOperation insertCustomer1 = TableOperation.insertOrReplace(customer);

            // Submit the operation to the table service.
            TableResult result = _cloudTable.execute(insertCustomer1);
            result.getResult();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void insertBatch(List<CustomerEntity> customerEntities) throws StorageException {
        try {
            TableBatchOperation batchOperation = new TableBatchOperation();
            // Create a customer entity to add to the table.
            CustomerEntity customer = new CustomerEntity("Smith", "Jeff");
            customer.setCtnNo("Jeff@contoso.com");
            customer.setConfirmBatchNo("425-555-0104");
            batchOperation.insertOrReplace(customer);

            // Create another customer entity to add to the table.
            CustomerEntity customer2 = new CustomerEntity("Smith", "Ben");
            customer2.setCtnNo("Ben@contoso.com");
            customer2.setConfirmBatchNo("425-555-0102");
            batchOperation.insertOrReplace(customer2);

            // Create a third customer entity to add to the table.
            CustomerEntity customer3 = new CustomerEntity("Smith", "Denise");
            customer3.setCtnNo("Denise@contoso.com");
            customer3.setConfirmBatchNo("425-555-0103");
            batchOperation.insertOrReplace(customer3);
            ArrayList<TableResult> result = _cloudTable.execute(batchOperation);
            result.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检索分区中的所有实体
     * @param propertyName
     * @param value
     * @return
     */
    public Iterable<CustomerEntity> getList(String propertyName, String value) {
        try {
            String partitionFilter = TableQuery.generateFilterCondition(propertyName,TableQuery.QueryComparisons.EQUAL,value);

            // Specify a partition query, using "Smith" as the partition key filter.
            TableQuery<CustomerEntity> partitionQuery = TableQuery.from(CustomerEntity.class).where(partitionFilter);

            Iterable<CustomerEntity> entities = _cloudTable.execute(partitionQuery);
            return entities;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 检索分区中的一部分实体
     *
     * @param partitionKey
     * @param value
     */
    public void getList(String partitionKey, String rowKey,String value) {
        try {

            // Create a filter condition where the partition key is "Smith".
            String partitionFilter = TableQuery.generateFilterCondition(
                    partitionKey,
                    TableQuery.QueryComparisons.EQUAL,
                    "Smith");

            // Create a filter condition where the row key is less than the letter "E".
            String rowFilter = TableQuery.generateFilterCondition(
                    rowKey,
                    TableQuery.QueryComparisons.LESS_THAN,
                    "E");

            // Combine the two conditions into a filter expression.
            String combinedFilter = TableQuery.combineFilters(partitionFilter,
                    TableQuery.Operators.AND, rowFilter);

            // Specify a range query, using "Smith" as the partition key,
            // with the row key being up to the letter "E".
            TableQuery<CustomerEntity> rangeQuery =
                    TableQuery.from(CustomerEntity.class)
                            .where(combinedFilter);

            // Loop through the results, displaying information about the entity
            for (CustomerEntity entity : _cloudTable.execute(rangeQuery)) {
                System.out.println(entity.getPartitionKey() +
                        " " + entity.getRowKey() +
                        "\t" + entity.getCtnNo() +
                        "\t" + entity.getConfirmBatchNo());
            }
        } catch (Exception e) {
            // Output the stack trace.
            e.printStackTrace();
        }
    }

    public void update(String partitionKey, String rowKey,String ctnNo) throws StorageException {
        try
        {
            // Retrieve the entity with partition key of "Smith" and row key of "Jeff".
            TableOperation retrieveSmithJeff = TableOperation.retrieve(partitionKey, rowKey, CustomerEntity.class);

            // Submit the operation to the table service and get the specific entity.
            CustomerEntity specificEntity = _cloudTable.execute(retrieveSmithJeff).getResultAsType();

            // Specify a new phone number.
            specificEntity.setCtnNo(ctnNo);

            // Create an operation to replace the entity.
            TableOperation replaceEntity = TableOperation.replace(specificEntity);

            // Submit the operation to the table service.
            _cloudTable.execute(replaceEntity);
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
    }


    /**
     * 查询一部分实体属性
     * @param fields
     * @throws StorageException
     */
    public void getList(String[] fields) throws StorageException {
        try
        {
            // Define a projection query that retrieves only the Email property
            TableQuery<CustomerEntity> projectionQuery =
                    TableQuery.from(CustomerEntity.class)
                            .select(new String[] {"Email"});

            // Define an Entity resolver to project the entity to the Email value.
            EntityResolver<String> emailResolver = new EntityResolver<String>() {
                @Override
                public String resolve(String PartitionKey, String RowKey, Date timeStamp, HashMap<String, EntityProperty> properties, String etag) {
                    return properties.get("Email").getValueAsString();
                }
            };

            // Loop through the results, displaying the Email values.
            for (String projectedString :
                    _cloudTable.execute(projectionQuery, emailResolver)) {
                System.out.println(projectedString);
            }
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
    }

    public Object delete(String partitionKey, String rowKey) throws StorageException {
        try
        {
            // Create an operation to retrieve the entity with partition key of "Smith" and row key of "Jeff".
            TableOperation retrieveSmithJeff = TableOperation.retrieve(partitionKey, rowKey, CustomerEntity.class);

            // Retrieve the entity with partition key of "Smith" and row key of "Jeff".
            CustomerEntity entitySmithJeff = _cloudTable.execute(retrieveSmithJeff).getResultAsType();

            // Create an operation to delete the entity.
            TableOperation deleteSmithJeff = TableOperation.delete(entitySmithJeff);

            // Submit the delete operation to the table service.
            TableResult tableResult = _cloudTable.execute(deleteSmithJeff);
            Object obj = tableResult.getResult();
            System.out.println(obj);
            return obj;
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
        return null;
    }

    public void deleteTable() throws StorageException {
        try
        {
            _cloudTable.deleteIfExists();
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
    }

}
