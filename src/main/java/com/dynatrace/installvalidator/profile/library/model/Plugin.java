package com.dynatrace.installvalidator.profile.library.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by kristof on 15.07.15.
 */
public class Plugin {
    private String id, name;

    public String getId() {
        return id;
    }
    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }
}
