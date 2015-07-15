package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlElement;

public class SensorGroup {
	private Sensor sensor;

	public Sensor getSensor() {
		return sensor;
	}
	
	@XmlElement(name = "sensor")
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
}