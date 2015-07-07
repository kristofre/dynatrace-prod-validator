package com.dynatrace.installvalidator.profile.library.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

/**
 * Created by kristof on 29.05.15.
 */
public class SensorDetail {
    private String id;
    private String name;
    private String tech;
    private Boolean defaultHide;
    private ArrayList<SensorProperty> sensorProperties;

    public String getId() {
        return id;
    }
    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getTech() {
        return tech;
    }
    @XmlAttribute
    public void setTech(String tech) {
        this.tech = tech;
    }

    public Boolean getDefaultHide() {
        return defaultHide;
    }
    @XmlAttribute(name = "defaulthide")
    public void setDefaultHide(Boolean defaultHide) {
        this.defaultHide = defaultHide;
    }

    public ArrayList<SensorProperty> getSensorProperties() {
        return sensorProperties;
    }

    @XmlElement(name = "property")
    public void setSensorProperties(ArrayList<SensorProperty> sensorProperties) {
        this.sensorProperties = sensorProperties;
    }
}
