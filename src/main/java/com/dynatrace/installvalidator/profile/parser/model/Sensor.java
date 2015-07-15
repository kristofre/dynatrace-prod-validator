package com.dynatrace.installvalidator.profile.parser.model;
import java.lang.*;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
public class Sensor {
	private String id;
	private int order;
	private Boolean placed;
	private String description;
	private String group;
	private String type;
	private String autoplace;
	private Boolean userdefined;
	private String key;
	private ArrayList<Class> classes;
	public String getId() {
		return id;
	}
	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}
	public int getOrder() {
		return order;
	}
	@XmlAttribute
	public void setOrder(int order) {
		this.order = order;
	}
	public Boolean getPlaced() {
		return placed;
	}
	@XmlAttribute
	public void setPlaced(Boolean placed) {
		this.placed = placed;
	}
	public String getDescription() {
		return description;
	}
	@XmlAttribute
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGroup() {
		return group;
	}
	@XmlAttribute
	public void setGroup(String group) {
		this.group = group;
	}
	public String getType() {
		return type;
	}
	@XmlAttribute
	public void setType(String type) {
		this.type = type;
	}
	public String getAutoplace() {
		return autoplace;
	}
	@XmlAttribute
	public void setAutoplace(String autoplace) {
		this.autoplace = autoplace;
	}
	public Boolean getUserdefined() {
		return userdefined;
	}
	@XmlAttribute
	public void setUserdefined(Boolean userdefined) {
		this.userdefined = userdefined;
	}
	public String getKey() {
		return key;
	}
	@XmlAttribute
	public void setKey(String key) {
		this.key = key;
	}
	public ArrayList<Class> getClasses() {
		return classes;
	}
	@XmlElement(name = "class")
	public void setClasses(ArrayList<Class> classes) {
		this.classes = classes;
	}
}