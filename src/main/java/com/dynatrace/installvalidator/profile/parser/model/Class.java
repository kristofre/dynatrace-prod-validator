package com.dynatrace.installvalidator.profile.parser.model;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
public class Class {
	private String techType;
	private String pattern;
	private String match;
	private Boolean delagationSuppression;
	private Boolean placed;
	private ArrayList<Method> methods;
	public String getTechType() {
		return techType;
	}
	@XmlAttribute(name = "techtype")
	public void setTechType(String techType) {
		this.techType = techType;
	}
	public String getPattern() {
		return pattern;
	}
	@XmlAttribute
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getMatch() {
		return match;
	}
	@XmlAttribute
	public void setMatch(String match) {
		this.match = match;
	}
	public Boolean getDelagationSuppression() {
		return delagationSuppression;
	}
	@XmlAttribute(name = "delegationsuppression")
	public void setDelagationSuppression(Boolean delagationSuppression) {
		this.delagationSuppression = delagationSuppression;
	}
	public Boolean getPlaced() {
		return placed;
	}
	@XmlAttribute
	public void setPlaced(Boolean placed) {
		this.placed = placed;
	}
	public ArrayList<Method> getMethods() {
		return methods;
	}
	@XmlElement(name = "method")
	public void setMethods(ArrayList<Method> methods) {
		this.methods = methods;
	}
	
	
}
