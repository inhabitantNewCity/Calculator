package com.tpms.calculater.model;

/**
 * Created by Tmp on 30.09.2016.
 */
public class ExecuterOperationsImpl {

    private Float mrPlusOperand;

    public Float addition(float firstOperand, float secondOperand){
        return firstOperand+ secondOperand;
    }

    public Float subtraction(float firstOperand, float secondOperand){
        return firstOperand - secondOperand;
    }

    public Float multiplication(float firstOperand, float secondOperand){
        return firstOperand * secondOperand;
    }

    public Float division(float firstOperand, float secondOperand){
        return firstOperand / secondOperand;
    }

    public Float save(float operand){
        mrPlusOperand = operand;
        return operand;
    }

    public Float unsave(float operand){
        Float result = mrPlusOperand;
        mrPlusOperand = null;
        return result;
    }

    public Float changeTag(float operand){
        return 0 - operand;
    }

}
