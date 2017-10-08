package biwanath.com.inventory.ui.productview;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import biwanath.com.inventory.data.productrepository.ProductDataSource;
import biwanath.com.inventory.data.userrepository.UserDataSource;
import biwanath.com.inventory.ui.userview.UserViewModel;


/**
 * Factory for ViewModels
 */
public class ProductViewModelFactory implements ViewModelProvider.Factory {

    private final ProductDataSource mProductDataSource;

    public ProductViewModelFactory(ProductDataSource productDataSource) {
        mProductDataSource = productDataSource;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProductViewModel.class)) {
            return (T) new ProductViewModel(mProductDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
