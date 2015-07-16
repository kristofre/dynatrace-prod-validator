package com.dynatrace.installvalidator.profile.parser.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class AgentGroup {
	private String id;
	private String description;
	private ArrayList<AgentMapping> agentMappings;
	private SensorPlacement sensorPlacement;
	
	public AgentGroup(){
		
	}
	
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

	public ArrayList<AgentMapping> getAgentMappings() {
		return agentMappings;
	}

	
	@XmlElementWrapper(name = "agentmappings")
	@XmlElement(name = "agentmapping")
	public void setAgentMappings(ArrayList<AgentMapping> agentMappings) {
		this.agentMappings = agentMappings;
	}

	public SensorPlacement getSensorPlacement() {
		return sensorPlacement;
	}
	@XmlElement(name = "sensorplacement")
	public void setSensorPlacement(SensorPlacement sensorPlacement) {
		this.sensorPlacement = sensorPlacement;
	}
}