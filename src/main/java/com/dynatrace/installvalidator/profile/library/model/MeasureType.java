package com.dynatrace.installvalidator.profile.library.model;

import javax.xml.bind.annotation.XmlAttribute;

public class MeasureType {
	private String id;
	private boolean hide;
	private String name;
	
	public String getId() {
		return id;
	}
	@XmlAttribute(name = "id")
	public void setId(String id) {
		this.id = id;
	}
	public boolean isHide() {
		return hide;
	}
	@XmlAttribute(name = "hide")
	public void setHide(boolean hide) {
		this.hide = hide;
	}
	public String getName() {
		return name;
	}
	@XmlAttribute(name = "name")
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
