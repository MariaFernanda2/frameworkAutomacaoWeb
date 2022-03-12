package br.com.itau.frameworkAutomacaoHub.core.interaction;

import static br.com.itau.frameworkAutomacaoHub.core.DriverFactory.getDriver;
import static br.com.itau.frameworkAutomacaoHub.enums.ExceptionsMessages.*;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import br.com.itau.frameworkAutomacaoHub.core.exceptions.InteractionsException;
import br.com.itau.frameworkAutomacaoHub.core.log.Logger;
import br.com.itau.frameworkAutomacaoHub.core.log.LoggerFactory;

/**
 * Interface que organiza métodos de manipulação do navegador.
 * 

 */
public interface IBrowser {

	Logger log = LoggerFactory.getLogger(IBrowser.class.getSimpleName());
//	public JavascriptExecutor jse = (JavascriptExecutor) getDriver();
	
	/**
	 * <p><strong>Substitui:</strong>
	 * <ul>
	 * <li>novaAba()</li>
	 * </ul>
	 * <p><strong>Função:</strong> abre uma nova guia no navegador.
	 * @return {@link Void void}
	 */
	default void newTab() {
		log.core("Método: newTab() - Abrindo uma nova guia.");
		((JavascriptExecutor) getDriver()).executeScript("window.open('about:blank', '-blank')");
	}
	
	/**
	 * <p><strong>Substitui:</strong>
	 * <ul>
	 * <li>trocarAba()</li>
	 * </ul>
	 * <p><strong>Função:</strong> abre uma nova guia no navegador.
	 * @param tab é a {@link Integer aba} do navegador que terá foco.
	 * @return {@link Void void}
	 */
	default void switchTab(Integer tab) {
		log.core(String.format("Método: switchTab() - Trocando para a guia %s.", tab.toString()));
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		tabs.forEach(t -> log.core(t));
		getDriver().switchTo().window(tabs.get(tab));
	}
	
	/**
	 * <p><strong>Substitui:</strong>
	 * <ul>
	 * <li>fecharAba()</li>
	 * </ul>
	 * <p><strong>Função:</strong> encerra a guia atual do navegador.
	 * @return {@link Void void}
	 */
	default void closeTab() {
		log.core("Método: closeTab() - Fechando a guia atual.");
		getDriver().close();
	}
	
	/**
	 * <p><strong>Função:</strong> realiza um scroll na tela até o elemento especificado.
	 * @param by é o {@link By seletor} do elemento.
	 * @param description é o {@link String nome} do elemento.
	 * @return {@link Void void}
	 * @throws NoSuchElementException caso o elemento não existir.
	 * @throws TimeoutException caso exceder o tempo de carregamento do elemento.
	 * @throws ElementNotVisibleException caso a visão do elemento estiver obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException caso o elemento não estiver mais visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas em um estado não interagível.
	 * @throws Exception caso ocorra uma exceção não tratada.
	 * @see InteractionsException
	 */
	public default void scroll(By by, String description) {
		log.core(String.format("Método: scroll() - Descendo a tela até o elemento %s.", description));
		try {

			WebElement e = getDriver().findElement(by);
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", e);
			
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
     * <li>scroll()</li>
     * </ul>
     * <p>
     * <strong>Função:</strong> Move a tela até o elemento centralizar
	 *
	 * @param by é o {@link By seletor} do elemento.
	 * @param description é o {@link String nome} do elemento.
	 * @throws NoSuchElementException caso o elemento não existir.
	 * @throws TimeoutException caso exceder o tempo de carregamento do elemento.
	 * @throws ElementNotVisibleException caso a visão do elemento estiver obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException caso o elemento não estiver mais visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas em um estado não interagível.
	 * @throws Exception caso ocorra uma exceção não tratada.
	 * @see InteractionsException
	 */
	default void scrollCenter(By by, String description) {
		log.core(String.format("Método: scrollCenter() - Descendo a tela até o elemento %s centralizar.", description));
		try {
			WebElement element = getDriver().findElement(by);

			String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
														+ "var elementTop = arguments[0].getBoundingClientRect().top;"
														+ "window.scrollBy(0, elementTop-(viewPortHeight/2));";

			((JavascriptExecutor) getDriver()).executeScript(scrollElementIntoMiddle, element);
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
	 * 
	 * <p>
	 * <strong>Função:</strong> atualiza a página e valida.
	 * 
	 * @param by            é o {@link By seletor} que valida a página.
	 * @param validation    é o {@link String texto} a ser validado.
	 * @param description    é a {@link String descrição} do campo a ser validado.
	 * 
	 * 
	 * @return {@link Void void}
	 * @throws NoSuchElementException          caso o elemento não existir.
	 * @throws TimeoutException                caso exceder o tempo de carregamento
	 *                                         do elemento.
	 * @throws ElementNotVisibleException      caso a visão do elemento estiver
	 *                                         obstruída ou ele estiver oculto.
	 * @throws StaleElementReferenceException  caso o elemento não estiver mais
	 *                                         visível na DOM.
	 * @throws ElementNotInteractableException caso o elemento estiver visível, mas
	 *                                         em um estado não interagível.
	 * @throws Exception                       caso ocorra uma exceção não tratada.
	 * @see InteractionsException
	 */
//	public void refresh(By by, String validation, String description) {
//		log.core("Método: refresh() - Atualizando página");
//
//		try {
//
//			getDriver().navigate().refresh();
//
//			pageValidation(by, validation, description);
//
//		} catch (NoSuchElementException e) {
//			NO_SUCH_ELEMENT.getException(e, description);
//
//		} catch (TimeoutException e) {
//			TIMEOUT.getException(e, description);
//
//		} catch (ElementNotVisibleException e) {
//			NOT_VISIBLE.getException(e, description);
//
//		} catch (StaleElementReferenceException e) {
//			STALE_REFERENCE.getException(e, description);
//
//		} catch (ElementNotInteractableException e) {
//			NOT_INTERACTABLE.getException(e, description);
//
//		} catch (NoSuchFrameException e) {
//			NO_SUCH_FRAME.getException(e, description);
//
//		} catch (Exception e) {
//			EXCEPTION.getException(e, description);
//		}
//
//	}
	
	
}
