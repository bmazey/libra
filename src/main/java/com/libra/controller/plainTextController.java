package com.libra.controller;

import com.libra.dto.Ciphertext;
import com.libra.dto.ClimbSample;
import com.libra.dto.Digraph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.libra.service.HttpClient;
import com.libra.service.bodyGenerator;

import io.swagger.models.HttpMethod;
import springfox.documentation.spring.web.json.Json;

@RestController
public class plainTextController {
	@Autowired
	HttpClient httpClient;
	@Autowired
	private bodyGenerator bodygenerator;
	@Value("${url.ciphertext}")
	String Curl;
	@Value("${url.digraph}")
	String Durl;
	@RequestMapping(value = "/api/entrance",method = RequestMethod.GET)
	public ResponseEntity<?> getCipher(@RequestParam("plainText") String plainText) {
		String cipherUrl = Curl + "?plainText=" + plainText;
		String digraphUrl = Durl + "?plainText=" + plainText;
		HttpMethod method = HttpMethod.GET;
		MultiValueMap<Json, Json> params = new LinkedMultiValueMap<Json, Json>();
		Ciphertext ciphertext = httpClient.Cipherclient(cipherUrl, method, params);
		Digraph digraph = httpClient.Digraphclient(digraphUrl, method, params);
		return ResponseEntity.ok(bodygenerator.produceBody(ciphertext, digraph));
	}

}
