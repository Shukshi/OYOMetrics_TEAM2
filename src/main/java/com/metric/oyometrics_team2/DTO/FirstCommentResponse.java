package com.metric.oyometrics_team2.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class FirstCommentResponse {
    private Long firstCommentMeanTime;
}
