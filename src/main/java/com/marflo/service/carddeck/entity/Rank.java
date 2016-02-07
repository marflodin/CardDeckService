package com.marflo.service.carddeck.entity;

public enum Rank {

    DEUCE(1), THREE(2), FOUR(3), FIVE(4), SIX(6), SEVEN(7),
    EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);
    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
