package com.example.demo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(classes = Demo1Application.class)
@AutoConfigureMockMvc
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // 如果需要，可以在这里进行任何设置
    }

    @Test
    public void add_PositiveNumbers_ReturnsSum() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/add")
                .param("a", "5")
                .param("b", "10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("15"));
    }

    @Test
    public void add_NegativeNumbers_ReturnsSum() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/add")
                .param("a", "-5")
                .param("b", "-10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("-15"));
    }

    @Test
    public void add_MixedNumbers_ReturnsSum() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/add")
                .param("a", "5")
                .param("b", "-10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("-5"));
    }

    @Test
    public void add_ZeroValues_ReturnsZero() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/add")
                .param("a", "0")
                .param("b", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0"));
    }

    @Test
    public void add_MaxAndMinValues_ReturnsSum() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/add")
                .param("a", String.valueOf(Integer.MAX_VALUE))
                .param("b", String.valueOf(Integer.MIN_VALUE)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0"));
    }
}
