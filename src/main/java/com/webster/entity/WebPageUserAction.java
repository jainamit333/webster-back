package com.webster.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by amit on 23/7/16.
 */

@Entity
@Table(name = "web_page_user_action")
@Data
public class WebPageUserAction implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String completeUrl;


    @Column
    private String mainUrl;

    @Column
    private String userId;

    @Column
    private Date createdAt;


    @Column
    private Date updatedAt;

    @Column
    private Integer likeValue;
}
