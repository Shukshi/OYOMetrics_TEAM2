package com.metric.oyometrics_team2.services;

import com.metric.oyometrics_team2.DTO.*;
import com.metric.oyometrics_team2.external.GithubApiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PullRequestService {

    @Autowired
    private GithubApiManager githubApiManager;

    @Autowired
    private PullRequestResponse pullRequestResponse;

    public Long FRMT_total_time;
    public int FRMT_count;

    public Long getFCMT_total_time() {
        return FCMT_total_time;
    }

    public void setFCMT_total_time(Long FCMT_total_time) {
        this.FCMT_total_time = FCMT_total_time;
    }

    public int getFCMT_count() {
        return FCMT_count;
    }

    public void setFCMT_count(int FCMT_count) {
        this.FCMT_count = FCMT_count;
    }

    public Long FCMT_total_time;

    public int FCMT_count;

    public Long getFRMT_total_time() {
        return FRMT_total_time;
    }

    public void setFRMT_total_time(Long FRMT_total_time) {
        this.FRMT_total_time = FRMT_total_time;
    }

    public int getFRMT_count() {
        return FRMT_count;
    }

    public void setFRMT_count(int FRMT_count) {
        this.FRMT_count = FRMT_count;
    }

    public LocalDateTime a = LocalDateTime.of(9999, 9, 9, 9, 9);
    public LocalDateTime getPullReviewFirstEvent(String url, String repoOwner, String repoName,LocalDateTime PR_createdAt){

        System.out.println(url);
        List<FirstReviewEvent> listOfCreatedAt = githubApiManager.getFirstReviewEventCreatedAt(url,repoOwner,repoName);
        System.out.println(listOfCreatedAt.size());
        if(listOfCreatedAt.size()==0){
            LocalDateTime dummy = a;
            return dummy;
        }
        LocalDateTime created_at = listOfCreatedAt.get(0).getSubmittedAt() ;
        System.out.println(created_at);
        if(created_at!=a){
            setFRMT_total_time(getFRMT_total_time()+Duration.between(PR_createdAt,created_at).getSeconds());
            setFRMT_count(getFRMT_count()+1);
        }
        return created_at;

    }

    public LocalDateTime getPullReviewFirstComment(String url, String repoOwner, String repoName,LocalDateTime PR_createdAt){

        System.out.println(url);
        List<FirstCommentEvent> listOfCreatedAt = githubApiManager.getFirstCommentEventCreatedAt(url,repoOwner,repoName);

        if(listOfCreatedAt.size()==0){
            LocalDateTime dummy = a;
            return dummy;
        }
        LocalDateTime created_at = listOfCreatedAt.get(0).getCreatedAt() ;
        System.out.println(created_at);
        if(created_at != a){
            setFCMT_total_time(getFCMT_total_time()+Duration.between(PR_createdAt,created_at).getSeconds());
            setFRMT_count(getFCMT_count()+1);
        }
        return created_at;

    }


    public FirstEventResponse getFirstReviewMeanTime( String repoOwner, String repoName ) throws InterruptedException {

        List <PullRequest> pullRequests = githubApiManager.getPullRequestData(repoOwner, repoName);


        setFRMT_total_time(new Long(0));
        setFRMT_count(1);


        for (int i=0;i<pullRequests.size();i++){
            PullRequest pr = pullRequests.get(i);
            String url = pr.getHtmlUrl();
            LocalDateTime PR_createdAt = pr.getCreatedAt();
            CompletableFuture.supplyAsync(() -> {
                try {
                    LocalDateTime createdAt = getPullReviewFirstEvent(url,repoOwner,repoName,PR_createdAt);
                    //Thread.sleep(2000);
                    return createdAt;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

        }
        Thread.sleep(20000);
        System.out.println(getFRMT_total_time());
        System.out.println(getFRMT_count());

        FirstEventResponse firstEventResponse = new FirstEventResponse();
        firstEventResponse.setFirstReviewMeanTime(getFRMT_total_time()/getFRMT_count());

        return firstEventResponse;
    }

    public FirstCommentResponse getFirstCommentMeanTime(String repoOwner, String repoName ) throws InterruptedException {

        List <PullRequest> pullRequests = githubApiManager.getPullRequestData(repoOwner, repoName);

        setFCMT_total_time(new Long(0));
        setFCMT_count(1);

        for (int i=0;i<pullRequests.size();i++){
            PullRequest pr = pullRequests.get(i);
            String url = pr.getHtmlUrl();
            LocalDateTime PR_createdAt = pr.getCreatedAt();
            CompletableFuture.supplyAsync(() -> {
                try {
                    LocalDateTime createdAt = getPullReviewFirstComment(url,repoOwner,repoName,PR_createdAt);

                    return createdAt;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

        }
        Thread.sleep(20000);
        System.out.println(getFCMT_total_time());
        System.out.println(getFCMT_count());

        FirstCommentResponse firstCommentResponse = new FirstCommentResponse();
        firstCommentResponse.setFirstCommentMeanTime(getFCMT_total_time()/getFCMT_count());

        return firstCommentResponse;
    }


    public PullRequestResponse getAverageTimeSpent(String repoOwner, String repoName){
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
        //System.out.println("WHAT happened");
        Long averageTimeSpent = totalTime.get()/ pullRequests.size();
        Duration totalMergeDays = Duration.between(firstMergedAT.get(), lastMergedAT.get());
//        System.out.println(totalMergeDays);
//        System.out.println("Working fine");

        Double totalValidDays = (double)totalMergeDays.toDays();
        Double PrMergeFrequency = mergedAtNotNULL.get()/totalValidDays;
        PullRequestResponse finalAns = new PullRequestResponse();

        finalAns.setAverageTimeSpent(averageTimeSpent);
        finalAns.setPRMergeFrequency(PrMergeFrequency);
        return finalAns;
    }
}





