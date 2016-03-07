package com.tas;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class TemplateUtil {
    public static String getMessageTextWithVelocityEngine(VelocityEngine velocityEngine,String template, Map model) {
        String text = mergeTemplateIntoString(velocityEngine, template, "UTF-8", model);
        return text;
    }

    public static String mergeTemplateIntoString(VelocityEngine velocityEngine, String templateLocation, String encoding, Map<String, Object> model) throws VelocityException {
        StringWriter result = new StringWriter();
        mergeTemplate(velocityEngine, templateLocation, encoding, model, result);
        return result.toString();
    }

    public static void mergeTemplate(VelocityEngine velocityEngine, String templateLocation, String encoding, Map<String, Object> model, Writer writer) throws VelocityException {
        try {
            VelocityContext ex = new VelocityContext(model);
            velocityEngine.mergeTemplate(templateLocation, encoding, ex, writer);
        } catch (VelocityException var6) {
            throw var6;
        } catch (RuntimeException var7) {
            throw var7;
        } catch (Exception var8) {
            throw new VelocityException(var8.toString());
        }
    }
}
