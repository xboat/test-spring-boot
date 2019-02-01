package com.example.shardingjdbc.config;

import com.example.shardingjdbc.algorithm.*;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@MapperScan(basePackages = "com.example.shardingjdbc.mapper")
public class DataSourceConfig {

    @Bean(name = "shardingDataSource")
    public DataSource sharding() throws SQLException {
        // 配置数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds0", DataSourceUtil.createDataSource("ds0"));
        dataSourceMap.put("ds1",  DataSourceUtil.createDataSource("ds1"));

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("t_order");
        orderTableRuleConfig.setActualDataNodes("ds${0..1}.t_order${0..1}");

//        List<String> orderActualDataNodes = new LinkedList<>();
//        orderActualDataNodes.add("ds${0..1}.t_order_${0..1}");
//        orderTableRuleConfig.setActualDataNodes(Joiner.on(",").join(orderActualDataNodes));


        // 配置分库 + 分表策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order${order_id % 2}"));
        //orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_item_id", "t_order_item${order_item_id % 2}"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        orderTableRuleConfig.setKeyGeneratorColumnName("order_id");
//        orderTableRuleConfig.setKeyGenerator(new IncrementKeyGenerator());
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        // 配置order_item表规则...
        TableRuleConfiguration orderItemTableRuleConfig = new TableRuleConfiguration();
        orderItemTableRuleConfig.setLogicTable("t_order_item");
        orderItemTableRuleConfig.setActualDataNodes("ds${0..1}.t_order_item${0..1}");
        orderItemTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_item_id", "t_order_item${order_item_id % 2}"));

//        List<String> orderItemActualDataNodes = new LinkedList<>();
//        orderItemActualDataNodes.add("ds0.t_order_item_${0..1}");
//        orderItemActualDataNodes.add("ds1.t_order_item_${0..1}");
//        orderItemTableRuleConfig.setActualDataNodes(Joiner.on(",").join(orderItemActualDataNodes));

        shardingRuleConfig.getTableRuleConfigs().add(orderItemTableRuleConfig);

//        shardingRuleConfig.getBindingTableGroups().add("t_order, t_order_item");
//
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", new PreciseModuloShardingTableAlgorithm(), new RangeModuloShardingTableAlgorithm()));
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new PreciseModuloShardingDatabaseAlgorithm(), new RangeModuloShardingDatabaseAlgorithm()));

        // 获取数据源对象
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap(), new Properties());
    }

//    @Bean
//    public DataSourceTransactionManager transactitonManager(DataSource shardingDataSource) {
//        return new DataSourceTransactionManager(shardingDataSource);
//    }
//
//    @Bean
//    @Primary
//    public SqlSessionTemplate testSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

//
//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer(){
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        //mapperScannerConfigurer.setSqlSessionFactoryBeanName("testSqlSessionTemplate");
//        mapperScannerConfigurer.setBasePackage("com.example.shardingjdbc.mapper");
//        return mapperScannerConfigurer;
//    }
}
