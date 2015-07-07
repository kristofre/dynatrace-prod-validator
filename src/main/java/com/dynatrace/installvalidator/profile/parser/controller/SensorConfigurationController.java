package com.dynatrace.installvalidator.profile.parser.controller;

import com.dynatrace.installvalidator.profile.parser.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by kristof on 01.06.15.
 */
public class SensorConfigurationController extends BaseController {
    public SensorConfigurationController(SystemProfile profile) {
        super(profile);
    }

    public ArrayList<Map<String, String>> getPropertiesForSensor(String agentGroup, String fullSensorName)
    {
        Configuration activeConfiguration = getActiveConfiguration();
        if(activeConfiguration.getSensorConfigurations()!=null) {
            ArrayList<SensorConfig> sensorConfigs = activeConfiguration.getSensorConfigurations();
            for (Iterator<SensorConfig> iterator = sensorConfigs.iterator(); iterator.hasNext(); ) {
                SensorConfig next = iterator.next();
                if(next.getRefAgentGroup().equals(agentGroup))
                {
                    ArrayList<AgentSensorConfig> configs = next.getAgentSensorConfigs();
                    for (Iterator<AgentSensorConfig> agentSensorConfigIterator = configs.iterator(); agentSensorConfigIterator.hasNext(); ) {
                        AgentSensorConfig agentSensorConfig = agentSensorConfigIterator.next();
                        if(agentSensorConfig.getId().equals(fullSensorName))
                        {
                            ArrayList<Map<String, String>> props = new ArrayList<Map<String, String>>();
                            ArrayList<SensorProperty> sensorProperties = agentSensorConfig.getProperties();
                            if (sensorProperties != null) {
                                for (Iterator<SensorProperty> sensorPropertyIterator = sensorProperties.iterator(); sensorPropertyIterator.hasNext(); ) {
                                    SensorProperty sensorProperty = sensorPropertyIterator.next();
                                    props.add(sensorProperty.getAttributes());
                                }
                                return props;
                            }
                        }
                    }
                }
            }
        }
        return new ArrayList<Map<String, String>>();
    }

    public Configuration getActiveConfiguration()
    {
        ArrayList<Configuration> configurations = getProfile().getConfigurations();
        for (Iterator<Configuration> iterator = configurations.iterator(); iterator.hasNext(); ) {
            Configuration next = iterator.next();
            if(next.getActive()) return next;
        }
        return new Configuration();
    }
}
