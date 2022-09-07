package com.metric.oyometrics_team2.services;

import com.metric.oyometrics_team2.DTO.PullRequest;
import com.metric.oyometrics_team2.DTO.PullRequestListResponse;
import com.metric.oyometrics_team2.external.GithubApiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PullRequestService {

    @Autowired
    private GithubApiManager githubApiManager;
    public Object getAverageTimeSpent(String repoOwner, String repoName){
        //build logic
        //return type Integer
        return githubApiManager.getPullRequestData(repoOwner, repoName);
    }
}



