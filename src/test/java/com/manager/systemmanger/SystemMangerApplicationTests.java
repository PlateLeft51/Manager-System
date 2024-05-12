package com.manager.systemmanger;

import com.manager.systemmanger.User.controller.AdminController;
import com.manager.systemmanger.User.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = {AdminController.class, UserController.class})
@AutoConfigureMockMvc
class SystemMangerApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Test
    void addUser() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Role-Info", "ewogICAgInVzZXJJZCI6IDEyMzQ1NiwKICAgICJhY2NvdW50TmFtZSI6ICJYWFhYWFhYIiwKICAgICJyb2xlIjogImFkbWluIgp9Cg==")
                .content("{\n" +
                        "    \"userId\":123456,\n" +
                        "    \"endpoint\" :[\n" +
                        "        \"resource A\",\n" +
                        "        \"resource B\",\n" +
                        "        \"resource C\"\n" +
                        "    ]\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Access granted!"));
    }

    @Test
    void userAccessResouce() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/resource A")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-Role-Info", "ewogICAgInVzZXJJZCI6IDEyMzQ1NiwKICAgICJhY2NvdW50TmFtZSI6ICJYWFhYWFhYIiwKICAgICJyb2xlIjogImFkbWluIgp9Cg=="))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Success"));
    }

}
