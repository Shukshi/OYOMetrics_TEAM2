package com.metric.oyometrics_team2.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeekDataAPIResponse {

    private Integer startOfWeekUnixTimeStamp;

    private Integer additionOfLines;

    private Integer deletionOfLines;

    private Integer commits;
}
