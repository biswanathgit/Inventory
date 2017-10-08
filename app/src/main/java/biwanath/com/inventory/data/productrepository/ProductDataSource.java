package biwanath.com.inventory.data.productrepository;

import io.reactivex.Flowable;

/**
 * Created by Biswanath Maity on 10/8/2017.
 */

public interface ProductDataSource {

    Flowable<Product> getProduct();

    void insertOrUpdateProduct(Product product);

    void deleteAllProduct();
}
