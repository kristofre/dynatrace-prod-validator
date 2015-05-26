package com.dynatrace.installvalidator.profile.parser.model;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class SensorPlacement  {
	private Boolean autoCaptureStrings;
	private Boolean ignoreGetterSetter;
	private ArrayList<Sensor> sensors;
	public Boolean getAutoCaptureStrings() {
		return autoCaptureStrings;
	}
	@XmlAttribute(name = "autocapturestrings")
	public void setAutoCaptureStrings(Boolean autoCaptureStrings) {
		this.autoCaptureStrings = autoCaptureStrings;
	}
	public Boolean getIgnoreGetterSetter() {
		return ignoreGetterSetter;
	}
	@XmlAttribute(name = "ignoregettersetter")
	public void setIgnoreGetterSetter(Boolean ignoreGetterSetter) {
		this.ignoreGetterSetter = ignoreGetterSetter;
	}
	public ArrayList<Sensor> getSensors() {
		return sensors;
	}
	
	@XmlElement(name = "sensor")
	public void setSensors(ArrayList<Sensor> sensors) {
		this.sensors = sensors;
	}
	
}
