package com.webster.service;

import com.webster.entity.UserTags;
import org.codehaus.jettison.json.JSONException;

import java.net.URISyntaxException;

/**
 * Created by amit on 23/7/16.
 */
public interface WebsterApi {

    void saveTabEvent(String tab) throws Exception;
    void saveUserWebPageAction(String clientData) throws Exception;
    void addNewTagsToUserGroup(String tag,String userId);
    void updateTagsToUserGroup(UserTags userTags);
    void saveWebPageUserLikes(String url, String absoluteUrl, Integer value, String userId);

    String getWebPageFrequencyChart(String url);
    String getAbsoluteWebPageFrequencyChart(String url);

    String getWebpageData(String url);
    String getAbsoluteWebpageData(String url);

    String getUserTags(String userId,Integer limit);
    String getAllUserTags(String userId);

    String getUserwebpageActionResult(String clientData) throws Exception;
}
