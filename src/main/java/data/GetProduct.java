package data;

import models.Product;
import models.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GetProduct {

    public static String getProductsTotal(Product productOne, Product productTwo) {
        BigDecimal total = new BigDecimal(productOne.getExpectedTotal())
                .add(new BigDecimal(productTwo.getExpectedTotal()));

        return total.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

}
