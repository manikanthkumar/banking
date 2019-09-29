package com.assign.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.assign.entity.Counter;
import com.assign.entity.CounterServiceType;
import com.assign.entity.ServiceType;
import com.assign.exception.RecordNotFoundException;
import com.assign.repository.CounterRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class CounterServiceImplTest {

	@InjectMocks
	private CounterServiceImpl service;

	@Mock
	private CounterRepository repo;

	List<Counter> counters = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		counters.add(new Counter(1L, "first", 1L, ServiceType.PRIORITY, CounterServiceType.deposit));
		counters.add(new Counter(2L, "second", 2L, ServiceType.PRIORITY, CounterServiceType.deposit));
		counters.add(new Counter(3L, "third", 3L, ServiceType.PRIORITY, CounterServiceType.enquiry));
		counters.add(new Counter(4L, "fourth", 4L, ServiceType.PRIORITY, CounterServiceType.deposit));
		counters.add(new Counter(5L, "fifth", 5L, ServiceType.PRIORITY, CounterServiceType.withdraw));
		counters.add(new Counter(6L, "sixth", 6L, ServiceType.REGULAR, CounterServiceType.deposit));
		counters.add(new Counter(7L, "seventh", 7L, ServiceType.REGULAR, CounterServiceType.withdraw));
	}

	@Test
	public void testCreateCounter() {
		when(repo.save(counters.get(0))).thenReturn(counters.get(0));
		assertEquals(counters.get(0), service.createCounter(counters.get(0)));
	}

	@Test
	public void testGetCounterById() throws Exception {
		Optional<Counter> counter = Optional.of(counters.get(1));
		when(repo.findById(2L)).thenReturn(counter);
		assertEquals(counters.get(1), service.getCounterById(2L));
	}

	@Test(expected = RecordNotFoundException.class)
	public void testGetCounterByIdException() throws Exception {
		when(repo.findById(2L)).thenReturn(Optional.empty());
		service.getCounterById(2L);
	}

	@Test
	public void testGetAllCounters() {
		when(repo.findAll()).thenReturn(counters);
		assertEquals(counters, service.getAllCounters());
	}

	@Test
	public void testGetCounterByService() {
		when(repo.findAll()).thenReturn(counters);
		List<Counter> counters1 = new ArrayList<>();
		counters1.add(counters.get(5));
		counters1.add(counters.get(6));
		assertEquals(counters1, service.getCounterByService(ServiceType.REGULAR));
	}

	@Test
	public void testUpdateCounter() {
		when(repo.save(counters.get(0))).thenReturn(counters.get(0));
		assertEquals(counters.get(0), service.updateCounter(counters.get(0)));
	}

	@Test
	public void testGetCounterByServiceAndCounterServiceType() {
		when(repo.findAll()).thenReturn(counters);
		List<Counter> counters1 = new ArrayList<>();
		counters1.add(counters.get(5));
		assertEquals(counters1,
				service.getCounterByServiceAndCounterServiceType(ServiceType.REGULAR, CounterServiceType.deposit));
	}

}
