package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Argument {
	private String pattern;
	private String deepObjectAccessor;
	private Boolean capture;
	private String equals;
	private String argumentName;
	public String getPattern() {
		return pattern;
	}
	@XmlAttribute
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getDeepObjectAccessor() {
		return deepObjectAccessor;
	}
	@XmlAttribute(name = "deepobjectaccessor")
	public void setDeepObjectAccessor(String deepObjectAccessor) {
		this.deepObjectAccessor = deepObjectAccessor;
	}
	public Boolean getCapture() {
		return capture;
	}
	@XmlAttribute
	public void setCapture(Boolean capture) {
		this.capture = capture;
	}
	public String getEquals() {
		return equals;
	}
	@XmlAttribute
	public void setEquals(String equals) {
		this.equals = equals;
	}
	public String getArgumentName() {
		return argumentName;
	}
	@XmlAttribute(name = "argumentname")
	public void setArgumentName(String argumentName) {
		this.argumentName = argumentName;
	}
}