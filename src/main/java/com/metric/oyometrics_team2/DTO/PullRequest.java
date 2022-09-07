package com.metric.oyometrics_team2.DTO;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PullRequest {
    private int createdAt;
    private int closedAt;
    private String htmlUrl;
}

