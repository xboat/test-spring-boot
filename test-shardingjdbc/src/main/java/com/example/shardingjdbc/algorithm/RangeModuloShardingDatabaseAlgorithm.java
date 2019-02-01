package com.example.shardingjdbc.algorithm;

import com.google.common.collect.Range;
import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class RangeModuloShardingDatabaseAlgorithm implements RangeShardingAlgorithm<Integer> {
    
    @Override
    public Collection<String> doSharding(final Collection<String> databaseNames, final RangeShardingValue<Integer> shardingValue) {
        Set<String> result = new LinkedHashSet<>();
        if (Range.closed(1, 5).encloses(shardingValue.getValueRange())) {
            for (String each : databaseNames) {
                if (each.endsWith("0")) {
                    result.add(each);
                }
            }
        } else if (Range.closed(6, 10).encloses(shardingValue.getValueRange())) {
            for (String each : databaseNames) {
                if (each.endsWith("1")) {
                    result.add(each);
                }
            }
        } else if (Range.closed(1, 10).encloses(shardingValue.getValueRange())) {
            result.addAll(databaseNames);
        } else {
            throw new UnsupportedOperationException();
        }
        return result;
    }
}
