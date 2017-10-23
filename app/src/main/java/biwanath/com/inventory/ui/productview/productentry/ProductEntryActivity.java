package biwanath.com.inventory.ui.productview.productentry;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;
import java.util.concurrent.Callable;

import biwanath.com.inventory.Injection;
import biwanath.com.inventory.R;
import biwanath.com.inventory.data.productrepository.Product;
import biwanath.com.inventory.ui.productview.ProductViewModelFactory;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ProductEntryActivity extends AppCompatActivity {

    private static final String TAG = ProductEntryActivity.class.getSimpleName();

    private TextView mProductName;

    private EditText mProductNameInput;
    private EditText mProductDescriptionInput;
    private EditText mProductPrice;

    private Button mUpdateButton;

    private ProductViewModelFactory mViewModelFactory;

    private ProductEntryViewModel mViewModel;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_entry);

        mProductName = findViewById(R.id.product_name);
        mProductNameInput = findViewById(R.id.product_name_input);
        mProductDescriptionInput = findViewById(R.id.product_description_input);
        mProductPrice = findViewById(R.id.product_price_input);
        mUpdateButton = findViewById(R.id.update_product);

        mDatabase = FirebaseDatabase.getInstance().getReference("product");

        mViewModelFactory = Injection.provideProductViewModelFactory(this);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProductEntryViewModel.class);
        mUpdateButton.setOnClickListener(v -> insertProduct());
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

    private void insertProduct() {
        String productName = mProductNameInput.getText().toString().trim();
        String productDec = mProductDescriptionInput.getText().toString().trim();
        String productPrice = mProductPrice.getText().toString().trim();

        if (!productName.equals("") && !productPrice.equals("")) {
            mUpdateButton.setEnabled(false);

            String uuid = UUID.randomUUID().toString();
            mDisposable.add( mViewModel.insertProduct(uuid,productName, productDec, Long.valueOf(productPrice))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                                mUpdateButton.setEnabled(true);
                                insertProductToFirebase(uuid,productName, productDec, Long.valueOf(productPrice));
                                this.finish();
                            },
                            throwable -> Log.e(TAG, "Unable to update Product Name", throwable)));
        }

    }

    private String insertProductToFirebase(String productId,String productName,String description, long price) {
        Product newProduct = new Product();
        newProduct.setProductId(productId);
        newProduct.setProductName(productName);
        newProduct.setDescription(description);
        newProduct.setProductPrice(price);
        mDatabase.push().setValue(newProduct, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Log.d(TAG, "onComplete: " + productId +" fb key "+databaseReference.getKey());
                if(databaseReference.getKey() != null){
                    updateProductWithFirebaseKey(productId,databaseReference.getKey());
                }
            }
        });
        return "msg";
    }

    private void updateProductWithFirebaseKey(String id,String fbkey) {
        mDisposable.add(mViewModel.updateProductWithFbkey(id,fbkey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {Log.d(TAG, "updateProductWithFirebaseKey: " + "key updated");},
                        throwable -> Log.e(TAG, "Unable to update Product Name", throwable)));
    }
}
