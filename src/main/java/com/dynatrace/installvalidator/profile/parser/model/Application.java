package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * Created by kristof on 27.05.15.
 */
public class Application {
    private Boolean isDefault;
    private String name;
    private String autoDetectType;
    private ArrayList<UriPattern> uriPatterns;

    public Boolean getIsDefault() {
        return isDefault;
    }
    @XmlAttribute(name = "isdefault")
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }
    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getAutoDetectType() {
        return autoDetectType;
    }
    @XmlAttribute(name = "autodetecttype")
    public void setAutoDetectType(String autoDetectType) {
        this.autoDetectType = autoDetectType;
    }

    public ArrayList<UriPattern> getUriPatterns() {
        return uriPatterns;
    }
    @XmlElementWrapper(name = "uripatterns")
    @XmlElement(name = "uripattern")
    public void setUriPatterns(ArrayList<UriPattern> uriPatterns) {
        this.uriPatterns = uriPatterns;
    }
}
