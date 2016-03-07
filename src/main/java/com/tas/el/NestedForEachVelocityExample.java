package com.tas.el;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NestedForEachVelocityExample {
    public static void main(String args[]){
        VelocityEngine engine = new VelocityEngine();
        engine.init();

        Template template = engine.getTemplate("velocitytemplate.vm");

        Map<String,List<String>> userNamesByCompany = new HashMap<String, List<String>>();
        {
            List<String> userNames = new ArrayList<String>();
            userNames.add("Raja");
            userNames.add("Roja");

            userNamesByCompany.put("android", userNames);
        }
        {
            List<String> userNames = new ArrayList<String>();
            userNames.add("Peter");
            userNames.add("John");

            userNamesByCompany.put("microsoft", userNames);
        }

        VelocityContext context = new VelocityContext();
        context.put("userNamesByCompany", userNamesByCompany);

        StringWriter sw = new StringWriter();
        template.merge(context, sw);

        System.out.println(sw.toString());
    }
}
