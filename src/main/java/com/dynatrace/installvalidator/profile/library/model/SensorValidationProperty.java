package com.dynatrace.installvalidator.profile.library.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by kristof on 25.06.15.
 */
public class SensorValidationProperty {
    private String name;
    private String datatype;
    private String propertyType;
    private String propertyTypeValidation;
    private String operator;
    private String value;

    public String getName() {
        return name;
    }
    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getDatatype() {
        return datatype;
    }
    @XmlAttribute(name = "datatype")
    public void setDatatype(String datatype) {
        this.datatype = datatype;
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

    public String getPropertyType() {
        return propertyType;
    }
    @XmlAttribute(name = "propertytype")
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyTypeValidation() {
        return propertyTypeValidation;
    }
    @XmlAttribute(name = "propertytypeval")
    public void setPropertyTypeValidation(String propertyTypeValidation) {
        this.propertyTypeValidation = propertyTypeValidation;
    }
}
