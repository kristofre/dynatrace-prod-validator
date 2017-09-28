package com.dynatrace.installvalidator.profile.reporting.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

import com.dynatrace.installvalidator.profile.library.SensorLibrary;
import com.dynatrace.installvalidator.profile.library.controller.SensorLibraryController;
import com.dynatrace.installvalidator.profile.library.model.SensorValidationProperty;
import com.dynatrace.installvalidator.profile.parser.controller.BusinessTransactionController;
import com.dynatrace.installvalidator.profile.parser.controller.IncidentRuleController;
import com.dynatrace.installvalidator.profile.parser.controller.MeasureController;
import com.dynatrace.installvalidator.profile.parser.controller.ProfileController;
import com.dynatrace.installvalidator.profile.parser.controller.SensorConfigurationController;
import com.dynatrace.installvalidator.profile.parser.controller.SensorGroupController;
import com.dynatrace.installvalidator.profile.parser.controller.UemConfigurationController;
import com.dynatrace.installvalidator.profile.parser.model.AgentGroup;
import com.dynatrace.installvalidator.profile.parser.model.Application;
import com.dynatrace.installvalidator.profile.parser.model.BusinessTransaction;
import com.dynatrace.installvalidator.profile.parser.model.Class;
import com.dynatrace.installvalidator.profile.parser.model.IncidentActionRef;
import com.dynatrace.installvalidator.profile.parser.model.IncidentCondition;
import com.dynatrace.installvalidator.profile.parser.model.IncidentRule;
import com.dynatrace.installvalidator.profile.parser.model.Measure;
import com.dynatrace.installvalidator.profile.parser.model.MeasureReference;
import com.dynatrace.installvalidator.profile.parser.model.MeasureThreshold;
import com.dynatrace.installvalidator.profile.parser.model.Method;
import com.dynatrace.installvalidator.profile.parser.model.Sensor;
import com.dynatrace.installvalidator.profile.parser.model.SensorGroup;
import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;
import com.dynatrace.installvalidator.profile.parser.model.UemApplicationConfig;
import com.dynatrace.installvalidator.profile.parser.model.UemJsAgentOption;
import com.dynatrace.installvalidator.profile.parser.model.UemModule;
import com.dynatrace.installvalidator.profile.parser.model.UriPattern;
import com.dynatrace.installvalidator.profile.reporting.model.CssClass;
import com.dynatrace.installvalidator.profile.reporting.model.HtmlHelper;
import com.dynatrace.installvalidator.profile.reporting.reader.HtmlCssFileReader;
import com.dynatrace.installvalidator.profile.reporting.writer.HtmlReportWriter;
import com.dynatrace.installvalidator.profile.validator.controller.SensorConfigValidator;
import com.dynatrace.installvalidator.profile.validator.model.ValidationResult;

/**
 * Created by kristof on 30.04.15.
 */
public class HtmlProfileReport {
    private MeasureController measureController;
    private UemConfigurationController uemConfigurationController;
    private SensorGroupController sensorGroupController;
    private ProfileController profileController;
    private SensorConfigurationController sensorConfigurationController;
    private SensorLibraryController sensorLibraryController;
    private BusinessTransactionController businessTransactionController;
    private IncidentRuleController incidentRuleController;
    private HtmlCssFileReader htmlCssFileReader;
    private HtmlHelper htmlHelper;

    private SensorConfigValidator sensorConfigValidator;

    private SystemProfile profile;
    private String profileName;
    private long modDate;
    private String dTVersion;
    public HtmlProfileReport(SystemProfile profile, String profileName, long modDate, String dTVersion, String configFile) {
    	this.sensorLibraryController = new SensorLibraryController(profile, configFile);
    	this.measureController = new MeasureController(profile, configFile);
        this.uemConfigurationController = new UemConfigurationController(profile);
        this.sensorGroupController = new SensorGroupController(profile, configFile);
        this.profileController = new ProfileController(profile, configFile);
        this.sensorConfigurationController = new SensorConfigurationController(profile, configFile);
        this.profile = profile;
        this.profileName = profileName;
        this.modDate = modDate;
        this.dTVersion = dTVersion;
        this.sensorConfigValidator = new SensorConfigValidator();
        this.businessTransactionController = new BusinessTransactionController(profile, configFile);
        this.incidentRuleController = new IncidentRuleController(profile, configFile);
        this.htmlHelper = new HtmlHelper();
        this.htmlCssFileReader = new HtmlCssFileReader();
    }

