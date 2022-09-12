package com.metric.oyometrics_team2.services;

import com.metric.oyometrics_team2.DTO.RepoLevelTemplate;
import com.metric.oyometrics_team2.DTO.WeekData;
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

    public  List<WeekData> getLastSevenDaysStats(String repoOwner, String repoName) {

        List<List<Integer>> addDelData = githubApiManager.getRepoLevelAddDelData(repoOwner, repoName);
        WeeklyCommits weeklyCommits = githubApiManager.getWeeklyCommits(repoOwner,repoName);
        System.out.println(weeklyCommits);
        List<WeekData> allData = new ArrayList<WeekData>();
        System.out.println();
        for(int i=addDelData.size()-10;i<addDelData.size();i++){
            WeekData weekData =new WeekData();
            weekData.setW(addDelData.get(i).get(0));
            weekData.setA(addDelData.get(i).get(1));
            weekData.setD(addDelData.get(i).get(2));
            allData.add(weekData);
        }
        List<Integer> yearlyData = weeklyCommits.getWeeklyCommits();
        int j=0;
        for(int i=yearlyData.size()-10;i<yearlyData.size();i++){

            allData.get(j).setC(yearlyData.get(i));
            j++;
        }


        return allData;
    }

}
