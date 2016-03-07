package com.tas.util;

import java.util.HashMap;

public class HTMLHelper {
    public static String generateHTML(String[] headers, String rows) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(generateHead());
        stringBuilder.append("<html><body>");
        stringBuilder.append("<form id=\"outputForm\" action=\"/input\" method=\"post\">");
        stringBuilder.append("<table>");
        stringBuilder.append(generateTableHeaders(headers));
        stringBuilder.append(rows);
        stringBuilder.append("</table>");
        stringBuilder.append("<input type=\"submit\" value=\"Submit\" onclick=\"onSubmitFunc()\">");
        stringBuilder.append("</form>");
        stringBuilder.append("<div id=\"result\"></div>\n");
        stringBuilder.append(generateScript());
        stringBuilder.append("</body></html>");
        return stringBuilder.toString();
    }

    public static String generateOutputTable(String[] headers, String rows) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(generateHead());
        stringBuilder.append("<div id=\"content\">");
        stringBuilder.append("<table>");
        stringBuilder.append(generateTableHeaders(headers));
        stringBuilder.append(rows);
        stringBuilder.append("</table>");
        stringBuilder.append("</div>");
        return stringBuilder.toString();
    }


    public static String generateTableHeaders(String[] headers) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < headers.length; i++) {
            String header = headers[i];
            stringBuilder.append("<th>");
            stringBuilder.append(header);
            stringBuilder.append("</th>");
        }
        return stringBuilder.toString();
    }

    public static String generateInputRow(String ders, String yil) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<tr>");
        stringBuilder.append("<td>").append(ders).append("</td>");
        stringBuilder.append("<td>").append(yil).append("</td>");
        stringBuilder.append("<td>");
        stringBuilder.append("<input type=\"text\" name=\"not:" + ders + ":" + yil + "\"/>");
        stringBuilder.append("</td>");
        stringBuilder.append("</tr>");
        return stringBuilder.toString();
    }

    public static String generateOutputRow(String[] dersLer, HashMap<String, String> notMap, String[] yillar) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dersLer.length; i++) {
            stringBuilder.append("<tr>");
            String ders = dersLer[i];
            stringBuilder.append("<td>").append(ders).append("</td>");
            for (int j = 0; j < yillar.length; j++) {
                String yil = yillar[j];
                stringBuilder.append("<td>").append(notMap.get("not:" + ders + ":" + yil)).append("</td>");
            }
            stringBuilder.append("</tr>");
        }
        return stringBuilder.toString();
    }

    public static String generateHead() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <title>submit demo</title>\n" +
                "  <style>\n" +
                "  p {\n" +
                "    margin: 0;\n" +
                "    color: blue;\n" +
                "  }\n" +
                "  div,p {\n" +
                "    margin-left: 10px;\n" +
                "  }\n" +
                "  span {\n" +
                "    color: red;\n" +
                "  }\n" +
                "  </style>\n" +
                "  <script src=\"https://code.jquery.com/jquery-1.10.2.js\"></script>\n" +
                "</head>");
        return stringBuilder.toString();
    }

    public static String generateScript() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<script>\n" +
                "var onSubmitFunc = function(){\n" +
                "    event.preventDefault();\n" +
                "    var jqxhr = $.post( \"/input\", $( \"#outputForm\" ).serialize())\n" +
                "            .done(function( data ) {\n" +
                "                    $( \"#result\" ).empty().append( data );\n" +
                "                })\n" +
                "            .fail(function() {\n" +
                "                alert( \"error\" );\n" +
                "            });\n" +
                "};\n" +
                "\n" +
                "</script>\n");
        return stringBuilder.toString();
    }
}
