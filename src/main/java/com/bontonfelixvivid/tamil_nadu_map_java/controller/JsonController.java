package com.bontonfelixvivid.tamil_nadu_map_java.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bontonfelixvivid.tamil_nadu_map_java.model.MySQLData;
import com.bontonfelixvivid.tamil_nadu_map_java.model.MySQLMapData;
import com.bontonfelixvivid.tamil_nadu_map_java.service.MySQLDataService;
import com.bontonfelixvivid.tamil_nadu_map_java.service.MySQLMapDataService;

@Controller
@RequestMapping("/api")
public class JsonController {

	@Autowired
    private MySQLDataService mySQLDataService;
	
	@Autowired
	private MySQLMapDataService mySQLMapDataService;
	
    @GetMapping(value="/getData")
    @ResponseBody
    public List<MySQLData> fetchMySQLData() {
    	return mySQLDataService.getMySQLData();
    }
    
    @GetMapping(value="/getMapData", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getMapDataGeoJson() {
        List<MySQLMapData> mapDataList = mySQLMapDataService.getMySQLMapData();
        JSONObject geoJson = mySQLMapDataService.convertToGeoJson(mapDataList);
        return geoJson.toString();
    }
    
}