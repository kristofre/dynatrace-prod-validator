package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by kristof on 14.07.15.
 */
public class UemModule {
    private String key;
    private boolean active;

    public String getKey() {
        return key;
    }
    public boolean isActive() {
		return active;
	}
    @XmlAttribute(name = "active")
	public void setActive(boolean active) {
		this.active = active;
	}
	@XmlAttribute(name = "key")
    public void setKey(String key) {
        this.key = key;
    }
    
}
