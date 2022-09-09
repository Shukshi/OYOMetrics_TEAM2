package com.metric.oyometrics_team2.services;

import com.metric.oyometrics_team2.DTO.PullRequest;
import com.metric.oyometrics_team2.DTO.UserLevel;
import com.metric.oyometrics_team2.DTO.UserTemplate;
import com.metric.oyometrics_team2.DTO.WeekData;
import com.metric.oyometrics_team2.external.GithubApiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;



@Service
public class UserLevelService {

    @Autowired
    private GithubApiManager githubApiManager;

    public List<UserTemplate> getUserLevelStats(String repoOwner, String repoName) {

        List<UserLevel> totalUserData = githubApiManager.getUserData(repoOwner, repoName);
        List<UserTemplate> userList = new ArrayList<UserTemplate>();

        for(int i=0;i<totalUserData.size();i++){
                UserLevel ul = new UserLevel();
                ul = totalUserData.get(i);
                UserTemplate user = new UserTemplate();
                user.setUserName(ul.getAuthor().getUserName());
                user.setTotal_commits(ul.getTotalCommits());
                List<WeekData> last7weeks = new ArrayList<WeekData>();

                Long commitslast7weeks = new Long(0);

                for(int j=ul.getWeekData().size()-10;j<ul.getWeekData().size();j++){
                last7weeks.add(ul.getWeekData().get(j));
                commitslast7weeks+=ul.getWeekData().get(j).getC();
            }
            Integer total_additions=0, total_deletions=0;

            for(int j=0;j<ul.getWeekData().size();j++){
                total_deletions+=ul.getWeekData().get(j).getD();
                total_additions+=ul.getWeekData().get(j).getA();
            }

            user.setTotal_additions(total_additions);
            user.setTotal_deletions(total_deletions);
            user.setLastSevenWeeksData(last7weeks);
            user.setCommit_frequency(commitslast7weeks/70);
            userList.add(user);

        }

        return userList;

    }
}