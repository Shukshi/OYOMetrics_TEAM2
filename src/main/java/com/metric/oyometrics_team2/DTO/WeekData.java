package com.metric.oyometrics_team2.DTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class WeekData {
    @JsonProperty("w")
    private Integer startOfWeekUnix;

    @JsonProperty("a")
    private Integer additionOfLines;

    @JsonProperty("d")
    private Integer deletionOfLines;

    @JsonProperty("c")
    private Integer commits;

}
