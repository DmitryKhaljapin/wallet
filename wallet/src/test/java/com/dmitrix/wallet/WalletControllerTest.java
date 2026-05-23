package com.dmitrix.wallet;

import com.dmitrix.wallet.api.model.WalletRequest;
import com.dmitrix.wallet.api.model.WalletResponse;
import com.dmitrix.wallet.application.ErrorMapper;
import com.dmitrix.wallet.application.WalletController;
import com.dmitrix.wallet.application.WalletMapper;
import com.dmitrix.wallet.domain.OperationType;
import com.dmitrix.wallet.domain.commands.UpdateWalletCommand;
import com.dmitrix.wallet.domain.entities.Wallet;
import com.dmitrix.wallet.domain.queries.GetWalletQuery;
import com.dmitrix.wallet.domain.useCases.GetWalletUseCase;
import com.dmitrix.wallet.domain.useCases.UpdateWalletUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WalletController.class)
public class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private WalletMapper mapper;

    @MockitoBean
    private UpdateWalletUseCase updateWalletService;

    @MockitoBean
    private GetWalletUseCase getWalletService;

    @MockitoBean
    private ErrorMapper errorMapper;

    @Test
    void shouldReturnWalletById() throws Exception {
        UUID walletId = UUID.randomUUID();
        Wallet wallet = new Wallet(walletId, new BigDecimal("100.00"));
        GetWalletQuery query = new GetWalletQuery(walletId);
        WalletResponse response = new WalletResponse().walletId(walletId).balance(new BigDecimal("100.00"));

        when(mapper.toQuery(walletId)).thenReturn(query);
        when(mapper.toResponse(wallet)).thenReturn(response);
        when(getWalletService.handle(query)).thenReturn(wallet);

        mockMvc.perform(get("/api/v1/wallets/" + walletId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.walletId").value(walletId.toString()))
                .andExpect(jsonPath(("$.balance")).value(100));

    }

    @Test
    void shouldReturnUpdatedWallet() throws Exception {
        UUID walletId = UUID.randomUUID();
        Wallet updatedWallet = new Wallet(walletId, new BigDecimal("140.00"));
        UpdateWalletCommand command = new UpdateWalletCommand(walletId, new BigDecimal("40.00"), OperationType.DEPOSIT);
        WalletRequest request = new WalletRequest().walletId(walletId).amount(new BigDecimal("40.00")).operationType(WalletRequest.OperationTypeEnum.DEPOSIT);
        WalletResponse response = new WalletResponse().walletId(walletId).balance(new BigDecimal("140.00"));

        when(mapper.toCommand(request)).thenReturn(command);
        when(mapper.toResponse(updatedWallet)).thenReturn(response);
        when(updateWalletService.handle(command)).thenReturn(updatedWallet);

        mockMvc.perform(post("/api/v1/wallet").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.walletId").value(walletId.toString()))
                .andExpect(jsonPath(("$.balance")).value(140));
    }

    @Test
    void shouldReturn400ForInvalidRequest() throws Exception {
        String invalidJson = """
        {
          "walletId": null,
          "operationType": null,
          "amount": null
        }
        """;

        mockMvc.perform(post("/api/v1/wallet").contentType(MediaType.APPLICATION_JSON).content(invalidJson))
                .andExpect(status().isBadRequest());
    }
}
