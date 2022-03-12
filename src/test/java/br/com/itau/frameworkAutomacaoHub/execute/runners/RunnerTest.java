package br.com.itau.frameworkAutomacaoHub.execute.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import br.com.itau.frameworkAutomacaoHub.core.utils.Utils;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = "br.com.cyber.frameworkSinistro.execute",
        
        tags = "",
        
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "json:target/evidencias/json/report.json",
        },
        snippets = SnippetType.CAMELCASE,
        dryRun = false,
        strict = true
)

public class RunnerTest {

    @BeforeClass
    public static void screenshotFileCheck() {
        Utils.createFiles();
        Utils.deleteFiles();
    }
}
