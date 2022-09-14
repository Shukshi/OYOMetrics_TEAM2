package com.metric.oyometrics_team2.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PRDetailsAPIResponse {

    private double noOfCommentsPerPR;

    private double noOfReviewCommentsPerPR;

    private double noOfCommitsPerPR;

    private double additionOfLinesPerPR;

    private double deletionsOfLinesPerPR;

    private double noOfChangedFilesPerPR;
}
