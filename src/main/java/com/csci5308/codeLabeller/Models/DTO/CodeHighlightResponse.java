package com.csci5308.codeLabeller.Models.DTO;

import com.csci5308.codeLabeller.Models.CodeAnnotations;
import com.csci5308.codeLabeller.Models.CodeSnippet;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CodeHighlightResponse {

    private String span_start_id;
    private String span_end_id;
    private String annotated_by;
    private AnnotationResponse annotation;

}
