package com.metric.oyometrics_team2.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RepoLevelResponse {
    @JsonProperty()
    public List<WeekData> lastOneYearAddDelData;

}
