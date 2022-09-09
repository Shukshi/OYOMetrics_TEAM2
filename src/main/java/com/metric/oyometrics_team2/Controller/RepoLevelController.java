package com.metric.oyometrics_team2.Controller;

import com.metric.oyometrics_team2.DTO.RepoLevelTemplate;
import com.metric.oyometrics_team2.DTO.UserTemplate;
import com.metric.oyometrics_team2.DTO.WeekData;
import com.metric.oyometrics_team2.services.RepoLevelService;
import com.metric.oyometrics_team2.services.UserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/RepoLevel", produces = MediaType.APPLICATION_JSON_VALUE)
public class RepoLevelController {
    @Autowired
    private RepoLevelService repoLevelService;

    @RequestMapping(value = "/last_seven_days_activity", method = RequestMethod.GET)
    public  List<WeekData> getRepoLevelStats(
            @RequestParam(value = "repo_owner", required = true) String repoOwner,
            @RequestParam(value = "repo_name", required = true) String repoName
    ) {
        return repoLevelService.getLastSevenDaysStats(repoOwner,repoName);
    }

}
