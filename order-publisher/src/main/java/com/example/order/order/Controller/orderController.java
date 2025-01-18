package com.example.order.order.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.order.Bean.getConnection;
import com.example.order.order.Model.ExceptionHandle;
import com.example.order.order.Model.OrderRequest;
import com.example.order.order.Service.PubliserOrder;
import com.example.order.order.Util.HandleException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class orderController {

    private static final Logger logger = LoggerFactory.getLogger(orderController.class);

    @Autowired
    private HandleException exceptions;

    @Autowired
    PubliserOrder publiser = new PubliserOrder();

    @PostMapping("/subscibe-order")
    public ResponseEntity<ExceptionHandle> subscibeOder(@Valid @RequestBody OrderRequest orderRequest,
            @RequestHeader(value = "PARTNER-ID", required = false) String partnerId) {
        logger.info("Process Service Subscibe Order");
        ExceptionHandle result = new ExceptionHandle();

        ExceptionHandle checkPartner = exceptions.checkPartner(partnerId);

        try {
            if (!checkPartner.isStatus()) {
                return new ResponseEntity<ExceptionHandle>(checkPartner, HttpStatus.FORBIDDEN);
            }
            if(getConnection.checkConnection()){
                publiser.pushOrder(orderRequest);
            }else{
                result.setStatus(false);
                result.setData(null);
                result.setMessage("Internal Server Error");
                return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);   
            }

            return new ResponseEntity<ExceptionHandle>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.setStatus(false);
            result.setData(null);
            result.setMessage("Internal Server Error");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
