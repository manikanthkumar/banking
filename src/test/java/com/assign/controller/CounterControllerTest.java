package com.assign.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.assign.entity.Counter;
import com.assign.entity.CounterServiceType;
import com.assign.entity.ServiceType;
import com.assign.service.CounterService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class CounterControllerTest {

	@InjectMocks
	private CounterController controller;

	@Mock
	private CounterService service;
	private MockMvc mockMvc;
	List<Counter> counters = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		counters.add(new Counter(1L, "first", 1L, ServiceType.PRIORITY, CounterServiceType.deposit));
		counters.add(new Counter(2L, "second", 2L, ServiceType.PRIORITY, CounterServiceType.deposit));
	}

	@Test
	public void testCreateCounter() throws Exception {
		when(service.createCounter(any(Counter.class))).thenReturn(counters.get(0));

		byte[] byteData = new ObjectMapper().writeValueAsBytes(counters.get(0));

		MvcResult result = mockMvc
				.perform(post("/counters/").contentType(MediaType.APPLICATION_JSON_UTF8).content(byteData)).andReturn();

		Counter counter = new ObjectMapper().readValue(result.getResponse().getContentAsString(),
				new TypeReference<Counter>() {
				});

		assertEquals(counter, counters.get(0));
	}

	@Test
	public void testGetCounterInfo() throws Exception {
		when(service.getAllCounters()).thenReturn(counters);

		byte[] byteData = new ObjectMapper().writeValueAsBytes(counters);

		MvcResult result = mockMvc
				.perform(get("/counters/").contentType(MediaType.APPLICATION_JSON_UTF8).content(byteData)).andReturn();

		List<Counter> counter = new ObjectMapper().readValue(result.getResponse().getContentAsString(),
				new TypeReference<List<Counter>>() {
				});

		assertEquals(counter, counters);
	}

}
