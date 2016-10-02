package com.tpms.calculater.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tmp on 30.09.2016.
 */
public class Calculater {

    private Map<String, Method> methods;
    private ExecuterOperationsImpl operations = new ExecuterOperationsImpl();

    public Calculater() {
        methods = new HashMap<String, Method>();
        try {
            methods.put("+", ExecuterOperationsImpl.class.getMethod("addition", new Class[]{float.class, float.class}));
            methods.put("-", ExecuterOperationsImpl.class.getMethod("subtraction", new Class[]{float.class, float.class}));
            methods.put("*", ExecuterOperationsImpl.class.getMethod("multiplication", new Class[]{float.class, float.class}));
            methods.put("/", ExecuterOperationsImpl.class.getMethod("division", new Class[]{float.class, float.class}));
            methods.put("MR+", ExecuterOperationsImpl.class.getMethod("save", new Class[]{float.class}));
            methods.put("MR-", ExecuterOperationsImpl.class.getMethod("unsave", new Class[]{float.class}));
            methods.put("+/-", ExecuterOperationsImpl.class.getMethod("changeTag", new Class[]{float.class}));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public Float calculater( Float firstOperand, Float secondOperand, String operation) throws InvocationTargetException, IllegalAccessException {
        Method method = methods.get(operation);
        Object[] arg = new Object[]{firstOperand, secondOperand};
        Float result = (Float) method.invoke(operations, arg);
        firstOperand = null;
        return result;

    }

    public Float calculater(Float operand, String operation) throws InvocationTargetException, IllegalAccessException {
        Method method = methods.get(operation);
        Object[] arg = new Object[]{operand};
        Float result = (Float) method.invoke(operations, arg);
        return result;
    }
}
