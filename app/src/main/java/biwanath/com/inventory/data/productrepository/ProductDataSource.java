package biwanath.com.inventory.data.productrepository;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Biswanath Maity on 10/8/2017.
 */

public interface ProductDataSource {

    Flowable<Product> getProduct();

    Flowable<List<Product>> getAllProducts();

    void insertOrUpdateProduct(Product product);

    void deleteAllProduct();
}
