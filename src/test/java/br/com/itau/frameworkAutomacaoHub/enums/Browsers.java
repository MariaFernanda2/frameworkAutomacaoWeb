package br.com.itau.frameworkAutomacaoHub.enums;

import static br.com.itau.frameworkAutomacaoHub.core.BrowserOptions.chromeOptions;
import static br.com.itau.frameworkAutomacaoHub.core.BrowserOptions.edgeOptions;
import static br.com.itau.frameworkAutomacaoHub.core.BrowserOptions.firefoxOptions;
import static br.com.itau.frameworkAutomacaoHub.core.BrowserOptions.operaOptions;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.com.itau.frameworkAutomacaoHub.core.Properties;
import br.com.itau.frameworkAutomacaoHub.core.exceptions.GridException;
import io.github.bonigarcia.wdm.WebDriverManager;

public enum Browsers {
	
	CHROME {
		@Override
		public WebDriver newDriver(boolean headless) {
			if (Properties.GRID) {
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				try {
					return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
				} catch (MalformedURLException e) {
					throw new GridException("Problemas com o Selenium grid");
				}
			} else {
				WebDriverManager.chromedriver().setup();
				return new ChromeDriver(chromeOptions(headless));				
			}
		}
	},
	FIREFOX {
		@Override
		public WebDriver newDriver(boolean headless) {
			if (Properties.GRID) {
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				try {
					return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
				} catch (MalformedURLException e) {
					throw new GridException("Problemas com o Selenium grid");
				}
			} else {
				WebDriverManager.firefoxdriver().setup();
				return new FirefoxDriver(firefoxOptions(headless));				
			}
		}
	},
	
	EDGE {
		@Override
		public WebDriver newDriver(boolean headless) {
			WebDriverManager.edgedriver().setup();
			return new EdgeDriver(edgeOptions(headless));
		}
	},

	OPERA {
		@Override
		public WebDriver newDriver(boolean headless) {
			WebDriverManager.operadriver().setup();
			return new OperaDriver(operaOptions(headless));
		}
	};

    public abstract WebDriver newDriver(boolean headless);
}
