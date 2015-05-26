package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Dynatrace {
	private SystemProfile systemProfile;
	private String version;
	public Dynatrace() {
		
	}
	public SystemProfile getSystemProfile() {
		return systemProfile;
	}
	
	@XmlElement(name = "systemprofile")
	public void setSystemProfile(SystemProfile systemProfile) {
		this.systemProfile = systemProfile;
	}
	public String getVersion() {
		return version;
	}
	@XmlAttribute
	public void setVersion(String version) {
		this.version = version;
	}
	
	
	
}
