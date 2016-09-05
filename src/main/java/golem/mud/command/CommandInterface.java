package golem.mud.command;

import golem.mud.exception.CommandException;

public interface CommandInterface {

    String getRegex();
    boolean matches(final String text);
    CommandResponse execute() throws CommandException;
}
