package com.csci5308.codeLabeller.Models.DTO;

import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor
public class StartSurveyResponse {
    private Long snippetId;
    private byte[] snippetText;
    private List<AnnotationResponse> surveyAnnotationList;
}
