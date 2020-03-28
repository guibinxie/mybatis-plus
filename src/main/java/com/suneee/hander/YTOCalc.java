package com.suneee.hander;

import com.suneee.constant.LogisticalEnum;

public class YTOCalc  implements Calc{
    @Override
    public double calc( double weight) {
         return 7+weight*1.5;
    }
}
