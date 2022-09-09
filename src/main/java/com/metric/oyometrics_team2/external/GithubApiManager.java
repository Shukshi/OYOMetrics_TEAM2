package com.metric.oyometrics_team2.external;

import com.fasterxml.jackson.core.type.TypeReference;
import com.metric.oyometrics_team2.DTO.PullRequest;
import com.metric.oyometrics_team2.DTO.UserLevel;
import com.metric.oyometrics_team2.DTO.WeekData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GithubApiManager extends RestApiManager{

    @Value("${external.github.url}")
    private String githubUrl;

//    @Value("${external.github.accessToken}")
//    private static String accessToken;
    private static final String REPO_PULL = "/repos";

    public
    List<UserLevel> getUserData(String repoOwner, String repoName){
        String url = new StringBuilder(REPO_PULL)
                .append("/")
                .append(repoOwner)
                .append("/")
                .append(repoName)
                .append("/stats/contributors")
                .toString();

        String query = "";
        return super.get(githubUrl, url, query, getRequestHeaders(), new TypeReference<List<UserLevel>>(){}.getType());

    }
    public
    List<PullRequest> getPullRequestData(String repoOwner, String repoName) {
        String url = new StringBuilder(REPO_PULL)
                .append("/")
                .append(repoOwner)
                .append("/")
                .append(repoName)
                .append("/pulls")
                .toString();

        String query = "state=closed";
        return super.get(githubUrl, url, query, getRequestHeaders(), new TypeReference<List<PullRequest>>(){}.getType());
    }

    public static HttpHeaders getRequestHeaders() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");

        requestHeaders.add("Authorization", "");
        return requestHeaders;
    }


}
