package com.bontonfelixvivid.tamil_nadu_map_java.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="tamilnadu")
@NoArgsConstructor
@AllArgsConstructor
public class MySQLMapData {
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOsmId() {
		return osmId;
	}

	public void setOsmId(Long osmId) {
		this.osmId = osmId;
	}

	public String getHospitalNameMap() {
		return hospitalNameMap;
	}

	public void setHospitalNameMap(String hospitalNameMap) {
		this.hospitalNameMap = hospitalNameMap;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getGeometryType() {
		return geometryType;
	}

	public void setGeometryType(String geometryType) {
		this.geometryType = geometryType;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="osm_id")
	private Long osmId;
	
	@Column(name="hospitalName")
	private String hospitalNameMap;
	
	@Column(name="zone_name")
	private String zoneName;
	
	@Column(name="ward_name")
	private String wardName;
	
	@Column(name="name_2")
	private String name2;
	
	@Column(name="geometry_type")
	private String geometryType;
	
	@Column(name="coordinates", columnDefinition = "json")
	private String coordinates;
	
}

