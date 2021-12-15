package com.bvr;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;



@RestController
public class DemoController {

	public DemoController() {
		// TODO Auto-generated constructor stub
	}

	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	
	@Autowired 
	private CircuitBreakerFactory circuitBreakerFactory;
	
	
	@Autowired
	private HttpBinService httpBin;
	
	
	
	
	public DemoController(CircuitBreakerFactory circuitBreakerFactory, HttpBinService httpBin) {
		super();
		this.circuitBreakerFactory = circuitBreakerFactory;
		this.httpBin = httpBin;
	}

	
	
	@GetMapping("/get")
	public Map get() {
			return httpBin.get();
	}
	
	
	@GetMapping("/delay/{seconds}")
	public Map delay(@PathVariable int seconds) {
		return circuitBreakerFactory.create("delay").run(httpBin.delaySupplier(seconds), t -> {
			logger.warn("DELAY CALL FAILED ERROR", t);
			Map<String, String> fallback = new HashMap<>();
			fallback.put("Hello", "Oracle");
			
			return fallback;
		});
	}
	
	
	
	
	
	
//	private static final String FIRSTSERVICE = "firstService";
	
	
//	@Autowired
//	private RestTemplate restTemplate;
//
//	
//	
//	@GetMapping("/order")
//	@RateLimiter(name=FIRSTSERVICE, fallbackMethod = "rateLimiterFallback")
//	public ResponseEntity<String> createOrder() {
//		String response = restTemplate.getForObject("http://localhost:9000/oracleblr/mypage", String.class);
//		logger.info(LocalTime.now() + "Call Processing Finished = " + Thread.currentThread().getName());
//		return new ResponseEntity<String>(response, HttpStatus.OK);
//	}
//	
//	
//	public ResponseEntity<String> rateLimiterFallback(Exception e) {
//		return new ResponseEntity<String>("Item Service does not permit further calls ", HttpStatus.TOO_MANY_REQUESTS);
//	}
//	
	
}
