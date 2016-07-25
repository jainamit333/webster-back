package com.webster.Util;

import org.springframework.stereotype.Component;

/**
 * Created by amit on 23/7/16.
 */
@Component
public class CouchIdUtil {


    public String userWebpageId(String userId,String url){
        return userId+"_"+url;
    }
}
