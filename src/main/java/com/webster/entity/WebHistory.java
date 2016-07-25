package com.webster.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by amit on 23/7/16.
 */
@Entity
@Table(name = "web_history")
@Data
public class WebHistory implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String completeUrl;

    @Column
    private String mainUrl;

    @Column
    private String userId;

    @Column
    private Date createdAt;
}
