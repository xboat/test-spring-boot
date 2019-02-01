package com.example.shardingjdbc.repository;

import io.shardingsphere.api.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class DataRepository {

    @Autowired
    private DataSource dataSource;

    public void createTable() throws SQLException {
        execute("CREATE TABLE IF NOT EXISTS t_order (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id))");
        execute("CREATE TABLE IF NOT EXISTS t_order_item (order_item_id BIGINT NOT NULL AUTO_INCREMENT, order_id BIGINT NOT NULL, user_id INT NOT NULL, PRIMARY KEY (order_item_id))");
    }

    public void insertData() throws SQLException {
        for (int i = 1; i < 1000; i++) {
            long orderId = insertAndGetGeneratedKey("INSERT INTO t_order (order_id,user_id, status) VALUES ("+i+","+i+", 'INIT')");
            execute(String.format("INSERT INTO t_order_item (order_id, user_id) VALUES (%d, "+i+")", i));
//            orderId = insertAndGetGeneratedKey("INSERT INTO t_order (user_id, status) VALUES ("+i+", 'INIT')");
//            execute(String.format("INSERT INTO t_order_item (order_id, user_id) VALUES (%d, "+i+")", orderId));
        }
    }

    private long insertAndGetGeneratedKey(final String sql) throws SQLException {
        long result = -1;
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    result = resultSet.getLong(1);
                }
            }
        }
        return result;
    }

    private void queryWithEqual() throws SQLException {
        String sql = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.user_id=?";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, 10);
            printQuery(preparedStatement);
        }
    }

    private void queryWithIn() throws SQLException {
        String sql = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.user_id IN (?, ?)";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, 10);
            preparedStatement.setInt(2, 11);
            printQuery(preparedStatement);
        }
    }

    private void queryWithHint() throws SQLException {
        String sql = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id";
        try (
                HintManager hintManager = HintManager.getInstance();
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            hintManager.addDatabaseShardingValue("t_order", "user_id");
            printQuery(preparedStatement);
        }
    }

    private void printQuery(final PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            System.out.println(resultSet.next());

            while (resultSet.next()) {
                System.out.print("order_item_id:" + resultSet.getLong(1) + ", ");
                System.out.print("order_id:" + resultSet.getLong(2) + ", ");
                System.out.print("u7ser_id:" + resultSet.getInt(3));
                System.out.println();
                break;
            }
        }
    }

    public void dropTable() throws SQLException {
        execute("DROP TABLE t_order_item");
        execute("DROP TABLE t_order");
    }

    private void execute(final String sql) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }
}