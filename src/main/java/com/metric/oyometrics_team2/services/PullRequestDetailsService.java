package com.metric.oyometrics_team2.services;

import com.metric.oyometrics_team2.DTO.FirstEventResponse;
import com.metric.oyometrics_team2.DTO.PullRequest;
import com.metric.oyometrics_team2.DTO.PullRequestDetailsResponse;
import com.metric.oyometrics_team2.DTO.PullRequestResponse;
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
//    //public int totalComments;
//    public int totalReviewComments;
//    public int totalCommits;
//    public int totalAdditions;
//    public int totalDeletions;
//    public int totalChangedFiles;
//    public int count;
//
//    public PullRequestDetailsResponse getPullRequestDetails(String repoOwner, String repoName ){
//
//        List <PullRequest> pullRequests = githubApiManager.getPullRequestData(repoOwner, repoName);
//        PullRequestDetailsResponse prDetails = new PullRequestDetailsResponse();
//        AtomicReference<Integer> totalComments = new AtomicReference<>(0);
//
//        setTotalReviewComments(0);
//        setTotalCommits(0);
//        setTotalAdditions(0);
//        setTotalDeletions(0);
//        setTotalChangedFiles(0);
//        setCount(1);
//
//        for (int i=0;i<pullRequests.size();i++){
//            PullRequest pr = pullRequests.get(i);
//            String url = pr.getHtmlUrl();
//
//            CompletableFuture.supplyAsync(() -> {
//                try {
//                    System.out.println(url);
//                    PullRequestDetailsResponse Details = githubApiManager.getPullRequestDetails(url,repoOwner,repoName);
//                    System.out.println(url);
//                    totalComments.updateAndGet(c ->c + Details.getNoOfCommentsPerPR());
//                    //setTotalComments(getTotalComments()+Details.getNoOfCommentsPerPR());
//                    setTotalReviewComments(getTotalReviewComments()+Details.getNoOfReviewCommentsPerPR());
//                    setTotalCommits(getTotalCommits()+Details.getNoOfCommitsPerPR());
//                    setTotalAdditions(getTotalAdditions()+Details.getAdditionOfLinesPerPR());
//                    setTotalDeletions(getTotalDeletions()+Details.getDeletionsOfLinesPerPR());
//                    setTotalChangedFiles(getTotalChangedFiles()+Details.getNoOfChangedFilesPerPR());
//                    setCount(getCount()+1);
//                    Thread.sleep(2000);
//                    return 0;
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        }
//        //prDetails.setNoOfCommentsPerPR(getTotalComments()/getCount());
//        prDetails.setNoOfCommentsPerPR(totalComments.get());
//        prDetails.setNoOfReviewCommentsPerPR(getTotalReviewComments()/getCount());
//        prDetails.setNoOfCommitsPerPR(getTotalCommits()/getCount());
//        prDetails.setAdditionOfLinesPerPR(getTotalAdditions()/getCount());
//        prDetails.setDeletionsOfLinesPerPR(getTotalDeletions()/getCount());
//        prDetails.setNoOfChangedFilesPerPR(getTotalChangedFiles()/getCount());
//
//        return prDetails;
//    }
//}



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

    public PullRequestDetailsResponse getPullRequestDetails(String repoOwner, String repoName ){

        List <PullRequest> pullRequests = githubApiManager.getPullRequestData(repoOwner, repoName);

        PullRequestDetailsResponse prDetails = new PullRequestDetailsResponse();

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

            PullRequestDetailsResponse Details = githubApiManager.getPullRequestDetails(url,repoOwner,repoName);
            System.out.println(Details);
            setTotalComments(getTotalComments()+Details.getNoOfCommentsPerPR());
            setTotalReviewComments(getTotalReviewComments()+Details.getNoOfReviewCommentsPerPR());
            setTotalCommits(getTotalCommits()+Details.getNoOfCommitsPerPR());
            setTotalAdditions(getTotalAdditions()+Details.getAdditionOfLinesPerPR());
            setTotalDeletions(getTotalDeletions()+Details.getDeletionsOfLinesPerPR());
            setTotalChangedFiles(getTotalChangedFiles()+Details.getNoOfChangedFilesPerPR());
            setCount(getCount()+1);


//            CompletableFuture.supplyAsync(() -> {
//                try {
//                    System.out.println(url);
//
//                      //Thread.sleep(2000);
//                      return 0;
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            });
        }
        prDetails.setNoOfCommentsPerPR(getTotalComments()/getCount());
        prDetails.setNoOfReviewCommentsPerPR(getTotalReviewComments()/getCount());
        prDetails.setNoOfCommitsPerPR(getTotalCommits()/getCount());
        prDetails.setAdditionOfLinesPerPR(getTotalAdditions()/getCount());
        prDetails.setDeletionsOfLinesPerPR(getTotalDeletions()/getCount());
        prDetails.setNoOfChangedFilesPerPR(getTotalChangedFiles()/getCount());

        return prDetails;
    }
}
