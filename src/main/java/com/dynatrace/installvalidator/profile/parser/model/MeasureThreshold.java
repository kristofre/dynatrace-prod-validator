package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by kristof on 14.07.15.
 */
public class MeasureThreshold {
    private String upperSevere;
    private String lowerSevere;
    private String upperWarning;
    private String lowerWarning;

    public String getLowerSevere() {
        return lowerSevere;
    }
    @XmlAttribute(name = "threshold.lower.severe")
    public void setLowerSevere(String lowerSevere) {
        this.lowerSevere = lowerSevere;
    }

    public String getLowerWarning() {
        return lowerWarning;
    }
    @XmlAttribute(name = "threshold.lower.warning")
    public void setLowerWarning(String lowerWarning) {
        this.lowerWarning = lowerWarning;
    }

    public String getUpperSevere() {
        return upperSevere;
    }
    @XmlAttribute(name = "threshold.upper.severe")
    public void setUpperSevere(String upperSevere) {
        this.upperSevere = upperSevere;
    }

    public String getUpperWarning() {
        return upperWarning;
    }
    @XmlAttribute(name = "threshold.upper.warning")
    public void setUpperWarning(String upperWarning) {
        this.upperWarning = upperWarning;
    }
}
