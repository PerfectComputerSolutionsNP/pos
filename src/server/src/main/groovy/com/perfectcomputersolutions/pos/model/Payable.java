package com.perfectcomputersolutions.pos.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface Payable {

    int SCALE = 2;

    RoundingMode ROUNDING_MODE = RoundingMode.CEILING;

    BigDecimal getCost();

    default String toDollarString(BigDecimal value) {

        return "$" + value.setScale(SCALE, ROUNDING_MODE).toPlainString();
    }

    default String getCostString() {

        return toDollarString(getCost());
    }
}
