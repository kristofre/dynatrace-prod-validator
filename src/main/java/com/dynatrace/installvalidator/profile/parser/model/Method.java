package com.dynatrace.installvalidator.profile.parser.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Method {
	private Boolean captureReturn;
	private String visibility;
	private Boolean overrideable;
	private Boolean argsspecified;
	private String deepObjectAccessor;
	private String inheritance;
	private String pattern;
	private Boolean onlyInstrumentIfSyncHappens;
	private String capture;
	private String api;
	private String match;
	private String placed;
	private ArrayList<Argument> arguments;
	public Boolean getCaptureReturn() {
		return captureReturn;
	}
	@XmlAttribute(name = "capturereturn")
	public void setCaptureReturn(Boolean captureReturn) {
		this.captureReturn = captureReturn;
	}
	public String getVisibility() {
		return visibility;
	}
	@XmlAttribute
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public Boolean getOverrideable() {
		return overrideable;
	}
	@XmlAttribute
	public void setOverrideable(Boolean overrideable) {
		this.overrideable = overrideable;
	}
	public Boolean getArgsspecified() {
		return argsspecified;
	}
	@XmlAttribute
	public void setArgsspecified(Boolean argsspecified) {
		this.argsspecified = argsspecified;
	}
	public String getDeepObjectAccessor() {
		return deepObjectAccessor;
	}
	@XmlAttribute(name = "deepobjectaccessor")
	public void setDeepObjectAccessor(String deepObjectAccessor) {
		this.deepObjectAccessor = deepObjectAccessor;
	}
	public String getInheritance() {
		return inheritance;
	}
	@XmlAttribute
	public void setInheritance(String inheritance) {
		this.inheritance = inheritance;
	}
	public String getPattern() {
		return pattern;
	}
	@XmlAttribute
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public Boolean getOnlyInstrumentIfSyncHappens() {
		return onlyInstrumentIfSyncHappens;
	}
	@XmlAttribute(name = "onlyinstrumentifsynchappens")
	public void setOnlyInstrumentIfSyncHappens(Boolean onlyInstrumentIfSyncHappens) {
		this.onlyInstrumentIfSyncHappens = onlyInstrumentIfSyncHappens;
	}
	public String getCapture() {
		return capture;
	}
	@XmlAttribute
	public void setCapture(String capture) {
		this.capture = capture;
	}
	public String getApi() {
		return api;
	}
	@XmlAttribute
	public void setApi(String api) {
		this.api = api;
	}
	public String getMatch() {
		return match;
	}
	@XmlAttribute
	public void setMatch(String match) {
		this.match = match;
	}
	public String getPlaced() {
		return placed;
	}
	@XmlAttribute
	public void setPlaced(String placed) {
		this.placed = placed;
	}
	public ArrayList<Argument> getArguments() {
		return arguments;
	}
	@XmlElement(name = "argument")
	public void setArguments(ArrayList<Argument> arguments) {
		this.arguments = arguments;
	}
}