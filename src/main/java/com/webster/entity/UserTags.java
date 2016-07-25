package com.webster.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by amit on 23/7/16.
 */
@Entity
@Table(name = "web_tags")
@Data
public class UserTags implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String userId;

    @Column
    private String tags;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;
}
