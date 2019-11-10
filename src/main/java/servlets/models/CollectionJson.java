package servlets.models;

import java.util.Collection;

/**
 * Класс для передачи списков через ajax-запросы.
 * @version 1.0.
 * @since 08.11.2019.
 * @author Alexander Beznos (ast1bn@mail.ru)
 */
public class CollectionJson {
    Collection collection;

    public CollectionJson(Collection collection) {
        this.collection = collection;
    }

    public CollectionJson() {
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
