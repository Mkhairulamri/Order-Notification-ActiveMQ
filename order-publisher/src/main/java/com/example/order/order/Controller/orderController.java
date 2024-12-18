package com.example.order.order.Controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.order.Model.ExceptionHandle;
import com.example.order.order.Service.PubliserOrder;
import com.example.order.order.Util.HandleException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class orderController {

    private static final Logger logger = LoggerFactory.getLogger(orderController.class);

    @Autowired
    private HandleException exceptions;

    @Autowired
    PubliserOrder publiser = new PubliserOrder();

    @PostMapping("/subscibe-order")
    public ResponseEntity<ExceptionHandle> subscibeOder(@RequestBody String param,
            @RequestHeader(value = "PARTNER-ID", required = false) String partnerId) {
        logger.info("Process Service Subscibe Order");
        ExceptionHandle result = new ExceptionHandle();
        ObjectMapper mapper = new ObjectMapper();

        ExceptionHandle checkPartner = exceptions.checkPartner(partnerId);

        try {
            if (!checkPartner.isStatus()) {
                return new ResponseEntity<ExceptionHandle>(checkPartner, HttpStatus.FORBIDDEN);
            }

            @SuppressWarnings("unchecked")
            Map<String, Object> mapParam = mapper.readValue(param, Map.class);

            @SuppressWarnings("unchecked")
            ExceptionHandle checkData = exceptions.checkData((Map<String, Object>) mapParam.get("data"));

            if (!checkData.isStatus()) {
                return new ResponseEntity<>(checkData, HttpStatus.BAD_REQUEST);
            }

            @SuppressWarnings("unchecked")
            ExceptionHandle checkOrder = exceptions.checkOrder((Map<String, Object>) mapParam.get("order"));

            if (!checkOrder.isStatus()) {
                return new ResponseEntity<>(checkOrder, HttpStatus.BAD_REQUEST);
            }

            mapParam.put("partnerId", partnerId);
            publiser.pushOrder(mapParam);

            return new ResponseEntity<ExceptionHandle>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.setStatus(false);
            result.setData(null);
            result.setMessage("Internal Server Error");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
