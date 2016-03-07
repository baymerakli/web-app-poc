package com.tas.servlet;

import com.tas.TemplateUtil;
import com.tas.modal.OutputRow;
import com.tas.util.DataProvider;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@WebServlet("/template")
public class TemplateServlet extends HttpServlet {
    private static String[] INPUT_HEADERS = {"Dersler", "Yillar", "Notlar"};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printOutputByTemplate(response, request);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printInputByTemplate(response, request);
    }

    private void printInputByTemplate(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        Map bodyModel = new HashMap();
        bodyModel.put("dersler", DataProvider.dersler);
        bodyModel.put("yillar", DataProvider.yillar);
        VelocityEngine velocityEngine = new VelocityEngine();
        String vmPath = request.getSession().getServletContext().getRealPath("/templates");
        Properties p = new Properties();
        p.setProperty("file.resource.loader.path", vmPath + "//");
        velocityEngine.init(p);
        String result = TemplateUtil.getMessageTextWithVelocityEngine(velocityEngine, "inputTemplate.vm", bodyModel);
        response.getWriter().write(result);
    }

    private void printOutputByTemplate(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        VelocityEngine engine = new VelocityEngine();
        String vmPath = request.getSession().getServletContext().getRealPath("/templates");
        Properties p = new Properties();
        p.setProperty("file.resource.loader.path", vmPath + "//");
        engine.init(p);

        Template template = engine.getTemplate("outputTemplate.vm");

        VelocityContext context = new VelocityContext();
        context.put("yillar", DataProvider.yillar);
        context.put("dersYilNotMap", getDersYilNotMap(request));

        StringWriter sw = new StringWriter();
        template.merge(context, sw);

        String result = sw.toString();
        response.getWriter().write(result);
    }

    private static HashMap<String, HashMap<String, String>> getDersYilNotMap(HttpServletRequest request) {
        HashMap<String, HashMap<String, String>> dersYilNotMap = new HashMap<String, HashMap<String, String>>();
        for (int i = 0; i < DataProvider.dersler.length; i++) {
            String ders = DataProvider.dersler[i];
            OutputRow row = new OutputRow();
            row.setDers(ders);
            dersYilNotMap.put(ders, new HashMap<String, String>());
            for (int j = 0; j < DataProvider.yillar.length; j++) {
                String yil = DataProvider.yillar[j];
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("not").append(":").append(ders).append(":").append(yil);
                String value = request.getParameter(stringBuilder.toString());
                dersYilNotMap.get(ders).put(yil, value);
            }
        }
        return dersYilNotMap;
    }
}
