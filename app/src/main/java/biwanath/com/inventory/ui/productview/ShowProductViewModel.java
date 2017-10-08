package biwanath.com.inventory.ui.productview;

import android.arch.lifecycle.ViewModel;

import java.util.List;

import biwanath.com.inventory.data.productrepository.Product;
import biwanath.com.inventory.data.productrepository.ProductDataSource;
import io.reactivex.Flowable;

/**
 * Created by Biswanath Maity on 10/8/2017.
 */

public class ShowProductViewModel extends ViewModel {
    private final ProductDataSource mProductDataSource;
    private List<Product> mProductList;

    public ShowProductViewModel(ProductDataSource mProductDataSource) {
        this.mProductDataSource = mProductDataSource;
    }

    public Flowable<List<Product>> getAllProductList() {
        return mProductDataSource.getAllProducts()
                // for every emission of the user, get the user name
                .map(productList -> {mProductList = productList;return mProductList;});
    }
}
