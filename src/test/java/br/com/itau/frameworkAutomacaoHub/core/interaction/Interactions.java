package br.com.itau.frameworkAutomacaoHub.core.interaction;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import br.com.itau.frameworkAutomacaoHub.core.exceptions.InteractionsException;
import br.com.itau.frameworkAutomacaoHub.core.interaction.interfaces.IAlert;
import br.com.itau.frameworkAutomacaoHub.core.interaction.interfaces.IAwait;
import br.com.itau.frameworkAutomacaoHub.core.interaction.interfaces.IClick;
import br.com.itau.frameworkAutomacaoHub.core.log.Logger;
import br.com.itau.frameworkAutomacaoHub.core.log.LoggerFactory;

import static br.com.itau.frameworkAutomacaoHub.core.DriverFactory.getDriver;
import static br.com.itau.frameworkAutomacaoHub.enums.ExceptionsMessages.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * Classe de <i>interações</i> genéricas para ser utilizada no padrão
 * <strong>Page Object</strong>.
 *
 * @see InteractionsException
 * @since 1.0
 */
public class Interactions implements IBrowser, IAwait, IClick, IAlert {

    public static final Logger log = LoggerFactory.getLogger(Interactions.class.getSimpleName());

    /**
     * <p>
     * <strong>Função:</strong> acessar uma URL.
     *
     * @param url {@link String string}.
     */
    public void url(String url) {

        log.core(String.format("Método: url() - Acessando o endereço: %s.", url));

        try {
            getDriver().get(url);
        } catch (Exception e) {
            WEBDRIVER.getException(e, url);
        }
    }

