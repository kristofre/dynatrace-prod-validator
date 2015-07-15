package com.dynatrace.installvalidator.profile.parser.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class AgentSensorConfig {
	private String Id;
	private String capture;
	private Boolean includeProps;
	private Boolean byteCountEnabled;
	private ArrayList<SensorProperty> properties;
	public String getId() {
		return Id;
	}
	@XmlAttribute
	public void setId(String id) {
		Id = id;
	}
	public String getCapture() {
		return capture;
	}
	@XmlAttribute
	public void setCapture(String capture) {
		this.capture = capture;
	}
	public Boolean getIncludeProps() {
		return includeProps;
	}
	@XmlAttribute(name = "includeprops")
	public void setIncludeProps(Boolean includeProps) {
		this.includeProps = includeProps;
	}
	public Boolean getByteCountEnabled() {
		return byteCountEnabled;
	}
	@XmlAttribute(name = "bytecountenabled")
	public void setByteCountEnabled(Boolean byteCountEnabled) {
		this.byteCountEnabled = byteCountEnabled;
	}
	public ArrayList<SensorProperty> getProperties() {
		return properties;
	}
	@XmlElement(name = "property")
	public void setProperties(ArrayList<SensorProperty> properties) {
		this.properties = properties;
	}
}