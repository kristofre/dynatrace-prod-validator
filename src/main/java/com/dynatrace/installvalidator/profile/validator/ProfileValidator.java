package com.dynatrace.installvalidator.profile.validator;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.dynatrace.installvalidator.profile.parser.model.AgentGroup;
import com.dynatrace.installvalidator.profile.parser.model.Dynatrace;
import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;
import com.dynatrace.installvalidator.profile.reporting.ProfileReport;

/**
 * Hello world!
 *
 */
public class ProfileValidator
{
    public static void main( String[] args )
    {
    	try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Dynatrace.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			File XMLfile = new File("/home/kristof/Documents/oldET.profile.xml");
			
			Dynatrace dtProfile = (Dynatrace) jaxbUnmarshaller.unmarshal(XMLfile);
			SystemProfile profile = dtProfile.getSystemProfile();
			System.out.println("Profile Description: " + profile.getDescription());

			
			ArrayList<AgentGroup> agentGroups = profile.getAgentGroups();
			
			for (AgentGroup group:agentGroups)
			{
				System.out.println("Group Name: " + group.getId());
			}

			ProfileReport report = new ProfileReport(profile);
			report.createReport(profile, "easyTravel");

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
