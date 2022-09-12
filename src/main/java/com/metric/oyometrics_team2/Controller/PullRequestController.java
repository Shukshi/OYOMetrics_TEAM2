package com.metric.oyometrics_team2.Controller;

import com.metric.oyometrics_team2.DTO.PullRequestResponse;
import com.metric.oyometrics_team2.services.PullRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(value = "/pr", produces = MediaType.APPLICATION_JSON_VALUE)

public class PullRequestController {

    @Autowired
    private PullRequestService pullRequestService;

    @Autowired
    private PullRequestResponse pullRequestResponse;
    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public PullRequestResponse getAverageTimeSpent(
            @RequestParam(value = "repo_owner", required = true) String repoOwner,
            @RequestParam(value = "repo_name", required = true) String repoName
    ) {
        return pullRequestService.getAverageTimeSpent(repoOwner, repoName);
    }

}
