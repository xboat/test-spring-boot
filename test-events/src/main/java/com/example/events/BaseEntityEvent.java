package com.example.events;

/**
 * @author xboat date 2019-01-14
 */
public class BaseEntityEvent<T> {
    public BaseEntityEvent(T entity)
    {
        this.entity = entity;
    }

    public T entity;

    public ActionTypeEnum actinType;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        entity = entity;
    }

    public ActionTypeEnum getActinType(){
        return this.actinType;
    }

    public void setActinType(ActionTypeEnum actinType){
        this.actinType = actinType;
    }
}
