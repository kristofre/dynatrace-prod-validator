package com.dynatrace.installvalidator.profile.reporting;

import com.dynatrace.installvalidator.profile.parser.controller.*;
import com.dynatrace.installvalidator.profile.library.controller.SensorLibraryController;
import com.dynatrace.installvalidator.profile.parser.model.*;
import com.dynatrace.installvalidator.profile.parser.model.Class;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import com.dynatrace.installvalidator.profile.validator.controller.SensorConfigValidator;
import com.dynatrace.installvalidator.profile.validator.model.ValidationResult;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * Created by kristof on 30.04.15.
 */
public class ProfileReport {
    private MeasureController measureController;
    private UemConfigurationController uemConfigurationController;
    private SensorGroupController sensorGroupController;
    private ProfileController profileController;
    private SensorConfigurationController sensorConfigurationController;
    private SensorLibraryController sensorLibraryController;

    private SensorConfigValidator sensorConfigValidator;

    private SystemProfile profile;
    public ProfileReport(SystemProfile profile) {
        this.measureController = new MeasureController(profile);
        this.uemConfigurationController = new UemConfigurationController(profile);
        this.sensorGroupController = new SensorGroupController(profile);
        this.profileController = new ProfileController(profile);
        this.sensorConfigurationController = new SensorConfigurationController(profile);
        this.profile = profile;
        this.sensorConfigValidator = new SensorConfigValidator();
        this.sensorLibraryController = new SensorLibraryController(profile);
    }

