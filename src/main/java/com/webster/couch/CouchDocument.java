package com.webster.couch;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.joda.time.DateTime;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

/**
 * Created by amit on 15/7/16.
 */
@org.springframework.data.couchbase.core.mapping.Document
@Cacheable
public class CouchDocument<T> {

    @Id
    private String id;

    @Field
    private DateTime createdAt;

    @Field
    private T document;

    @Field
    private Map<String,Object> metaData;

    @Field
    private int referenceId;

    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public CouchDocument(){
        createdAt = new DateTime();
    }

    public T getDocument() {
        return document;
    }

    public void setDocument(T document) {
        this.document = document;
    }

    public Map<String, Object> getMetaData() {
        return metaData;
    }

    public void setMetaData(Map<String, Object> metaData) {
        this.metaData = metaData;
    }

    public void setId(String id) {
        this.id = id;
    }
}

