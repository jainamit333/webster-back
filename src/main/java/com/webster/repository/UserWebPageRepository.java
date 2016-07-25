package com.webster.repository;

import com.webster.entity.UserWebpage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by amit on 23/7/16.
 */
@Repository
public interface UserWebPageRepository extends CrudRepository<UserWebpage,String> {

}
