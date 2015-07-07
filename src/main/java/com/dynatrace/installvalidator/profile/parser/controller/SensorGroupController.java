package com.dynatrace.installvalidator.profile.parser.controller;

import com.dynatrace.installvalidator.profile.library.SensorLibrary;
import com.dynatrace.installvalidator.profile.library.model.SensorDetail;
import com.dynatrace.installvalidator.profile.parser.model.SensorGroup;
import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by kristof on 27.05.15.
 */
public class SensorGroupController extends BaseController{
    public SensorGroupController(SystemProfile profile) {
        super(profile);
    }

    public HashMap<String, String> getCustomSensorGroupsAsMap()
    {
        HashMap<String, String> map = new HashMap<String, String>();
        ArrayList<SensorGroup> groups = getProfile().getSensorGroups();
        for (Iterator<SensorGroup> iterator = groups.iterator(); iterator.hasNext(); ) {
            SensorGroup next = iterator.next();
            if(next.getSensor()!=null) {
                map.put(next.getSensor().getKey(), next.getSensor().getDescription());
            }
        }

        return  map;
    }

    public ArrayList<SensorGroup> getSensorGroups()
    {
        return getProfile().getSensorGroups();
    }

    public String translateSensor(String fullSensorPackageName)
    {
        String translatedName = translateStandardSensor(fullSensorPackageName);
        if(fullSensorPackageName.equals(translatedName))
            return translateCustomSensor(translatedName);
        return translatedName;
    }

    private String translateCustomSensor(String fullSensorPackageName)
    {
        HashMap<String, String> sensorGroups = getCustomSensorGroupsAsMap();
        if(sensorGroups.containsKey(fullSensorPackageName)) return sensorGroups.get(fullSensorPackageName);
        return fullSensorPackageName;
    }

    public String translateStandardSensor(String fullSensorPackageName)
    {
        SensorLibrary library = this.getSensorLibrary();
        ArrayList<SensorDetail> sensorDetails = library.getSensorDetails();
        for (Iterator<SensorDetail> iterator = sensorDetails.iterator(); iterator.hasNext(); ) {
            SensorDetail next = iterator.next();
            if (fullSensorPackageName.equals(next.getId())) return next.getName();
        }
        return fullSensorPackageName;
    }

    public String getSensorTech(String fullSensorName)
    {
        String sensorType = getStandardSensorTech(fullSensorName);
        if(!sensorType.equals("NAN")) return sensorType;
        sensorType = getCustomSensorTech(fullSensorName);
        if(!sensorType.equals("NAN")) return sensorType;

        return "NAN";
    }

    public String getStandardSensorTech(String fullSensorName)
    {
        SensorLibrary library = this.getSensorLibrary();
        ArrayList<SensorDetail> sensorDetails = library.getSensorDetails();
        for (Iterator<SensorDetail> iterator = sensorDetails.iterator(); iterator.hasNext(); ) {
            SensorDetail next = iterator.next();
            if (next.getId().equals(fullSensorName)) return  next.getTech();
        }

        return "NAN";
    }

    public String getCustomSensorTech(String fullSensorName)
    {
        ArrayList<SensorGroup> customGroups = getProfile().getSensorGroups();
        for (Iterator<SensorGroup> iterator = customGroups.iterator(); iterator.hasNext(); ) {
            SensorGroup next = iterator.next();
            if(next.getSensor()!=null && next.getSensor().getKey().equals(fullSensorName)) {
                return next.getSensor().getType();
            }
        }
        return "NAN";
    }

    public boolean displaySensor(String fullSensorName)
    {
        SensorLibrary library = this.getSensorLibrary();
        ArrayList<SensorDetail> sensorDetails = library.getSensorDetails();
        for (Iterator<SensorDetail> iterator = sensorDetails.iterator(); iterator.hasNext(); ) {
            SensorDetail next = iterator.next();
            if(next.getId().equals(fullSensorName)) {
                boolean defaultHide = next.getDefaultHide()==null?false:next.getDefaultHide();
                return !defaultHide;
            }
        }
        return true;
    }
}
