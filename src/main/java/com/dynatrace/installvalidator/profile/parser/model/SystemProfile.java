package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;


public class SystemProfile {
	private String description;
	private ArrayList<AgentGroup> agentGroups;
	private ArrayList<SensorGroup> sensorGroups;
	private ArrayList<Configuration> configurations;
	private ArrayList<Measure> measures;
	
	public SystemProfile(){
		
	}
	
	public String getDescription() {
		return description;
	}
	
	@XmlAttribute
	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<AgentGroup> getAgentGroups() {
		return agentGroups;
	}
	
	// XmLElementWrapper generates a wrapper element around XML representation
	@XmlElementWrapper(name = "agentgroups")
	// XmlElement sets the name of the entities in collection
	@XmlElement(name = "agentgroup")
	public void setAgentGroups(ArrayList<AgentGroup> agentGroups) {
		this.agentGroups = agentGroups;
	}

	public ArrayList<SensorGroup> getSensorGroups() {
		return sensorGroups;
	}
	
	@XmlElementWrapper(name = "sensorgroups")
	@XmlElement(name = "sensorgroup")
	public void setSensorGroups(ArrayList<SensorGroup> sensorGroups) {
		this.sensorGroups = sensorGroups;
	}

	public ArrayList<Configuration> getConfigurations() {
		return configurations;
	}
	@XmlElementWrapper(name = "configurations")
	@XmlElement(name = "configuration")
	public void setConfigurations(ArrayList<Configuration> configurations) {
		this.configurations = configurations;
	}

	public ArrayList<Measure> getMeasures() {
		return measures;
	}
	@XmlElementWrapper(name = "measures")
	@XmlElement(name = "measure")
	public void setMeasures(ArrayList<Measure> measures) {
		this.measures = measures;
	}
	
}
