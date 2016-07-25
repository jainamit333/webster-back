package com.webster.entity;

import lombok.Data;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.Document;


import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by amit on 23/7/16.
 */
@Document
@Data
public class UserWebpage<T> implements Serializable{

    @Id
    private String Id;

    @Field
    private T document;

    @Field
    private Date createdAt;

    @Field
    private Date updatedAt;

    @Field
    private Map<String,String> metaData;


}
