package com.metric.oyometrics_team2.services;

import com.metric.oyometrics_team2.DTO.WeekData;
import com.metric.oyometrics_team2.DTO.WeekDataAPIResponse;
import com.metric.oyometrics_team2.DTO.WeeklyCommits;
import com.metric.oyometrics_team2.external.GithubApiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepoLevelService {

    @Autowired
    private  GithubApiManager githubApiManager;

    public  List<WeekDataAPIResponse> getLastSevenDaysStats(String repoOwner, String repoName) {


        List<List<Integer>> addDelData = githubApiManager.getRepoLevelAddDelData(repoOwner, repoName);
        WeeklyCommits weeklyCommits = githubApiManager.getWeeklyCommits(repoOwner,repoName);
        System.out.println(weeklyCommits);

        List<WeekDataAPIResponse> allData = new ArrayList<>();

        System.out.println();
        for(int i=addDelData.size()-10;i<addDelData.size();i++){
            WeekDataAPIResponse weekData =new WeekDataAPIResponse();
            weekData.setStartOfWeekUnixTimeStamp(addDelData.get(i).get(0));
            weekData.setAdditionOfLines(addDelData.get(i).get(1));
            weekData.setDeletionOfLines(addDelData.get(i).get(2));
            allData.add(weekData);
        }
        List<Integer> yearlyData = weeklyCommits.getWeeklyCommits();
        int j=0;
        for(int i=yearlyData.size()-10;i<yearlyData.size();i++){

            allData.get(j).setCommits(yearlyData.get(i));
            j++;
        }


        return allData;
    }

}
