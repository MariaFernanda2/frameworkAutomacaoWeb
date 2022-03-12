package br.com.itau.frameworkAutomacaoHub.execute.hooks;

import static br.com.itau.frameworkAutomacaoHub.core.DriverFactory.getDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.itau.frameworkAutomacaoHub.core.utils.InputDeDados;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;

public class HookBefore {
	
	@Before(order = 0)
	public static void coletarDados(Scenario scenario) throws IOException {
		
		String pathScenario = scenario.getUri().replace("file:", "");
		
		List<String> allLines = Files.readAllLines(Paths.get(pathScenario));
		int count = 1;
		for (String line : allLines) {
			if (count == scenario.getLine()) {
				InputDeDados.setInput(line);
			}
			count++;
		}
	}

//	@Before(order = 0)
//	public void setarConfiguracoes() {
//		getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//		new WebDriverWait(getDriver(), 10);
//	}

}
