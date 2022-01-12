package com.huso.transformation.service;

import com.huso.transformation.model.TransformationRequest;
import com.huso.transformation.model.TransformationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.huso.transformation.util.Util.validateLettern;


@Service
public class TransFormationService {
	private final Logger log = LoggerFactory.getLogger(TransFormationService.class);
	private final Pattern camelCase = Pattern.compile("_([a-z])");

	public TransformationResponse convertTransformation(TransformationRequest transformationRequest) {
		log.info("Convert Transformation : {} ", transformationRequest);
		Set<String> items = transformationRequest.getItems().stream().map(item -> {
			String transform = camelCase.matcher(item).replaceAll(l -> l.group(1).toUpperCase());
			log.info(transform);
			validateLettern(transform);
			return  transform;
		}).collect(Collectors.toSet());
		return  new TransformationResponse(transformationRequest.getName(), items, System.currentTimeMillis());
	}
}
