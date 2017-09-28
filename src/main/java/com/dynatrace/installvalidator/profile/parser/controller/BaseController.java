package com.dynatrace.installvalidator.profile.parser.controller;

import com.dynatrace.installvalidator.profile.library.SensorLibrary;
import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;

/**
 * Created by kristof on 27.05.15.
 */
public class BaseController {
    private static SystemProfile profile = null;
    protected SensorLibrary sensorLibrary;
    protected String configFile;

    public BaseController(SystemProfile profile, String configFile) {
        this.profile = profile;
        this.configFile = configFile;
        this.sensorLibrary = getSensorLibrary();
    }

    public static SystemProfile getProfile() {
        return profile;
    }

    public static void setProfile(SystemProfile profile) {
        BaseController.profile = profile;
    }
    
    public SensorLibrary getSensorLibrary()
    {
        sensorLibrary = SensorLibrary.getInstance(configFile);
        return sensorLibrary;
    }

}
