package com.tas.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class FirstServlet extends HttpServlet {
    private static String ANSWER ="answer";
    private static String PREVIOUS ="your previos answer is 0.";
    private static String MESSAGE ="<html><body>You said 0!1</body></html>";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost --->");
//        InputStream requestBodyInput = request.getInputStream();
        String yesOrNoParam = request.getParameter("param");
        print(request,response,yesOrNoParam);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet --->");

        String yesOrNoParam = request.getParameter("param");
//        print(request,response,yesOrNoParam);
        printResim(request,response);
    }

    private void print(HttpServletRequest request,HttpServletResponse response, String yesOrNoParam) throws ServletException, IOException{
        HttpSession session = request.getSession();
        String previousParam = null;
        if(null != session.getAttribute(ANSWER)){
            previousParam = (String) session.getAttribute(ANSWER);
        }
        session.setAttribute(ANSWER,yesOrNoParam);
        String mesaj = MESSAGE.replaceAll("0",yesOrNoParam);;
        String previousMesaj = "";
        if(previousParam != null) {
            previousMesaj = PREVIOUS.replaceAll("0",previousParam);
        }
        mesaj = mesaj.replaceAll("1", previousMesaj);

        response.getWriter().write(mesaj);
    }

    private void printResim(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("img/png");
        String pathToWeb = getServletContext().getRealPath(File.separator);
        File file = new File(pathToWeb+"/oz.jpg");
        BufferedImage bi = ImageIO.read(file);
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(bi,"jpg", outputStream);
    }
}
