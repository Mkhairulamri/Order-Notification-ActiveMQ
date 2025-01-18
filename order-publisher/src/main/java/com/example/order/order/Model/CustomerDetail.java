package com.example.order.order.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDetail {
    
    @NotNull(message = "Customer name cannot be Null")
    private String custName;
    
    @NotNull(message = "Customer Phone cannot be Null")
    private String custPhone;

    @NotNull(message = "Customer Email Cannot be Null")
    @Email(message = "Customer Email is Not Valid")
    private String custMail;
}
