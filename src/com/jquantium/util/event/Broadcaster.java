package com.jquantium.util.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mykhailo_Bohdanov on 31/08/2015.
 */
public interface Broadcaster {
    List<Watcher> watchers = new ArrayList<>();

    default void watch(Watcher watcher) {
        watchers.add(watcher);
    }
    default void unwatch(Watcher watcher) {
        watchers.remove(watcher);
    }

    default void broadcast() {
        for (Watcher watcher : watchers) {
            watcher.listen(this);
        }
    }

}
