package com.metric.oyometrics_team2.services;

import com.metric.oyometrics_team2.DTO.UserLevel;
import com.metric.oyometrics_team2.DTO.UserLevelResponse;
import com.metric.oyometrics_team2.DTO.WeekData;
import com.metric.oyometrics_team2.DTO.WeekDataAPIResponse;
import com.metric.oyometrics_team2.external.GithubApiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserLevelService {

    @Autowired
    private GithubApiManager githubApiManager;

    public List<UserLevelResponse> getUserLevelStats(String repoOwner, String repoName) {

        List<UserLevel> totalUserData = githubApiManager.getUserData(repoOwner, repoName);
        List<UserLevelResponse> userList = new ArrayList<>();


        for(int i=0;i<totalUserData.size();i++){
                UserLevel ul = new UserLevel();
                ul = totalUserData.get(i);
                UserLevelResponse user = new UserLevelResponse();
                user.setUserName(ul.getAuthor().getUserName());
                user.setTotalCommits(ul.getTotalCommits());
                List<WeekDataAPIResponse> last7weeks = new ArrayList<>();


                double commitslast7weeks = new Long(0);

                for(int j=ul.getWeekData().size()-10;j<ul.getWeekData().size();j++){
                    //System.out.println(ul.getWeekData().get(j));
                    WeekData wd= ul.getWeekData().get(j);
                    WeekDataAPIResponse wdAPIr = new WeekDataAPIResponse();

                    wdAPIr.setCommits(wd.getCommits());
                    wdAPIr.setDeletionOfLines(wd.getDeletionOfLines());
                    wdAPIr.setAdditionOfLines(wd.getAdditionOfLines());
                    wdAPIr.setStartOfWeekUnixTimeStamp(wd.getStartOfWeekUnix());

                    last7weeks.add(wdAPIr);
                commitslast7weeks+=ul.getWeekData().get(j).getCommits();
            }
            Integer total_additions=0, total_deletions=0;

            for(int j=0;j<ul.getWeekData().size();j++){
                total_deletions+=ul.getWeekData().get(j).getDeletionOfLines();
                total_additions+=ul.getWeekData().get(j).getAdditionOfLines();
            }

            user.setTotalAdditionOfLines(total_additions);
            user.setTotalDeletionsOfLines(total_deletions);
            user.setLastTenWeeksData(last7weeks);
            user.setCommitFrequencyOfLastTenWeeks(commitslast7weeks/70);
            userList.add(user);

        }

        return userList;

    }
}
