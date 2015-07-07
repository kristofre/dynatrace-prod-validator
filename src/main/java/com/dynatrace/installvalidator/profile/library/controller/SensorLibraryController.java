package com.dynatrace.installvalidator.profile.library.controller;

import com.dynatrace.installvalidator.profile.parser.controller.BaseController;
import com.dynatrace.installvalidator.profile.library.SensorLibrary;
import com.dynatrace.installvalidator.profile.library.model.SensorDetail;
import com.dynatrace.installvalidator.profile.library.model.SensorProperty;
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

    public SensorProperty getSensorPropertyForSensorAndProperty(String sensor, String property){
        ArrayList<SensorDetail> sensorDetail = sensorLibrary.getSensorDetails();
        for (Iterator<SensorDetail> iterator = sensorDetail.iterator(); iterator.hasNext(); ) {
            SensorDetail next = iterator.next();
            if(next.getId().equals(sensor))
            {
                ArrayList<SensorProperty> properties = next.getSensorProperties();
                if(properties != null)
                {
                    for (Iterator<SensorProperty> sensorPropertyIterator = properties.iterator(); sensorPropertyIterator.hasNext(); ) {
                        SensorProperty sensorProperty = sensorPropertyIterator.next();
                        if(sensorProperty.getName().equals(property)) return sensorProperty;
                    }
                }
            }
        }
        return null;
    }
}
