package com.libra.service;

import org.springframework.stereotype.Service;

import com.libra.dto.Ciphertext;
import com.libra.dto.ClimbSample;
import com.libra.dto.Digraph;

@Service
public class bodyGenerator {
	public ClimbSample produceBody(Ciphertext ciphertext,Digraph digraph) {
		ClimbSample climbsample = new ClimbSample();
		climbsample.setCiphertext(ciphertext.getCiphertext());
		climbsample.setDigraph(digraph.getDigraph());
		return climbsample;
	}
}
