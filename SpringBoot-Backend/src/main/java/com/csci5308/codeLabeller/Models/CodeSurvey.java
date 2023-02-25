package com.csci5308.codeLabeller.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor
public class CodeSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long surveyID;

    @NonNull
    private String username;
    private String surveyName;
    private String surveyLanguage;
    @OneToMany(mappedBy = "survey")
    private Set<CodeSnippet> snippetList;

    @OneToMany(mappedBy = "survey")
    private Set<CodeAnnotations> annotationList;

}
