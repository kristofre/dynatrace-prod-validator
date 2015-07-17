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
    private ArrayList<Plugin> plugins;
    private ArrayList<MeasureType> types;

    public ArrayList<SensorDetail> getSensorDetails() {
        return sensorDetails;
    }

    @XmlElementWrapper(name = "sensors")
    @XmlElement(name = "sensor")
    public void setSensorDetails(ArrayList<SensorDetail> sensorDetails) {
        this.sensorDetails = sensorDetails;
    }

    public ArrayList<Plugin> getPlugins() {
        return plugins;
    }
    @XmlElementWrapper(name = "plugins")
    @XmlElement(name = "plugin")
    public void setPlugins(ArrayList<Plugin> plugins) {
        this.plugins = plugins;
    }

	public ArrayList<MeasureType> getTypes() {
		return types;
	}
	@XmlElementWrapper(name = "measuretypes")
    @XmlElement(name = "type")
	public void setTypes(ArrayList<MeasureType> types) {
		this.types = types;
	}
    
    
}
