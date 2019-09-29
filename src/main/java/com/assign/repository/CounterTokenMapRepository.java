package com.assign.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assign.entity.CounterTokenMap;

@Repository
public interface CounterTokenMapRepository extends JpaRepository<CounterTokenMap, Long> {

}
