package com.example.order.order.Util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.order.order.Model.ExceptionHandle;

@Component
public class HandleException {

    ExceptionHandle result = new ExceptionHandle();

    public ExceptionHandle checkPartner(String partnerId) {
        if (!partnerId.substring(0, 3).equals("TEST")) {
            Map<String, Object> data = new HashMap<>();
            data.put("partnerId", partnerId);
            result.setStatus(false);
            result.setData(data);
            result.setMessage("Invalid Partner ID");
        } else {
            result.setStatus(true);
            result.setData(null);
            result.setMessage(null);
        }
        return result;
    }

    public ExceptionHandle checkData(Map<String, Object> data) {

        if (data.get("customer_name").toString().equals(null)) {
            result.setStatus(false);
            result.setMessage("customer_name is null");
            return result;
        }

        if (data.get("email").toString().equals(null)) {
            result.setStatus(false);
            result.setMessage("email is null");
            return result;
        }

        if (data.get("phone_no").toString().equals(null)) {
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
