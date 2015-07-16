package com.dynatrace.installvalidator.profile.parser.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class SensorConfig {
	private String refAgentGroup;
	private int captureCpuOption;
	private double purePathPercentage;
	private int stringCaptureLength;
	
	private ArrayList<AgentSensorConfig> agentSensorConfigs;

	public String getRefAgentGroup() {
		return refAgentGroup;
	}
	@XmlAttribute(name = "refagentgroup")
	public void setRefAgentGroup(String refAgentGroup) {
		this.refAgentGroup = refAgentGroup;
	}

	public int getCaptureCpuOption() {
		return captureCpuOption;
	}
	@XmlAttribute(name = "capturecpuoption")
	public void setCaptureCpuOption(int captureCpuOption) {
		this.captureCpuOption = captureCpuOption;
	}

	public double getPurePathPercentage() {
		return purePathPercentage;
	}
	@XmlAttribute(name = "purepathpercentage")
	public void setPurePathPercentage(double purePathPercentage) {
		this.purePathPercentage = purePathPercentage;
	}

	public int getStringCaptureLength() {
		return stringCaptureLength;
	}
	@XmlAttribute(name = "stringcapturelength")
	public void setStringCaptureLength(int stringCaptureLength) {
		this.stringCaptureLength = stringCaptureLength;
	}

	public ArrayList<AgentSensorConfig> getAgentSensorConfigs() {
		return agentSensorConfigs;
	}
	
	@XmlElement(name = "sensor")
	public void setAgentSensorConfigs(
			ArrayList<AgentSensorConfig> agentSensorConfigs) {
		this.agentSensorConfigs = agentSensorConfigs;
	}
	
	
}
