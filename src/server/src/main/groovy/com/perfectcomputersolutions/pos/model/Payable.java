package com.perfectcomputersolutions.pos.model;

import com.perfectcomputersolutions.pos.utility.Money;

public interface Payable {

    long getCost();

    default double getCents() {

        return Money.millsToCents(getCost());
    }

    default double getDollars() {

        return Money.millsToDollars(getCost());
    }
}
