package com.example.events;

/**
 * @author xboat date 2019-01-14
 */
public enum ActionTypeEnum {

    Select("查询", 1),
    Insert("添加", 2),
    Update("更新", 3),
    Delete("删除", 4);

    private String value;
    private int key;

    ActionTypeEnum(String value, int key) {
        this.value = value;
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
