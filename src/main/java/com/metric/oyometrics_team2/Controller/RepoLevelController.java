package com.metric.oyometrics_team2.Controller;

import com.metric.oyometrics_team2.DTO.WeekData;
import com.metric.oyometrics_team2.services.RepoLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/RepoLevel", produces = MediaType.APPLICATION_JSON_VALUE)
public class RepoLevelController {
    @Autowired
    private RepoLevelService repoLevelService;

    @CrossOrigin
    @RequestMapping(value = "/last_ten_weeks_activity", method = RequestMethod.GET)
    public  List<WeekData> getRepoLevelStats(
            @RequestParam(value = "repo_owner", required = true) String repoOwner,
            @RequestParam(value = "repo_name", required = true) String repoName
    ) {
        return repoLevelService.getLastSevenDaysStats(repoOwner,repoName);
    }

}
