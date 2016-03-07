package com.tas.servlet;

import com.tas.util.DataProvider;
import com.tas.util.HTMLHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/input/*")
public class InputServlet extends HttpServlet {
    private static String[] HEADERS = {"Dersler","Yillar","Notlar"};
    private String[] headers = new String[DataProvider.yillar.length + 1];

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        headers[0] = "Dersler";
        for (int i = 0; i < DataProvider.yillar.length; i++) {
            headers[i + 1] = DataProvider.yillar[i];
        }
        HashMap<String, String> notMap = new HashMap<String, String>();
        for (int i = 0; i < DataProvider.dersler.length; i++) {
            String ders = DataProvider.dersler[i];
            for (int j = 0; j < DataProvider.yillar.length; j++) {
                String yil = DataProvider.yillar[j];
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("not").append("-").append(ders).append("-").append(yil);
                notMap.put(stringBuilder.toString(), request.getParameter(stringBuilder.toString()));
            }
        }
        printResultOutput(response, notMap);
//        printOutputByTemplate(response, request, notMap);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        printInputByTemplate(response,request);
        printResult(response);
    }

    private String generateRows() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < DataProvider.dersler.length; i++) {
            String ders = DataProvider.dersler[i];
            for (int j = 0; j < DataProvider.yillar.length; j++) {
                String yil = DataProvider.yillar[j];
                stringBuilder.append(HTMLHelper.generateInputRow(ders,yil));
            }
        }
        return stringBuilder.toString();
    }

//    private void printInputByTemplate(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
//        Map bodyModel = new HashMap();
//        bodyModel.put("dersler",DataProvider.dersler);
//        bodyModel.put("yillar",DataProvider.yillar);
//        VelocityEngine velocityEngine = new VelocityEngine();
//        String vmPath = request.getSession().getServletContext().getRealPath("/templates");
//        Properties p = new Properties();
//        p.setProperty("file.resource.loader.path", vmPath+"//");
//        velocityEngine.init(p);
//        String result = TemplateUtil.getMessageTextWithVelocityEngine(velocityEngine,"inputTemplate.vm",bodyModel);
//        response.getWriter().write(result);
//    }

//    private void printOutputByTemplate(HttpServletResponse response, HttpServletRequest request, Map<String,String> notMap) throws ServletException, IOException {
//        Map bodyModel = new HashMap();
//        bodyModel.put("dersler",DataProvider.dersler);
//        bodyModel.put("yillar",DataProvider.yillar);
//        bodyModel.put("notlar",notMap);
//        VelocityEngine velocityEngine = new VelocityEngine();
//        String vmPath = request.getSession().getServletContext().getRealPath("/templates");
//        Properties p = new Properties();
//        p.setProperty("file.resource.loader.path", vmPath+"//");
//        velocityEngine.init(p);
//        String result = TemplateUtil.getMessageTextWithVelocityEngine(velocityEngine,"outputTemplate.vm",bodyModel);
//        response.getWriter().write(result);
//    }

    private void printResult(HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write(HTMLHelper.generateHTML(HEADERS,generateRows()));
    }

    private void printResultOutput(HttpServletResponse response, HashMap<String, String> notMap) throws ServletException, IOException {
        String result =
                HTMLHelper.generateOutputTable(headers
                        , HTMLHelper.generateOutputRow(
                                DataProvider.dersler, notMap, DataProvider.yillar
                        ));
        response.getWriter().write(result);
    }
}
