package com.perfectcomputersolutions.pos.model;

import com.perfectcomputersolutions.pos.utility.Money;

public interface Payable {

    long getCost();

    default double getDollars() {

        return Money.centsToDollars(getCost());
    }
}
