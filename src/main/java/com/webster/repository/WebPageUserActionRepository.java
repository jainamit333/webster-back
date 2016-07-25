package com.webster.repository;

import com.webster.entity.WebPageUserAction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by amit on 23/7/16.
 */
@Repository
public interface WebPageUserActionRepository  extends CrudRepository<WebPageUserAction,Integer> {
    List<WebPageUserAction> findByCompleteUrlAndUserId(String url, String userId);

    Long countByCompleteUrlAndLike(String url, int i);

    Long countByMainUrlAndLike(String url, int i);
}
