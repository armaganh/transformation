package com.huso.transformation.api;


import com.huso.transformation.model.TransformationRequest;
import com.huso.transformation.model.TransformationResponse;
import com.huso.transformation.service.TransFormationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.dsig.TransformService;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/transformation")
public class TransformationController {
	private final Logger log = LoggerFactory.getLogger(TransformationController.class);

	@Autowired
	TransFormationService transFormationService;

	@PostMapping
	public ResponseEntity<TransformationResponse> transformItems(@RequestBody TransformationRequest transformationRequest) {
		return new ResponseEntity<TransformationResponse>(transFormationService.convertTransformation(transformationRequest), HttpStatus.OK);
	}

}
