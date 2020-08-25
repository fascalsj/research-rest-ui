package com.mitrais.technicaltest.controller;


import com.mitrais.technicaltest.entity.AccountEntity;
import com.mitrais.technicaltest.enumerator.Gender;
import com.mitrais.technicaltest.service.RegistrationService;
import org.hibernate.JDBCException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    private RegistrationService registrationService;

    private String firstName = "Fascal";
    private String lastName = "Sapty Jarockohir";
    private String dateOfBirth = "21/04/1994";
    private String mobileNumber = "0808088928";
    private String email = "email@gmail.com";
    private Gender genderEnum = Gender.MALE;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    public void registerSuccess() throws Exception {
        AccountEntity userEntity = new AccountEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setGender(genderEnum);
        userEntity.setMobileNumber(mobileNumber);
        userEntity.setEmail(email);
        userEntity.setBirthDate(formatter.parse(dateOfBirth));


        Mockito.when(registrationService.create(Mockito.any(AccountEntity.class)))
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
                        "    \"gender\": \"MALE\"\n" +
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
                                "        \"gender\": \"MALE\"\n" +
                                "    }\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void registerIllegalArgumentation() throws Exception {
        //For Handle Duplication email or mobile number
        AccountEntity userEntity = new AccountEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setGender(genderEnum);
        userEntity.setMobileNumber(mobileNumber);
        userEntity.setEmail(email);
        userEntity.setBirthDate(formatter.parse(dateOfBirth));

        Mockito.when(registrationService.create(Mockito.any(AccountEntity.class))).thenThrow(IllegalArgumentException.class);

        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
                .post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"first_name\": \"Fascal\",\n" +
                        "    \"last_name\": \"Sapty Jarockohir\",\n" +
                        "    \"mobileNumber\": \"0808088928\",\n" +
                        "    \"email\": \"email@gmail.com\",\n" +
                        "    \"birth_date\": \"21/04/1994\",\n" +
                        "    \"gender\": \"male\"\n" +
                        "}");
        mockMvc.perform(requestBuilders)
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void registerHttpMessageNotReadableExceptionTest() throws Exception {
        //Validation for Enum
        AccountEntity userEntity = new AccountEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setGender(genderEnum);
        userEntity.setMobileNumber(mobileNumber);
        userEntity.setEmail(email);
        userEntity.setBirthDate(formatter.parse(dateOfBirth));

        Mockito.when(registrationService.create(Mockito.any(AccountEntity.class)))
                .thenThrow(HttpMessageNotReadableException.class);

        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
                .post("/register")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilders)
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void registerErrorJDBCConnection() throws Exception {
        //Error in Database
        Mockito.when(registrationService.create(Mockito.any(AccountEntity.class)))
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
                        "    \"gender\": \"MALE\"\n" +
                        "}");

        mockMvc.perform(requestBuilders)
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void checkingEmailUnique() throws Exception {
        Mockito.when(registrationService.findByEmail(Mockito.anyString()))
                .thenReturn(email);

        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
                .get("/register")
                .param("email",email);

        mockMvc.perform(requestBuilders)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(email))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void checkingMobileNumberUnique() throws Exception {
        Mockito.when(registrationService.findByMobileNumber(Mockito.anyString()))
                .thenReturn(mobileNumber);

        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders
                .get("/register")
                .param("mobileNumber",mobileNumber);

        mockMvc.perform(requestBuilders)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(mobileNumber))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }




}