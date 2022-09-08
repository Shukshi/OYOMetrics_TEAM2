package com.metric.oyometrics_team2.services;

import com.metric.oyometrics_team2.DTO.PullRequest;
import com.metric.oyometrics_team2.external.GithubApiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PullRequestService {

    @Autowired
    private GithubApiManager githubApiManager;
    public AtomicInteger getAverageTimeSpent(String repoOwner, String repoName){

        System.out.println("WORKS TILL LINE 21 SERVICE");

        List <PullRequest> pullsList = githubApiManager.getPullRequestData(repoOwner, repoName);
        int total = 0;
        //List<PullRequest> pullRequests = (List<PullRequest>) ((List)super.get(getContactUrl + "?property_id=" + propertyId,null, List.class)).stream().map(item -> new ObjectMapper().convertValue(item, ContactsDto.class)).collect(Collectors.toList());
        System.out.println("WORKS TILL LINE 25 SERVICE");

        AtomicInteger averageTime = new AtomicInteger();

        //pullsList.stream().forEach(pulls -> System.out.println(pulls));

        System.out.println("WORKS TILL LINE 30 SERVICE");
        System.out.println(averageTime);
        return averageTime;
    }
}





