package biwanath.com.inventory;

import android.content.Context;

import biwanath.com.inventory.data.ApplicationDatabase;
import biwanath.com.inventory.data.userrepository.LocalUserDataSource;
import biwanath.com.inventory.data.userrepository.UserDataSource;
import biwanath.com.inventory.ui.ViewModelFactory;


/**
 * Enables injection of data sources.
 */
public class Injection {

    public static UserDataSource provideUserDataSource(Context context) {
        ApplicationDatabase database = ApplicationDatabase.getInstance(context);
        return new LocalUserDataSource(database.userDao());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        UserDataSource dataSource = provideUserDataSource(context);
        return new ViewModelFactory(dataSource);
    }
}
