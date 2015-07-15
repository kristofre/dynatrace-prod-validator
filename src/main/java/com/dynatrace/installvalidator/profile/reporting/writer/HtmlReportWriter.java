package com.dynatrace.installvalidator.profile.reporting.writer;

import com.dynatrace.installvalidator.profile.parser.model.SystemProfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by kristof on 09.07.15.
 */
public class HtmlReportWriter {
    public void writeHtmlReportToFile(StringBuilder html, String fileName)
    {
        try {
            FileOutputStream out = new FileOutputStream(fileName);
            out.write(html.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
