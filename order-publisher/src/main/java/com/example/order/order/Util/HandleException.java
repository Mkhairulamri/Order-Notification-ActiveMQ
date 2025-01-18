package com.example.order.order.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.example.order.order.Model.ExceptionHandle;

@Component
public class HandleException {

    ExceptionHandle result = new ExceptionHandle();

    public ExceptionHandle checkPartner(String partnerId) {
        if (partnerId.isEmpty() || partnerId == null) {
            result.setStatus(true);
            result.setData(null);
            result.setMessage("Missing Mandatory");
        } else if (!partnerId.substring(0, 3).equals("TEST")) {
            Map<String, Object> data = new HashMap<>();
            data.put("partnerId", partnerId);
            result.setStatus(false);
            result.setData(data);
            result.setMessage("Unnauthorized, Partner ID not registred in our System");
        } else {
            result.setStatus(true);
            result.setData(null);
            result.setMessage("Unnauthorized Request");
        }
        return result;
    }

    public ExceptionHandle checkData(Map<String, Object> data) {

        if (data.get("custName").toString().equals(null)) {
            result.setStatus(false);
            result.setMessage("customer_name is null");
            return result;
        }

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (data.get("email").toString().equals(null) || Pattern.matches(emailRegex, data.get("custMail").toString())) {
            result.setStatus(false);
            result.setMessage("email is null or Invalid");
            return result;
        }

        if (data.get("mailPhone").toString().equals(null)) {
            result.setStatus(false);
            result.setMessage("phone_no is null");
            return result;
        }

        result.setStatus(true);
        return result;
    }

    public ExceptionHandle checkOrder(Map<String, Object> order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkOrder'");
    }

}
