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
//        AtomicReference<Long> totalTime = new AtomicReference<>(Long.valueOf(0));
//
//        weekData.forEach(user -> {
//            System.out.println(user.getAuthor().getUserName());
//            System.out.println(user.getTotalCommits());
//            System.out.println(user.getWeekData().size());
//        });
        List<UserTemplate> userList = new ArrayList<UserTemplate>();

        for(int i=0;i<totalUserData.size();i++){
                UserLevel ul = new UserLevel();
                ul = totalUserData.get(i);
            UserTemplate user = new UserTemplate();
            user.setUserName(ul.getAuthor().getUserName());
            user.setTotal_commits(ul.getTotalCommits());
            List<WeekData> last7weeks = new ArrayList<WeekData>();
            for(int j=ul.getWeekData().size()-7;j<ul.getWeekData().size();j++){
                last7weeks.add(ul.getWeekData().get(j));
            }
            Integer total_additions=0, total_deletions=0;
            for(int j=0;j<ul.getWeekData().size();j++){
                total_deletions+=ul.getWeekData().get(j).getD();
                total_additions+=ul.getWeekData().get(j).getA();
            }
            user.setTotal_additions(total_additions);
            user.setTotal_deletions(total_deletions);
            user.setLastSevenWeeksData(last7weeks);
            userList.add(user);
        }

        return userList;

    }
}
