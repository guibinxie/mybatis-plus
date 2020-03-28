package com.suneee.hander;

import com.suneee.constant.LogisticalEnum;

public class STOCalc implements  Calc {
    @Override
    public double calc( double weight) {
        return 8+weight*1.3;
    }
}
