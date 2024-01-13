package data;

import models.Payment;
import models.Product;

import java.math.BigDecimal;

public class GetPayment {

    public static Payment getValidBuyNowAndPayLaterPayment() {
        return new Payment("Buy Now Pay Later", "6 monthly installments");
    }

}
