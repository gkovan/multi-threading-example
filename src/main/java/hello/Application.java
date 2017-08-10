package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}




	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {


   		return args -> {
			log.info("GK: test message");
			MyRestCall myRestCall = new MyRestCall();
			myRestCall.makeTheCall(restTemplate, log);

			final ExecutorService executorService = Executors.newFixedThreadPool(4);
			final Future<String> res1 = executorService.submit(new MyCallable("thread 1", log));
      final Future<String> res2 = executorService.submit(new MyCallable("thread 2", log));
			final Future<String> res3 = executorService.submit(new MyCallable("thread 3", log));
      final Future<String> res4 = executorService.submit(new MyCallable("thread 4", log));


			String obj1 = res1.get();
      String obj2 = res2.get();
			String obj3 = res3.get();
			String obj4 = res4.get();
			log.info(obj1);
			log.info(obj2);
			log.info(obj3);
			log.info(obj4);
			executorService.shutdown();


/*
			Quote quote = restTemplate.getForObject(
					"http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			log.info(quote.toString());
*/

		};
	}
}
