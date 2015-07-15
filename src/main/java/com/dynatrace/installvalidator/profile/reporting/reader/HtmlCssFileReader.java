package com.dynatrace.installvalidator.profile.reporting.reader;

import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * Created by kristof on 14.07.15.
 */
public class HtmlCssFileReader {
    private static final String CSSFILELOCATION = "target/classes/stylesheet.css";

    public String getStylesheet() throws IOException {
        FileInputStream inputStream = new FileInputStream(CSSFILELOCATION);
        try {
            String everything = IOUtils.toString(inputStream);
            return everything;
        } finally {
            inputStream.close();
        }
    }
}
