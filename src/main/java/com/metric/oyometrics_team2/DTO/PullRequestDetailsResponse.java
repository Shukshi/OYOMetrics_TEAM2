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
    private Integer noOfCommentsPerPR;

    @JsonProperty("review_comments")
    private Integer noOfReviewCommentsPerPR;

    @JsonProperty("commits")
    private Integer noOfCommitsPerPR;

    @JsonProperty("additions")
    private Integer additionOfLinesPerPR;

    @JsonProperty("deletions")
    private Integer deletionsOfLinesPerPR;

    @JsonProperty("changed_files")
    private Integer noOfChangedFilesPerPR;
}
