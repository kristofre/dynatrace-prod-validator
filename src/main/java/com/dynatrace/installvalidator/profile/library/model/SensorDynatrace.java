package com.dynatrace.installvalidator.profile.library.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by kristof on 29.05.15.
 */
@XmlRootElement(name="dynatrace")
public class SensorDynatrace {
    public SensorDynatrace() {
    }

    private ArrayList<SensorDetail> sensorDetails;

    public ArrayList<SensorDetail> getSensorDetails() {
        return sensorDetails;
    }
    // XmLElementWrapper generates a wrapper element around XML representation
    @XmlElementWrapper(name = "sensors")
    // XmlElement sets the name of the entities in collection
    @XmlElement(name = "sensor")
    public void setSensorDetails(ArrayList<SensorDetail> sensorDetails) {
        this.sensorDetails = sensorDetails;
    }
}
