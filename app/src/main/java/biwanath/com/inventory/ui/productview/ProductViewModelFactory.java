package biwanath.com.inventory.ui.productview;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import biwanath.com.inventory.data.productrepository.ProductDataSource;


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
        if (modelClass.isAssignableFrom(ProductEntryViewModel.class)) {
            return (T) new ProductEntryViewModel(mProductDataSource);
        }
        if (modelClass.isAssignableFrom(ShowProductViewModel.class)) {
            return (T) new ShowProductViewModel(mProductDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
