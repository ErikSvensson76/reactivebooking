package com.example.reactivebooking.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

public abstract class BaseModel<T, ID> implements Persistable<ID> {
    @Transient
    protected boolean isNew;
    @Transient
    public abstract T setIsNew();
    @Transient
    public abstract boolean isNew();
}
