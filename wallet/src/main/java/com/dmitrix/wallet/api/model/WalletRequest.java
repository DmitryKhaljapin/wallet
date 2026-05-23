package com.dmitrix.wallet.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import java.util.UUID;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * WalletRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-22T11:35:35.071607500+03:00[Europe/Moscow]", comments = "Generator version: 7.6.0")
public class WalletRequest {

  private UUID walletId;

  /**
   * Gets or Sets operationType
   */
  public enum OperationTypeEnum {
    DEPOSIT("DEPOSIT"),
    
    WITHDRAW("WITHDRAW");

    private String value;

    OperationTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static OperationTypeEnum fromValue(String value) {
      for (OperationTypeEnum b : OperationTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private OperationTypeEnum operationType;

  private BigDecimal amount;

  public WalletRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public WalletRequest(UUID walletId, OperationTypeEnum operationType, BigDecimal amount) {
    this.walletId = walletId;
    this.operationType = operationType;
    this.amount = amount;
  }

  public WalletRequest walletId(UUID walletId) {
    this.walletId = walletId;
    return this;
  }

  /**
   * Get walletId
   * @return walletId
  */
  @NotNull @Valid 
  @Schema(name = "walletId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("walletId")
  public UUID getWalletId() {
    return walletId;
  }

  public void setWalletId(UUID walletId) {
    this.walletId = walletId;
  }

  public WalletRequest operationType(OperationTypeEnum operationType) {
    this.operationType = operationType;
    return this;
  }

  /**
   * Get operationType
   * @return operationType
  */
  @NotNull 
  @Schema(name = "operationType", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("operationType")
  public OperationTypeEnum getOperationType() {
    return operationType;
  }

  public void setOperationType(OperationTypeEnum operationType) {
    this.operationType = operationType;
  }

  public WalletRequest amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  */
  @NotNull @Valid 
  @Schema(name = "amount", example = "1000.5", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WalletRequest walletRequest = (WalletRequest) o;
    return Objects.equals(this.walletId, walletRequest.walletId) &&
        Objects.equals(this.operationType, walletRequest.operationType) &&
        Objects.equals(this.amount, walletRequest.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(walletId, operationType, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WalletRequest {\n");
    sb.append("    walletId: ").append(toIndentedString(walletId)).append("\n");
    sb.append("    operationType: ").append(toIndentedString(operationType)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

