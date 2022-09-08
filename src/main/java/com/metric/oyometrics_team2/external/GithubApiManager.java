package com.metric.oyometrics_team2.external;

import com.metric.oyometrics_team2.DTO.PullRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GithubApiManager extends RestApiManager{

    @Value("${external.github.url}")
    private String githubUrl;

//    @Value("${external.github.accessToken}")
//    private static String accessToken;
    private static final String REPO_PULL = "/repos";

    public List<PullRequest> getPullRequestData(String repoOwner, String repoName) {
        String url = new StringBuilder(REPO_PULL)
                .append("/")
                .append(repoOwner)
                .append("/")
                .append(repoName)
                .append("/pulls")
                .toString();

        return super.get(githubUrl, url, null, getRequestHeaders(), List.class);
    }

    public static HttpHeaders getRequestHeaders() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");

        requestHeaders.add("Authorization", "Bearer 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");
        return requestHeaders;
    }


}
