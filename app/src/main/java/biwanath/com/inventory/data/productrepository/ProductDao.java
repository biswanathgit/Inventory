package biwanath.com.inventory.data.productrepository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import biwanath.com.inventory.data.userrepository.User;
import io.reactivex.Flowable;

/**
 * Created by Biswanath Maity on 10/8/2017.
 */
@Dao
public interface ProductDao {
    @Query("SELECT * FROM products LIMIT 1")
    Flowable<Product> getProduct();

    @Query("SELECT * FROM products")
    Flowable<List<Product>> getAllProduct();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertProduct(Product product);

    @Query("UPDATE products SET firebaseKey = :fbkey WHERE pid == :pId ")
    void updateProductFbKey(String pId, String fbkey);

    @Query("DELETE FROM products where pid == :productId and firebaseKey == :fbKey")
    void deleteSingleProduct(String productId ,String fbKey);

    @Query("DELETE FROM products")
    void deleteAllUsers();
}
