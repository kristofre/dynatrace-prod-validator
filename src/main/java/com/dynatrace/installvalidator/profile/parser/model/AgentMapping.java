package com.dynatrace.installvalidator.profile.parser.model;
import javax.xml.bind.annotation.XmlAttribute;

public class AgentMapping {
	private String hostPattern;
	private String id;
	private String nameMatch;
	private String hostMatch;
	private Boolean capture;
	private String namePattern;
	
	public AgentMapping(){
		
	}
	
	public String getHostPattern() {
		return hostPattern;
	}
	@XmlAttribute(name = "hostpattern")
	public void setHostPattern(String hostPattern) {
		this.hostPattern = hostPattern;
	}
	public String getId() {
		return id;
	}
	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}
	public String getNameMatch() {
		return nameMatch;
	}
	@XmlAttribute(name = "namematch")
	public void setNameMatch(String nameMatch) {
		this.nameMatch = nameMatch;
	}
	public String getHostMatch() {
		return hostMatch;
	}
	@XmlAttribute(name = "hostmatch")
	public void setHostMatch(String hostMatch) {
		this.hostMatch = hostMatch;
	}
	public Boolean getCapture() {
		return capture;
	}
	@XmlAttribute
	public void setCapture(Boolean capture) {
		this.capture = capture;
	}
	public String getNamePattern() {
		return namePattern;
	}
	@XmlAttribute(name = "namepattern")
	public void setNamePattern(String namePattern) {
		this.namePattern = namePattern;
	}
	
	
}
