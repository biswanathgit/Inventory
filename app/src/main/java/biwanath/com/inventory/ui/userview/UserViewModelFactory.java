package biwanath.com.inventory.ui.userview;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import biwanath.com.inventory.data.userrepository.UserDataSource;
import biwanath.com.inventory.ui.userview.UserViewModel;


/**
 * Factory for ViewModels
 */
public class UserViewModelFactory implements ViewModelProvider.Factory {

    private final UserDataSource mDataSource;

    public UserViewModelFactory(UserDataSource dataSource) {
        mDataSource = dataSource;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(mDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
