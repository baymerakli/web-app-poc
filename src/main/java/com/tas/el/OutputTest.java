package com.tas.el;

import com.tas.util.DataProvider;
import com.tas.modal.OutputRow;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.HashMap;

public class OutputTest {
    public static void main(String args[]){
        VelocityEngine engine = new VelocityEngine();
        engine.init();

        Template template = engine.getTemplate("outputTemplate.vm");

        VelocityContext context = new VelocityContext();
        context.put("dersler", DataProvider.dersler);
        context.put("yillar", DataProvider.yillar);
        context.put("dersYilNotMap", getDersYilNotMap());

        StringWriter sw = new StringWriter();
        template.merge(context, sw);

        System.out.println(sw.toString());
    }

    private static HashMap<String,HashMap<String,String>> getDersYilNotMap(){
        HashMap<String,HashMap<String,String>> dersYilNotMap = new HashMap<String, HashMap<String, String>>();
        for (int i = 0; i < DataProvider.dersler.length; i++) {
            String ders = DataProvider.dersler[i];
            OutputRow row = new OutputRow();
            row.setDers(ders);
            dersYilNotMap.put(ders, new HashMap<String, String>());
            for (int j = 0; j < DataProvider.yillar.length; j++) {
                String yil = DataProvider.yillar[j];
                if(j%2 == 1) {
                    dersYilNotMap.get(ders).put(yil, "random not" + j);
                }
            }
        }
        return dersYilNotMap;
    }
}
