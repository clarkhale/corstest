package net.opequon.corsservice;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;


@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	/*
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}*/

	@CrossOrigin(origins = {"http://127.0.0.1","https://static.corstest.apps.cfh-test.sandbox1479.opentlc.com"})
	@GetMapping("/greeting")
    public Greeting greeting(@RequestParam(required=false, defaultValue="World") String name, @RequestHeader Map<String, String> headers, HttpServletRequest hsr) {
		System.out.println("==== in greeting ====");
		System.out.println("Request URL " + hsr.getRequestURL());
		System.out.println("Request URI " + hsr.getRequestURI());
        headers.forEach((key, value) -> {
			System.out.println(String.format("Header '%s' = %s", key, value));
	    });
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/greeting-javaconfig")
	public Greeting greetingWithJavaconfig(@RequestParam(required=false, defaultValue="World") String name) {
		System.out.println("==== in greeting ====");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
