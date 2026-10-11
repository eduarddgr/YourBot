
/**

This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

For more information, please refer to <https://unlicense.org/>

**/

/**
 * This bot is incomplete:
 * 	- En passant is not covered
 * 	- Neither is Rocade
 * 
 * -> The bot does get the Rocade move from the server
 * but does not calculate it down the tree...
 * -> En passant (both double as single) are not passed to the bot.
 **/

package org.bamboomy.c44;

import java.io.IOException;
import java.util.Properties;

import org.bamboomy.c44.rest.GamePoller;
import org.bamboomy.c44.rest.Poller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = { "org.bamboomy.c44" })
public class Application extends SpringBootServletInitializer {
	
	public static String BOT_NAME = "OurBot";

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(Application.class);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/index.html").allowedOrigins("http://localhost:8080");
			}
		};
	}

	public static void main(String[] args) throws Exception {

		Properties prop = new Properties();

		String serverUrl = null;

		String identifierHash = null;

		try {

			prop.load(Poller.class.getClassLoader().getResourceAsStream("application.properties"));

			serverUrl = prop.getProperty("server.url");

			System.out.println(prop.getProperty("identifierHash"));

			identifierHash = prop.getProperty("identifierHash");

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		SpringApplication.run(Application.class, args);

		System.out.println("waiting...");

		try {

			Thread.sleep(3_000);

		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		(new Thread(new GamePoller(serverUrl, identifierHash))).start();
	}
}
