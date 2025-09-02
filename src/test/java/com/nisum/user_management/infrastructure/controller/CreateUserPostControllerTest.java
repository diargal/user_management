package com.nisum.user_management.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.user_management.UserManagementApplication;
import com.nisum.user_management.domain.exception.ExistEmailException;
import com.nisum.user_management.domain.model.LoginResponse;
import com.nisum.user_management.domain.model.Phone;
import com.nisum.user_management.domain.model.UserResponse;
import com.nisum.user_management.domain.port.AuthRepository;
import com.nisum.user_management.domain.port.CreateUserRepository;
import com.nisum.user_management.infrastructure.controller.dto.PhoneDto;
import com.nisum.user_management.infrastructure.controller.dto.UserRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest(classes = UserManagementApplication.class)
class CreateUserPostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CreateUserRepository createUserRepository;
    @MockBean
    private AuthRepository authRepository;

    @Test
    @WithMockUser
    void createUserSuccessful() throws Exception {
        UUID id = UUID.randomUUID();
        String name = "Jhon Doe";
        String userEmail = "john.doe.888@example.com";
        String password = "Password123*";
        String cellphone = "+579863333";
        String cityCode = "05757";
        String countryCode = "57";
        String token = "I'm a Token";
        LocalDateTime now = LocalDateTime.now();
        Phone phone = new Phone(cellphone, cityCode, countryCode);
        List<Phone> phones = List.of(phone);
        PhoneDto phoneDto = new PhoneDto(cellphone, cityCode, countryCode);
        List<PhoneDto> phonesDto = List.of(phoneDto);
        UserResponse response = new UserResponse(id, name, userEmail,
                phones, now, now, null, true, token);
        UserRequestDto userRequestDto = new UserRequestDto(name, userEmail, password, phonesDto, true);
        LoginResponse loginResponse = new LoginResponse(token);

        when(createUserRepository.execute(any())).thenReturn(response);
        when(authRepository.execute(any())).thenReturn(loginResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isactive").value(Boolean.TRUE))
                .andDo(print());
    }

    @Test
    void createUserEmailExistsThrownException() throws Exception {
        String name = "Diego Garcia";
        String userEmail = "email_exist@gmail.com";
        String password = "123Pw*541";
        String cellphone = "+579863333";
        String cityCode = "05757";
        String countryCode = "57";
        LocalDateTime now = LocalDateTime.now();
        Phone phone = new Phone(cellphone, cityCode, countryCode);
        List<Phone> phones = List.of(phone);
        PhoneDto phoneDto = new PhoneDto(cellphone, cityCode, countryCode);
        List<PhoneDto> phonesDto = List.of(phoneDto);
        UserRequestDto userRequestDto = new UserRequestDto(name, userEmail, password, phonesDto, true);
        String emailErrorMessage = "El email ya se encuentra registrado.";

        when(createUserRepository.execute(any())).thenThrow(new ExistEmailException(emailErrorMessage));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje")
                        .value(emailErrorMessage))
                .andDo(print());
    }

    @Test
    void createUserEmailFormatThrownException() throws Exception {
        String name = "Jhon Doe";
        String userEmail = "bad_format_email";
        String password = "123Pw*541";
        String cellphone = "+579863333";
        String cityCode = "05757";
        String countryCode = "57";
        PhoneDto phoneDto = new PhoneDto(cellphone, cityCode, countryCode);
        List<PhoneDto> phonesDto = List.of(phoneDto);
        UserRequestDto userRequestDto = new UserRequestDto(name, userEmail, password, phonesDto, true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje")
                        .value("email -> El correo debe seguir una expresión regular para validar" +
                                " que formato sea el correcto.(aaaaaaa@dominio.cl)"))
                .andDo(print());
    }

    @Test
    void createUserEmailEmptyThrownException() throws Exception {
        String name = "Jhon Doe";
        String userEmail = "";
        String password = "123Pw*541";
        String cellphone = "+579863333";
        String cityCode = "05757";
        String countryCode = "57";
        PhoneDto phoneDto = new PhoneDto(cellphone, cityCode, countryCode);
        List<PhoneDto> phonesDto = List.of(phoneDto);
        UserRequestDto userRequestDto = new UserRequestDto(name, userEmail, password, phonesDto, true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje")
                        .value("email -> El correo debe seguir una expresión regular para" +
                                " validar que formato sea el correcto.(aaaaaaa@dominio.cl)"))
                .andDo(print());
    }

    @Test
    void createUserIncorrectPasswordThrownException() throws Exception {
        String name = "Jhon Doe";
        String userEmail = "aaaaaaa@dominio.cl";
        String password = "11233";
        String cellphone = "+579863333";
        String cityCode = "05757";
        String countryCode = "57";
        PhoneDto phoneDto = new PhoneDto(cellphone, cityCode, countryCode);
        List<PhoneDto> phonesDto = List.of(phoneDto);
        UserRequestDto userRequestDto = new UserRequestDto(name, userEmail, password, phonesDto, true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje")
                        .value("password -> La contraseña debe tener al menos 7 caracteres, contener" +
                                " al menos una letra mayúscula, una minúscula, un número y un carácter especial."))
                .andDo(print());
    }

    @Test
    void createUserCityCodeEmptyThrownException() throws Exception {
        String name = "Jhon Doe";
        String userEmail = "aaaaaaa@dominio.cl";
        String password = "123Pw*541";
        String cellphone = "+579863333";
        String cityCode = "";
        String countryCode = "57";
        PhoneDto phoneDto = new PhoneDto(cellphone, cityCode, countryCode);
        List<PhoneDto> phonesDto = List.of(phoneDto);
        UserRequestDto userRequestDto = new UserRequestDto(name, userEmail, password, phonesDto, true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje")
                        .value("phones[0].cityCode -> City code es obligatorio"))
                .andDo(print());
    }

    @Test
    void createUserCountryCodeEmptyThrownException() throws Exception {
        String name = "Jhon Doe";
        String userEmail = "aaaaaaa@dominio.cl";
        String password = "123Pw*541";
        String cellphone = "+579863333";
        String cityCode = "1";
        String countryCode = "";
        PhoneDto phoneDto = new PhoneDto(cellphone, cityCode, countryCode);
        List<PhoneDto> phonesDto = List.of(phoneDto);
        UserRequestDto userRequestDto = new UserRequestDto(name, userEmail, password, phonesDto, true);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensaje")
                        .value("phones[0].countryCode -> Country code es obligatorio"))
                .andDo(print());
    }
}