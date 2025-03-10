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
@Table(name="sample")
@NoArgsConstructor
@AllArgsConstructor
public class MySQLData {
	
	@Id
	@Column(name = "full_id")
	private Long fullId;

	public Long getFullId() {
		return fullId;
	}

	public void setFullId(Long fullId) {
		this.fullId = fullId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Integer getBirth() {
		return birth;
	}

	public void setBirth(Integer birth) {
		this.birth = birth;
	}

	public Integer getDeath() {
		return death;
	}

	public void setDeath(Integer death) {
		this.death = death;
	}

	@Column(name = "hospital_name")
	private String hospitalName;
	
	@Column(name = "birth")
	private Integer birth;
	
	@Column(name = "death")
	private Integer death;
}
