package com.huso.transformation.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class TransformationResponse {
    private String name;
    private Set<String> items;
    private Long timestamp;
}
