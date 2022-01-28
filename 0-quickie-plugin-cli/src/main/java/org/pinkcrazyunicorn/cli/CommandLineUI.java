package org.pinkcrazyunicorn.cli;

import org.apache.commons.cli.*;
import org.pinkcrazyunicorn.*;
import org.pinkcrazyunicorn.event.Event;
import org.pinkcrazyunicorn.event.EventAnswer;
import org.pinkcrazyunicorn.event.EventCallback;
import org.pinkcrazyunicorn.event.EventType;

import java.util.*;
import java.util.stream.Collectors;

public class CommandLineUI implements UI {
    private Map<EventType, EventCallback> eventMap;
    private List<String> commands;
    private String[] args;
    private boolean wasRun = false;

    public CommandLineUI(String[] args) {
        this.eventMap = new HashMap<>();
        this.args = args;
        this.commands = new ArrayList<>();
    }

    @Override
    public void registerEvent(EventType event, EventCallback callback) {
        this.eventMap.put(event, callback);
        this.commands.add(event.getName());
    }

    @Override
    public void handleEvent(Event event) {
        EventCallback callback = this.eventMap.get(event.getType());
        EventAnswer answer = callback.call(event.getData());
        System.out.println(answer.getTitle());
        if (answer.getData().isPresent()) {
            System.out.println(answer.getData().get().toJson());
        }
    }

    @Override
    public Event getUserEvent() {
        if (this.wasRun) {
            return null; // TODO: How to handle program exit?
        }
        this.wasRun = true;

        Options options = new Options();
        String commandsString = commands.stream().collect(Collectors.joining(", "));
        options.addOption("n", "profile-name", true, "name of profile to use");
        options.addOption("c", "command", true, "command to execute, possible values: [" + commandsString + "]");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmdLine;
        try {
            cmdLine = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println("Parsing of command line arguments failed, reason: " + e.getMessage());
            printHelp(options);
            return null; // TODO: How to handle program exit?
        }

        if (!cmdLine.hasOption("command")) {
            System.out.println("Command must be specified!");
            printHelp(options);
            return null; // TODO: How to handle program exit?
        }

        String command = cmdLine.getOptionValue("command");

        if (!commands.contains(command)) {
            System.out.println("Unknow command " + command);
            printHelp(options);
            return null; // TODO: How to handle program exit?
        }

        Map<String, String> properties = new HashMap<>();
        for (Option option : cmdLine.getOptions()) {
            String optionName = option.getLongOpt();
            properties.put(optionName, cmdLine.getOptionValue(optionName));
        }

        return new Event(new EventType(command), properties);
    }

    private void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("quickie", options);
    }
}
