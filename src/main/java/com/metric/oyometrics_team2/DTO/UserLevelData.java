package com.metric.oyometrics_team2.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserLevelData {

    private Author author;

    private Integer totalCommits;

    private List<WeekDataAPIResponse> weekData;
}
