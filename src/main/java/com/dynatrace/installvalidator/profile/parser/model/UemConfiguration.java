package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * Created by kristof on 27.05.15.
 */
public class UemConfiguration {
    private ArrayList<Application> applications;
    private UemApplicationConfig defaultUemAppConfig;

    public ArrayList<Application> getApplications() {
        return applications;
    }
    @XmlElementWrapper(name = "applications")
    @XmlElement(name = "application")
    public void setApplications(ArrayList<Application> applications) {
        this.applications = applications;
    }

    public UemApplicationConfig getDefaultUemAppConfig() {
        return defaultUemAppConfig;
    }
    @XmlElement(name = "uemappconfig")
    public void setDefaultUemAppConfig(UemApplicationConfig defaultUemAppConfig) {
        this.defaultUemAppConfig = defaultUemAppConfig;
    }
}
