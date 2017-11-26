package com.example.free.mymvpdemo.bean;

/**
 * Created by free on 2017/11/26.
 */

public class MySection extends RaySectionEntity<RecycleObject> {
    public MySection(boolean isHeader, String header, RecycleObject timeLine) {
        super(isHeader, header, timeLine);
    }

    public MySection(RecycleObject t) {
        super(t);
    }
}
