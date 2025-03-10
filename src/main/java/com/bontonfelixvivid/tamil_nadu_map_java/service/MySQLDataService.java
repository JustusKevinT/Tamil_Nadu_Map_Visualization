package com.bontonfelixvivid.tamil_nadu_map_java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bontonfelixvivid.tamil_nadu_map_java.model.MySQLData;
import com.bontonfelixvivid.tamil_nadu_map_java.repository.MySQLDataRepository;

@Service
public class MySQLDataService {

	@Autowired
	private MySQLDataRepository mySQLDataRepository;

	public List<MySQLData> getMySQLData() {
		return mySQLDataRepository.findAll();
	}
	
}

