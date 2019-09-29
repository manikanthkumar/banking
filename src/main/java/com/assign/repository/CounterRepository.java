package com.assign.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assign.entity.Counter;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Long>  {

}
