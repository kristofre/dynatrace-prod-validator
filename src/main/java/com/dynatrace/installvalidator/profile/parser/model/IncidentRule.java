package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * Created by kristof on 15.07.15.
 */
public class IncidentRule {
    private String id, incidentDashboardName, description;
    private int flags, timeframe;
    private boolean visible;
    private ArrayList<IncidentCondition> conditions;
    private IncidentSensitivity sensitivity;
    private ArrayList<IncidentActionRef> actions;

    public ArrayList<IncidentActionRef> getActions() {
        return actions;
    }
    @XmlElementWrapper(name = "actions")
    @XmlElement(name = "actionref")
    public void setActions(ArrayList<IncidentActionRef> actions) {
        this.actions = actions;
    }

    public ArrayList<IncidentCondition> getConditions() {
        return conditions;
    }
    @XmlElementWrapper(name = "conditions")
    @XmlElement(name = "condition")
    public void setConditions(ArrayList<IncidentCondition> conditions) {
        this.conditions = conditions;
    }

    public String getDescription() {
        return description;
    }
    @XmlAttribute(name = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    public int getFlags() {
        return flags;
    }
    @XmlAttribute(name = "flags")
    public void setFlags(int flags) {
        this.flags = flags;
    }

    public String getId() {
        return id;
    }
    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public String getIncidentDashboardName() {
        return incidentDashboardName;
    }
    @XmlAttribute(name = "incidentdashboardname")
    public void setIncidentDashboardName(String incidentDashboardName) {
        this.incidentDashboardName = incidentDashboardName;
    }

    public IncidentSensitivity getSensitivity() {
        return sensitivity;
    }
    @XmlElement(name = "sensitivity")
    public void setSensitivity(IncidentSensitivity sensitivity) {
        this.sensitivity = sensitivity;
    }

    public int getTimeframe() {
        return timeframe;
    }
    @XmlAttribute(name = "timeframe")
    public void setTimeframe(int timeframe) {
        this.timeframe = timeframe;
    }

    public boolean isVisible() {
        return visible;
    }
    @XmlAttribute(name = "visible")
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
