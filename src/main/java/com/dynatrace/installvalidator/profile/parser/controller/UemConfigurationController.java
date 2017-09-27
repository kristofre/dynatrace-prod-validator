package com.dynatrace.installvalidator.profile.parser.controller;

//import com.dynatrace.installvalidator.App;
import com.dynatrace.installvalidator.profile.parser.model.*;

import java.util.ArrayList;

/**
 * Created by kristof on 27.05.15.
 */
public class UemConfigurationController {
    private static SystemProfile profile = null;
    private static final String UEMMODE_CAPTURE_SERVER_AND_ENDUSER_SIDE = "CAPTURE_SERVER_AND_ENDUSER_SIDE";
    private static final String UEMMODE_CAPTURE_SERVER_SIDE = "CAPTURE_SERVER_SIDE";
    private static final String UEMMODE_CAPTURE_OFF = "CAPTURE_OFF";
    public UemConfigurationController(SystemProfile profile) {
        this.profile = profile;
    }

    public ArrayList<Application> getApplications()
    {
        return profile.getUemConfiguration().getApplications();
    }

    public UemApplicationConfig getDefaultUemConfig()
    {
        return profile.getUemConfiguration().getDefaultUemAppConfig();
    }

    public boolean isUemEnabledForApplication(Application application)
    {
        try {
            UemSensorConfig uemSensorConfig = application.getUemApplicationConfig().getUemSensorConfig();
            if(uemSensorConfig.getUemMode().equals(UEMMODE_CAPTURE_SERVER_AND_ENDUSER_SIDE)) return true;
        } catch (NullPointerException ex) {
            UemSensorConfig defaultConfig = profile.getUemConfiguration().getDefaultUemAppConfig().getUemSensorConfig();
            if(defaultConfig.getUemMode().equals(UEMMODE_CAPTURE_SERVER_AND_ENDUSER_SIDE)) return true;
        }
        return false;
    }

    public String getUemModeForApplication(Application application)
    {
        try {
            UemSensorConfig uemSensorConfig = application.getUemApplicationConfig().getUemSensorConfig();
            if(uemSensorConfig.getUemMode().equals(UEMMODE_CAPTURE_SERVER_AND_ENDUSER_SIDE)) return "UEM";
            else if (uemSensorConfig.getUemMode().equals(UEMMODE_CAPTURE_SERVER_SIDE)) return "Server-Side Only";
            else if (uemSensorConfig.getUemMode().equals(UEMMODE_CAPTURE_OFF)) return "No Data";
        } catch (NullPointerException ex) {
            UemSensorConfig defaultConfig = profile.getUemConfiguration().getDefaultUemAppConfig().getUemSensorConfig();
            if(defaultConfig.getUemMode().equals(UEMMODE_CAPTURE_SERVER_AND_ENDUSER_SIDE)) return "UEM (from default)";
            else if (defaultConfig.getUemMode().equals(UEMMODE_CAPTURE_SERVER_SIDE)) return "Server-Side Only (from default)";
            else if (defaultConfig.getUemMode().equals(UEMMODE_CAPTURE_OFF)) return "No Data (from default)";
        }
        return "NAN";
    }

    /*public UemApplicationConfig getFinalUemApplicationConfigForApplication(Application application)
    {
        try {
            UemSensorConfig uemSensorConfig = application.getUemApplicationConfig().getUemSensorConfig();
            return uemSensorConfig;
        } catch (NullPointerException ex) {
            UemSensorConfig defaultConfig = profile.getUemConfiguration().getDefaultUemAppConfig().getUemSensorConfig();
            if(defaultConfig.getUemMode().equals(UEMMODE_CAPTURE_SERVER_AND_ENDUSER_SIDE)) return true;
        }
    }*/

    public int getBandwidthCalculationTimeout(Application application)
    {
        try {
            UemJsAgentOption option = application.getUemApplicationConfig().getUemSensorConfig().getUemJsAgentOption();
            String agentOption = option.getAgentOption();
            if(agentOption.contains("bandwidth")){
                String bw = agentOption.substring(agentOption.indexOf("bandwidth="), agentOption.contains("_m")?agentOption.indexOf("_m"):agentOption.length());
                return Integer.parseInt(bw);
            }
        } catch (NullPointerException e) {
            return 0;
        }
        return 0;
    }
}
