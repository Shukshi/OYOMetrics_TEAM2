package com.metric.oyometrics_team2.Controller;

import com.metric.oyometrics_team2.DTO.*;
import com.metric.oyometrics_team2.services.PullRequestDetailsService;
import com.metric.oyometrics_team2.services.PullRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@CrossOrigin
@RestController
@RequestMapping(value = "/pr", produces = MediaType.APPLICATION_JSON_VALUE)

public class PullRequestController {

    @Autowired
    private PullRequestService pullRequestService;

    @Autowired
    private PullRequestResponse pullRequestResponse;

    @Autowired
    private PullRequestDetailsService pullRequestDetailsService;

    @CrossOrigin
    @RequestMapping(value = "/average-time-spent", method = RequestMethod.GET)
    public PullRequestResponse getAverageTimeSpent(
            @RequestParam(value = "repo_owner", required = true) String repoOwner,
            @RequestParam(value = "repo_name", required = true) String repoName
    ) {
        return pullRequestService.getAverageTimeSpent(repoOwner, repoName);
    }

    @CrossOrigin
    @RequestMapping(value = "/first-review-mean-time", method = RequestMethod.GET)
    public FirstEventResponse getFirstReviewMeanTime(
            @RequestParam(value = "repo_owner", required = true) String repoOwner,
            @RequestParam(value = "repo_name", required = true) String repoName
    ) throws InterruptedException {

        FirstEventResponse firstEventResponse= pullRequestService.getFirstReviewMeanTime(repoOwner, repoName);
        //Thread.sleep(10000);
        return firstEventResponse;
    }

    @CrossOrigin
    @RequestMapping(value = "/first-comment-mean-time", method = RequestMethod.GET)
    public FirstCommentResponse getFirstCommentMeanTime(
            @RequestParam(value = "repo_owner", required = true) String repoOwner,
            @RequestParam(value = "repo_name", required = true) String repoName
    ) throws InterruptedException {

        FirstCommentResponse firstCommentResponse= pullRequestService.getFirstCommentMeanTime(repoOwner, repoName);
        //Thread.sleep(10000);
        return firstCommentResponse;
    }

    @CrossOrigin
    @RequestMapping(value = "/last-100-pull-request-details", method = RequestMethod.GET)
    public PRDetailsAPIResponse getPullRequestDetails(
            @RequestParam(value = "repo_owner", required = true) String repoOwner,
            @RequestParam(value = "repo_name", required = true) String repoName
    ) throws InterruptedException {

        PRDetailsAPIResponse pullRequestDetails= pullRequestDetailsService.getPullRequestDetails(repoOwner, repoName);

        //Thread.sleep(10000);
        return pullRequestDetails;
    }


}