package com.subscriber.order.order_subsriber.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MailData {
    ProductModel productModel;
    CustomerModel customerModel;
    OrderModel orderModel;
}
