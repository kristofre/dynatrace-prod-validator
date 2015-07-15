package com.dynatrace.installvalidator.profile.validator;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

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
			ProfileRepository profileRepository = new ProfileRepository();
			File XMLfile = new File(inputFile);
			Dynatrace dynatrace = profileRepository.getDynatrace(XMLfile);
			SystemProfile profile = dynatrace.getSystemProfile();
			String profileName = XMLfile.getName().substring(0, XMLfile.getName().indexOf(".profile.xml"));

			HtmlProfileReport report = new HtmlProfileReport(profile, profileName, XMLfile.lastModified(), dynatrace.getVersion());
			if(outputFile.isEmpty())
			{
				File f = new File(".");
				outputFile = f.getCanonicalPath() + "/" + profileName + "_validated.html";
			}
			report.createReport(outputFile);

		}  catch (IOException e) {
			e.printStackTrace();
		}
	}
}
