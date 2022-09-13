package com.metric.oyometrics_team2.services;

import com.metric.oyometrics_team2.DTO.PullRequest;
import com.metric.oyometrics_team2.DTO.PullRequestResponse;
import com.metric.oyometrics_team2.external.GithubApiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PullRequestService {

    @Autowired
    private GithubApiManager githubApiManager;

    @Autowired
    private PullRequestResponse pullRequestResponse;

    public PullRequestResponse getPullRequestData(String repoOwner, String repoName){
        List <PullRequest> pullRequests = githubApiManager.getPullRequestData(repoOwner, repoName);
        AtomicReference<Long> totalTime = new AtomicReference<>(Long.valueOf(0));

        AtomicReference<LocalDateTime> lastMergedAT = new AtomicReference<>(LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC));
        AtomicReference<LocalDateTime> firstMergedAT = new AtomicReference<>(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));

        AtomicReference<Long> mergedAtNotNULL = new AtomicReference<>(Long.valueOf(0));

        pullRequests.forEach(pulls -> {
            Duration diff = Duration.between(pulls.getCreatedAt(), pulls.getClosedAt());
            totalTime.updateAndGet(v -> v + diff.getSeconds());
            if(pulls.getMergedAt() != null) {
                mergedAtNotNULL.updateAndGet(v -> v + 1);
                if (pulls.getMergedAt().isAfter(lastMergedAT.get())) {
                    lastMergedAT.set(pulls.getMergedAt());
                }
                if (pulls.getMergedAt().isBefore(firstMergedAT.get())) {
                    firstMergedAT.set(pulls.getMergedAt());
                }
            }
        });
        //System.out.println("WHAT hapened");
        Long averageTimeSpent = totalTime.get()/ pullRequests.size();
        Duration totalMergeDays = Duration.between(firstMergedAT.get(), lastMergedAT.get());
//        System.out.println(totalMergeDays);
//        System.out.println("Working fine");

        Double totalValidDays = (double)totalMergeDays.toDays();
        Double PrMergeFrequency = mergedAtNotNULL.get()/totalValidDays;

        HashMap<String, String> sample = new HashMap<String, String>();

        PullRequestResponse finalAns = new PullRequestResponse();

        finalAns.setAverageTimeSpent(averageTimeSpent);
        finalAns.setPrMergeFrequency(PrMergeFrequency);

        return finalAns;
    }
}


