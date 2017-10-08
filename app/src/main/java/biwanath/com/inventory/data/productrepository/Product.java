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
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "pid")
    private String productId;

    @ColumnInfo(name = "name")
    private String mProductName;

    @ColumnInfo(name = "price")
    private long mProductPrice;

    @ColumnInfo(name = "description")
    private String mDescription;

    public Product() {
    }

    @NonNull
    public String getProductId() {
        return productId;
    }

    public void setProductId(@NonNull String productId) {
        this.productId = productId;
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
}
