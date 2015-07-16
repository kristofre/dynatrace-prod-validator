package com.dynatrace.installvalidator.profile.reporting.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Created by kristof on 07.07.15.
 */
public class HtmlHelper {
    public String generateReportHeader(String profileName, String profileDescription, String css, String version, long modDate)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>\n");
        builder.append("<html>\n");
        builder.append("<head>\n");
        builder.append("<style>\n");
        builder.append(css);
        builder.append("</style>\n");
        builder.append("</head>\n");
        builder.append("<body>\n");

        builder.append("<h1>" + profileName + "</h1>");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        builder.append(generateDivWithClass("Report generated on: " + dateFormat.format(date), CssClass.SUBTITLE));
        builder.append(generateDivWithClass("Dynatrace Application Monitoring Version: " + version, CssClass.SUBTITLE));
        builder.append(generateDivWithClass("System Profile last modified on: " + dateFormat.format(modDate), CssClass.SUBTITLE));
        return builder.toString();
    }

    public String generateReportFooter()
    {
        String returnVal = "</body>\n" +
                "</html>";

        return returnVal;
    }

    private String generateTableHeader(String title, String[] headers, HtmlTableStyle htmlTableStyle)
    {
        String tableStyle = "width:100%;";
        if (htmlTableStyle.isBorder())
        {
            if(htmlTableStyle.getTableAttribs().isEmpty())
            {
                //if
            }
            else tableStyle += "border-style:" + htmlTableStyle.getTableAttribs() + ";";
        }
        return generateTableHeader(title, headers);
    }

    public String generateSectionHeader(String title)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("<h2 id=" + StringEscapeUtils.escapeHtml4(title).replace(" ", "_") + ">" +title + "</h2><br>");
        return builder.toString();
    }

    public String generateTableHeader(String title, String[] headers)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("<b>" + ">" +title + "</b><br>");
        builder.append("<table>");
        if(headers.length>0) {
            builder.append("<tr>");
            for (int i = 0; i < headers.length; i++) {
                builder.append("<th>" + headers[i] + "</th>");
            }
            builder.append("</tr>");
        }
        return builder.toString();
    }

    public String generateTableRow(String[] values)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("<tr>");
        for (int i = 0; i < values.length; i++) {
            builder.append("<td>"+StringEscapeUtils.escapeHtml4(values[i])+"</td>");
        }
        builder.append("</tr>\n");
        return builder.toString();
    }

    public String generateTableRowAlreadyEscapedData(String[] values)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("<tr>");
        for (int i = 0; i < values.length; i++) {
            builder.append("<td>"+(values[i])+"</td>");
        }
        builder.append("</tr>\n");
        return builder.toString();
    }

    public String generateDiv(String text)
    {
        return "<div>"+text+"</div>";
    }

    public String generateDivWithId(String text, String id)
    {
        return "<div id=\""+id+"\">"+text+"</div>";
    }

    public String generateDivWithClass(String text, String cssclass)
    {
        return "<div class=\""+cssclass+"\">"+text+"</div>";
    }

    public String generateTableFooter()
    {
        return ("</table>");
    }

    public String generateListItem(String text)
    {
        return "<li>" + text + "</li>";
    }

}
