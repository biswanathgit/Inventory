package biwanath.com.inventory.ui.productview;

import android.arch.lifecycle.ViewModel;

import java.util.UUID;

import biwanath.com.inventory.data.productrepository.Product;
import biwanath.com.inventory.data.productrepository.ProductDataSource;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.internal.operators.completable.CompletableFromAction;

/**
 * Created by Biswanath Maity on 10/8/2017.
 */

public class ProductViewModel extends ViewModel {
    private final ProductDataSource mProductDataSource;
    private Product mProduct;

    public ProductViewModel(ProductDataSource mProductDataSource) {
        this.mProductDataSource = mProductDataSource;
    }

    public Flowable<String> getProductName() {
        return mProductDataSource.getProduct()
                // for every emission of the user, get the user name
                .map(product -> {
                    mProduct = product;
                    return product.getProductName();
                });
    }

    public Completable updateProduct(final String productName) {
        return new CompletableFromAction(() -> {
             if(mProduct== null){
                 mProduct = new Product();
                 mProduct.setProductId(UUID.randomUUID().toString());
                 mProduct.setProductName(productName);
             }else {
                 mProduct.setProductId(mProduct.getProductId());
                 mProduct.setProductName(productName);
             }
            mProductDataSource.insertOrUpdateProduct(mProduct);
        });
    }
}
