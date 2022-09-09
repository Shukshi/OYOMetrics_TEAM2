package com.metric.oyometrics_team2.Controller;

import com.metric.oyometrics_team2.DTO.UserLevel;
import com.metric.oyometrics_team2.DTO.UserTemplate;
import com.metric.oyometrics_team2.services.PullRequestService;
import com.metric.oyometrics_team2.services.UserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/UserLevel", produces = MediaType.APPLICATION_JSON_VALUE)

public class UserLevelController {

    @Autowired
    private UserLevelService userLevelService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UserTemplate> getAverageTimeSpent(
            @RequestParam(value = "repo_owner", required = true) String repoOwner,
            @RequestParam(value = "repo_name", required = true) String repoName
    ) {
        return userLevelService.getUserLevelStats(repoOwner, repoName);
    }

}
