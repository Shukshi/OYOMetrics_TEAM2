package com.metric.oyometrics_team2.Controller;

import com.metric.oyometrics_team2.DTO.UserLevelResponse;
import com.metric.oyometrics_team2.services.UserLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/UserLevel", produces = MediaType.APPLICATION_JSON_VALUE)

public class UserLevelController {

    @Autowired
    private UserLevelService userLevelService;

    @CrossOrigin
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UserLevelResponse> getUserLevelStats(
            @RequestParam(value = "repo_owner", required = true) String repoOwner,
            @RequestParam(value = "repo_name", required = true) String repoName
    ) {
        return userLevelService.getUserLevelStats(repoOwner, repoName);
    }

}
