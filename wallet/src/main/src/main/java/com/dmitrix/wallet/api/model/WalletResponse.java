package com.dmitrix.wallet.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.util.UUID;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * WalletResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-22T21:28:03.529419600+03:00[Europe/Moscow]", comments = "Generator version: 7.6.0")
public class WalletResponse {

  private UUID walletId;

  private BigDecimal balance;

  public WalletResponse walletId(UUID walletId) {
    this.walletId = walletId;
    return this;
  }

  /**
   * Get walletId
   * @return walletId
  */
  @Valid 
  @Schema(name = "walletId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("walletId")
  public UUID getWalletId() {
    return walletId;
  }

  public void setWalletId(UUID walletId) {
    this.walletId = walletId;
  }

  public WalletResponse balance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * @return balance
  */
  @Valid 
  @Schema(name = "balance", example = "15000.75", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("balance")
  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WalletResponse walletResponse = (WalletResponse) o;
    return Objects.equals(this.walletId, walletResponse.walletId) &&
        Objects.equals(this.balance, walletResponse.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(walletId, balance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WalletResponse {\n");
    sb.append("    walletId: ").append(toIndentedString(walletId)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

