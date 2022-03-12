package br.com.itau.frameworkAutomacaoHub.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.itau.frameworkAutomacaoHub.enums.Browsers;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PropertiesTest {

	boolean close() default false;

	boolean headless() default false;

	boolean grid() default false;

	Browsers browser() default Browsers.CHROME;
}
