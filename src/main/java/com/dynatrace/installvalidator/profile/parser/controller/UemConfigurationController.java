package com.dynatrace.installvalidator.profile.parser.controller;

import com.dynatrace.installvalidator.profile.parser.model.Application;
import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;

import java.util.ArrayList;

/**
 * Created by kristof on 27.05.15.
 */
public class UemConfigurationController {
    private static SystemProfile profile = null;
    public UemConfigurationController(SystemProfile profile) {
        this.profile = profile;
    }

    public ArrayList<Application> getApplications()
    {
        return profile.getUemConfiguration().getApplications();
    }
}
