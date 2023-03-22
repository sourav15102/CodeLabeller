package com.csci5308.codeLabeller.Enums;

public enum JwtNumbers {
    BearerMark (7),
    JwtTokenHours (10),
    Seconds (60),
    Minutes (60),
    Miliseconds (1000);

    private final int value;

    JwtNumbers(int i) {
        this.value = i;
    }

    public int getValue(){
        return value;
    }
}
