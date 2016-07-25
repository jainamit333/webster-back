package com.webster.controller;

import com.webster.service.WebsterApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by amit on 25/7/16.
 */
@RestController
@RequestMapping("/api")
public class ApiController {


    @Autowired
    private WebsterApi websterApi;

    @RequestMapping(value = "tab/event",method = RequestMethod.POST)
    public void tabEvent(String eventData) throws Exception {
        websterApi.saveTabEvent(eventData);

    }

    @RequestMapping(value = "tab/userPageInfo")
    public void saveUserWebPageAction(String clientData) throws Exception{
        websterApi.saveUserWebPageAction(clientData);
    }

    @RequestMapping(value = "tab/freq")
    public String getWebPageFrequencyChart(String url){
        return websterApi.getWebPageFrequencyChart(url);
    }

    @RequestMapping(value = "tab/domain/freq")
    public String getAbsoluteWebPageFrequencyChart(String url){
        return websterApi.getAbsoluteWebPageFrequencyChart(url);
    }


    @RequestMapping(value = "tab/data")
    public String getWebpageData(String url){
        return websterApi.getWebpageData(url);

    }

    @RequestMapping(value = "tab/domain/data")
    public String getAbsoluteWebpageData(String url){
        return websterApi.getAbsoluteWebpageData(url);

    }
    //getUserwebpageActionResult

    @RequestMapping(value = "user/tag/limit")
    public String getUserTags(String userId,Integer pageNumber){
        return websterApi.getUserTags(userId,pageNumber);

    }

    @RequestMapping(value = "user/tag")
    public String getAllUserTags(String userId){
        return websterApi.getAllUserTags(userId);
    }

    @RequestMapping(value = "tab/user/action")
    public String getUserwebpageActionResult(String clientData) throws Exception{
        return websterApi.getUserwebpageActionResult(clientData);
    }


}
