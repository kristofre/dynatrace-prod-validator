package com.dynatrace.installvalidator.profile.validator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.dynatrace.installvalidator.profile.parser.model.AgentGroup;
import com.dynatrace.installvalidator.profile.parser.model.Dynatrace;
import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;
import com.dynatrace.installvalidator.profile.reporting.ProfileReport;
import org.apache.commons.cli.*;

/**
 * Hello world!
 *
 */
public class ProfileValidator
{
    public static void main( String[] args )
    {
		CommandLineParser parser = new DefaultParser();
		Options options = new Options();
		Option inputOption = new Option("i", "input", true, "system profile to be analyzed [required]");
		inputOption.setRequired(true);
		options.addOption(inputOption);
		options.addOption("o", "out", true, "file to write to [defaults to current directory]");

		try
		{
			CommandLine line = parser.parse( options, args );


			String input = "";
			String output = "";
			if (line.hasOption("input")) input = line.getOptionValue("input");

			if (line.hasOption("output")) output = line.getOptionValue("output");

			processProfile(input, output);
		} catch (ParseException e) {
			System.out.println( e.getMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("profilevalidator", options);
		}


	}

	private static void processProfile(String inputFile, String outputFile)
	{
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Dynatrace.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			File XMLfile = new File(inputFile);

			Dynatrace dtProfile = (Dynatrace) jaxbUnmarshaller.unmarshal(XMLfile);
			SystemProfile profile = dtProfile.getSystemProfile();
			//System.out.println("Profile Description: " + profile.getDescription());


			ArrayList<AgentGroup> agentGroups = profile.getAgentGroups();

			for (AgentGroup group:agentGroups)
			{
				//System.out.println("Group Name: " + group.getId());
			}

			String profileName = XMLfile.getName().substring(0, XMLfile.getName().indexOf(".profile.xml"));

			ProfileReport report = new ProfileReport(profile);
			if(outputFile.isEmpty())
			{
				File f = new File(".");
				outputFile = f.getCanonicalPath() + "/" + profileName + "_validated.html";
			}
			report.createReport(profile, profileName, outputFile);

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
