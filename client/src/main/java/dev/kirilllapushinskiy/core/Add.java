package dev.kirilllapushinskiy.core;

import dev.kirilllapushinskiy.commands.AbstractCommand;

public class Add extends AbstractCommand {

    static private final String COMMAND_NAME = "ADD";

    protected Add() {
        super(COMMAND_NAME, false);
    }

    public static Add init() {
        return INSTANCE;
    }

    private static final Add INSTANCE = new Add();

}