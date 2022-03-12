package br.com.itau.frameworkAutomacaoHub.core;

import br.com.itau.frameworkAutomacaoHub.core.interaction.Interactions;
import br.com.itau.frameworkAutomacaoHub.core.log.Logger;
import br.com.itau.frameworkAutomacaoHub.core.log.LoggerFactory;

/**
 * Uma classe base para todos os PageObjects.
 *
 */
public abstract class BasePage {

    protected Interactions interactions;

    protected final Logger log = getLogger();

    public BasePage() {
        interactions = new Interactions();
    }

    public final Logger getLogger() {
        if (log == null) {
                return LoggerFactory.getLogger(this.getClass().getSimpleName());
        }
        return log;
    }
}