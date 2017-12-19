package com.example.free.mymvpdemo.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;


class OkHttpCallManager {
    private ConcurrentHashMap<Object, List<Call>> callMap;
    private static OkHttpCallManager manager;

    private OkHttpCallManager() {
        callMap = new ConcurrentHashMap<>();
    }

    public static OkHttpCallManager getInstance() {
        if (manager == null) {
            manager = new OkHttpCallManager();
        }
        return manager;
    }

    public void addCall(Object tag, Call call) {
        if (tag != null && call != null) {
            if(callMap.containsKey(tag)){
                List<Call> valies = new ArrayList<>();
                valies.add(call);
                callMap.put(tag,valies);
            }else{
                List<Call> valies  = callMap.get(tag);
                if(valies == null){
                    valies = new ArrayList<>();
                }
                valies.add(call);
            }
        }
    }

    public List<Call> getCall(Object tag) {
        if (tag != null) {
            return callMap.get(tag);
        }

        return null;
    }

    public void removeCall(Object tag) {
        if (tag != null) {
            callMap.remove(tag);
        }
    }

    public void removiAll(){
        callMap.clear();
    }
}
