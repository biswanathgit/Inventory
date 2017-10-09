package biwanath.com.inventory.ui.productview.showproduct;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import biwanath.com.inventory.Injection;
import biwanath.com.inventory.R;
import biwanath.com.inventory.data.productrepository.Product;
import biwanath.com.inventory.ui.productview.ProductViewModelFactory;
import biwanath.com.inventory.ui.productview.productentry.ProductEntryActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ProductActivity extends AppCompatActivity {
    public static final String TAG = ProductActivity.class.getSimpleName();

    private ProductViewModelFactory mViewModelFactory;
    private ShowProductViewModel mViewModel;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private List<Product> productList;
    private Button btnProductEntry;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        productList = new ArrayList<>();
        mViewModelFactory = Injection.provideProductViewModelFactory(this);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ShowProductViewModel.class);

        btnProductEntry = findViewById(R.id.btn_product_entry);
        recyclerView = findViewById(R.id.rv_product_list);
        btnProductEntry.setOnClickListener(v -> navigateToProductEntry());
    }


    @Override
    protected void onStart() {
        super.onStart();
        mDisposable.add(mViewModel.getAllProductList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> showProductList(list),
                        throwable -> Log.e(TAG, "Unable to fetch Product Name", throwable)));
    }

    private void showProductList(List<Product> products){
        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ProductListAdapter productListAdapter = new ProductListAdapter(products,this);
        recyclerView.setAdapter(productListAdapter);
        productListAdapter.notifyDataSetChanged();
    }

    private void navigateToProductEntry() {
        Intent i = new Intent(ProductActivity.this,ProductEntryActivity.class);
        startActivity(i);
    }
}
