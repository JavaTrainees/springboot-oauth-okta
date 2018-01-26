package com.okta.examples.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HelloController {

	@Value("#{ @environment['okta.redirect.uri'] }")
	protected String oktaRedirectUri;

	@Value("#{ @environment['okta.api.uri'] }")
	protected String oktaApiUri;

	@Value("#{ @environment['security.oauth2.client.clientId'] }")
	protected String oktaApiClientId;

	@RequestMapping("/")
	public String hello() {
		return "home";
	}

	@RequestMapping("/doit")
	public String login(Model model, Principal user) {
		if (user != null) {
			return secure( user);
		}

		model.addAttribute("oktaApiUri", oktaApiUri);
		model.addAttribute("oktaApiClientId", oktaApiClientId);
		model.addAttribute("oktaRedirectUri", oktaRedirectUri);

		return "login";
	}

	@RequestMapping("/secure")
	public String secure(Principal user) {
		// model.addAttribute("user", "Ram");
		return "secure";
	}
}
