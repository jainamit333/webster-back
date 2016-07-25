package com.webster.repository;

import com.webster.entity.UserTags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by amit on 23/7/16.
 */
@Repository
public interface UserTagsRepository extends PagingAndSortingRepository<UserTags,Integer> {


    List<UserTags> findByTagsAndUserId(String tag, String userId);

    Page<UserTags> findByUserId(Pageable pageable, String name);

    List<UserTags> findByUserId(String name);
}
