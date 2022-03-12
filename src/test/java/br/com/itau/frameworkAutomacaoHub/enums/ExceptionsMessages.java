package br.com.itau.frameworkAutomacaoHub.enums;

import java.time.LocalDateTime;

import br.com.itau.frameworkAutomacaoHub.core.exceptions.InteractionsException;
import br.com.itau.frameworkAutomacaoHub.core.interaction.Interactions;
import br.com.itau.frameworkAutomacaoHub.core.log.Logger;
import br.com.itau.frameworkAutomacaoHub.core.log.LoggerFactory;

public enum ExceptionsMessages {

	NO_SUCH_ELEMENT {

		@Override
		public void getException(Exception e, String description) {
			String message = String.format("O elemento %s não foi encontrado.", description);
			setException(e, message);
		}
	},

	TIMEOUT {

		@Override
		public void getException(Exception e, String description) {
			String message = String.format("Tempo excedido para encontrar o elemento %s.", description);
			setException(e, message);
		}
	},

	NOT_VISIBLE {

		@Override
		public void getException(Exception e, String description) {
			String message = String.format("A visão do elemento %s está obstruída ou ele está oculto.", description);
			setException(e, message);
		}
	},

	EXCEPTION {

		@Override
		public void getException(Exception e, String description) {
			String message = String.format(
					"Uma exceção inesperada ocorreu no elemento %s." + System.lineSeparator()
							+ "Por favor trate esta exceção no enumerador ExceptionsMessages e na classe Interactions",
					description);
			setException(e, message);
		}
	},

	NOT_INTERACTABLE {

		@Override
		public void getException(Exception e, String description) {
			String message = String.format("O elemento %s não está interagível.", description);
			setException(e, message);
		}
	},

	STALE_REFERENCE {

		@Override
		public void getException(Exception e, String description) {
			String message = String.format("O elemento %s não está mais visível na DOM.", description);
			setException(e, message);
		}
	},

	INTERRUPTED {
		@Override
		public void getException(Exception e, String description) {
			String message = String.format("A thread aguardava implicitamente quando foi interrompida!");
			setException(e, message);
		}
	},
	
	WEBDRIVER {

		@Override
		public void getException(Exception e, String description) {
			String message = String.format("Falha ao se conectar na plataforma para a url de %s.", description);
			setException(e, message);
		}
	},
	
	NO_SUCH_FRAME {

		@Override
		public void getException(Exception e, String description) {
			String message = String.format("O frame %s não existe.", description);
			setException(e, message);
		}
	},
	
	IO_EXCEPTION {
		@Override
		public void getException(Exception e, String description) {
			String message = String.format("Falha ao realizar e armazenar a captura de tela. (%s)", description);
			setException(e, message);
		}
	},

	NO_ALERT_PRESENT_EXCEPTION {
		@Override
		public void getException(Exception e, String description){
			String message = String.format("Alert não encontrado. %s", description);
			setException(e, message);
		}
	};

	protected void setException(Exception e, String message) {
		
		message = (!message.contains("  ")) ? message : message.concat(System.lineSeparator() + "Você esqueceu de descrever o elemento.");
		
		log.error(String.format(
				System.lineSeparator() + 
				System.lineSeparator() + 
				"                    !!! UM ERRO ACONTECEU !!!" + 
				System.lineSeparator() + 
				System.lineSeparator() + 
				"======================= MENSAGEM AMIGÁVEL =======================" + 
				System.lineSeparator() +
				System.lineSeparator() +
				message + 
				System.lineSeparator() + 
				System.lineSeparator() +
				"======================= DESCRIÇÃO TÉCNICA =======================" + 
				System.lineSeparator() +
				System.lineSeparator() + 
				"%s - %s" + 
				System.lineSeparator() + 
				System.lineSeparator() +
				"============================= DICAS =============================" + 
				System.lineSeparator() +
				System.lineSeparator() + 
				"Verifique se o seu Xpath está correto." + 
				System.lineSeparator() +
				"Verifique se não existe um Iframe nessa página." + 
				System.lineSeparator() +
				"Verifique se seu PageObject foi instanciado corretamente em caso de nullPointer." +
				System.lineSeparator() + 
				System.lineSeparator() +
				"====================== QUE OS JOGOS COMECEM ======================" + 
				System.lineSeparator() +
				System.lineSeparator(), 
				LocalDateTime.now(), e.getMessage()));
		
		throw new InteractionsException(message);
	}

	public abstract void getException(Exception e, String description);

	protected static final Logger log = LoggerFactory.getLogger(Interactions.class.getSimpleName());
}
