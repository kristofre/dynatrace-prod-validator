package com.dynatrace.installvalidator.profile.library.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by kristof on 25.06.15.
 */
public class SensorProperty {
    private String name;
    private String type;
    private String operator;
    private String value;

    public String getName() {
        return name;
    }
    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }

    public String getOperator() {
        return operator;
    }
    @XmlAttribute
    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }
    @XmlAttribute
    public void setValue(String value) {
        this.value = value;
    }
}
