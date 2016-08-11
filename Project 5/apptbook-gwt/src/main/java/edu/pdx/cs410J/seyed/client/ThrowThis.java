package edu.pdx.cs410J.seyed.client;

/**
 * Created by seyed on 8/13/15.
 */
public class ThrowThis extends RuntimeException {
    public ThrowThis(String message) {
        super(message);
    }

    public ThrowThis() {
    }
}