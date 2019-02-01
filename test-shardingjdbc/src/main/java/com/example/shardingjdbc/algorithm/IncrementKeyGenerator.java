package com.example.shardingjdbc.algorithm;

import io.shardingsphere.core.keygen.generator.KeyGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public final class IncrementKeyGenerator implements KeyGenerator {
    
    private final AtomicInteger count = new AtomicInteger();
    
    @Getter
    @Setter
    private Properties properties = new Properties();
    
    @Override
    public Comparable<?> generateKey() {
        return count.incrementAndGet();
    }
}
