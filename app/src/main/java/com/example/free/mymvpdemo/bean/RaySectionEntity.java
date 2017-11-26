package com.example.free.mymvpdemo.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by free on 2017/11/16.
 */

public abstract class RaySectionEntity<T> extends SectionEntity {

    public T t;

    public RaySectionEntity(boolean isHeader, String header, T t) {
        super(isHeader, header);
        this.t = t;
    }

    public RaySectionEntity(T t) {
        super(t);
    }
}
