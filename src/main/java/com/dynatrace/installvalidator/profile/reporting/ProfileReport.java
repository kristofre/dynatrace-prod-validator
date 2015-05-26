package com.dynatrace.installvalidator.profile.reporting;

import com.dynatrace.installvalidator.profile.controller.MeasureController;
import com.dynatrace.installvalidator.profile.parser.model.AgentGroup;
import com.dynatrace.installvalidator.profile.parser.model.Measure;
import com.dynatrace.installvalidator.profile.parser.model.Sensor;
import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kristof on 30.04.15.
 */
public class ProfileReport {
    private MeasureController measureController;
    public ProfileReport(SystemProfile profile) {
        this.measureController = new MeasureController(profile);
    }

    public void createReport(SystemProfile profile, String profileName)
    {
        String fileLocation = "/home/kristof/Documents/profileReport.htm";
        StringBuilder html = new StringBuilder();


        html.append(generateReportHeader(profileName, profile.getDescription()));

        //html.append(generateAgentGroupLists(profile.getAgentGroups()));
        html.append(generateCustomMeasuresList());
        html.append(generateRegexMeasuresList());

        html.append(generateReportFooter());
        try {
            FileOutputStream out = new FileOutputStream(fileLocation);
            out.write(html.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateAgentGroupLists(ArrayList<AgentGroup> agentGroups)
    {
        StringBuilder builder = new StringBuilder();
        for (Iterator<AgentGroup> agentGroupIterator = agentGroups.iterator(); agentGroupIterator.hasNext(); ) {
            AgentGroup agentGroup = agentGroupIterator.next();
            builder.append("<b>" + agentGroup.getId() + "</b>\n");
            builder.append("<table style=\"width:100%\">");
            ArrayList<Sensor> sensors = agentGroup.getSensorPlacement().getSensors();
            for (Iterator<Sensor> sensorIterator = sensors.iterator(); sensorIterator.hasNext(); ) {
                Sensor sensor = sensorIterator.next();
                if(sensor.getPlaced())
                {
                    builder.append("<tr>\n<td>" + sensor.getId() + "</td>");
                }
            }
        }
        builder.append("</table>");
        return builder.toString();
    }

    private String generateCustomMeasuresList()
    {
        StringBuilder builder = new StringBuilder();
        ArrayList<Measure> customMeasures = measureController.getCustomMeasures();

        builder.append("<b> Custom Measures </b>\n");
        builder.append("<table style=\"width:100%;border-style:1px solid black\">");

        for (Iterator<Measure> measureIterator = customMeasures.iterator(); measureIterator.hasNext(); ) {
            Measure measure = measureIterator.next();
            builder.append("<tr>\n<td>" + measure.getId() + "</td></tr>");
        }

        builder.append("</table>");

        return builder.toString();
    }

    private String generateRegexMeasuresList()
    {
        StringBuilder builder = new StringBuilder();
        ArrayList<Measure> regexMeasures = measureController.getMeasuresThatUseRegexEval();

        builder.append("<b> Regex Measures </b>\n");
        builder.append("<table style=\"width:100%;border-style:1px solid black\">");

        for (Iterator<Measure> measureIterator = regexMeasures.iterator(); measureIterator.hasNext(); ) {
            Measure measure = measureIterator.next();
            builder.append("<tr>\n<td>" + measure.getId() + "</td></tr>");
        }

        builder.append("</table>");

        return builder.toString();
    }

    private String generateReportHeader(String profileName, String profileDescription)
    {
        String returnVal = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n";
        returnVal += "<h1>" + profileName + "</h1>";
        return returnVal;
    }

    private String generateReportFooter()
    {
        String returnVal = "</body>\n" +
                "</html>";

        return returnVal;
    }
}
