package br.com.itau.frameworkAutomacaoHub.execute.hooks;

import static br.com.itau.frameworkAutomacaoHub.core.DriverFactory.getDriver;
import static br.com.itau.frameworkAutomacaoHub.core.DriverFactory.killDriver;
import static br.com.itau.frameworkAutomacaoHub.core.utils.Utils.formatter;
import static br.com.itau.frameworkAutomacaoHub.core.utils.Utils.fullPageScreenshot;
import static br.com.itau.frameworkAutomacaoHub.core.utils.Utils.pathScreenshot;

import java.io.File;

import br.com.itau.frameworkAutomacaoHub.core.Properties;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;

public class HookAfter {
	
	@After(order = 1)
	public void screenshot(Scenario scenario) {

		String nome = String.format("%s_%s", scenario.getName().replace(" ", "_"), formatter()); 
		String evidencia = (!scenario.isFailed()) ? evidencia = "sucesso" : "erro";
		String caminho = pathScreenshot() + evidencia + File.separator;

		fullPageScreenshot(caminho, nome, getDriver());
	}

	@After(order = 0)
	public void finalizar() {
		if (Properties.CLOSE)
			killDriver();
	}
}
