package com.webster.repository;

import com.webster.entity.WebPageUserAction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by amit on 23/7/16.
 */
public interface WebPageUserActionRepository  extends CrudRepository<WebPageUserAction,Long> {
    List<WebPageUserAction> findByCompleteUrlAndUserId(String url, String userId);

    Long countByCompleteUrlAndLikeValue(String url, int i);

    Long countByMainUrlAndLikeValue(String url, int i);
}
