package com.suneee.hander;

import com.suneee.constant.LogisticalEnum;

public class ZTOCalc implements  Calc {
    @Override
    public double calc(double weight) {
        return 9+weight*1.7;
    }
}
