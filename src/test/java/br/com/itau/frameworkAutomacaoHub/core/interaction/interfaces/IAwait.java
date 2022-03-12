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
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public interface IAwait {

    Logger log = LoggerFactory.getLogger(IAwait.class.getSimpleName());

    /**
     * <p>
     * <strong>Função:</strong> compara duas unidades de tempo em segundos e retorna
     * uma {@link String mensagem} descrevendo o tempo decorrido.
     *
     * @param before um {@link Instant momento} anterior no tempo.
     * @param after  um {@link Instant momento} posterior no tempo.
     * @return {@link String string}
     * @see #awaitElement(By, String)
     * @see #awaitElement(By, Integer, String)
     */
    default String compareTime(Instant before, Instant after) {
        log.core("Método: compareTime() - Calculando o tempo de carregamento.");
        Long time = ChronoUnit.SECONDS.between(before, after);
        return "O tempo decorrido foi de: " + time.toString();
    }

    /**
     * <p>
     * <strong>Substitui:</strong>
     * <ul>
     * <li>esperarElemento()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> aguarda o tempo de carregamento informado de um
     * elemento, lançando uma exceção caso esse tempo seja ultrapassado. Também
     * informa quanto tempo levou para que o elemento fosse carregado em caso de
     * sucesso.
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param seconds     é a quantidade de {@link Integer segundos} para aguardar.
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
     * @see #compareTime(Instant, Instant)
     * @see #awaitElement(By, String)
     */
    default void awaitElement(By by, Integer seconds, String description) {
        Instant before = Instant.now();
        log.core(String.format("Método: awaitElement() - Aguardando o elemento %s por %s segundos.", description, seconds.toString()));

        try {
            new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(seconds))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(Exception.class)
                    .until(ExpectedConditions.elementToBeClickable(by));
            //new WebDriverWait(getDriver(), seconds).until(ExpectedConditions.elementToBeClickable(getDriver().findElement(by)));
            log.core(compareTime(before, Instant.now()));

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
     * <p>
     * <strong>Substitui:</strong>
     * <ul>
     * <li>esperarElemento()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> aguarda o tempo de carregamento de um elemento por
     * 15 segundos, lançando uma exceção caso esse tempo seja ultrapassado. Também
     * informa quanto tempo levou para que o elemento fosse carregado em caso de
     * sucesso.
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param description é o {@link String nome} do elemento.
     * @throws NoSuchElementException          caso o elemento não existir.
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
     * @see #compareTime(Instant, Instant)
     * @see #awaitElement(By, Integer, String)
     */
    default void awaitElement(By by, String description) {
        Instant before = Instant.now();
        log.core(String.format("Método: awaitElement() - Aguardando o elemento %s por 15 segundos.", description));

        try {

//			WebDriverWait wait = new WebDriverWait(getDriver(), 20);
//			wait.until(ExpectedConditions.elementToBeClickable(by));
            new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofMillis(20000))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.elementToBeClickable(by));

            log.core(compareTime(before, Instant.now()));

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
     * <p>
     * <strong>Substitui:</strong>
     * <ul>
     * <li>esperarElemento()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> aguarda o tempo de carregamento de um elemento por
     * 20 segundos, lançando uma exceção caso esse tempo seja ultrapassado. Também
     * informa quanto tempo levou para que o elemento fosse carregado em caso de
     * sucesso.
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param timeout	  é a duração da espera pela busca do elemento.
     * @param polling	  é o tempo de tentativa de busca do elemento.
     * @param description é o {@link String nome} do elemento.
     * @throws NoSuchElementException          caso o elemento não existir.
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
     * @see #compareTime(Instant, Instant)
     * @see #awaitElement(By, Integer, String)
     */
    default void awaitElement(By by, int timeout, int polling, String description) {
        Instant before = Instant.now();
        log.core(String.format("Método: awaitElement() - Aguardando o elemento %s por %d segundos.", description, timeout));

        try {

            new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(timeout))
                    .pollingEvery(Duration.ofSeconds(polling))
                    .ignoring(Exception.class)
                    .until(ExpectedConditions.elementToBeClickable(by));

            log.core(compareTime(before, Instant.now()));

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
}
