package com.mindbox.notes.infra;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NoteUpdateLimiter {

    private final Map<Long, Long> lastUpdateTime = new ConcurrentHashMap<>();
    private static final long MIN_INTERVAL_MS = 500; // 1 second

    public boolean canUpdate(Long noteId) {
        long now = System.currentTimeMillis();
        return lastUpdateTime.compute(noteId, (id, last) -> {
            if (last == null || now - last >= MIN_INTERVAL_MS) {
                return now;
            } else {
                return last;
            }
        }) == now;
    }
}
