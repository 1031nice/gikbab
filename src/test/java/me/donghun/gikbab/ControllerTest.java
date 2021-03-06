package me.donghun.gikbab;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void upload() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/upload"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void search() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/search").param("input", "순두부찌개"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("순두부찌개"));
    }

    @Test
    public void todayDiet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todayDiet"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}