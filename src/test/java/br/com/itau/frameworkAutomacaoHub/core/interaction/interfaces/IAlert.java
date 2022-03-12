package br.com.itau.frameworkAutomacaoHub.core.interaction.interfaces;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import br.com.itau.frameworkAutomacaoHub.core.log.Logger;
import br.com.itau.frameworkAutomacaoHub.core.log.LoggerFactory;

import static br.com.itau.frameworkAutomacaoHub.core.DriverFactory.getDriver;
import static br.com.itau.frameworkAutomacaoHub.enums.ExceptionsMessages.*;

import java.time.Duration;

public interface IAlert {
    Logger log = LoggerFactory.getLogger(IAlert.class.getSimpleName());

    default String getTextAlert() {
        log.core("Método: getTextAlert() - Obtendo o texto do alert do navegador");
        String description = "getTextAlert";
        try {

            Alert alert = new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(20000))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(Exception.class)
                    .until(ExpectedConditions.alertIsPresent());

            return alert.getText();

        } catch (TimeoutException e) {
            TIMEOUT.getException(e, description);

        } catch (NoAlertPresentException e){
            NO_ALERT_PRESENT_EXCEPTION.getException(e, description);

        } catch (Exception e) {
            EXCEPTION.getException(e, description);
        }
        return null;
    }

    default void writeAlert(String text) {
        log.core("Método: writePromptAlert() - Escrevendo no alert do navegador: " + text);
        String description = "WriteAlert: " + text;
        try {

            Alert alert = new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(20000))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(Exception.class)
                    .until(ExpectedConditions.alertIsPresent());

            alert.sendKeys(text);

        } catch (TimeoutException e) {
            TIMEOUT.getException(e, description);

        } catch (NoAlertPresentException e){
            NO_ALERT_PRESENT_EXCEPTION.getException(e, description);

        } catch (Exception e) {
            EXCEPTION.getException(e, description);
        }
    }

    default void acceptAlert(boolean accept) {
        log.core("Método: acceptPromptAlert() - Interagindo com o alert do navegador: " + accept);
        String description = "AcceptAlert " + accept;
        try {

            Alert alert = new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(20000))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(Exception.class)
                    .until(ExpectedConditions.alertIsPresent());

            if (accept)
                alert.accept();
            else
                alert.dismiss();

        } catch (TimeoutException e) {
            TIMEOUT.getException(e, description);

        } catch (NoAlertPresentException e){
            NO_ALERT_PRESENT_EXCEPTION.getException(e, description);

        } catch (Exception e) {
            EXCEPTION.getException(e, description);
        }
    }
}
