package br.com.itau.frameworkAutomacaoHub.core.interaction.interfaces;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import br.com.itau.frameworkAutomacaoHub.core.exceptions.InteractionsException;
import br.com.itau.frameworkAutomacaoHub.core.log.Logger;
import br.com.itau.frameworkAutomacaoHub.core.log.LoggerFactory;

import static br.com.itau.frameworkAutomacaoHub.core.DriverFactory.getDriver;
import static br.com.itau.frameworkAutomacaoHub.enums.ExceptionsMessages.*;

import java.time.Duration;

public interface IClick extends IAwait {

    Logger log = LoggerFactory.getLogger(IClick.class.getSimpleName());

    /**
     * <p>
     * <strong>Substitui:</strong>
     * <ul>
     * <li>clicar()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> clica no elemento especificado.
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param description é o {@link String nome} do elemento.
     * @throws NoSuchElementException          caso o elemento não existir.
     * @throws TimeoutException                caso exceder o tempo de carregamento
     *                                         do elemento.
     * @throws ElementNotVisibleException      caso a visão do elemento estiver
     *                                         obstruída ou ele estiver oculto.
     * @throws StaleElementReferenceException  caso o elemento não estiver mais
     *                                         visível na DOM.
     * @throws ElementNotInteractableException caso o elemento estiver visível, mas
     *                                         em um estado não interagível.
     * @see InteractionsException
     */
    default void click(By by, String description) {
        log.core(String.format("Método: click() - Clicando no elemento %s", description));
        try {

            awaitElement(by, description);
            getDriver().findElement(by).click();

        } catch (NoSuchElementException e) {
            NO_SUCH_ELEMENT.getException(e, description);

        } catch (TimeoutException e) {
            TIMEOUT.getException(e, description);

        } catch (ElementNotVisibleException e) {
            NOT_VISIBLE.getException(e, description);

        } catch (StaleElementReferenceException e) {
            STALE_REFERENCE.getException(e, description);

        } catch (ElementNotInteractableException e) {
            NOT_INTERACTABLE.getException(e, description);

        } catch (Exception e) {
            EXCEPTION.getException(e, description);
        }
    }

    /**
     * <strong>Função:</strong> clica no elemento baseado no atributo especificado.
     *
     * @param attribute é o {@link String atributo} do elemento.
     * @param value     é o {@link String valor} do atributo.
     * @throws NoSuchElementException          caso o elemento não existir.
     * @throws TimeoutException                caso exceder o tempo de carregamento
     *                                         do elemento.
     * @throws ElementNotVisibleException      caso a visão do elemento estiver
     *                                         obstruída ou ele estiver oculto.
     * @throws StaleElementReferenceException  caso o elemento não estiver mais
     *                                         visível na DOM.
     * @throws ElementNotInteractableException caso o elemento estiver visível, mas
     *                                         em um estado não interagível.
     * @see InteractionsException
     */
    default void clickByAttribute(String attribute, String value) {
        log.core(String.format("Método: clickByAttribute() - Clicando no elemento baseado no atributo %s", attribute));
        try {
            By element = By.xpath(
                    String.format("//*[@%s='%s']", attribute, value)
            );

            awaitElement(element, attribute);
            getDriver().findElement(element).click();

        } catch (NoSuchElementException e) {
            NO_SUCH_ELEMENT.getException(e, attribute);

        } catch (TimeoutException e) {
            TIMEOUT.getException(e, attribute);

        } catch (ElementNotVisibleException e) {
            NOT_VISIBLE.getException(e, attribute);

        } catch (StaleElementReferenceException e) {
            STALE_REFERENCE.getException(e, attribute);

        } catch (ElementNotInteractableException e) {
            NOT_INTERACTABLE.getException(e, attribute);

        } catch (Exception e) {
            EXCEPTION.getException(e, attribute);
        }
    }

    /**
     * <strong>Função:</strong> clica no elemento baseado no texto especificado.
     *
     * @param text é o {@link String texto} do elemento.
     * @throws NoSuchElementException          caso o elemento não existir.
     * @throws TimeoutException                caso exceder o tempo de carregamento
     *                                         do elemento.
     * @throws ElementNotVisibleException      caso a visão do elemento estiver
     *                                         obstruída ou ele estiver oculto.
     * @throws StaleElementReferenceException  caso o elemento não estiver mais
     *                                         visível na DOM.
     * @throws ElementNotInteractableException caso o elemento estiver visível, mas
     *                                         em um estado não interagível.
     * @see InteractionsException
     */
    default void clickByText(String text) {
        log.core(String.format("Método: clickByText() - Clicando no elemento baseado no texto: %s", text));
        try {
            By element = By.xpath(
                    String.format("//*[contains(text(), '%s')]", text)
            );

            awaitElement(element, text);
            getDriver().findElement(element).click();

        } catch (NoSuchElementException e) {
            NO_SUCH_ELEMENT.getException(e, text);

        } catch (TimeoutException e) {
            TIMEOUT.getException(e, text);

        } catch (ElementNotVisibleException e) {
            NOT_VISIBLE.getException(e, text);

        } catch (StaleElementReferenceException e) {
            STALE_REFERENCE.getException(e, text);

        } catch (ElementNotInteractableException e) {
            NOT_INTERACTABLE.getException(e, text);

        } catch (Exception e) {
            EXCEPTION.getException(e, text);
        }
    }

    /**
     * <strong>Função:</strong> clica no elemento baseado no <strong>exato</strong> texto especificado.
     * <p>Este método normaliza o espaçamento do texto procurado.</p>
     *
     * @param text é o {@link String texto} do elemento.
     * @throws NoSuchElementException          caso o elemento não existir.
     * @throws TimeoutException                caso exceder o tempo de carregamento
     *                                         do elemento.
     * @throws ElementNotVisibleException      caso a visão do elemento estiver
     *                                         obstruída ou ele estiver oculto.
     * @throws StaleElementReferenceException  caso o elemento não estiver mais
     *                                         visível na DOM.
     * @throws ElementNotInteractableException caso o elemento estiver visível, mas
     *                                         em um estado não interagível.
     * @see InteractionsException
     */
    default void clickByNormalizeText(String text) {
        log.core(String.format("Método: clickByNormalizeText() - Clicando no elemento baseado no texto normalizado: %s", text));
        try {
            By element = By.xpath(
                    String.format("//*[normalize-space(text())='%s']", text)
            );

            awaitElement(element, text);
            getDriver().findElement(element).click();

        } catch (NoSuchElementException e) {
            NO_SUCH_ELEMENT.getException(e, text);

        } catch (TimeoutException e) {
            TIMEOUT.getException(e, text);

        } catch (ElementNotVisibleException e) {
            NOT_VISIBLE.getException(e, text);

        } catch (StaleElementReferenceException e) {
            STALE_REFERENCE.getException(e, text);

        } catch (ElementNotInteractableException e) {
            NOT_INTERACTABLE.getException(e, text);

        } catch (Exception e) {
            EXCEPTION.getException(e, text);
        }
    }

    /**
     * <strong>Função:</strong> clica no elemento, porém ele não tem tratamento de exceções.
     * <p>Esse método não contém tratativa de exceção.</p>
     * @param by          é o {@link By seletor} do elemento.
     * @param seconds     é o {@link int tempo} do elemento.
     * @param description é o {@link String nome} do elemento.
     */
    default void clickWithoutException(By by, int seconds, String description) {
        log.core(String.format("Método: clickWithoutException() - Clicando no elemento %s", description));

        new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class)
                .until(ExpectedConditions.elementToBeClickable(by));

        getDriver().findElement(by).click();
    }
    
}
