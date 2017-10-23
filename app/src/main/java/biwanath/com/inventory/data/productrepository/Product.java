package biwanath.com.inventory.data.productrepository;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * Created by Biswanath Maity on 10/8/2017.
 */
@Entity(tableName = "products")
public class Product {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "pid")
    private String productId;

    @ColumnInfo(name = "name")
    private String mProductName;

    @ColumnInfo(name = "price")
    private long mProductPrice;

    @ColumnInfo(name = "description")
    private String mDescription;

    @ColumnInfo(name = "firebaseKey")
    private String mFirebaseKey;

    public Product() {
    }

    public void setProductId(@NonNull String productId) {
        this.productId = productId;
    }

    @NonNull
    public String getProductId() {
        return productId;
    }


    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    public long getProductPrice() {
        return mProductPrice;
    }

    public void setProductPrice(long mProductPrice) {
        this.mProductPrice = mProductPrice;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getFirebaseKey() {
        return mFirebaseKey;
    }

    public void setFirebaseKey(String mFirebaseKey) {
        this.mFirebaseKey = mFirebaseKey;
    }

}
