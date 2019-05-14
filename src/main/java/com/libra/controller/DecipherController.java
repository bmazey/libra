package com.libra.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.libra.dto.ClimbSample;

@RestController
public class DecipherController{
	@Value("${url.decipher}")
	String DEurl;
	@RequestMapping(value = "/api/decipher", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> performPerfectPlainText(@RequestBody ClimbSample sample) {
		String url = DEurl;
		Gson gson = new Gson();
		String result = "";
		String param = gson.toJson(sample);
//		System.out.println(param);
		try{
			URL realUrl = new URL(url);
			//start connection
			URLConnection conn =  realUrl.openConnection();
			//set configure
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			PrintWriter out = new PrintWriter(conn.getOutputStream());
			//send parameter
			out.print(param);
			out.flush();
			//get respond
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line; 
            }
		} catch (Exception e) {
			System.out.println("POSTexception:" + e);
			e.printStackTrace();
		} 
		
		return ResponseEntity.ok(result);
	}
	

}