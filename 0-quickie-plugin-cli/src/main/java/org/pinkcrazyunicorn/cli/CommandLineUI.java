package org.pinkcrazyunicorn.cli;

import org.apache.commons.cli.*;
import org.pinkcrazyunicorn.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandLineUI implements UI {
    // map command name -> needed options
    private static Map<String, Set<String>> commands = Map.of(
            "addProfile", Set.of("profile-name")
    );

    private Map<EventType, EventCallback> eventMap;
    private String[] args;
    private Options options;
    private boolean wasRun = false;

    public CommandLineUI(String[] args) {
        this.eventMap = new HashMap<>();
        this.args = args;

        String commandsString = commands.keySet().stream().collect(Collectors.joining(", "));

        this.options = new Options();

        this.options.addOption("n", "profile-name", true, "name of profile to use");
        this.options.addOption("c", "command", true, "command to execute, possible values: [" + commandsString + "]");
    }

    @Override
    public void registerEvent(EventType event, EventCallback callback) {
        this.eventMap.put(event, callback);
    }

    @Override
    public void handleEvent(Event event) {
        EventCallback callback = this.eventMap.get(event.getType());
        EventAnswer answer = callback.call(event.getData());
        System.out.println(answer.getTitle());
        Map<String, String> properties = answer.getProperties();
        for (String property : properties.keySet()) {
            System.out.print(property + ": ");
            System.out.println(properties.get(property));
        }
    }

    @Override
    public Event getUserEvent() {
        if (this.wasRun) {
            return null;
        }
        this.wasRun = true;
        CommandLineParser parser = new DefaultParser();
        CommandLine cmdLine = null;
        try {
            cmdLine = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println("Parsing of command line arguments failed, reason: " + e.getMessage());
            printHelp();
            return null; // TODO: How to handle program exit?
        }

        if (!cmdLine.hasOption("command")) {
            System.out.println("Command must be specified!");
            printHelp();
            return null; // TODO: How to handle program exit?
        }

        String command = cmdLine.getOptionValue("command");

        if (!commands.containsKey(command)) {
            System.out.println("Unknow command " + command);
            printHelp();
            return null; // TODO: How to handle program exit?
        }

        for (String requirement : commands.get(command)) {
            if (!cmdLine.hasOption(requirement)) {
                System.out.println("Error: " + command + " requires option --" + requirement);
                printHelp();
                return null; // TODO: How to handle program exit?
            }
        }

        Map<String, String> properties = new HashMap<>();
        for (Option option : cmdLine.getOptions()) {
            String optionName = option.getLongOpt();
            properties.put(optionName, cmdLine.getOptionValue(optionName));
        }

        return new Event(new EventType(command), properties);
    }

    private void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("quickie", this.options);
    }
}
