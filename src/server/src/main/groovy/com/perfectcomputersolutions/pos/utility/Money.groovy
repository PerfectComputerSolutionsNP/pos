package com.perfectcomputersolutions.pos.utility

class Money {

    static double millsToCents(long mills) {

        return (double) mills / 10.0
    }

    static double millsToDollars(long mills) {

        return (double) mills / 1000.0
    }
}
