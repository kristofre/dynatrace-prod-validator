package com.dynatrace.installvalidator.profile.parser.controller;

import com.dynatrace.installvalidator.profile.library.SensorLibrary;
import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;

/**
 * Created by kristof on 27.05.15.
 */
public class BaseController {
    private static SystemProfile profile = null;
    private SensorLibrary sensorLibrary;

    public BaseController(SystemProfile profile) {
        this.profile = profile;
        sensorLibrary = getSensorLibrary();
    }

    public static SystemProfile getProfile() {
        return profile;
    }

    public static void setProfile(SystemProfile profile) {
        BaseController.profile = profile;
    }

    public SensorLibrary getSensorLibrary()
    {
        sensorLibrary = SensorLibrary.getInstance();
        return sensorLibrary;
    }

}
