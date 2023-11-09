package br.com.solutis.squad1.paymentservice.controller;

import br.com.solutis.squad1.paymentservice.dto.PaymentPostCreditCardDto;
import br.com.solutis.squad1.paymentservice.dto.PaymentPostDto;
import br.com.solutis.squad1.paymentservice.model.entity.enums.FormPayment;
import br.com.solutis.squad1.paymentservice.model.entity.enums.StatusPayment;
import br.com.solutis.squad1.paymentservice.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PaymentService service;

    @Test
    @DisplayName("Calls the update method which will update the payment in the order microservice")
    @WithMockUser(authorities = "payment:update:status")
    void updateStatus_ShouldReturnNoContentStatus() throws Exception {
        Long paymentId = 1L;

        mvc.perform(put("/api/v1/payment/" + paymentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(StatusPayment.CONFIRMED)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Returns code 201 if successful")
    @WithMockUser(authorities = "payment:create")
    void save_ShouldReturnCreatedStatus() throws Exception {
        PaymentPostDto dto = createPaymentPostDto();

        mvc.perform(post("/api/v1/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Returns code 400 if no dto is passed fails")
    @WithMockUser(authorities = "payment:create")
    void save_ShouldReturnNoContentStatus() throws Exception {
        mvc.perform(post("/api/v1/payment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Returns code 201 if successful")
    @WithMockUser(authorities = "payment:create:credit-card")
    void saveCreditCard_ShouldReturnCreatedStatus() throws Exception {
        PaymentPostCreditCardDto dto = createPaymentPostCreditCardDto();

        mvc.perform(post("/api/v1/payment/credit-card")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Returns code 400 if no dto is passed fails")
    @WithMockUser(authorities = "payment:create:credit-card")
    void saveCreditCard_ShouldReturnNoContentStatus() throws Exception {
        mvc.perform(post("/api/v1/payment/credit-card")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Returns 204 after successful deletion")
    @WithMockUser(authorities = "payment:delete")
    void deletePayment_ShouldReturnNoContentStatus() throws Exception {
        Long paymentId = 1L;

        mvc.perform(delete("/api/v1/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(paymentId)))
                .andExpect(status().isNoContent());
    }

    private PaymentPostDto createPaymentPostDto(){
        return new PaymentPostDto(
                1L,
                new BigDecimal(200),
                FormPayment.PIX
        );
    }

    private PaymentPostCreditCardDto createPaymentPostCreditCardDto(){
        return new PaymentPostCreditCardDto(
                1L,
                new BigDecimal(200),
                FormPayment.CREDIT_CARD,
                "79927398713",
                "123",
                YearMonth.now().plusYears(1).format(DateTimeFormatter.ofPattern("MM/yy"))
        );
    }
}