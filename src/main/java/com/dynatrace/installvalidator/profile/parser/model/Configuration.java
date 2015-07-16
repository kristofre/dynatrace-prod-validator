package com.dynatrace.installvalidator.profile.parser.model;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Configuration {
	private String id;
	private String description;
	private Boolean active;
	
	private ArrayList<SensorConfig> sensorConfigurations;

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	@XmlAttribute
	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	@XmlAttribute
	public void setActive(Boolean active) {
		this.active = active;
	}

	public ArrayList<SensorConfig> getSensorConfigurations() {
		return sensorConfigurations;
	}
	
	@XmlElement(name = "sensorconfig")
	public void setSensorConfigurations(ArrayList<SensorConfig> sensorConfigurations) {
		this.sensorConfigurations = sensorConfigurations;
	}
	
	
}
