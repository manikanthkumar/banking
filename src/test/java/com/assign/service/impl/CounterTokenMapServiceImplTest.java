package com.assign.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
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

import com.assign.entity.CounterTokenMap;
import com.assign.repository.CounterTokenMapRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class CounterTokenMapServiceImplTest {

	@InjectMocks
	private CounterTokenMapServiceImpl service;

	@Mock
	private CounterTokenMapRepository repo;

	List<CounterTokenMap> maps = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		maps.add(new CounterTokenMap(1L, 1L));
		maps.add(new CounterTokenMap(2L, 1L));
		maps.add(new CounterTokenMap(3L, 1L));
		maps.add(new CounterTokenMap(4L, 2L));
		maps.add(new CounterTokenMap(5L, 2L));
	}

	@Test
	public void testCreateMap() {
		when(repo.save(maps.get(0))).thenReturn(maps.get(0));
		assertEquals(maps.get(0), service.createMap(maps.get(0)));
	}

	@Test
	public void testUpdateMap() {
		when(repo.save(maps.get(1))).thenReturn(maps.get(1));
		assertEquals(maps.get(1), service.updateMap(maps.get(1)));
	}

	@Test
	public void testDeleteMapByTokenId() {
		doNothing().when(repo).deleteById(1L);
		assertEquals("Deleted Successfully", service.deleteMapByTokenId(1L));
	}

	@Test
	public void testGetByCounterId() {
		when(repo.findAll()).thenReturn(maps);
		List<CounterTokenMap> maps1 = new ArrayList<>();
		maps1.add(maps.get(3));
		maps1.add(maps.get(4));
		assertEquals(maps1, service.getByCounterId(2L));
	}

	@Test
	public void testGetCounterIdFromTokenId() {
		Optional<CounterTokenMap> map = Optional.of(maps.get(1));
		when(repo.findById(2L)).thenReturn(map);
		Long counterId = 1L;
		assertEquals(counterId.toString(), service.getCounterIdFromTokenId(2L).toString());
	}

	@Test
	public void testGetCounterIdFromTokenIdNull() {
		when(repo.findById(2L)).thenReturn(Optional.empty());
		assertNull(service.getCounterIdFromTokenId(2L));
	}

	@Test
	public void testGetByTokenId() {
		Optional<CounterTokenMap> map = Optional.of(maps.get(1));
		when(repo.findById(2L)).thenReturn(map);
		assertEquals(map, service.getByTokenId(2L));
	}

	@Test
	public void testGetAll() {
		when(repo.findAll()).thenReturn(maps);
		assertEquals(maps, service.getAll());
	}

}
