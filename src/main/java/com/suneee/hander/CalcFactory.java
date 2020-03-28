package com.suneee.hander;

import com.suneee.constant.LogisticalEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CalcFactory {
    private Map<LogisticalEnum, Function<Double,Double>> map = new HashMap<>();

    {
        map.put(LogisticalEnum.JD,this::calcyto);
        map.put(LogisticalEnum.YTO,this::calcsto);
        map.put(LogisticalEnum.STO,this::calczto);
        map.put(LogisticalEnum.ZTO,this::calcjd);

    }


    public double calcyto( double weight) {
        return 7+weight*1.5;
    }

    public double calcsto( double weight) {
        return 8+weight*1.3;
    }

    public double calczto(double weight) {
        return 9+weight*1.7;
    }
    public double calcjd( double weight) {

        return 10+weight*1.1;
    }


    public  double create(LogisticalEnum logisticalEnum,double weight){
        double calc = map.get(logisticalEnum).apply(weight);
        return  calc;
    }
}
