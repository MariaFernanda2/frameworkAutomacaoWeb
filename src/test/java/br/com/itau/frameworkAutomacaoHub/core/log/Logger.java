package br.com.itau.frameworkAutomacaoHub.core.log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logger {

    private final String log;

    // ANSI escape code
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30;1m";
    private static final String ANSI_RED = "\u001B[31;1m";
    private static final String ANSI_GREEN = "\u001B[32;1m";
    private static final String ANSI_YELLOW = "\u001B[33;1m";
    private static final String ANSI_BLUE = "\u001B[34;1m";
    private static final String ANSI_PURPLE = "\u001B[35;1m";
    private static final String ANSI_CYAN = "\u001B[36;1m";
    private static final String ANSI_WHITE = "\u001B[37;1m";

    private static final String INFO = ANSI_BLUE + "INFO" + ANSI_RESET;
    private static final String WARN = ANSI_YELLOW + "WARN" + ANSI_RESET;
    private static final String ERROR = ANSI_RED + "ERROR" + ANSI_RESET;

    private static final String DEBUG = ANSI_PURPLE + "DEBUG" + ANSI_RESET;
    private static final String TRACE = ANSI_CYAN + "TRACE" + ANSI_RESET;
    private static final String CORE = ANSI_PURPLE + "CORE" + ANSI_RESET;

    private final String SHOW_LOG;

    private void print(Object any) {
        System.out.println(any);
    }

    private String changeCollorFunctionName(String text) {
        Pattern pattern = Pattern.compile("([a-zA-Z_{1}][a-zA-Z0-9_]+)(?=\\()");
        Matcher matcher = pattern.matcher(text);
        String functionName;

        if (matcher.find()) {
            functionName = matcher.group(1).strip();
            return text.replace(functionName + "()", ANSI_CYAN + functionName +"()" + ANSI_RESET);
        }

        return text;
    }

    private String finalText(String text) {
        return changeCollorFunctionName(text) + ANSI_RESET;
    }

    public Logger(String className) {
        this.log = className;
        this.SHOW_LOG = ANSI_RED + log + ANSI_RESET;
    }

    public void info(String text) {
        this.print("[" + INFO + "] [" + SHOW_LOG + "] - " + finalText(text));
    }

    public void warn(String text) {
        this.print("[" + WARN + "] [" + SHOW_LOG + "] - " + finalText(text));
    }

    public void error(String text) {
        this.print("[" + ERROR + "] [" + SHOW_LOG + "] - " + finalText(text));
    }

    public void debug(String text) {
        this.print("[" + DEBUG + "] [" + SHOW_LOG + "] - " + finalText(text));
    }

    public void trace(String text) {
        this.print("[" + TRACE + "] [" + SHOW_LOG + "] - " + finalText(text));
    }

    public void core(String text) {
        this.print("[" + CORE + "] [" + SHOW_LOG + "] - " + finalText(text));
    }

    public void manual(String text) {
        this.print("\u001B[33;1;4m[MANUAL] [" + this.log + "] - " + finalText(text));
    }
}
