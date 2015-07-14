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
	private UemConfiguration uemConfiguration;
	private ArrayList<Technology> technologiesUsed;
	private ArrayList<BusinessTransaction> businessTransactions;

	private Boolean isTechDotNetActivated;
	private Boolean isTechPhpActivated;
	private Boolean isTechJavaActivated;
	private Boolean isTechBrowserActivated;
	private Boolean isTechNativeActivated;
	private Boolean isTechWebServerActivated;


	
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

	public UemConfiguration getUemConfiguration() {
		return uemConfiguration;
	}
	@XmlElement(name = "uemconfiguration")
	public void setUemConfiguration(UemConfiguration uemConfiguration) {
		this.uemConfiguration = uemConfiguration;
	}

	public ArrayList<Technology> getTechnologiesUsed() {
		return technologiesUsed;
	}
	@XmlElement(name = "technology")
	public void setTechnologiesUsed(ArrayList<Technology> technologiesUsed) {
		this.technologiesUsed = technologiesUsed;
	}

	public ArrayList<BusinessTransaction> getBusinessTransactions() {
		return businessTransactions;
	}
	@XmlElementWrapper(name = "transactions")
	@XmlElement(name = "transaction")
	public void setBusinessTransactions(ArrayList<BusinessTransaction> businessTransactions) {
		this.businessTransactions = businessTransactions;
	}

	public Boolean getIsTechDotNetActivated() {
		return isTechDotNetActivated;
	}

	public void setIsTechDotNetActivated(Boolean isTechDotNetActivated) {
		this.isTechDotNetActivated = isTechDotNetActivated;
	}

	public Boolean getIsTechPhpActivated() {
		return isTechPhpActivated;
	}

	public void setIsTechPhpActivated(Boolean isTechPhpActivated) {
		this.isTechPhpActivated = isTechPhpActivated;
	}

	public Boolean getIsTechJavaActivated() {
		return isTechJavaActivated;
	}

	public void setIsTechJavaActivated(Boolean isTechJavaActivated) {
		this.isTechJavaActivated = isTechJavaActivated;
	}

	public Boolean getIsTechBrowserActivated() {
		return isTechBrowserActivated;
	}

	public void setIsTechBrowserActivated(Boolean isTechBrowserActivated) {
		this.isTechBrowserActivated = isTechBrowserActivated;
	}

	public Boolean getIsTechNativeActivated() {
		return isTechNativeActivated;
	}

	public void setIsTechNativeActivated(Boolean isTechNativeActivated) {
		this.isTechNativeActivated = isTechNativeActivated;
	}

	public Boolean getIsTechWebServerActivated() {
		return isTechWebServerActivated;
	}

	public void setIsTechWebServerActivated(Boolean isTechWebServerActivated) {
		this.isTechWebServerActivated = isTechWebServerActivated;
	}



}
