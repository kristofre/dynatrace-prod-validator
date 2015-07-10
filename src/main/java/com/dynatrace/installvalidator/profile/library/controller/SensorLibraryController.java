package com.dynatrace.installvalidator.profile.library.controller;

import com.dynatrace.installvalidator.profile.parser.controller.BaseController;
import com.dynatrace.installvalidator.profile.library.SensorLibrary;
import com.dynatrace.installvalidator.profile.library.model.SensorDetail;
import com.dynatrace.installvalidator.profile.library.model.SensorValidationProperty;
import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kristof on 25.06.15.
 */
public class SensorLibraryController extends BaseController{
    private SensorLibrary sensorLibrary;

    public SensorLibraryController(SystemProfile profile) {
        super(profile);
        this.sensorLibrary = getSensorLibrary();
    }

    public SensorValidationProperty getSensorPropertyForSensorAndProperty(String sensor, String property){
        ArrayList<SensorDetail> sensorDetail = sensorLibrary.getSensorDetails();
        for (Iterator<SensorDetail> iterator = sensorDetail.iterator(); iterator.hasNext(); ) {
            SensorDetail next = iterator.next();
            if(next.getId().equals(sensor))
            {
                ArrayList<SensorValidationProperty> properties = next.getSensorProperties();
                if(properties != null)
                {
                    for (Iterator<SensorValidationProperty> sensorPropertyIterator = properties.iterator(); sensorPropertyIterator.hasNext(); ) {
                        SensorValidationProperty sensorValidationProperty = sensorPropertyIterator.next();
                        if(sensorValidationProperty.getName().equals(property)) return sensorValidationProperty;
                    }
                }
            }
        }
        return null;
    }
    public ArrayList<SensorValidationProperty> getSensorPropertiesForSensorAndProperty(String sensor, String property){
        ArrayList<SensorDetail> sensorDetail = sensorLibrary.getSensorDetails();
        ArrayList<SensorValidationProperty> sensorProperties = new ArrayList<SensorValidationProperty>();
        for (Iterator<SensorDetail> iterator = sensorDetail.iterator(); iterator.hasNext(); ) {
            SensorDetail next = iterator.next();
            if(next.getId().equals(sensor))
            {
                ArrayList<SensorValidationProperty> properties = next.getSensorProperties();
                if(properties != null)
                {
                    for (Iterator<SensorValidationProperty> sensorPropertyIterator = properties.iterator(); sensorPropertyIterator.hasNext(); ) {
                        SensorValidationProperty sensorValidationProperty = sensorPropertyIterator.next();
                        if(sensorValidationProperty.getName().equals(property)) sensorProperties.add(sensorValidationProperty);
                    }
                }
            }
        }
        return sensorProperties;
    }

    public ArrayList<SensorValidationProperty> getSensorPropertiesForSensor(String sensor)
    {
        ArrayList<SensorDetail> sensorDetail = sensorLibrary.getSensorDetails();

        for (Iterator<SensorDetail> iterator = sensorDetail.iterator(); iterator.hasNext(); ) {
            SensorDetail next = iterator.next();
            if(next.getId().equals(sensor))
            {
                ArrayList<SensorValidationProperty> properties = next.getSensorProperties();
                if (properties!=null)
                    return properties;
            }
        }
        return new ArrayList<SensorValidationProperty>();
    }
}
