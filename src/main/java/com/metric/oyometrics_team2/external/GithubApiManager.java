package com.metric.oyometrics_team2.external;

import com.fasterxml.jackson.core.type.TypeReference;
import com.metric.oyometrics_team2.DTO.*;
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
        System.out.println(url);
        return super.get(githubUrl, url, query, getRequestHeaders(), new TypeReference<List<UserLevel>>(){}.getType());

    }

    public
    WeeklyCommits getWeeklyCommits(String repoOwner, String repoName){
        String url = new StringBuilder(REPO_PULL)
                .append("/")
                .append(repoOwner)
                .append("/")
                .append(repoName)
                .append("/stats/participation")
                .toString();

        String query = "";
        return super.get(githubUrl, url, query, getRequestHeaders(), new TypeReference<WeeklyCommits>(){}.getType());

    }

    public
    List<List<Integer>> getRepoLevelAddDelData(String repoOwner, String repoName){
        String url = new StringBuilder(REPO_PULL)
                .append("/")
                .append(repoOwner)
                .append("/")
                .append(repoName)
                .append("/stats/code_frequency")
                .toString();

        String query = "";
        return super.get(githubUrl, url, query, getRequestHeaders(), new TypeReference<List<List<Integer>>>(){}.getType());

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

        requestHeaders.add("Authorization", "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");
        return requestHeaders;

    }


}