    /**
     * <p>
     * <strong>Substitui:</strong>
     * <ul>
     * <li>escrever()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> escreve uma {@link String string} no elemento
     * especificado.
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param text        é a {@link String string} a ser escrita no elemento.
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
    public void write(By by, String text, String description) {
        log.core(String.format("Método: write() - Escrevendo '%s' no elemento %s.", text, description));
        try {
            getDriver().findElement(by).sendKeys(text);

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
     * <li>escreverSlow()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> escreve lentamente uma {@link String string} no
     * elemento especificado.
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param text        é a {@link String string} a ser escrita no elemento.
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
    public void writeSlowly(By by, String text, String description) {
        log.core(String.format("Método: writeSlowly() - Escrevendo lentamente '%s' no elemento %s.", text, description));
        try {
            textClear(by, description);
            WebElement txtValor = getDriver().findElement(by);
            List<String> list = Arrays.asList(text.split(""));
            list.forEach(txtValor::sendKeys);

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
     * <strong>Função:</strong> Limpa o campo e escreve uma {@link String string} no
     * elemento especificado.
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param text        é a {@link String string} a ser escrita no elemento.
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
    public void clearAndWrite(By by, String text, String description) {
        log.core(String.format("Método: clearAndWrite() - Limpando o campo e escrevendo  '%s' no elemento %s.", text, description));
        try {
            awaitElement(by, description);
            backspace(by, text);
            write(by, text, description);

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
     * <strong>Função:</strong> Utiliza o tamanho do texto para utilizar a tecla <strong>BACKSPACE</strong> para apagar.
     *
     * @param by   é o {@link By by} do elemento
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
    public void backspace(By by, String text) {
        log.core(String.format("Método: backspace() - Apagando o texto: %s", text));
        try {
            for (int i = 0; i <= text.length(); i++) {
                getDriver().findElement(by).sendKeys(Keys.BACK_SPACE);
            }

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
     * <p>
     * <strong>Substitui:</strong>
     * <ul>
     * <li>limpar()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> limpa o texto no elemento especificado.
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
    public void textClear(By by, String description) {
        log.core(String.format("Método: textClear() - Apagando o texto no elemento: %s.", description));
        try {
            getDriver().findElement(by).clear();

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
     * <li>isRadioMarcado()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> verifica se o elemento especificado está
     * selecionado/marcado.
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param description é o {@link String nome} do elemento.
     * @return {@link Boolean boolean}
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
    public boolean isRadioSelected(By by, String description) {
        log.core(String.format("Método: isRadioSelected() - Verificando se o elemento %s está marcado/selecionado.", description));
        try {
            return getDriver().findElement(by).isSelected();

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
        return false;
    }

    /**
     * <p>
     * <strong>Substitui:</strong>
     * <ul>
     * <li>obterTexto()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> obtém, loga e retorna o valor textual do elemento
     * especificado ou {@link Null null} em caso de falha.
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param description é o {@link String nome} do elemento.
     * @return {@link String string}
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
    public String getText(By by, String description) {
        log.core(String.format("Método: getText() - Obtendo texto do elemento %s.", description));
        try {
            String text = getDriver().findElement(by).getText();
            log.core(String.format("O texto obtido foi: %s.", text));
            return text;

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
        return null;
    }

    /**
     * <p>
     * <strong>Substitui:</strong>
     * <ul>
     * <li>validaPagina()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> Valida se a página atual é a página desejada e
     * verifica se a mesma está carregada.
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param text        é a {@link String string} validadora à ser comparada com o
     *                    texto obtido do elemento.
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
     * @see #awaitElement
     * @see #getText(By, String)
     */
    public void pageValidation(By by, String text, String description) {
        log.core(String.format("Método: pageValidation() - Validando a página atual pelo elemento: %s.", description));
        try {
            awaitElement(by, description);
            Assert.assertTrue("A página falhou no processo de verificação.", getText(by, description).contains(text));

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
     * <strong>Substitui:</strong>
     * <ul>
     * <li>obterTitleElemento()</li>
     * <li>obterLinkElemento()</li>
     * <li>obterClasseElemento()</li>
     * <li>obterReadOnlyElemento()</li>
     * </ul>
     *
     * <p>
     * <strong>Função:</strong> esta interação tem a função de retornar um atributo
     * específico de um elemento ou {@link Null null} caso o atributo não exista.
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param attribute   é o {@link String atributo} que será obtido.
     * @param description é o {@link String nome} do elemento.
     * @return {@link String string}
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
    public String getAttribute(By by, String attribute, String description) {
        log.core(String.format("Método: getAttribute() - Obtendo o atributo %s do elemento: %s.", attribute, description));
        try {
            String text = getDriver().findElement(by).getAttribute(attribute);
            log.core(String.format("O atributo obtido foi: %s.", text));
            return text;

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
        return null;
    }

    /**
     * @param milissegundos um {@link Integer int} em milissegundos.
     * @see InteractionsException
     * @deprecated <p>
     * Ao invés disso utilize o método
     * {@link #awaitElement(By, String)}.
     * <p>
     * <strong>Substitui:</strong>
     * <ul>
     * <li>aguarda()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> faz com que a thread entre no modo
     * "sleep" por um determinado período de tempo.
     */
    public void wait(int milissegundos) {
        log.core(String.format("Método: wait() - Aguardando implicitamente por %.1f segundos", ((float) milissegundos / 1000)));
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            INTERRUPTED.getException(e, "");
        }
    }

    /**
     * <p>
     * <strong>Substitui:</strong>
     * <ul>
     * <li>elementExists()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> verifica se o elemento especificado está visível na
     * tela.
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param description é o {@link String nome} do elemento.
     * @return {@link Boolean boolean}
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
    public boolean isElementDisplayed(By by, String description) {
        log.core(String.format("Método: isElementDisplayed() - Verificando se o elemento %s está visível.", description));
        try {
            new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(4))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(Exception.class)
                    .until(ExpectedConditions.elementToBeClickable(by));

            return getDriver().findElement(by).isDisplayed();

        } catch (Exception e) {
            log.warn("Método: isElementDisplayed() - o elemento " + description + " não está visível");
            return false;
        }
    }

    /**
     * <strong>Função:</strong> verifica se o elemento especificado está visível na
     * tela.
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param description é o {@link String nome} do elemento.
     * @return {@link Boolean boolean}
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
    public boolean isElementDisplayed(By by, int seconds, String description) {
        log.core(String.format("Método: isElementDisplayed() - Verificando se o elemento %s está visível.", description));
        try {
            new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(seconds))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(Exception.class)
                    .until(ExpectedConditions.elementToBeClickable(by));

            return getDriver().findElement(by).isDisplayed();

        } catch (Exception e) {
            log.warn("Método: isElementDisplayed() - o elemento " + description + " não está visível");
            return false;
        }
    }

    /**
     * <strong>Função:</strong> verifica se o elemento especificado existe na DOM
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param description é o {@link String nome} do elemento.
     * @return {@link Boolean boolean}
     */
    public boolean isElementExists(By by, String description) {
        log.core(String.format("Método: isElementExists() - Verificando se o elemento %s existe na DOM.", description));
        try {
            new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(4))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(Exception.class)
                    .until(ExpectedConditions.elementToBeClickable(by));

            getDriver().findElement(by);
            return true;

        } catch (Exception e) {
            log.warn("Método: isElementExists() - o elemento " + description + " não existe na DOM");
            return false;
        }
    }

    /**
     * <strong>Função:</strong> verifica se o elemento especificado existe na DOM
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param description é o {@link String nome} do elemento.
     * @return {@link Boolean boolean}
     */
    public boolean isElementExists(By by, int seconds, String description) {
        log.core(String.format("Método: isElementExists() - Verificando se o elemento %s existe na DOM.", description));
        try {
            new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofSeconds(seconds))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(Exception.class)
                    .until(ExpectedConditions.elementToBeClickable(by));

            getDriver().findElement(by);
            return true;

        } catch (Exception e) {
            log.warn("Método: isElementExists() - o elemento " + description + " não existe na DOM");
            return false;
        }
    }


