package hello;

import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;



public class MyRestCall {

/***
  @Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
***/

   public void makeTheCall(RestTemplate restTemplate, Logger log) {
     Quote quote = restTemplate.getForObject(
         "http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
     log.info(quote.toString());
   }



}
