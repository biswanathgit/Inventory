package biwanath.com.inventory;

import android.content.Context;

import biwanath.com.inventory.data.ApplicationDatabase;
import biwanath.com.inventory.data.productrepository.LocalProductDataSource;
import biwanath.com.inventory.data.productrepository.ProductDataSource;
import biwanath.com.inventory.data.userrepository.LocalUserDataSource;
import biwanath.com.inventory.data.userrepository.UserDataSource;
import biwanath.com.inventory.ui.productview.ProductViewModelFactory;
import biwanath.com.inventory.ui.userview.UserViewModelFactory;


/**
 * Enables injection of data sources.
 */
public class Injection {
   //user
    public static UserDataSource provideUserDataSource(Context context) {
        ApplicationDatabase database = ApplicationDatabase.getInstance(context);
        return new LocalUserDataSource(database.userDao());
    }

    public static UserViewModelFactory provideUserViewModelFactory(Context context) {
        UserDataSource dataSource = provideUserDataSource(context);
        return new UserViewModelFactory(dataSource);
    }

    //product
    public static ProductDataSource provideProductDataSource(Context context) {
        ApplicationDatabase database = ApplicationDatabase.getInstance(context);
        return new LocalProductDataSource(database.productDao()) {
        };
    }
    public static ProductViewModelFactory provideProductViewModelFactory(Context context) {
        ProductDataSource dataSource = provideProductDataSource(context);
        return new ProductViewModelFactory(dataSource);
    }
}
