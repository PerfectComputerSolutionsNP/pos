package com.perfectcomputersolutions.pos.utility

class Money {

    static double centsToDollars(long cents) {

        return (double) cents / 100.0
    }

    static String toPriceStringDollars(double dollars) {

        return String.format("\$%.2f", dollars)
    }

    static String toPriceStringCents(long cents) {

        return String.format("%d¢", cents)
    }
}
