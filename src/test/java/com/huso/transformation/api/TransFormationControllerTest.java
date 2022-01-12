package com.huso.transformation.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huso.transformation.model.TransformationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class TransFormationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void postingSimpleTransformation() throws Exception {

	  TransformationRequest transformationRequest = new TransformationRequest();
	  transformationRequest.setName("hello world");
	  transformationRequest.setItems(Arrays.asList("foo_bar", "bar", "bar", "baz", "baz"));
    this.mockMvc
      .perform(post("/api/transformation")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(transformationRequest)))
        .andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.items", hasSize(3)))
			.andExpect(jsonPath("$.items[0]", is("fooBar")))
			.andExpect(jsonPath("$.items[1]", is("bar")))
			.andExpect(jsonPath("$.items[2]", is("baz")))
			.andExpect(jsonPath("$.name", is("hello world")))
			.andExpect(jsonPath("$.timestamp",  notNullValue()));
  }
	@Test
	public void letterException() throws Exception {

		TransformationRequest transformationRequest = new TransformationRequest();
		transformationRequest.setName("hello world");
		transformationRequest.setItems(Arrays.asList("123asdasd", "bar"));
		this.mockMvc
				.perform(post("/api/transformation")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(transformationRequest)))
				.andExpect(status().isBadRequest());
	}
}
