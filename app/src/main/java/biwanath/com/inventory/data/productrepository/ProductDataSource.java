package biwanath.com.inventory.data.productrepository;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Biswanath Maity on 10/8/2017.
 */

public interface ProductDataSource {

    Flowable<Product> getProduct();

    Flowable<List<Product>> getAllProducts();

    long insertOrUpdateProduct(Product product);

    //update Firebase product key
    void updateProductFbKey(String pId, String fbkey);

    //delete product on product id or firebase key
    void deleteProduct(String productId,String firebaseKey);

    void deleteAllProduct();

}
