package com.dynatrace.installvalidator.profile.reporting;

/**
 * Created by kristof on 07.07.15.
 */
public class HtmlHelper {
    public String generateReportHeader(String profileName, String profileDescription)
    {
        String returnVal = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n";
        returnVal += "<h1>" + profileName + "</h1>";
        return returnVal;
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

    public String generateTableHeader(String title, String[] headers)
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


}
