package com.tas.el;

import com.tas.TemplateUtil;
import org.apache.commons.el.ExpressionEvaluatorImpl;
import org.apache.velocity.app.VelocityEngine;

import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.FunctionMapper;
import javax.servlet.jsp.el.VariableResolver;
import java.util.HashMap;
import java.util.Map;

public class TestEl {
    public static void main(String[] args) throws Exception {
        String result = testTemplate();
        System.out.println("-------------------------");
        System.out.println(result);
        System.out.println("-------------------------");
        HashMap vars = new HashMap();
        VariableResolver variableResolver = new SimpleVariableResolver(vars);
        ExpressionEvaluator sEvaluator = new ExpressionEvaluatorImpl();

        vars.put("str", "${8 + 2}");
        vars.put("num", new Integer(8));
        HashMap pc = new HashMap();
        pc.put("str", "in_pc");
        vars.put("pageContext", pc);

        String pAttributeValue;
        pAttributeValue = "${2 + 2}";
        pAttributeValue = "${'${8 + 2}'} = ${num / 3}";
        pAttributeValue = "${str} = ${num + 2}";
        pAttributeValue = "${pageContext.str} = something";
        Object pPageContext = null;
        Class pExpectedType = String.class;
        FunctionMapper functionMapper = null;
        String defaultPrefix = null;

        System.out.println(
                sEvaluator.evaluate
                        (pAttributeValue,
                                pExpectedType,
                                variableResolver,
                                functionMapper));


        // sEvaluator.parseExpressionString (pAttributeValue);
    }

    public static class SimpleVariableResolver implements VariableResolver {
        private HashMap vars;
        public SimpleVariableResolver(HashMap vars) {
            this.vars = vars;
        }

        public Object resolveVariable(String pName) {
            return vars.get(pName);
        }
    }

    private static String testTemplate() {
        Map bodyModel = new HashMap();
        bodyModel.put("first","First Message");
        bodyModel.put("second","Second Message");
        String[] dersler = {"Dersler","Yillar","Notlar"};
        String[] yillar = {"2014","2015","2016"};
        bodyModel.put("dersler",dersler);
        bodyModel.put("yillar",yillar);
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();

        return TemplateUtil.getMessageTextWithVelocityEngine(velocityEngine,"inputTemplate.vm",bodyModel);
    }
}
