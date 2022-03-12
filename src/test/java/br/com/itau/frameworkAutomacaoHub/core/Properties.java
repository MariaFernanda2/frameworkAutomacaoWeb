package br.com.itau.frameworkAutomacaoHub.core;

import br.com.itau.frameworkAutomacaoHub.core.utils.Utils;
import br.com.itau.frameworkAutomacaoHub.enums.Browsers;

public class Properties {
	
	public static boolean CLOSE = Utils.getOption("CLOSE");
	public static boolean HEADLESS = Utils.getOption("HEADLESS");
	public static boolean GRID = Utils.getOption("GRID");

	public static Browsers BROWSER = Browsers.valueOf(Utils.getValueProps("BROWSER"));
}
