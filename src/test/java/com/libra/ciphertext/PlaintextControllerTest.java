package com.libra.ciphertext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libra.LibraGatewayApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=LibraGatewayApplication.class)
@AutoConfigureMockMvc
public class PlaintextControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void plaintextControllerPostOk() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/api/plaintext?plaintext=test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void plaintextControllerPostBad() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/api/plaintext?plaintext=test!"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}

