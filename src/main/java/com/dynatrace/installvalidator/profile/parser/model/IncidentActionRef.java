package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by kristof on 15.07.15.
 */
public class IncidentActionRef {
    private String smartExecution, severity, type, execution, bundleVersion, roleKey, refaction, key;
    private boolean smartAlert;
    private int roleType;

    public String getBundleVersion() {
        return bundleVersion;
    }
    @XmlAttribute(name = "bundleversion")
    public void setBundleVersion(String bundleVersion) {
        this.bundleVersion = bundleVersion;
    }

    public String getExecution() {
        return execution;
    }
    @XmlAttribute(name = "execution")
    public void setExecution(String execution) {
        this.execution = execution;
    }

    public String getKey() {
        return key;
    }
    @XmlAttribute(name = "key")
    public void setKey(String key) {
        this.key = key;
    }

    public String getRefaction() {
        return refaction;
    }
    @XmlAttribute(name = "refaction")
    public void setRefaction(String refaction) {
        this.refaction = refaction;
    }

    public String getRoleKey() {
        return roleKey;
    }
    @XmlAttribute(name = "rolekey")
    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public int getRoleType() {
        return roleType;
    }
    @XmlAttribute(name = "roletype")
    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    public String getSeverity() {
        return severity;
    }
    @XmlAttribute(name = "severity")
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public boolean isSmartAlert() {
        return smartAlert;
    }
    @XmlAttribute(name = "smartalert")
    public void setSmartAlert(boolean smartAlert) {
        this.smartAlert = smartAlert;
    }

    public String getSmartExecution() {
        return smartExecution;
    }
    @XmlAttribute(name = "smartexecution")
    public void setSmartExecution(String smartExecution) {
        this.smartExecution = smartExecution;
    }

    public String getType() {
        return type;
    }
    @XmlAttribute(name = "type")
    public void setType(String type) {
        this.type = type;
    }
}
