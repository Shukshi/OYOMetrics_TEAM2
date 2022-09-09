package com.metric.oyometrics_team2.services;

import com.metric.oyometrics_team2.DTO.PullRequest;
import com.metric.oyometrics_team2.external.GithubApiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PullRequestService {

    @Autowired
    private GithubApiManager githubApiManager;
    public Long getAverageTimeSpent(String repoOwner, String repoName){

        List<PullRequest> pullRequests = githubApiManager.getPullRequestData(repoOwner, repoName);
        AtomicReference<Long> totalTime = new AtomicReference<>(Long.valueOf(0));


        pullRequests.forEach(pulls -> {
            Duration diff = Duration.between(pulls.getCreatedAt(), pulls.getClosedAt());
            totalTime.updateAndGet(v -> v + diff.getSeconds());
        });


        Long i = totalTime.get()/ pullRequests.size();

        System.out.println(i);

        return i;
    }
}





