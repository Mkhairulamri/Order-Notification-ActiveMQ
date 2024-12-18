package com.example.order.order.Model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionHandle {
    private boolean status;
    private Map<String, Object> data;
    private String message;
}
