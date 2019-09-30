package com.assign.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assign.entity.Counter;
import com.assign.entity.CounterServiceType;
import com.assign.entity.ServiceType;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Long> {

	List<Counter> findByServiceTypeAndCounterService(ServiceType servicetype, CounterServiceType counterservice);

	List<Counter> findByServiceType(ServiceType servicetype);
}
