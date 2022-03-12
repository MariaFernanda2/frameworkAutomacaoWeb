package br.com.itau.frameworkAutomacaoHub.core.exceptions;

import static br.com.itau.frameworkAutomacaoHub.core.utils.Utils.formatter;

import br.com.itau.frameworkAutomacaoHub.core.utils.InputDeDados;

public class GridException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message;
	
	public GridException(String message) {
		this.message = String.format("%s - %s", formatter(), message);
	}
		
	@Override
	public String toString() {
	
		return 
		"============================ ERRO ============================"
		+ System.lineSeparator()
		+ System.lineSeparator()
		+ this.getClass().getSimpleName() + ": " + message
		+ System.lineSeparator()
		+ System.lineSeparator()
		+ "====================== MASSA UTILIZADA ======================"
		+ System.lineSeparator()
		+ System.lineSeparator()
		+ InputDeDados.getInput()
		+ System.lineSeparator()
		+ System.lineSeparator()
		+ "=========================== DICAS ==========================="
		+ System.lineSeparator()
		+ System.lineSeparator()
		+ "Verifique se o seu Xpath está correto."
		+ System.lineSeparator()
		+ "Verifique se não existe um Iframe nessa página."
		+ System.lineSeparator()
		+ "Verifique se seu PageObject foi instanciado corretamente em caso de nullPointer."
		+ System.lineSeparator()
		;
	}
	
}
