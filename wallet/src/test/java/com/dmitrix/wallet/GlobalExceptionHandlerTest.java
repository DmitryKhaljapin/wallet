package com.dmitrix.wallet;

import org.springframework.http.MediaType;
import com.dmitrix.wallet.api.model.ErrorResponse;
import com.dmitrix.wallet.application.ErrorMapper;
import com.dmitrix.wallet.application.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DummyController.class)
@Import(GlobalExceptionHandler.class)
public class GlobalExceptionHandlerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ErrorMapper errorMapper;

    @Test
    void shouldHandleBadJson() throws Exception {
        ErrorResponse error = new ErrorResponse().message("Invalid request body").status(400);

        when(errorMapper.toResponse("Invalid request body", 400))
                .thenReturn(error);

        mockMvc.perform(post("/test/bad-json").contentType(MediaType.APPLICATION_JSON).content("{ invalid json }")                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid request body"))
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void shouldHandleValidationError() throws Exception {

        ErrorResponse error = new ErrorResponse().message("Validation failed").status(400);

        when(errorMapper.toResponse("Validation failed", 400)).thenReturn(error);

        String invalidJson = """
            {
              "walletId": null,
              "operationType": null,
              "amount": null
            }
            """;

        mockMvc.perform(post("/test/bad-json").contentType(MediaType.APPLICATION_JSON).content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void shouldHandle404() throws Exception {
        ErrorResponse error = new ErrorResponse().message("Wallet not found").status(404);

        when(errorMapper.toResponse("Wallet not found", 404)).thenReturn(error);

        mockMvc.perform(get("/test/not-found"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Wallet not found"))
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void shouldHandle422() throws Exception {

        ErrorResponse error = new ErrorResponse().message("Not enough money").status(422);

        when(errorMapper.toResponse("Not enough money", 422)).thenReturn(error);

        mockMvc.perform(get("/test/not-enough-money"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("Not enough money"))
                .andExpect(jsonPath("$.status").value(422));
    }


    @Test
    void shouldHandle500() throws Exception {

        ErrorResponse error = new ErrorResponse().message("Internal server error").status(500);

        when(errorMapper.toResponse("Internal server error", 500)).thenReturn(error);

        mockMvc.perform(get("/test/unknown"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Internal server error"))
                .andExpect(jsonPath("$.status").value(500));
    }
}
