package biwanath.com.inventory.data.productrepository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import biwanath.com.inventory.data.userrepository.User;
import io.reactivex.Flowable;

/**
 * Created by Biswanath Maity on 10/8/2017.
 */
@Dao
public interface ProductDao {
    @Query("SELECT * FROM products LIMIT 1")
    Flowable<Product> getAllProduct();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    @Query("DELETE FROM products")
    void deleteAllUsers();
}
