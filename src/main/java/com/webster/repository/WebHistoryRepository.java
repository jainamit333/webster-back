package com.webster.repository;

import com.webster.entity.WebHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amit on 23/7/16.
 */

public interface WebHistoryRepository extends CrudRepository<WebHistory,Integer> {
}
