package org.pinkcrazyunicorn.quickie.plugins.cli;

import org.apache.commons.cli.*;
import org.pinkcrazyunicorn.quickie.adapters.UI;
import org.pinkcrazyunicorn.quickie.adapters.event.*;

import java.util.*;

public class CommandLineUI implements UI {
    private final Map<EventType, EventCallback> eventMap;
    private final List<String> commands;
    private final Map<String, Collection<EventParameter>> requiredParametersFor;
    private final Set<EventParameter> requiredParameters;
    private final String[] args;
    private boolean wasRun = false;

    public CommandLineUI(String[] args) {
        this.eventMap = new HashMap<>();
        this.args = args;
        this.commands = new ArrayList<>();
        this.requiredParametersFor = new HashMap<>();
        this.requiredParameters = new HashSet<>();
    }

    @Override
    public void registerEvent(EventType event, EventCallback callback) {
        this.eventMap.put(event, callback);
        this.commands.add(event.getName());
        this.requiredParameters.addAll(callback.getRequiredParameters());
        this.requiredParametersFor.put(event.getName(), callback.getRequiredParameters());
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

    private Options constructOptions() {
        Options options = new Options();
        String commandsString = String.join(", ", commands);
        options.addOption("c", "command", true, "command to execute, possible values: [" + commandsString + "]");

        for (EventParameter parameter : this.requiredParameters) {
            options.addOption(parameter.getName().substring(0, 1), parameter.getName(), true, parameter.getDescription());
        }

        return options;
    }

    @Override
    public Event getUserEvent() {
        if (this.wasRun) {
            return null; // TODO: How to handle program exit?
        }
        this.wasRun = true;

        Options options = this.constructOptions();

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
            System.out.println("Unknown command " + command);
            printHelp(options);
            return null; // TODO: How to handle program exit?
        }

        if (!this.ensureRequiredParameters(command, cmdLine)) {
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

    private boolean ensureRequiredParameters(String command, CommandLine cmdLine) {
        for (EventParameter parameter : this.requiredParametersFor.get(command)) {
            if (!cmdLine.hasOption(parameter.getName())) {
                System.out.println("'" + parameter.getName() + "' must be specified");
                return false;
            }
        }
        return true;
    }

    private void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("quickie", options);
    }
}
