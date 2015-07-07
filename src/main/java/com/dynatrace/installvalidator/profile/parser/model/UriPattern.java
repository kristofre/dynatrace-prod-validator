package com.dynatrace.installvalidator.profile.parser.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by kristof on 27.05.15.
 */
public class UriPattern {
    private String pattern;
    private String match;

    public String getPattern() {
        return pattern;
    }
    @XmlAttribute(name = "pattern")
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getMatch() {
        return match;
    }
    @XmlAttribute(name = "match")
    public void setMatch(String match) {
        this.match = match;
    }
}
