package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by kristof on 15.07.15.
 */
public class IncidentSensitivity {
    private String id;
    private int duration, minNonViolatedPeriod, delay;

    public int getDelay() {
        return delay;
    }
    @XmlAttribute(name = "delay")
    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDuration() {
        return duration;
    }
    @XmlAttribute(name = "duration")
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }
    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public int getMinNonViolatedPeriod() {
        return minNonViolatedPeriod;
    }
    @XmlAttribute(name = "minnonviolatedperiod")
    public void setMinNonViolatedPeriod(int minNonViolatedPeriod) {
        this.minNonViolatedPeriod = minNonViolatedPeriod;
    }
}
