package com.example.reactivebooking.model;

import org.springframework.data.annotation.Transient;

public abstract class BaseModel<T> {
    @Transient
    protected boolean isNew;
    @Transient
    public abstract T setIsNew();
    @Transient
    public abstract boolean isNew();
}
