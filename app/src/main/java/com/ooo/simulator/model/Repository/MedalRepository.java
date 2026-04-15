package com.ooo.simulator.model.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MedalRepository<T> {
    private final List<T> items;

    public MedalRepository() {
        items = new ArrayList<>();
    }

    public void add(T item) { items.add(item); }
    public boolean remove(T item) { return items.remove(item); }
    public List<T> getAll() { return new ArrayList<>(items); }
    public List<T> findByPredicate(Predicate<T> predicate) {
        return items.stream().filter(predicate).collect(Collectors.toList());
    }
    public boolean isEmpty() { return items.isEmpty(); }
    public int size() { return items.size(); }
    public void clear() { items.clear(); }
}