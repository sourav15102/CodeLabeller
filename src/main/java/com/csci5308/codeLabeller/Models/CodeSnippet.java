package com.csci5308.codeLabeller.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor
public class CodeSnippet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long codeSnippetId;
    @ManyToOne
    @JoinColumn(name = "SurveyID")
    private CodeSurvey survey;
    @Lob
    private byte[] snippetText;

    @ToString.Exclude
    @ManyToMany
    private Set<CodeAnnotations> tags;
}
