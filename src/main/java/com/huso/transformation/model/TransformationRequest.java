package com.huso.transformation.model;

import lombok.Data;

import java.util.List;

@Data
public class TransformationRequest {
    private String name;
    private List<String> items;
}
