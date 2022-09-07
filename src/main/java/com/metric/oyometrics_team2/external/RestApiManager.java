package com.metric.oyometrics_team2.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestApiManager {
    @Autowired
    private RestTemplate restTemplate;

    public <T> T get(String baseUrl, String url, String query, HttpHeaders requestHeaders, Class<T> responseClassType) throws RuntimeException{
        //ResponseEntity<T> responseEntity = null;
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestHeaders);
        String fullUrl = getFullUrl(baseUrl, url, query);
        return restTemplate.exchange(fullUrl, HttpMethod.GET, requestEntity, responseClassType).getBody();
    }

    private String getFullUrl(String baseUrl, String url, String query) {
        StringBuilder fullUrl = new StringBuilder();
        fullUrl.append(baseUrl);
        if (url != null) {
            fullUrl.append(url);
        }
        if (query != null && query.startsWith("?")) {
            query = query.substring(1);
        }
        //query = TransformUtil.trimToNull(query);
        if (query != null) {
            fullUrl.append("?");
            fullUrl.append(query);
        }
        return fullUrl.toString();
    }
}
