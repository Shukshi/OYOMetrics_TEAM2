package com.metric.oyometrics_team2.DTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PullRequestDetailsResponse {
    @JsonProperty("comments")
    private double noOfCommentsPerPR;

    @JsonProperty("review_comments")
    private double noOfReviewCommentsPerPR;

    @JsonProperty("commits")
    private double noOfCommitsPerPR;

    @JsonProperty("additions")
    private double additionOfLinesPerPR;

    @JsonProperty("deletions")
    private double deletionsOfLinesPerPR;

    @JsonProperty("changed_files")
    private double noOfChangedFilesPerPR;
}
