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

@WebServlet("/output/*")
public class OutputServlet extends HttpServlet {

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
                stringBuilder.append("not").append(":").append(ders).append(":").append(yil);
                notMap.put(stringBuilder.toString(), request.getParameter(stringBuilder.toString()));
            }
        }
        printResult(response, notMap);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("yanlis yerdesin");
    }

    private void printResult(HttpServletResponse response, HashMap<String, String> notMap) throws ServletException, IOException {
        response.getWriter().write(
                HTMLHelper.generateHTML(headers
                        , HTMLHelper.generateOutputRow(
                                DataProvider.dersler, notMap, DataProvider.yillar
                        )));
    }
}
