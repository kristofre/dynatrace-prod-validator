package com.dynatrace.installvalidator.profile.validator;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.dynatrace.installvalidator.profile.library.SensorLibrary;
import com.dynatrace.installvalidator.profile.parser.model.Dynatrace;
import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;
import com.dynatrace.installvalidator.profile.parser.repository.ProfileRepository;
import com.dynatrace.installvalidator.profile.reporting.generator.HtmlProfileReport;
import org.apache.commons.cli.*;

/**
 * Hello world!
 *
 */
public class ProfileValidator
{
	private static final String CONFIGFILELOCATION = "/sensortranslations.xml";
	
    public static void main( String[] args )
    {
		CommandLineParser parser = new DefaultParser();
		Options options = new Options();
		Option inputOption = new Option("i", "input", true, "system profile to be analyzed [required]");
		inputOption.setRequired(true);
		options.addOption(inputOption);
		options.addOption("o", "output", true, "file to write to [defaults to current directory]");
		//options.addOption("c", "config", true, "optional config file to overwrite packaged one");

		try
		{
			CommandLine line = parser.parse( options, args );


			String input = "";
			String output = "";
			String config = "";
			if (line.hasOption("input")) input = line.getOptionValue("input");

			if (line.hasOption("output")) output = line.getOptionValue("output");
			
			if (line.hasOption("config")) config = line.getOptionValue("config");

			processProfile(input, output);
		} catch (ParseException e) {
			System.out.println( e.getMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("profilevalidator", options);
		}


	}

	private static void processProfile(String inputFile, String outputFile)
	{
		File XMLfile = new File(inputFile);
		String profileName = XMLfile.getName().substring(0, XMLfile.getName().indexOf(".profile.xml"));
		if(outputFile.isEmpty())
		{
			String test;
			File f = new File(".");
			try {
				outputFile = f.getCanonicalPath() + "/" + profileName + "_validated.html";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(generateIntro(inputFile, outputFile));
		ProfileRepository profileRepository = new ProfileRepository();
		Dynatrace dynatrace = profileRepository.getDynatrace(XMLfile);
		SystemProfile profile = dynatrace.getSystemProfile();
		HtmlProfileReport report = new HtmlProfileReport(profile, profileName, XMLfile.lastModified(), dynatrace.getVersion());
		
		report.createReport(outputFile);
	
		System.out.println(generateOutro(""));
	}
	
	private static String generateIntro(String inputFile, String outputFile)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Dynatrace Application Monitoring System Profile Reporting Tool\n");
		builder.append("--------------------------------------------------------------\n");
		builder.append("File to be analyzed: " + inputFile + "\n");
		builder.append("Output File: " + outputFile + "\n");
		return builder.toString();
	}
	
	private static String generateOutro(String status)
	{
		return "Report generated\n";
	}
}
