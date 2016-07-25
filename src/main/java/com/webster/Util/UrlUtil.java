package com.webster.Util;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by amit on 23/7/16.
 */
@Component
public class UrlUtil {

    public String getHostUtl(String completeUrl) throws URISyntaxException {
        URI uri = new URI(completeUrl);
        String domain = uri.getHost();
        return domain;
    }
}
