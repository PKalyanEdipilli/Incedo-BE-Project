package com.incedo.loganalyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.incedo.loganalyzer.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

	List<Log> findAllByMessage(String message);

}