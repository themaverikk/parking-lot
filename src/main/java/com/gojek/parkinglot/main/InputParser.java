package com.gojek.parkinglot.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InputParser {
    Commands commands;
    static ParkingSpace parkingspace;
    public InputParser() {
        commands = new Commands();
        parkingspace = new ParkingSpace();
    }
    public void parseTextInput(final String inputString) {
        // Split the input string to get command and input value
        final String[] inputs = inputString.split(" ");
        switch (inputs.length) {
            case 1:
                try {
                    final Method method = commands.commandsMap.get(inputString);
                    if (method != null) {
                        method.invoke(parkingspace);
                    } else {
                        System.out.println("Invalid input");
                    }
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                } catch (final InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    final Method method = commands.commandsMap.get(inputs[0]);
                    if (method != null) {
                        method.invoke(parkingspace, inputs[1]);
                    } else {
                        System.out.println("Invalid input");
                    }
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                } catch (final InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    final Method method = commands.commandsMap.get(inputs[0]);
                    if (method != null) {
                        method.invoke(parkingspace, inputs[1], inputs[2]);
                    } else {
                        System.out.println("Invalid input");
                    }
                } catch (final IllegalAccessException e) {
                    e.printStackTrace();
                } catch (final InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Invalid input.");
        }
    }
    public void parseFileInput(final String filePath) {
        // Assuming input to be a valid file path.
        final File inputFile = new File(filePath);
        try {
            final BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    parseTextInput(line.trim());
                }
            } catch (final IOException ex) {
                System.out.println("Error in reading the input file.");
                ex.printStackTrace();
            }
        } catch (final FileNotFoundException e) {
            System.out.println("File not found in the path specified.");
            e.printStackTrace();
        }
    }
}
