package com.metric.oyometrics_team2.DTO;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Builder
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor

public class PullRequest {
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("closed_at")
    private LocalDateTime closedAt;

    @JsonProperty("html_url")
    private String htmlUrl;
}

