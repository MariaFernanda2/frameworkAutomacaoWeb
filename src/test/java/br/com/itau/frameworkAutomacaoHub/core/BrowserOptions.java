package br.com.itau.frameworkAutomacaoHub.core;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.opera.OperaOptions;

public class BrowserOptions {

	public static ChromeOptions chromeOptions(boolean headless) {
		ChromeOptions options = new ChromeOptions();

		if (headless) {
			options.setAcceptInsecureCerts(true);
			options.setPageLoadStrategy(PageLoadStrategy.NONE);
			options.addArguments("test-type");
			options.addArguments("enable-automation");
			options.addArguments("--headless");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-extensions");
			options.addArguments("--enable-precise-memory-info");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-default-apps");
			options.addArguments("--disable-infobars");
			options.addArguments("window-size=1920,1080");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-gpu");
			options.addArguments("--no-default-browser-check");

			return options;
		}

		options.setAcceptInsecureCerts(true);

		return options;
	}

	public static FirefoxOptions firefoxOptions(boolean headless) {
		FirefoxOptions options = new FirefoxOptions();
		if (headless) {
			FirefoxBinary binary = new FirefoxBinary();
			binary.addCommandLineOptions("--headless");
			
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
			"application/zip,application/octet-stream,image/jpeg,application/vnd.ms-outlook,text/html,application/pdf, text/xml,application/xml,application/xhtml+xml,text/javascript,application/json");
			profile.setPreference("browser.download.manager.focusWhenStarting", false);
			profile.setPreference("browser.download.manager.useWindow", false);
			profile.setPreference("browser.download.manager.showAlertOnComplete", false);
			
			options.addArguments("window-size=1920,1080");
			options.setPageLoadStrategy(PageLoadStrategy.NONE);
			options.setProfile(profile);
			options.setBinary(binary);
			return options;
		}
		return options;
	}
	
	public static EdgeOptions edgeOptions(boolean headless) {
		EdgeOptions options = new EdgeOptions();
		if (headless) {
		}
		return options;
	}

	public static OperaOptions operaOptions(boolean headless) {
		OperaOptions options = new OperaOptions();
		if (headless) {
		}
		return options;
	}

}
