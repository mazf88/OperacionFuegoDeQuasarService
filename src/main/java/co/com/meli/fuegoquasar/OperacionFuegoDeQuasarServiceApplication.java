package co.com.meli.fuegoquasar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "co.com.meli.fuegoquasar.*" })
@RefreshScope
@SpringBootApplication
public class OperacionFuegoDeQuasarServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperacionFuegoDeQuasarServiceApplication.class, args);
	}

}