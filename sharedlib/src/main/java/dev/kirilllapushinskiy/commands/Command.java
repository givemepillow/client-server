package dev.kirilllapushinskiy.commands;

interface Command {

    String getCommandName();

    String[] getCommandArgs();

    Boolean withArgs();

    Boolean requiredArgs();

}
