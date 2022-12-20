package com.barkovsky.check_runner.parser;

import com.barkovsky.check_runner.exception.CommandLineArgumentException;
public class CommandLineParser {

    public int getId(String arg) throws CommandLineArgumentException{
        String[] arr = arg.split("-", 2);
        return this.parseStringToInt(arr[0], arg);
    }
    public int getCount(String arg) throws CommandLineArgumentException{
        String[] arr = arg.split("-", 2);
        return this.parseStringToInt(arr[1], arg);
    }
    public int getDiscountCardNumber(String arg) throws CommandLineArgumentException{
        String[] arr = arg.split("-", 2);
        return this.parseStringToInt(arr[1], arg);
    }
    public String getFileName(String arg) {
        String[] arr = arg.split("-", 2);
        return arr[1];
    }
    private int parseStringToInt(String str, String arg) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException exception) {
            throw new CommandLineArgumentException("Incorrect argument: " + arg);
        }
    }

}
