package com.huso.transformation.service;


import com.huso.transformation.exception.LetterValidationException;
import com.huso.transformation.model.TransformationRequest;
import com.huso.transformation.model.TransformationResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;



@SpringBootTest
public class TransFormationServiceTest {
	private final Logger log = LoggerFactory.getLogger(TransFormationServiceTest.class);
	@Autowired
	TransFormationService transFormationService;

	@Test
	public void simpleItemsOk() {
		TransformationRequest transformationRequest = new TransformationRequest();
		transformationRequest.setName("hello world");
		transformationRequest.setItems(Arrays.asList( "bar", "baz"));
		TransformationResponse transformationResponse = transFormationService.convertTransformation(transformationRequest);
        assertTrue(transformationResponse.getItems() != null && !transformationResponse.getItems().isEmpty());
		assertEquals(new HashSet<>(transformationResponse.getItems()),new HashSet<>(Arrays.asList("bar", "baz")));
	}

	@Test
	public void duplicateItemsOk() {
		TransformationRequest transformationRequest = new TransformationRequest();
		transformationRequest.setName("hello world");
		transformationRequest.setItems(Arrays.asList("foo", "bar", "bar", "baz", "baz"));
		TransformationResponse transformationResponse = transFormationService.convertTransformation(transformationRequest);
		assertTrue(transformationResponse.getItems() != null && !transformationResponse.getItems().isEmpty());
		assertEquals(transformationResponse.getItems().size(), 3);
		assertEquals(new HashSet<>(transformationResponse.getItems()), new HashSet<>(Arrays.asList("foo", "bar", "baz")));
	}

	@Test
	public void camelCaseTransformation() {
		TransformationRequest transformationRequest = new TransformationRequest();
		transformationRequest.setName("hello world");
		transformationRequest.setItems(Arrays.asList("foo_bar", "bar", "bar", "baz", "baz"));
		TransformationResponse transformationResponse = transFormationService.convertTransformation(transformationRequest);
		assertTrue(transformationResponse.getItems() != null && !transformationResponse.getItems().isEmpty());
		assertEquals(new HashSet<>(transformationResponse.getItems()), new HashSet<>(Arrays.asList("fooBar", "bar", "baz")));
	}

	@Test
	public void nonLettersValidationError() {
		TransformationRequest transformationRequest = new TransformationRequest();
		transformationRequest.setName("hello world");
		transformationRequest.setItems(Arrays.asList("123asd", "bar", "12", "baz"));
		Assertions.assertThrows(LetterValidationException.class, () -> {
			transFormationService.convertTransformation(transformationRequest);
		});
	}

	@Test
	public void nonLettersValidationForTwoUnderscoreError() {
		TransformationRequest transformationRequest = new TransformationRequest();
		transformationRequest.setName("hello world");
		transformationRequest.setItems(Arrays.asList("foo__bar"));
		Assertions.assertThrows(LetterValidationException.class, () -> {
			transFormationService.convertTransformation(transformationRequest);
		});
	}
}