    public void createReport(SystemProfile profile, String profileName, String outputFile)
    {
        String fileLocation = outputFile;
        StringBuilder html = new StringBuilder();


        html.append(generateReportHeader(profileName, profile.getDescription()));

        html.append(generateAgentGroupLists(profile.getAgentGroups()));
        html.append(generateCustomMeasuresList());
        html.append(generateRegexMeasuresList());
        html.append(generateAgentSplitMeasuresList());
        html.append(generateAppSplitMeasuresList());
        html.append(generateApplicationTable());
        html.append(generateSensorGroupList());

        html.append(generateMeasureListOfType("JdbcMeasure"));


        html.append(generateReportFooter());
        try {
            FileOutputStream out = new FileOutputStream(fileLocation);
            out.write(html.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateSensorGroupList()
    {
        StringBuilder builder = new StringBuilder();
        ArrayList<SensorGroup> sensorGroups = sensorGroupController.getSensorGroups();

        for (Iterator<SensorGroup> iterator = sensorGroups.iterator(); iterator.hasNext(); ) {
            SensorGroup next = iterator.next();

            Sensor sensor = next.getSensor();
            if(sensor!=null)
            {
                String headers[] = {"Class", "Methods"};
                builder.append(generateTableHeader(("Sensor Group : " + sensor.getDescription()), headers));
                ArrayList<Class> classes = sensor.getClasses();
                if(classes != null && classes.size()>0) {
                    for (Iterator<Class> classIterator = classes.iterator(); classIterator.hasNext(); ) {
                        Class aClass = classIterator.next();
                        if (aClass.getPlaced()) {
                            ArrayList<Method> methods = aClass.getMethods();
                            StringBuilder methodBuilder = new StringBuilder();
                            if (methods != null && methods.size() > 0) {
                                for (Iterator<Method> methodIterator = methods.iterator(); methodIterator.hasNext(); ) {
                                    Method method = methodIterator.next();
                                    if(method.getCapture().startsWith("active")) methodBuilder.append(generateTextToDisplayOnEmptyMethodRule(method.getPattern()) + " (" + method.getMatch() + ")\n");
                                }
                            }
                            StringBuilder rowBuilder = new StringBuilder();
                            rowBuilder.append(aClass.getPattern() + " (" + aClass.getMatch() + ")");
                            String rowArgs[] = {rowBuilder.toString(), methodBuilder.toString()};
                            builder.append(generateTableRow(rowArgs));
                        }
                    }
                }
                builder.append(generateTableFooter());
            }

        }

        return builder.toString();
    }

    private String generateTextToDisplayOnEmptyMethodRule(String methodPattern)
    {
        if (methodPattern.isEmpty()) return "ALL METHODS";
        return methodPattern;
    }

    private String generateAgentGroupLists(ArrayList<AgentGroup> agentGroups)
    {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        String headers[] = {"Sensor Name", "Sensor Properties"};
        for (Iterator<AgentGroup> agentGroupIterator = agentGroups.iterator(); agentGroupIterator.hasNext(); ) {
            AgentGroup agentGroup = agentGroupIterator.next();
            builder.append(generateTableHeader((agentGroup.getId()), headers));
            ArrayList<Sensor> sensors = agentGroup.getSensorPlacement().getSensors();
            for (Iterator<Sensor> sensorIterator = sensors.iterator(); sensorIterator.hasNext(); ) {
                Sensor sensor = sensorIterator.next();
                if(sensor.getPlaced())
                {
                    String sensorId = sensor.getId();

                    ArrayList<String> activeTech = profileController.getActiveTechnologies();
                    if (activeTech.contains(sensorGroupController.getSensorTech(sensorId)) && sensorGroupController.displaySensor(sensorId))
                    {
                        ArrayList<Map<String, String>> properties = sensorConfigurationController.getPropertiesForSensor(agentGroup.getId(), sensorId);
                        StringBuilder props = new StringBuilder();
                        for (Iterator<Map<String, String>> iterator = properties.iterator(); iterator.hasNext(); ) {
                            Map<String, String> next = iterator.next();
                            Iterator it = next.entrySet().iterator();
                            while (it.hasNext())
                            {
                                Map.Entry pair = (Map.Entry)it.next();
                                com.dynatrace.installvalidator.profile.library.model.SensorProperty sensorProperty = sensorLibraryController.getSensorPropertyForSensorAndProperty(sensorId, pair.getKey().toString());
                                ValidationResult res = new ValidationResult();
                                if(sensorProperty != null)
                                {
                                    res = sensorConfigValidator.validateSensorConfig(pair.getKey().toString(), pair.getValue().toString(), sensorProperty);
                                    System.out.println(res);
                                }
                                if(res.isValid()) props.append("<div>[" + StringEscapeUtils.escapeHtml(pair.getKey().toString()) + " : " + StringEscapeUtils.escapeHtml(pair.getValue().toString()) + "] </div>");
                                else props.append("<div style=\"color:red;\">[" + StringEscapeUtils.escapeHtml(pair.getKey().toString()) + " : " + StringEscapeUtils.escapeHtml(pair.getValue().toString()) + "] </div>");
                            }
                            props.append("<br>");
                        }
                        String[] rowVars = {translatePackageToSensorName(sensor.getId()), props.toString()};
                        builder.append(generateTableRowAlreadyEscapedData(rowVars));
                    }

                    /*if(profileController)
                    if(!isDotNetActivated
                            && (sensor.getId().startsWith("com.dynatrace.diagnostics.knowledgesensor.dotnet")
                                ||sensor.getId().startsWith("com.dynatrace.diagnostics.sensorgroup.memory..NET.")
                                ||sensor.getId().startsWith("com.dynatrace.diagnostics.sensorgroup.method..NET."))) {}
                    else if(!isPhpActivated && sensor.getId().startsWith("com.dynatrace.diagnostics.knowledgesensor.php")) {}*/
                    /*if(isDotNetActivated && sensorGroupController.getSensorTech(sensorId).equals(sensorGroupController.getSensorLibrary().NETTECH))
                        generateTableRow(rowVars);
                    else if(isPhpActivated && sensorGroupController.getSensorTech(sensorId).equals(sensorGroupController.getSensorLibrary().PHPTECH))
                        generateTableRow(rowVars);
                    if( && sensorGroupController.getSensorTech(sensorId).equals(sensorGroupController.getSensorLibrary().NETTECH))
                        generateTableRow(rowVars);
                    else
                    {
                        generateTableRow(rowVars);
                    }*/
                }
            }
            i++;
            builder.append(generateTableFooter());
        }

        return builder.toString();
    }

    private String translatePackageToSensorName(String packageName)
    {
        return sensorGroupController.translateSensor(packageName);
    }

    private String generateCustomMeasuresList()
    {
        StringBuilder builder = new StringBuilder();
        ArrayList<Measure> customMeasures = measureController.getCustomMeasures();
        String headers[] = {"Measure", "Measure Type"};
        builder.append(generateTableHeader(("Custom Measures (total = " + customMeasures.size() + ")"), headers));

        for (Iterator<Measure> measureIterator = customMeasures.iterator(); measureIterator.hasNext(); ) {
            Measure measure = measureIterator.next();
            String[] fields = {measure.getId(), measure.getMeasureType()};
            builder.append(generateTableRow(fields));
        }

        builder.append(generateTableFooter());

        return builder.toString();
    }

    private String generateMeasureListOfType(String type)
    {
        StringBuilder builder = new StringBuilder();
        ArrayList<Measure> customMeasures = measureController.getMeasuresOfType(true, type);
        String headers[] = {"Measure", "Measure Type"};
        builder.append(generateTableHeader((type + "Custom Measures (total = " + customMeasures.size() + ")"), headers));

        for (Iterator<Measure> measureIterator = customMeasures.iterator(); measureIterator.hasNext(); ) {
            Measure measure = measureIterator.next();
            String[] fields = {measure.getId(), measure.getMeasureType()};
            builder.append(generateTableRow(fields));
        }

        builder.append(generateTableFooter());

        return builder.toString();
    }

    private String generateRegexMeasuresList()
    {
        ArrayList<Measure> regexMeasures = measureController.getMeasuresThatUseRegexEval(true);
        return generateMeasureTable(regexMeasures, "Regex Measures");
    }

    private String generateAgentSplitMeasuresList()
    {
        ArrayList<Measure> splitMeasures = measureController.getMeasuresThatSplitByAgent(true, true);
        return generateMeasureTable(splitMeasures, "Agent Split Measures");
    }

    private String generateAppSplitMeasuresList()
    {
        ArrayList<Measure> splitMeasures = measureController.getMeasuresThatSplitByApplication(true, true);
        return generateMeasureTable(splitMeasures, "Application Split Measures");
    }

    private String generateApplicationTable()
    {
        StringBuilder builder = new StringBuilder();
        String headers[] = {"Application"};
        builder.append(generateTableHeader(("Applications"), headers));
        ArrayList<Application> applications = uemConfigurationController.getApplications();
        int i = 1;

        for (Iterator<Application> applicationIterator = applications.iterator(); applicationIterator.hasNext(); ) {
            Application application = applicationIterator.next();
            builder.append("<tr>\n<td>" + i + " : " + application.getName() + "</td></tr>");
            i++;
        }
        builder.append(generateTableFooter());
        return builder.toString();
    }


    private String generateTableHeader(String title, String[] headers)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("<b>" + title + "</b><br>");
        builder.append("<table style=\"width:100%;border-style:1px solid black\">");
        if(headers.length>0) {
            builder.append("<tr>");
            for (int i = 0; i < headers.length; i++) {
                builder.append("<th>" + headers[i] + "</th>");
            }
            builder.append("</tr>");
        }
        return builder.toString();
    }

    private String generateMeasureTable(ArrayList<Measure> measuresToList, String title)
    {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        String headers[] = {"Measure", "Measure Type"};
        builder.append(generateTableHeader((title + " (total = " + measuresToList.size() + ")"), headers));

        for (Iterator<Measure> measureIterator = measuresToList.iterator(); measureIterator.hasNext(); ) {
            Measure measure = measureIterator.next();
            String[] fields = {measure.getId(), measure.getMeasureType()};
            builder.append(generateTableRow(fields));
            i++;
        }

        builder.append(generateTableFooter());

        return builder.toString();
    }

    private String generateTableFooter()
    {
        return ("</table>");
    }

    private String generateTableRow(String[] values)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("<tr>");
        for (int i = 0; i < values.length; i++) {
            builder.append("<td>"+StringEscapeUtils.escapeHtml(values[i])+"</td>");
        }
        builder.append("</tr>\n");
        return builder.toString();
    }

    private String generateTableRowAlreadyEscapedData(String[] values)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("<tr>");
        for (int i = 0; i < values.length; i++) {
            builder.append("<td>"+(values[i])+"</td>");
        }
        builder.append("</tr>\n");
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
