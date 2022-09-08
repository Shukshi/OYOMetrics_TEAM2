package com.metric.oyometrics_team2.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;

@Component
public class RestApiManager {
    @Autowired
    private RestTemplate restTemplate;

    public <T> T get(String baseUrl, String url, String query, HttpHeaders requestHeaders, Type responseClassType) throws RuntimeException{
        //ResponseEntity<T> responseEntity = null;
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestHeaders);
        String fullUrl = getFullUrl(baseUrl, url, query);
        return restTemplate.exchange(fullUrl, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<T>() {
            @Override
            public Type getType() {
                return responseClassType;
            }
        }
        ).getBody();
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
