package com.example.threading;

import java.util.List;

/**
 * @author xboat date 2019-01-05
 */
public interface ThreadCallBack {
    /**
     * 执行
     * @param list
     * @param <T>
     */
    <T> void process(List<T> list);
}

