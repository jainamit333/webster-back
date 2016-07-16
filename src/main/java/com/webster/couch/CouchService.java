package com.webster.couch;

import java.util.List;

/**
 * Created by amit on 15/7/16.
 */
public interface CouchService<CouchDocument> {

    void add(CouchDocument t);
    Object get(String key);
    void delete(String key);
    List<CouchDocument> getFromDocument(int i);
    void deleteFromDocument(int i);
}
