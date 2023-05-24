package org.nvware.core;

import org.nvware.BuildVars;

//        "your condition"? "step if true":"step if condition fails"
public abstract class Commdand {


    public static boolean isHelp(String cmd) {
        return cmd.startsWith("/h") || cmd.startsWith("/help");
    }

    public static boolean is504(String cmd) {
        return cmd.startsWith("/504") || cmd.startsWith("/start") || cmd.startsWith("/menu");
    }

    //"/gt/lang/word" or "/gt/word";
    public static boolean isGoogleTranslator(String cmd) {
        return cmd.startsWith("/gt");
    }

    public static boolean isView(String cmd) {
        return cmd.startsWith("/?") || cmd.startsWith("/view");
    }

    public static boolean isUpdate(String cmd) {
        return cmd.startsWith("/update") || cmd.startsWith("/edit");
    }

    public static boolean isSave(String cmd) {
        return cmd.startsWith("/save") || cmd.startsWith("/add");
    }

    public static boolean isDelete(String cmd) {
        return cmd.startsWith("/del") || cmd.startsWith("/delete");
    }

    public static boolean isNumeric(String cmd) {
        String str = cmd.replace("/", "");
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static boolean islesson(String cmd) {
        if (!isNumeric(cmd))
            return false;
        int lesson = Commdand.toInteger(cmd.replace("/", ""));
        if (lesson >= 1 && lesson <= 42)
            return true;
        return false;
    }

    private static int toInteger(String cmd) {
        if (BuildVars.debug)
            System.out.println("isInteger cmd : " + cmd);
        try {
            // the String to int conversion happens here
            int i = Integer.parseInt(cmd.trim());

            // print out the value after the conversion
            return i;
        } catch (NumberFormatException nfe) {
//            nfe.printStackTrace();
            return -1;
        }
    }

}
