package com.example.order.order.Model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequest {
    @NotNull(message = "Transaction ID cannot be Null")
    private String transactionId;

    @NotNull(message = "Customer Detail cannot be null")
    private CustomerDetail custDetail;

}
