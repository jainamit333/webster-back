package com.webster.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by amit on 15/7/16.
 */
@Entity
@Data
public class WebsterLog {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String userId;

    @Column
    private String webpage;

    @Column
    private String webpageCore;

    @Column
    private String couchKey;

}
