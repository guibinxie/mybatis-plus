package com.suneee.hander;

import com.suneee.constant.LogisticalEnum;

public class JDCalc implements  Calc {


    @Override
    public double calc( double weight) {

         return 10+weight*1.1;
    }
}
