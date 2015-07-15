package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by kristof on 14.07.15.
 */
public class UemModule {
    private String key;

    public String getKey() {
        return key;
    }
    @XmlAttribute(name = "key")
    public void setKey(String key) {
        this.key = key;
    }
}
