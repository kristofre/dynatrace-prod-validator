package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kristof on 14.07.15.
 */
public class UemApplicationConfig {
   private String visitTimeOutUnit;
    private double visittimeout;
    private UemSensorConfig uemSensorConfig;

    public UemSensorConfig getUemSensorConfig() {
        return uemSensorConfig;
    }
    @XmlElement(name = "uemsensorconfig")
    public void setUemSensorConfig(UemSensorConfig uemSensorConfig) {
        this.uemSensorConfig = uemSensorConfig;
    }

    public double getVisittimeout() {
        return visittimeout;
    }
    @XmlAttribute(name = "visittimeout")
    public void setVisittimeout(double visittimeout) {
        this.visittimeout = visittimeout;
    }

    public String getVisitTimeOutUnit() {
        return visitTimeOutUnit;
    }
    @XmlAttribute(name = "visittimeoutunit")
    public void setVisitTimeOutUnit(String visitTimeOutUnit) {
        this.visitTimeOutUnit = visitTimeOutUnit;
    }
}
