package biwanath.com.inventory.data.productrepository;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Biswanath Maity on 10/8/2017.
 */

public class LocalProductDataSource implements ProductDataSource {
   private final ProductDao mProductDao;

    public LocalProductDataSource(ProductDao mProductDao) {
        this.mProductDao = mProductDao;
    }

    @Override
    public Flowable<Product> getProduct() {
        return mProductDao.getProduct();
    }

    @Override
    public Flowable<List<Product>> getAllProducts() {
        return mProductDao.getAllProduct();
    }

    @Override
    public void insertOrUpdateProduct(Product product) {
        mProductDao.insertProduct(product);
    }

    @Override
    public void deleteAllProduct() {
        mProductDao.deleteAllUsers();
    }
}
