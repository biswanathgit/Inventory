package biwanath.com.inventory.ui.productview.productentry;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import biwanath.com.inventory.data.productrepository.Product;
import biwanath.com.inventory.data.productrepository.ProductDataSource;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.internal.operators.completable.CompletableFromAction;

import static biwanath.com.inventory.ui.productview.showproduct.ProductActivity.TAG;

/**
 * Created by Biswanath Maity on 10/8/2017.
 */

public class ProductEntryViewModel extends ViewModel {
    public static final String TAG = "ProductEntryViewModel";
    private final ProductDataSource mProductDataSource;
    private Product mProduct;

    public ProductEntryViewModel(ProductDataSource mProductDataSource) {
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

    public Completable insertProduct(final String productId,String productName,String description, long price) {
        return new CompletableFromAction(() -> {
            Product newProduct = new Product();
            newProduct.setProductId(productId);
            newProduct.setProductName(productName);
            newProduct.setDescription(description);
            newProduct.setProductPrice(price);
            long t =mProductDataSource.insertOrUpdateProduct(newProduct);
            Log.d(TAG, "insertProduct: " +t);
        });
    }

    public Completable updateProductWithFbkey(final String id,String fbKey) {
        return new CompletableFromAction(() -> {
            //Product updateProduct = new Product();
            //updateProduct.setProductId(id);
            //updateProduct.setFirebaseKey(fbKey);
            mProductDataSource.updateProductFbKey(id,fbKey);
        });
    }
}
