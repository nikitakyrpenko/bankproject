package utility;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

public class CollectionUtility {

    private CollectionUtility() {}

    public static <T> List<T> nullSafeListInitialize(List<T> items) {
        return items == null ? emptyList() : unmodifiableList(items);
    }

    public static <T> List<T> createCopyAndUpdateUnmodifiableList(List<T> items, T item){
        List<T> copy = new ArrayList<>(items);
        copy.add(item);
        return unmodifiableList(copy);
    }
}