    /**
     * <strong>Função:</strong> verifica se o button esta enabled
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param description é o {@link String nome} do elemento.
     * @return {@link Boolean boolean}
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
    public boolean buttonIsEnabled(By by, String description) {
        log.core(String.format("Método: buttonIsEnabled() - Verificando se o button %s está enabled.", description));

        try {
            return getDriver().findElement(by).isEnabled();
        } catch (NoSuchElementException e) {
            NO_SUCH_ELEMENT.getException(e, description);
        } catch (TimeoutException e) {
            TIMEOUT.getException(e, description);
        } catch (ElementNotVisibleException e) {
            NOT_VISIBLE.getException(e, description);
        } catch (Exception e) {
            log.warn("Método: isElementDisplayed() - o elemento " + description + " não está visível");
            return false;
        }
        return false;
    }

    /**
     * <p>
     * <strong>Substitui:</strong>
     * <ul>
     * <li>listaParaClicks()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> cria uma lista de elementos e clica em um deles
     * aleatoriamente.
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param description é o {@link String nome} geral do elementos.
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
    public void randomClickList(By by, String description) {
        log.core(String.format("Método: randomClickList() - Gerando uma lista randômica para clicks nos elementos %s.", description));
        try {

            List<WebElement> elementos = getDriver().findElements(by);
            log.core(String.format("O número de elementos é %d", elementos.size()));
            Integer n = (int) (Math.random() * (elementos.size() - 1));
            log.core(String.format("Selecionando o elemento de número %s.", n.toString()));
            elementos.get(n).click();

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
     * <li>combo()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> seleciona um elemento de uma lista dropdown (combo)
     * através do atributo "value" (e não do texto aparente).
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param value       é o {@link String valor} da opção do combo (e não o seu
     *                    texto).
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
    public void selectComboByValue(By by, String value, String description) {
        log.core(String.format("Método: selectComboByValue() - Selecionando o elemento %s do combo através do valor %s.", description, value));
        try {

            new Select(getDriver().findElement(by)).selectByValue(value);

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
     * <strong>Função:</strong> manipula um elemento do tipo slider apenas em seu
     * eixo horizontal. Caso esse método não funcione, experimente o
     * {@link #sliderSendKeys(By, Integer, String)}
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param number      é o {@link Integer número} utilizado para quantificar o
     *                    movimento do elemento.
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
     * @see #sliderSendKeys(By, Integer, String)
     */
    public void slider(By by, Integer number, String description) {
        log.core(String.format("Método: slider() - movendo o elemento %s %s unidades.", description, number.toString()));
        try {

            new Actions(getDriver()).dragAndDropBy(getDriver().findElement(by), number, 0).build().perform();

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
     * <li>sliderComSeta()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> manipula um elemento do tipo slider apenas em seu
     * eixo horizontal utilizando o método sendKeys para a direita. Caso esse método
     * não funcione, experimente o {@link #slider(By, Integer, String)}
     *
     * @param by          é o {@link By seletor} do elemento.
     * @param repetitions é a {@link Integer quantidade} de vezes que o comando de
     *                    seta será enviada.
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
     * @see #slider(By, Integer, String)
     */
    public void sliderSendKeys(By by, Integer repetitions, String description) {
        log.core(String.format("Método: sliderSendKeys() - movendo o elemento %s com %s repetições de sendKeys.", description, repetitions.toString()));
        try {

            click(by, "Clicando no Slider");
            for (int i = 0; i <= repetitions; i++) {
                getDriver().findElement(by).sendKeys(Keys.ARROW_RIGHT);
            }

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
     * <li>entrarFrame()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> troca o frame atual pelo index especificado no
     * parâmetro.
     *
     * @param index       é o {@link Integer index} do frame a ser acessado.
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
     * @see #switchFrame(String, String)
     */
    public void switchFrame(Integer index, String description) {
        log.core(String.format("Método: entrarFrame() - no elemento índice %s.", index.toString()));
        try {

            getDriver().switchTo().frame(index);

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

        } catch (NoSuchFrameException e) {
            NO_SUCH_FRAME.getException(e, description);

        } catch (Exception e) {
            EXCEPTION.getException(e, description);
        }
    }

    /**
     * <p>
     * <strong>Substitui:</strong>
     * <ul>
     * <li>entrarFrame()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> troca o frame atual pelo nome especificado no
     * parâmetro.
     *
     * @param frame       é o {@link String nome} do frame a ser acessado.
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
     * @see #switchFrame(Integer, String)
     */
    public void switchFrame(String frame, String description) {
        log.core(String.format("Método: entrarFrame() - no elemento de nome %s.", description));
        try {

            getDriver().switchTo().frame(frame);

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

        } catch (NoSuchFrameException e) {
            NO_SUCH_FRAME.getException(e, description);

        } catch (Exception e) {
            EXCEPTION.getException(e, description);
        }
    }

    /**
     * <p>
     * <strong>Função:</strong> retorna para o frame do conteúdo principal.
     *
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
    public void frameDefault() {
        log.core("Método: frameDefault() - Voltando ao conteúdo principal.");
        String description = "Frame Default";

        try {

            getDriver().switchTo().defaultContent();

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

        } catch (NoSuchFrameException e) {
            NO_SUCH_FRAME.getException(e, description);

        } catch (Exception e) {
            EXCEPTION.getException(e, description);
        }
    }

    /**
     * <p>
     * <strong>Função:</strong> atualiza a página e valida.
     *
     * @param by          é o {@link By seletor} que valida a página.
     * @param validation  é o {@link String texto} a ser validado.
     * @param description é a {@link String descrição} do campo a ser validado.
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
    public void refresh(By by, String validation, String description) {
        log.core("Método: refresh() - Atualizando página");

        try {

            getDriver().navigate().refresh();

            pageValidation(by, validation, description);

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

        } catch (NoSuchFrameException e) {
            NO_SUCH_FRAME.getException(e, description);

        } catch (Exception e) {
            EXCEPTION.getException(e, description);
        }

    }

    /**
     * <p>
     * <strong>Função:</strong> atualiza a página e valida.
     * @param description é a {@link String descrição} do campo a ser validado.
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
    public void refresh(String description) {
        log.core("Método: refresh() - Atualizando página");

        try {

            getDriver().navigate().refresh();

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

        } catch (NoSuchFrameException e) {
            NO_SUCH_FRAME.getException(e, description);

        } catch (Exception e) {
            EXCEPTION.getException(e, description);
        }

    }

    /**
     * <p>
     * <strong>Substitui:</strong>
     * <ul>
     * <li>combo()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> verifica quantos elementos existem dentro da lista
     * de elementos.
     *
     * @param by          é o {@link By seletor} da lista de elementos.
     * @param description é o {@link String nome} da lista de elementos.
     * @return {@link int int}
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
    public int sizeListElements(By by, String description) {
        log.core(String.format("Método: sizeListElements() - Contando quantos elemento tem dentro do %s.", description));
        try {
            return getDriver().findElements(by).size();

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
        return 0;
    }

    /**
     * <p>
     * <strong>Substitui:</strong>
     * <ul>
     * <li>combo()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> verifica quantos elementos existem dentro da lista
     * de elementos.
     *
     * @param by          é o {@link By seletor} da lista de elementos.
     * @param description é o {@link String nome} da lista de elementos.
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

    public void moverMouse(By by, String description) {
        log.core(String.format("Método: moverMouse() - movendo mouse %s", description));
        try {

            Actions mouse = new Actions(getDriver());
            WebElement elemento = getDriver().findElement(by);
            mouse.moveToElement(elemento).perform();

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
     * <strong>Esse é um método simplificado do moverMouser()</strong>
     * <strong>Substitui:</strong>
     * <ul>
     * <li>combo()</li>
     * </ul>
     * </p>
     * <strong>Função:</strong> verifica quantos elementos existem dentro da lista
     * de elementos.
     *
     * @param by é o {@link By seletor} da lista de elementos.
     * @see #moverMouse(By, String)
     */
    public void moverMouse(By by) {
        moverMouse(by, "");
    }
}
