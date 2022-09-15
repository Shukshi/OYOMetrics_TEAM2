package com.metric.oyometrics_team2.external;

import com.fasterxml.jackson.core.type.TypeReference;
import com.metric.oyometrics_team2.DTO.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

        String query = "per_page=30&state=closed";
        return super.get(githubUrl, url, query, getRequestHeaders(), new TypeReference<List<PullRequest>>(){}.getType());
    }

    public
    List<FirstReviewEvent> getFirstReviewEventCreatedAt(String rawUrl, String repoOwner, String repoName) {

        int j=rawUrl.length()-1;
        String pullNo="";
        while(j>=0){
            if(rawUrl.charAt(j)=='/'){
                break;
            }
            pullNo= rawUrl.charAt(j)+pullNo;
            j--;
        }

        String url = new StringBuilder(REPO_PULL)
                .append("/")
                .append(repoOwner)
                .append("/")
                .append(repoName)
                .append("/pulls")
                .append("/")
                .append(pullNo)
                .append("/reviews")
                .toString();

        String query = "";
        return super.get(githubUrl, url, query, getRequestHeaders(), new TypeReference<List<FirstReviewEvent>>(){}.getType());
    }


    public
    List<FirstCommentEvent> getFirstCommentEventCreatedAt(String rawUrl, String repoOwner, String repoName) {


        int j=rawUrl.length()-1;
        String pullNo="";
        while(j>=0){
            if(rawUrl.charAt(j)=='/'){
                break;
            }
            pullNo= rawUrl.charAt(j)+pullNo;
            j--;
        }

        String url = new StringBuilder(REPO_PULL)
                .append("/")
                .append(repoOwner)
                .append("/")
                .append(repoName)
                .append("/pulls")
                .append("/")
                .append(pullNo)
                .append("/comments")
                .toString();

        String query = "";
        return super.get(githubUrl, url, query, getRequestHeaders(), new TypeReference<List<FirstCommentEvent>>(){}.getType());

    }



    public
    PullRequestDetailsResponse getPullRequestDetails(String rawUrl, String repoOwner, String repoName) {

        int j=rawUrl.length()-1;
        String pullNo="";
        while(j>=0){
            if(rawUrl.charAt(j)=='/'){
                break;
            }
            pullNo= rawUrl.charAt(j)+pullNo;
            j--;
        }

        String url = new StringBuilder(REPO_PULL)
                .append("/")
                .append(repoOwner)
                .append("/")
                .append(repoName)
                .append("/pulls")
                .append("/")
                .append(pullNo)
                .toString();

        String query = "";
        return super.get(githubUrl, url, query, getRequestHeaders(), new TypeReference<PullRequestDetailsResponse>(){}.getType());
    }



    public static HttpHeaders getRequestHeaders() {

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");

        requestHeaders.add("Authorization", "Bearer ");
        return requestHeaders;

    }


}