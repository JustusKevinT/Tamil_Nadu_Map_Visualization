package com.bontonfelixvivid.tamil_nadu_map_java.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bontonfelixvivid.tamil_nadu_map_java.model.MySQLMapData;
import com.bontonfelixvivid.tamil_nadu_map_java.repository.MySQLMapDataRepository;

@Service
public class MySQLMapDataService {
	
	@Autowired
	private MySQLMapDataRepository mySQLMapDataRepository;

	public List<MySQLMapData> getMySQLMapData(){
		return mySQLMapDataRepository.findAll();
	}
	
	
	public JSONObject convertToGeoJson(List<MySQLMapData> mapDataList) {
        JSONObject geoJson = new JSONObject();
        geoJson.put("type", "FeatureCollection");
        geoJson.put("name", "TamilNadu");
        JSONObject crs = new JSONObject();
        crs.put("type", "name");
        JSONObject crsProperties = new JSONObject();
        crsProperties.put("name", "urn:ogc:def:crs:OGC:1.3:CRS84");
        crs.put("properties", crsProperties);
        geoJson.put("crs", crs);

        JSONArray features = new JSONArray();
        for (MySQLMapData mapData : mapDataList) {
            JSONObject feature = new JSONObject();
            feature.put("type", "Feature");
            JSONObject properties = new JSONObject();
            properties.put("full_id", mapData.getId());
            properties.put("osm_id", mapData.getOsmId());
            properties.put("Hospital Name", mapData.getHospitalNameMap());
            properties.put("Zone Name", mapData.getZoneName());
            properties.put("Ward Name", mapData.getWardName());
            properties.put("NAME_2", mapData.getName2());

            feature.put("properties", properties);

            JSONObject geometry = new JSONObject();
            geometry.put("type", mapData.getGeometryType());
            geometry.put("coordinates", new JSONArray(mapData.getCoordinates()));

            feature.put("geometry", geometry);
            features.put(feature);
        }
        geoJson.put("features", features);

        return geoJson;
    }
	
}
