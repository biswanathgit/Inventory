package biwanath.com.inventory.ui.productview;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import biwanath.com.inventory.Injection;
import biwanath.com.inventory.R;
import biwanath.com.inventory.ui.userview.UserViewModel;
import biwanath.com.inventory.ui.userview.UserViewModelFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ProductActivity extends AppCompatActivity {

    private static final String TAG = ProductActivity.class.getSimpleName();

    private TextView mProductName;

    private EditText mProductNameInput;

    private Button mUpdateButton;

    private ProductViewModelFactory mViewModelFactory;

    private ProductViewModel mViewModel;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        mProductName = findViewById(R.id.product_name);
        mProductNameInput = findViewById(R.id.product_name_input);
        mUpdateButton = findViewById(R.id.update_product);

        mViewModelFactory = Injection.provideProductViewModelFactory(this);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProductViewModel.class);
        mUpdateButton.setOnClickListener(v -> updateProductName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDisposable.add(mViewModel.getProductName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userName -> mProductName.setText(userName),
                        throwable -> Log.e(TAG, "Unable to fetch Product Name", throwable)));
    }

    @Override
    protected void onStop() {
        super.onStop();
        // clear all the subscriptions
        mDisposable.clear();
    }


    private void updateProductName() {
        String productName = mProductNameInput.getText().toString();
        if(!productName.trim().equals("")) {
            // Disable the update button until the user name update has been done
            mUpdateButton.setEnabled(false);

            // Subscribe to updating the user name.
            // Re-enable the button once the user name has been updated
            mDisposable.add(mViewModel.updateProduct(productName)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                                mUpdateButton.setEnabled(true);
                                mProductNameInput.setText("");
                            },
                            throwable -> Log.e(TAG, "Unable to update Product Name", throwable)));
        }
    }
}
