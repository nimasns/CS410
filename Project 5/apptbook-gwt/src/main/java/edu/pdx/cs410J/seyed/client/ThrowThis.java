package edu.pdx.cs410J.seyed.client;

/**
 * Created by Seyed
 */
public class ThrowThis extends RuntimeException {
    public ThrowThis(String message) {
        super(message);
    }

    public ThrowThis() {
    }
}