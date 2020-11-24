package com.java.customisedGenericClass.experiment.bean;

import java.util.*;

public class DAO<T> {
    private Map<String, T> map = new HashMap<>();

    public DAO() {
    }

    public void save(String id, T entity){
        map.put(id, entity);
    }

    public T get(String id){
        return map.get(id);
    }

    public void update(String id, T entity){
        if (map.containsKey(id))
            save(id, entity);
    }

    public List<T> list(){
        Collection<T> values = map.values();
        List<T> l = new ArrayList<>();
        for ( T v: values){
            l.add(v);
        }
        return l;
    }

    public void delete(String id){
        map.remove(id);
    }
    public Map<String, T> getMap() {
        return map;
    }

    public void setMap(Map<String, T> map) {
        this.map = map;
    }
}
