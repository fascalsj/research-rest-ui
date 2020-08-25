package com.mitrais.technicaltest.controller;


import com.mitrais.technicaltest.entity.UserEntity;
import com.mitrais.technicaltest.service.RegistrationService;
import org.hibernate.JDBCException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RegistrationService registrationService;

    @Test
    public void registerSuccess() throws Exception {
        //Result from Service
        String firstName = "Fascal";
        String lastName = "Sapty Jarockohir";
        String dateOfBirth = "21/04/1994";
        String phoneNUmber = "0808088928";
        String email = "email@gmail.com";
        String gender = "male";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setGender(gender);
        userEntity.setMobileNumber(phoneNUmber);
        userEntity.setEmail(email);
        userEntity.setBirthDate(formatter.parse(dateOfBirth));


        Mockito.when(registrationService.create(Mockito.any(UserEntity.class)))
                .thenReturn(userEntity);

        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
                .post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"mobile_number\": \"0808088928\",\n" +
                        "    \"first_name\": \"Fascal\",\n" +
                        "    \"last_name\": \"Sapty Jarockohir\",\n" +
                        "    \"email\": \"email@gmail.com\",\n" +
                        "    \"birth_date\": \"21/04/1994\",\n" +
                        "    \"gender\": \"male\"\n" +
                        "}");


        mockMvc.perform(requestBuilders)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().json(
                        "{\n" +
                                "    \"status\": 200,\n" +
                                "    \"message\": \"Registration Success\",\n" +
                                "    \"data\": {\n" +
                                "        \"mobile_number\": \"0808088928\",\n" +
                                "        \"first_name\": \"Fascal\",\n" +
                                "        \"last_name\": \"Sapty Jarockohir\",\n" +
                                "        \"email\": \"email@gmail.com\",\n" +
                                "        \"birth_date\": \"21/04/1994\",\n" +
                                "        \"gender\": \"male\"\n" +
                                "    }\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void registerIllegalArgumentation() throws Exception {
        //Result from Service
        String firstName = "Fascal";
        String lastName = "Sapty Jarockohir";
        String dateOfBirth = "21/04/1994";
        String phoneNUmber = "0808088928";
        String email = "email@gmail.com";
        String gender = "male";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setGender(gender);
        userEntity.setMobileNumber(phoneNUmber);
        userEntity.setEmail(email);
        userEntity.setBirthDate(formatter.parse(dateOfBirth));

        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
                .post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"first_name\": \"Fascal\",\n" +
                        "    \"last_name\": \"Sapty Jarockohir\",\n" +
                        "    \"email\": \"email@gmail.com\",\n" +
                        "    \"birth_date\": \"21/04/1994\",\n" +
                        "    \"gender\": \"male\"\n" +
                        "}");


        mockMvc.perform(requestBuilders)
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void registerErrorJDBCConnection() throws Exception {
        //Result from Service
        String firstName = "Fascal";
        String lastName = "Sapty Jarockohir";
        String dateOfBirth = "21/04/1994";
        String phoneNUmber = "0808088928";
        String email = "email@gmail.com";
        String gender = "male";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setGender(gender);
        userEntity.setMobileNumber(phoneNUmber);
        userEntity.setEmail(email);
        userEntity.setBirthDate(formatter.parse(dateOfBirth));


        Mockito.when(registrationService.create(Mockito.any(UserEntity.class)))
                .thenThrow(JDBCException.class);

        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
                .post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"mobile_number\": \"0808088928\",\n" +
                        "    \"first_name\": \"Fascal\",\n" +
                        "    \"last_name\": \"Sapty Jarockohir\",\n" +
                        "    \"email\": \"email@gmail.com\",\n" +
                        "    \"birth_date\": \"21/04/1994\",\n" +
                        "    \"gender\": \"male\"\n" +
                        "}");


        mockMvc.perform(requestBuilders)
                .andDo(MockMvcResultHandlers.print());

    }


}