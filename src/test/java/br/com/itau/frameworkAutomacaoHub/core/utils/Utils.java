package br.com.itau.frameworkAutomacaoHub.core.utils;

import static br.com.itau.frameworkAutomacaoHub.enums.ExceptionsMessages.IO_EXCEPTION;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;

import br.com.itau.frameworkAutomacaoHub.core.interaction.Interactions;
import br.com.itau.frameworkAutomacaoHub.core.log.Logger;
import br.com.itau.frameworkAutomacaoHub.core.log.LoggerFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Utils {

	private static final File fileSuccess = new File(pathScreenshot() + "sucesso");
	private static final File fileError = new File(pathScreenshot() + "erro");

	private static final Logger log = LoggerFactory.getLogger(Utils.class.getSimpleName());

	public static void fullPageScreenshot(String path, String nome, WebDriver driver) {
		log.core(String.format("Método: fullPageScreenshot() - Tirando Screenshot do arquivo %s", nome));

		try {
			new Interactions().frameDefault();
			Screenshot screenshot = new AShot()
					.shootingStrategy(
							ShootingStrategies
							.viewportPasting(1000)
					).takeScreenshot(driver);

			ImageIO.write(
					screenshot.getImage(),
					"PNG",
					new File(String.format("%s_%s.png", path, nome))
			);
			log.core(String.format("Salvando a Printscreen no Caminho %s", path));
			log.core(String.format("O nome da Printscreen é %s", nome));

		} catch (IOException e) {
			IO_EXCEPTION.getException(e, "");
		}
	}

	public static String formatter() {
		return DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss").format(LocalDateTime.now());
	}

	public static String pathScreenshot() {
		return "target" + File.separator + "evidencias"	+ File.separator + "screenshots" + File.separator;
	}

	public static void deleteFiles() {
		if (Objects.requireNonNull(fileSuccess.listFiles()).length > 0)
			Arrays.stream(Objects.requireNonNull(fileSuccess.listFiles()))
				.forEach(f -> {
					if(!f.delete())
						log.error("Erro ao tentar esvaziar a pasta de Evidencias/Sucesso");
				});

		if (Objects.requireNonNull(fileError.listFiles()).length > 0)
			Arrays.stream(Objects.requireNonNull(fileError.listFiles()))
				.forEach(f -> {
					if(!f.delete())
						log.error("Erro ao tentar esvaziar a pasta de Evidencias/Erro");
				});
	}

	public static void createFiles() {
		if(new File(pathScreenshot()).mkdirs())
			log.core("Criando diretório de Screenshots");

		if(fileSuccess.mkdirs())
			log.core("Criando subdiretório de erro em Screenshots");

		if(fileError.mkdirs())
			log.core("Criando subdiretório de sucesso em Screenshots");
	}

	public static Properties getProperties(){
		Properties props = new Properties();

		String fileName = "options.properties";

		try {
			log.core("Fazendo leitura da properties");
			props.load(Utils.class.getClassLoader().getResourceAsStream(fileName));
		} catch (IOException e) {
			log.error("Erro ao tentar ler as propriedades");
			e.printStackTrace();
		}

		return props;
	}

	public static boolean getOption(String property){
		return Boolean.parseBoolean( System.getenv( property ) )
				||
				Boolean.parseBoolean( Utils.getProperties().getProperty( property ) );
	}

	public static String getValueProps(String property){
		if (System.getenv(property) != null)
			return System.getenv( property );

		return Utils.getProperties().getProperty( property );
	}
}
