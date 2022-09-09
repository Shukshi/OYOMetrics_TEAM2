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
    private Integer w;

    @JsonProperty("a")
    private Integer a;

    @JsonProperty("d")
    private Integer d;

    @JsonProperty("c")
    private Integer c;

}
