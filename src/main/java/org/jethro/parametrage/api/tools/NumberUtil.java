package org.jethro.parametrage.api.tools;

import java.math.BigDecimal;

public class NumberUtil {

    public static Integer myRand(Integer max){
        return 1 + (int) (Math.random() * (max - 1));
    }


    public static Integer BigDemalToInteger(BigDecimal number){
        return number.intValue();
    }
}
