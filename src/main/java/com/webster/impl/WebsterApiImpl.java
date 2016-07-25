package com.webster.impl;

import com.webster.Util.CouchIdUtil;
import com.webster.Util.JsonValueUsingPath;
import com.webster.Util.UrlUtil;
import com.webster.entity.UserTags;
import com.webster.entity.UserWebpage;
import com.webster.entity.WebHistory;
import com.webster.entity.WebPageUserAction;
import com.webster.repository.UserTagsRepository;
import com.webster.repository.UserWebPageRepository;
import com.webster.repository.WebHistoryRepository;
import com.webster.repository.WebPageUserActionRepository;
import com.webster.service.WebsterApi;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by amit on 23/7/16.
 */

@Component
public class WebsterApiImpl implements WebsterApi {

//    @Autowired
//    private UserTagsRepository userTagsRepository;

    @Autowired
    private UserWebPageRepository userWebPageRepository;

    @Autowired
    private WebHistoryRepository webHistoryRepository;

    @Autowired
    private WebPageUserActionRepository webPageUserActionRepository;

    @Autowired
    private JsonValueUsingPath jsonValueUsingPath;

    @Autowired
    private UrlUtil urlUtil;

    @Autowired
    private CouchIdUtil couchIdUtil;

    @Override
    public void saveTabEvent(String tab) throws Exception {

        String tabCompleteUrl = (String) jsonValueUsingPath.getGenericValue(tab,"tab_info.url");
        String domainName = urlUtil.getHostUtl(tabCompleteUrl);
        String userId = (String) jsonValueUsingPath.getGenericValue(tab,"id");
        WebHistory webHistory = new WebHistory();
        webHistory.setCreatedAt(new Date());
        webHistory.setCompleteUrl(tabCompleteUrl);
        webHistory.setMainUrl(domainName);
        webHistory.setUserId(userId);
        webHistoryRepository.save(webHistory);

    }

    @Override
    public void saveUserWebPageAction(String clientData) throws Exception {

        UserWebpage<String> userWebpage = new UserWebpage<>();
        String userId = (String) jsonValueUsingPath.getGenericValue(clientData,"id");
        String completeUrl = (String) jsonValueUsingPath.getGenericValue(clientData,"tag_info.url");
        String domainName = urlUtil.getHostUtl(completeUrl);
        Map<String,String> metaData = new HashMap<>();
        metaData.put("email",(String) jsonValueUsingPath.getGenericValue(clientData,"email"));
        userWebpage.setId(couchIdUtil.userWebpageId(userId,completeUrl));
        userWebpage.setDocument(clientData);
        userWebpage.setCreatedAt(new Date());
        userWebpage.setMetaData(metaData);
        userWebPageRepository.save(userWebpage);
        Integer like = (Integer) jsonValueUsingPath.getGenericValue(clientData,"like");
        saveWebPageUserLikes(completeUrl,domainName, like, userId);
        JSONArray tags = ((JSONArray)jsonValueUsingPath.getGenericValue(clientData,"tags"));
        for(int i =0;i<tags.length();i++){
            addNewTagsToUserGroup((String) tags.get(i),userId);
        }

    }

    @Override
    @Async
    public void addNewTagsToUserGroup(String tag, String userId) {
//        List<UserTags> userTags = userTagsRepository.findByTagsAndUserId(tag,userId);
//        if(userTags == null || userTags.size()==0){
//            UserTags userTag = new UserTags();
//            userTag.setUserId(userId);
//            userTag.setTags(tag);
//            userTag.setCreatedAt(new Date());
//            userTag.setUpdatedAt(new Date());
//            userTagsRepository.save(userTag);
//        }else{
//            updateTagsToUserGroup(userTags.get(0));
//        }
    }

    @Override
    public void updateTagsToUserGroup(UserTags userTags) {
        userTags.setUpdatedAt(new Date());
        //userTagsRepository.save(userTags);
    }

    @Override
    @Async
    public void saveWebPageUserLikes(String url, String absoluteUrl, Integer value, String userId) {

        List<WebPageUserAction> webPageUserActions = webPageUserActionRepository.findByCompleteUrlAndUserId(url,userId);
        if(webPageUserActions==null || webPageUserActions.size()==0){
            WebPageUserAction webPageUserAction = new WebPageUserAction();
            webPageUserAction.setUserId(userId);
            webPageUserAction.setCompleteUrl(url);
            webPageUserAction.setMainUrl(absoluteUrl);
            webPageUserAction.setCreatedAt(new Date());
            webPageUserAction.setUpdatedAt(new Date());
            webPageUserAction.setLikeValue(value);
            webPageUserActionRepository.save(webPageUserAction);
        }else{
            webPageUserActions.get(0).setUpdatedAt(new Date());
            webPageUserActionRepository.save(webPageUserActions.get(0));
        }
    }

    @Override
    public String getWebPageFrequencyChart(String url) {
        return "{'result':'user construction , wait till second version'}";
    }

    @Override
    public String getAbsoluteWebPageFrequencyChart(String url) {
        return "{'result':'user construction , wait till second version'}";
    }

    @Override
    public String getWebpageData(String url) {
        return webPageUserActionRepository.countByCompleteUrlAndLikeValue(url,1).toString();
    }

    @Override
    public String getAbsoluteWebpageData(String url) {
        return webPageUserActionRepository.countByMainUrlAndLikeValue(url,1).toString();
    }

    @Override
    public String getUserTags(String userId, Integer limit) {
        Pageable pageable = new PageRequest(limit - 1, 20, Sort.Direction.DESC, "updatedAt");
        return "Currenlty Not Implemented";//userTagsRepository.findByUserId(pageable,userId).toString();
    }

    @Override
    public String getAllUserTags(String userId) {
        return "";//userTagsRepository.findByUserId(userId).toString();
    }

    @Override
    public String getUserwebpageActionResult(String clientData) throws Exception {

        String userId = (String) jsonValueUsingPath.getGenericValue(clientData,"id");
        String completeUrl = (String) jsonValueUsingPath.getGenericValue(clientData,"current_tab_url");
        return userWebPageRepository.findOne(couchIdUtil.userWebpageId(userId,completeUrl)).toString();
    }
}
