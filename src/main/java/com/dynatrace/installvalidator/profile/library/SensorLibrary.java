package com.dynatrace.installvalidator.profile.library;

import com.dynatrace.installvalidator.profile.library.model.Plugin;
import com.dynatrace.installvalidator.profile.library.model.SensorDynatrace;
import com.dynatrace.installvalidator.profile.parser.model.Dynatrace;
import com.dynatrace.installvalidator.profile.library.model.SensorDetail;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kristof on 29.05.15.
 */
public class SensorLibrary {
    private static SensorLibrary instance = null;

    private static final String CONFIGFILELOCATION = "/sensortranslations.xml";
    //private static final String SENSORLISTFILELOCATION = "target/classes/sensortranslations.xml";
    private File sensorTranslation;
    private ArrayList<SensorDetail> sensorDetails;
    private SensorDynatrace sensorDynatrace;

    public static  String NETTECH = ".NET";
    public static  String JAVATECH = "Java";
    public static  String NETMEMTECH = ".NET Memory";
    public static  String JAVAMEMTECH = "Java Memory";
    public static  String PHPTECH = "PHP";
    public static  String BROWSERTECH = "Browser";
    public static  String WEBSERVERTECH = "Web Server";
    public static  String JAVASCRIPTTECH = "Javascript";
    public static  String IMSTECH = "IMS";
    public static  String CICSTECH = "CICS";
    public static  String SAMPLINGTECH = "Sampling";

    protected SensorLibrary() {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(SensorDynatrace.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            //File XMLfile = new File(SENSORLISTFILELOCATION);
            //sensorDynatrace = (SensorDynatrace) jaxbUnmarshaller.unmarshal(XMLfile);
            
            InputStream stream = getClass().getResourceAsStream(CONFIGFILELOCATION);
            sensorDynatrace = (SensorDynatrace) jaxbUnmarshaller.unmarshal(stream);

        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }
    public static SensorLibrary getInstance(){
        if (instance == null){
            instance = new SensorLibrary();
        }
        return instance;
    }
 

    public ArrayList<SensorDetail> getSensorDetails()
    {
        if(sensorDynatrace != null)
            return sensorDynatrace.getSensorDetails();
        else
            return null;
    }

    public ArrayList<Plugin> getPlugins()
    {
        if(sensorDynatrace != null)
            return sensorDynatrace.getPlugins();
        else
            return null;
    }


    public String getSensorTech(String sensorName)
    {
        return "NOTUSED";
    }
}
