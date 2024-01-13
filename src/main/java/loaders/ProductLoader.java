package loaders;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Product;
import models.ProductList;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductLoader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Product> loadProductsFromJson(String filePath) {
        try {
            ProductList productList = objectMapper.readValue(new File(filePath), ProductList.class);
            return productList.getProducts();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Product getProductByKey(String filePath, String key) throws NoSuchElementException {
        List<Product> products = loadProductsFromJson(filePath);
        if (products != null) {
            for (Product product : products) {
                if (product.getExpectedItem().equals(key)) {
                    return product;
                }
            }
        }
        throw new NoSuchElementException("El producto " + key + " no existe en el archivo");
    }

}
