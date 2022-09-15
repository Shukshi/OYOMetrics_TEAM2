package com.metric.oyometrics_team2.services;

import com.metric.oyometrics_team2.DTO.*;
import com.metric.oyometrics_team2.external.GithubApiManager;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;


@Service
@Getter
@Setter
public class PullRequestDetailsService {

    @Autowired
    private GithubApiManager githubApiManager;

    @Autowired
    private PullRequestResponse pullRequestResponse;

    public double totalComments;
    public double totalReviewComments;
    public double totalCommits;
    public double totalAdditions;
    public double totalDeletions;
    public double totalChangedFiles;
    public double count;

    public PRDetailsAPIResponse getPullRequestDetails(String repoOwner, String repoName ) throws InterruptedException {

        List <PullRequest> pullRequests = githubApiManager.getPullRequestData(repoOwner, repoName);

        PRDetailsAPIResponse prDetails = new PRDetailsAPIResponse();

        //Long zero = new Long(0);
        double zero = 0;
        setTotalComments(zero);
        setTotalReviewComments(zero);
        setTotalCommits(zero);
        setTotalAdditions(zero);
        setTotalDeletions(zero);
        setTotalChangedFiles(zero);
        setCount(zero);

        for (int i=0;i<pullRequests.size();i++){
            PullRequest pr = pullRequests.get(i);
            String url = pr.getHtmlUrl();




            CompletableFuture.supplyAsync(() -> {
                try {
                    System.out.println(url);
                    PullRequestDetailsResponse Details = githubApiManager.getPullRequestDetails(url,repoOwner,repoName);
                    System.out.println(Details);
                    setTotalComments(getTotalComments()+Details.getNoOfCommentsPerPR());
                    setTotalReviewComments(getTotalReviewComments()+Details.getNoOfReviewCommentsPerPR());
                    setTotalCommits(getTotalCommits()+Details.getNoOfCommitsPerPR());
                    setTotalAdditions(getTotalAdditions()+Details.getAdditionOfLinesPerPR());
                    setTotalDeletions(getTotalDeletions()+Details.getDeletionsOfLinesPerPR());
                    setTotalChangedFiles(getTotalChangedFiles()+Details.getNoOfChangedFilesPerPR());
                    setCount(getCount()+1);

                    System.out.println("Additions ******** "+getTotalAdditions());
                    System.out.println("Counter *********** "+getCount());

                      return 0;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        Thread.sleep(10000);
        prDetails.setNoOfCommentsPerPR(getTotalComments()/getCount());
        prDetails.setNoOfReviewCommentsPerPR(getTotalReviewComments()/getCount());
        prDetails.setNoOfCommitsPerPR(getTotalCommits()/getCount());
        prDetails.setAdditionOfLinesPerPR(getTotalAdditions()/getCount());
        prDetails.setDeletionsOfLinesPerPR(getTotalDeletions()/getCount());
        prDetails.setNoOfChangedFilesPerPR(getTotalChangedFiles()/getCount());
        prDetails.setNoOfPullRequestsFetched(getCount());


        return prDetails;
    }
}



//@Service
//@Getter
//@Setter
//public class PullRequestDetailsService {
//
//    @Autowired
//    private GithubApiManager githubApiManager;
//
//    @Autowired
//    private PullRequestResponse pullRequestResponse;
//
//    public double totalComments;
//    public double totalReviewComments;
//    public double totalCommits;
//    public double totalAdditions;
//    public double totalDeletions;
//    public double totalChangedFiles;
//    public double count;
//
//    public PRDetailsAPIResponse getPullRequestDetails(String repoOwner, String repoName ){
//
//        List <PullRequest> pullRequests = githubApiManager.getPullRequestData(repoOwner, repoName);
//
//        PRDetailsAPIResponse prDetails = new PRDetailsAPIResponse();
//
//        //Long zero = new Long(0);
//        double zero = 0;
//        setTotalComments(zero);
//        setTotalReviewComments(zero);
//        setTotalCommits(zero);
//        setTotalAdditions(zero);
//        setTotalDeletions(zero);
//        setTotalChangedFiles(zero);
//        setCount(zero);
//
//        for (int i=0;i<pullRequests.size();i++){
//            PullRequest pr = pullRequests.get(i);
//            String url = pr.getHtmlUrl();
//
//            PullRequestDetailsResponse Details = githubApiManager.getPullRequestDetails(url,repoOwner,repoName);
//            System.out.println(Details);
//            setTotalComments(getTotalComments()+Details.getNoOfCommentsPerPR());
//            setTotalReviewComments(getTotalReviewComments()+Details.getNoOfReviewCommentsPerPR());
//            setTotalCommits(getTotalCommits()+Details.getNoOfCommitsPerPR());
//            setTotalAdditions(getTotalAdditions()+Details.getAdditionOfLinesPerPR());
//            setTotalDeletions(getTotalDeletions()+Details.getDeletionsOfLinesPerPR());
//            setTotalChangedFiles(getTotalChangedFiles()+Details.getNoOfChangedFilesPerPR());
//            setCount(getCount()+1);
//
//
////            CompletableFuture.supplyAsync(() -> {
////                try {
////                    System.out.println(url);
////
////                      //Thread.sleep(2000);
////                      return 0;
////                } catch (Exception e) {
////                    throw new RuntimeException(e);
////                }
////            });
//        }
//        prDetails.setNoOfCommentsPerPR(getTotalComments()/getCount());
//        prDetails.setNoOfReviewCommentsPerPR(getTotalReviewComments()/getCount());
//        prDetails.setNoOfCommitsPerPR(getTotalCommits()/getCount());
//        prDetails.setAdditionOfLinesPerPR(getTotalAdditions()/getCount());
//        prDetails.setDeletionsOfLinesPerPR(getTotalDeletions()/getCount());
//        prDetails.setNoOfChangedFilesPerPR(getTotalChangedFiles()/getCount());
//
//        return prDetails;
//    }
//}
