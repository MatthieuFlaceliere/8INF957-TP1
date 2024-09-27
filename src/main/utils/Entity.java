package main.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Entity {
    private static final Map<Object, AtomicLong> idMap = new HashMap<>();
    private final Long id;

    protected Entity() {
        id = idMap.computeIfAbsent(this.getClass(), k -> new AtomicLong(0)).incrementAndGet();
    }

    public Long getId() {
        return id;
    }
}