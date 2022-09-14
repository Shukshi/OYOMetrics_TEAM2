package com.metric.oyometrics_team2.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserLevelResponse {
    public String userName;
    public Integer totalCommits;
    public Integer totalAdditionOfLines;
    public Integer totalDeletionsOfLines;
    public double commitFrequencyOfLastTenWeeks;
    public List<WeekDataAPIResponse> lastTenWeeksData;

}