    public void createReport(String outputFile)
    {
        StringBuilder html = new StringBuilder();
        String css = "";
        try {
            css = htmlCssFileReader.getStylesheet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //SystemProfile profile = dynatrace.getSystemProfile();
        html.append(htmlHelper.generateReportHeader(profileName, profile.getDescription(), css, dTVersion, modDate));

        ArrayList<String> sectionTitles = new ArrayList<String>();
        String sectionApplications = "Applications";
        String sectionAgentGroups = "Agent Groups";
        String sectionBusinessTransactions = "User Defined Business Transactions";
        String sectionMeasures = "User Defined Measures";
        String sectionRegexMeasures = "User Defined Measures That Use Regular Expressions";
        String sectionAgentSplitMeasures = "User Defined Measures That Split By Agent";
        String sectionApplicationSplitMeasures = "User Defined Measures That Split By Application";
        String sectionSensorGroups = "Sensor Groups";
        String sectionIncidents = "Incidents";
        sectionTitles.add(sectionApplications);
        sectionTitles.add(sectionAgentGroups);
        sectionTitles.add(sectionSensorGroups);
        sectionTitles.add(sectionBusinessTransactions);
        sectionTitles.add(sectionMeasures);
        sectionTitles.add(sectionIncidents);
        sectionTitles.add(sectionRegexMeasures);
        sectionTitles.add(sectionAgentSplitMeasures);
        sectionTitles.add(sectionApplicationSplitMeasures);


        html.append(generateSectionList(sectionTitles));

        html.append(generateApplicationTable(sectionApplications));
        html.append(generateAgentGroupLists(sectionAgentGroups));
        html.append(generateSensorGroupList(sectionSensorGroups));
        html.append(generateBusinessTransactionList(sectionBusinessTransactions));
        html.append(generateCustomMeasuresList(sectionMeasures));
        html.append(generateIncidentTable(sectionIncidents));
        html.append(generateRegexMeasuresList(sectionRegexMeasures));
        html.append(generateAgentSplitMeasuresList(sectionAgentSplitMeasures));
        html.append(generateAppSplitMeasuresList(sectionApplicationSplitMeasures));

        html.append(generateMeasureListOfType("JdbcMeasure"));

        HtmlReportWriter writer = new HtmlReportWriter();
        writer.writeHtmlReportToFile(html, outputFile);
        html.append(htmlHelper.generateReportFooter());

    }

    private String generateSectionList(ArrayList<String> sectionTitles)
    {
        StringBuilder builder = new StringBuilder();

        for (Iterator<String> iterator = sectionTitles.iterator(); iterator.hasNext(); ) {
            String next = iterator.next();
            builder.append("<a href=#" + StringEscapeUtils.escapeHtml4(next).replace(" ", "_") + ">" + next + "</a></br>");
        }
        return builder.toString();
    }

    private String generateSensorGroupList(String sectionTitle)
    {
        StringBuilder builder = new StringBuilder();
        ArrayList<SensorGroup> sensorGroups = sensorGroupController.getSensorGroups();
        builder.append(htmlHelper.generateSectionHeader(sectionTitle));

        for (Iterator<SensorGroup> iterator = sensorGroups.iterator(); iterator.hasNext(); ) {
            SensorGroup next = iterator.next();

            Sensor sensor = next.getSensor();
            if(sensor!=null)
            {
                String headers[] = {"Class", "Methods"};
                builder.append(htmlHelper.generateTableHeader(("Sensor Group : " + sensor.getDescription() + " (" + sensor.getType() + " / " + sensor.getGroup() + ")"), headers));
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
                                    if(method.getCapture().toLowerCase().equals("active"))
                                        methodBuilder.append(htmlHelper.generateDiv("[" + method.getMatch() + "] " + generateTextToDisplayOnEmptyMethodRule(method.getPattern())));
                                    if(method.getCapture().toLowerCase().equals("startpp"))
                                        methodBuilder.append(htmlHelper.generateDiv("[" + method.getMatch() + "] " + generateTextToDisplayOnEmptyMethodRule(method.getPattern()) + "[Entry Point]"));
                                }
                            }
                            StringBuilder rowBuilder = new StringBuilder();
                            rowBuilder.append(htmlHelper.generateDiv("[" + aClass.getMatch() + "] " + generateTextToDisplayOnEmptyMethodRule(aClass.getPattern()) + ""));
                            String rowArgs[] = {rowBuilder.toString(), methodBuilder.toString()};
                            builder.append(htmlHelper.generateTableRowAlreadyEscapedData(rowArgs));
                        }
                    }
                }
                builder.append(htmlHelper.generateTableFooter());
            }

        }

        return builder.toString();
    }

    private String generateTextToDisplayOnEmptyMethodRule(String methodPattern)
    {
        if (methodPattern.isEmpty()) return "<all methods>(*)";
        return methodPattern;
    }

    private String generateBusinessTransactionList(String sectionTitle)
    {
        ArrayList<BusinessTransaction> customBusinessTransactions = businessTransactionController.getUserDefinedBusinessTransactions();
        StringBuilder builder = new StringBuilder();
        builder.append(htmlHelper.generateSectionHeader(sectionTitle));
        String headers[] = {"Act.", "Business Transaction", "PW", "Exp.", "Baseline", "Scope", "Filters", "Results", "Splittings"};
        builder.append(htmlHelper.generateTableHeader("", headers));
        for (Iterator<BusinessTransaction> businessTransactionIterator = customBusinessTransactions.iterator(); businessTransactionIterator.hasNext(); ) {
            BusinessTransaction transaction = businessTransactionIterator.next();
            String filters = "<ul>";
            String results = "<ul>";
            String splits = "<ul>";
            ArrayList<MeasureReference> filterArrayList = transaction.getFilters();
            ArrayList<MeasureReference> resultArrayList = transaction.getResults();
            ArrayList<MeasureReference> splitArrayList = transaction.getSplittings();
            for (Iterator<MeasureReference> referenceIterator = filterArrayList.iterator(); referenceIterator.hasNext(); ) {
                MeasureReference filterRef = referenceIterator.next();
                filters += htmlHelper.generateListItem(filterRef.getRefMeasure() + (referenceIterator.hasNext()?" [" + filterRef.getLogicalOperator()+"]":""));

            }
            if(transaction.isErrorDetectionSupport()) results += htmlHelper.generateListItem("Failure Rates");
            for (Iterator<MeasureReference> iterator = resultArrayList.iterator(); iterator.hasNext(); ) {
                MeasureReference resultRef = iterator.next();
                results += htmlHelper.generateListItem(resultRef.getRefMeasure());
            }
            ArrayList<Measure> values = transaction.getValues();
            boolean isApplicationAggregated = false;
            boolean isAgentAggregated = false;
            boolean isBaselined = false;
            for (Iterator<Measure> iterator = values.iterator(); iterator.hasNext(); ) {
                Measure next = iterator.next();
                if(!next.getIsApplicationAggregated()) isApplicationAggregated = true;
                if(!next.getIsAggregated()) isAgentAggregated = true;
                if(next.getCalculateBaseline()) isBaselined = true;
            }
            if(isApplicationAggregated) splits += htmlHelper.generateListItem("Application");
            if(isAgentAggregated) splits += htmlHelper.generateListItem("Agent");
            for (Iterator<MeasureReference> iterator = splitArrayList.iterator(); iterator.hasNext(); ) {
                MeasureReference splitRef = iterator.next();
                splits += htmlHelper.generateListItem(splitRef.getRefMeasure());
            }
            filters += "</ul>";
            results += "</ul>";
            splits += "</ul>";
            String pw = transaction.isAggregateGroups()?"Yes":"No";
            String enabled = transaction.isEnabled()?"Yes":"No";
            String export = transaction.isExport()?"Yes":"No";
            String baseline = isBaselined?"Yes":"No";
            String[] rowVars = {enabled, transaction.getId(), pw, export, baseline, businessTransactionController.translateServiceContext(transaction.getServicecontext()), filters, results, splits};
            builder.append(htmlHelper.generateTableRowAlreadyEscapedData(rowVars));
        }
        builder.append(htmlHelper.generateTableFooter());
        return builder.toString();
    }

    private String generateAgentGroupLists(String sectionTitle)
    {
        ArrayList<AgentGroup> agentGroups = profile.getAgentGroups();
        StringBuilder builder = new StringBuilder();
        builder.append(htmlHelper.generateSectionHeader(sectionTitle));
        int i = 1;
        String headers[] = {"Sensor Name", "Sensor Properties"};
        for (Iterator<AgentGroup> agentGroupIterator = agentGroups.iterator(); agentGroupIterator.hasNext(); ) {
            AgentGroup agentGroup = agentGroupIterator.next();
            builder.append(htmlHelper.generateTableHeader((agentGroup.getId()), headers));
            ArrayList<Sensor> sensors = agentGroup.getSensorPlacement().getSensors();
            for (Iterator<Sensor> sensorIterator = sensors.iterator(); sensorIterator.hasNext(); ) {
                Sensor sensor = sensorIterator.next();
                if(sensor.getPlaced())
                {
                    String sensorId = sensor.getId();

                    ArrayList<String> activeTech = profileController.getActiveTechnologies();
                    if (activeTech.contains(sensorGroupController.getSensorTech(sensorId)) && sensorGroupController.displaySensor(sensorId))
                    {
                        ArrayList<HashMap<String, String>> properties = sensorConfigurationController.getPropertiesForSensor(agentGroup.getId(), sensorId);
                        ArrayList<SensorValidationProperty> validationRules = sensorLibraryController.getSensorPropertiesForSensor(sensorId);
                        StringBuilder props = new StringBuilder();

                        ValidationResult result = sensorConfigValidator.validateSensorConfig(properties, validationRules);

                        for (Iterator<HashMap<String, String>> iterator = properties.iterator(); iterator.hasNext(); ) {
                            HashMap<String, String> next = iterator.next();
                            Iterator it = next.entrySet().iterator();
                            StringBuilder propBuilder = new StringBuilder();
                            while (it.hasNext())
                            {
                                Map.Entry pair = (Map.Entry)it.next();
                                String sensorProperty = pair.getKey().toString();
                                String sensorValue = pair.getValue().toString();

                                //ValidationResult res = sensorConfigValidator.validateSensorConfigProperty(next, validationRules);
                                ValidationResult res = sensorConfigValidator.validateSensorConfigForParticularProperty(next, validationRules, sensorProperty);
                                //if(!res.isValid()) System.out.println(res);

                                if(res.isValid()) propBuilder.append(htmlHelper.generateDiv("[" + StringEscapeUtils.escapeHtml4(pair.getKey().toString()) + " : " + StringEscapeUtils.escapeHtml4(pair.getValue().toString()) + "]"));

                                else propBuilder.append(htmlHelper.generateDivWithClass("[" + StringEscapeUtils.escapeHtml4(pair.getKey().toString()) + " : " + StringEscapeUtils.escapeHtml4(pair.getValue().toString()) + "] -- REASON: [" + res.toString() +"]", CssClass.ERROR));

                            }
                            props.append(htmlHelper.generateDivWithClass(propBuilder.toString(), CssClass.PROPERTYBLOCK));
                        }
                        String[] rowVars = {translatePackageToSensorName(sensor.getId()), props.toString()};
                        builder.append(htmlHelper.generateTableRowAlreadyEscapedData(rowVars));
                    }
                }
            }
            i++;
            builder.append(htmlHelper.generateTableFooter());
        }

        return builder.toString();
    }

    private String translatePackageToSensorName(String packageName)
    {
        return sensorGroupController.translateSensor(packageName);
    }

    private String generateCustomMeasuresList(String sectionTitle)
    {
        ArrayList<Measure> customMeasures = measureController.getCustomMeasures();
        return generateMeasureTable(customMeasures, sectionTitle);
    }

    private String generateMeasureListOfType(String type)
    {
        ArrayList<Measure> customMeasures = measureController.getMeasuresOfType(true, type);
        return generateMeasureTable(customMeasures, type + " Measures");
    }

    private String generateRegexMeasuresList(String sectionTitle)
    {
        ArrayList<Measure> regexMeasures = measureController.getMeasuresThatUseRegexEval(true);
        return generateMeasureTable(regexMeasures, sectionTitle);
    }

    private String generateAgentSplitMeasuresList(String sectionTitle)
    {
        ArrayList<Measure> splitMeasures = measureController.getMeasuresThatSplitByAgent(true, true);
        return generateMeasureTable(splitMeasures, sectionTitle);
    }

    private String generateAppSplitMeasuresList(String sectionTitle)
    {
        ArrayList<Measure> splitMeasures = measureController.getMeasuresThatSplitByApplication(true, true);
        return generateMeasureTable(splitMeasures, sectionTitle);
    }

    private String generateApplicationTable(String title)
    {
        StringBuilder builder = new StringBuilder();
        String headers[] = {"Application", "Pattern", "UEM", "UEM Options"};
        builder.append(htmlHelper.generateSectionHeader(title));
        builder.append(htmlHelper.generateTableHeader("", headers));
        ArrayList<Application> applications = uemConfigurationController.getApplications();
        UemApplicationConfig defaultUemConfig = uemConfigurationController.getDefaultUemConfig();
        int i = 1;

        for (Iterator<Application> applicationIterator = applications.iterator(); applicationIterator.hasNext(); ) {
            Application application = applicationIterator.next();
            StringBuilder patternBuilder = new StringBuilder();
            //builder.append("<tr style=\"border:1px solid black\">\n<td style=\"border:1px solid black\">" + i + " : " + application.getName() + "</td></tr>");
            i++;
            ArrayList<UriPattern> patterns = application.getUriPatterns();
            if(patterns!=null)
            {
                for (Iterator<UriPattern> uriPatternIterator = patterns.iterator(); uriPatternIterator.hasNext(); ) {
                    UriPattern next = uriPatternIterator.next();
                    String sPattern = "<div>[" + next.getMatch() + "] " + next.getPattern() + "</div>";
                    patternBuilder.append(sPattern);
                }
            }

            boolean isUemEnabled = uemConfigurationController.isUemEnabledForApplication(application);

            StringBuilder uemOptions = new StringBuilder();
            if(isUemEnabled) {
                try {
                    UemJsAgentOption option =  application.getUemApplicationConfig().getUemSensorConfig().getUemJsAgentOption();
                    uemOptions.append(generateUemOptionList(option));

                } catch (NullPointerException e) {
                    UemJsAgentOption option = defaultUemConfig.getUemSensorConfig().getUemJsAgentOption();
                    uemOptions.append(generateUemOptionList(option));
                }
            }
            String uem = uemConfigurationController.getUemModeForApplication(application);
            String[] rowVars = {application.getName(), patternBuilder.toString(), uem, uemOptions.toString()};
            builder.append(htmlHelper.generateTableRowAlreadyEscapedData(rowVars));
        }
        builder.append(htmlHelper.generateTableFooter());
        return builder.toString();
    }

    private String generateUemOptionList(UemJsAgentOption uemJsAgentOption)
    {
        ArrayList<UemModule> modules = uemJsAgentOption.getModules();
        StringBuilder uemOptions = new StringBuilder();
        uemOptions.append(htmlHelper.generateDivWithClass("UEM Sensors", CssClass.LISTHEADER));
        if(modules!=null)
        {
            for (Iterator<UemModule> uemModuleIterator = modules.iterator(); uemModuleIterator.hasNext(); ) {
                UemModule uemModule = uemModuleIterator.next();
                if(uemModule.isActive())
                	uemOptions.append(htmlHelper.generateDivWithClass(translatePackageToSensorName(uemModule.getKey()), CssClass.LISTITEM));
            }
        }


        String agentOption = uemJsAgentOption.getAgentOption();
        if(agentOption.contains("bandwidth")){
            //String bw = agentOption.substring(agentOption.indexOf("bandwidth="), agentOption.contains("_m")?agentOption.indexOf("_m"):agentOption.length()-1);
            uemOptions.append(htmlHelper.generateDivWithClass("Bandwidth Calculation", CssClass.LISTHEADER));

            if(!agentOption.contains("_m")) {
                String bw = agentOption.substring(agentOption.indexOf("bandwidth=")+10);
                uemOptions.append(htmlHelper.generateDiv("Timeout: " + bw));

            }
            else {
                String bw = agentOption.substring(agentOption.indexOf("bandwidth=")+10, agentOption.indexOf("_m"));
                uemOptions.append(htmlHelper.generateDivWithClass("Timeout: " + bw, CssClass.LISTITEM));
                uemOptions.append(htmlHelper.generateDivWithClass("Mobile: True", CssClass.LISTITEM));
            }
        }

        return uemOptions.toString();
    }

    private String generateIncidentTable(String sectionTitle)
    {
        StringBuilder builder = new StringBuilder();
        String headers[] = {"Incident", "Conditions", "Actions"};
        ArrayList<IncidentRule> incidentRules = incidentRuleController.getActiveIncidentRules();
        builder.append(htmlHelper.generateSectionHeader(sectionTitle));
        builder.append(htmlHelper.generateTableHeader("", headers));

        for (Iterator<IncidentRule> incidentRuleIterator = incidentRules.iterator(); incidentRuleIterator.hasNext(); ) {
            IncidentRule incidentRule = incidentRuleIterator.next();
            String conditionList = "<ul>";
            String actionList = "<ul>";
            ArrayList<IncidentCondition> conditions = incidentRule.getConditions();
            ArrayList<IncidentActionRef> actions = incidentRule.getActions();
            if(conditions!= null)
            {
                for (Iterator<IncidentCondition> incidentConditionIterator = conditions.iterator(); incidentConditionIterator.hasNext(); ) {
                    IncidentCondition condition = incidentConditionIterator.next();
                    conditionList += htmlHelper.generateListItem(condition.getRefMeasure() + " [" + condition.getAggregate() + "] [" + condition.getThresholdSeverity() + "]" + (incidentConditionIterator.hasNext()?" [" + condition.getLogicalOperator() + "]":""));
                }
            }
            if(actions!= null)
            {
                for (Iterator<IncidentActionRef> incidentActionRefIterator = actions.iterator(); incidentActionRefIterator.hasNext(); ) {
                    IncidentActionRef actionRef = incidentActionRefIterator.next();
                    actionList += htmlHelper.generateListItem(incidentRuleController.translatePlugin(actionRef.getType()));
                }
            }
            conditionList += "</ul>";
            actionList += "</ul>";

            String[] rowVars = {incidentRule.getId(), conditionList, actionList};
            builder.append(htmlHelper.generateTableRowAlreadyEscapedData(rowVars));
        }

        builder.append(htmlHelper.generateTableFooter());

        return builder.toString();
    }

    private String generateMeasureTable(ArrayList<Measure> measuresToList, String title)
    {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        String headers[] = {"Measure", "Thresholds", "Measure Type"};
        builder.append(htmlHelper.generateSectionHeader(title));
        builder.append(htmlHelper.generateTableHeader((title + " (total = " + measuresToList.size() + ")"), headers));

        for (Iterator<Measure> measureIterator = measuresToList.iterator(); measureIterator.hasNext(); ) {
            Measure measure = measureIterator.next();
            String thresholds = "";
            MeasureThreshold measureThreshold = measure.getMeasureThreshold();
            if(measureThreshold != null)
            {
                if(measureThreshold.getUpperSevere() != null) thresholds += "<div>Upper Severe: " + measureThreshold.getUpperSevere() + "</div>";
                if(measureThreshold.getUpperWarning() != null) thresholds += "<div>Upper Warning: " + measureThreshold.getUpperWarning() + "</div>";
                if(measureThreshold.getLowerWarning() != null) thresholds += "<div>Lower Warning: " + measureThreshold.getLowerWarning() + "</div>";
                if(measureThreshold.getLowerSevere() != null) thresholds += "<div>Lowe Severe: " + measureThreshold.getLowerSevere() + "</div>";
            }
            String[] fields = {measure.getId(), thresholds, measure.getMeasureType()};
            builder.append(htmlHelper.generateTableRowAlreadyEscapedData(fields));
            i++;
        }

        builder.append(htmlHelper.generateTableFooter());

        return builder.toString();
    }








}
