package br.com.itau.frameworkAutomacaoHub.core.log;

public class LoggerFactory {

    private LoggerFactory(){}

    public static Logger getLogger(String className){
        return new Logger(className);
    }
}
