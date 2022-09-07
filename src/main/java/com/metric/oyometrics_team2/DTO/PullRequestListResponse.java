package com.metric.oyometrics_team2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PullRequestListResponse {
    private List<PullRequest> listPullRequest;
}
