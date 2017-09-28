package com.dynatrace.installvalidator.profile.parser.controller;

import com.dynatrace.installvalidator.profile.library.model.Plugin;
import com.dynatrace.installvalidator.profile.parser.model.IncidentRule;
import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kristof on 15.07.15.
 */
public class IncidentRuleController extends BaseController {
    public IncidentRuleController(SystemProfile profile, String configFile) {
        super(profile, configFile);
    }

    public ArrayList<IncidentRule> getIncidentRules()
    {
        return this.getProfile().getIncidentRules();
    }

    public ArrayList<IncidentRule> getActiveIncidentRules()
    {
        ArrayList<IncidentRule> activeIncidentRules = new ArrayList<IncidentRule>();
        ArrayList<IncidentRule> allIncidentRules = this.getProfile().getIncidentRules();
        for (Iterator<IncidentRule> incidentRuleIterator = allIncidentRules.iterator(); incidentRuleIterator.hasNext(); ) {
            IncidentRule incidentRule = incidentRuleIterator.next();
            if(incidentRule.getFlags()!=0) activeIncidentRules.add(incidentRule);
        }
        return activeIncidentRules;
    }

    public String translatePlugin(String pluginId)
    {
        ArrayList<Plugin> plugins = getSensorLibrary().getPlugins();
        for (Iterator<Plugin> iterator = plugins.iterator(); iterator.hasNext(); ) {
            Plugin plugin = iterator.next();
            if(plugin.getId().equals(pluginId)) return plugin.getName();
        }

        return pluginId;
    }
}
