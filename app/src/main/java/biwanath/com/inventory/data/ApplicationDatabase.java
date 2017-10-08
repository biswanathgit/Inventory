
package biwanath.com.inventory.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import biwanath.com.inventory.data.productrepository.Product;
import biwanath.com.inventory.data.productrepository.ProductDao;
import biwanath.com.inventory.data.userrepository.User;
import biwanath.com.inventory.data.userrepository.UserDao;


/**
 * The Room database that contains the Users table
 */
@Database(entities = {User.class, Product.class}, version = 1)
public abstract class ApplicationDatabase extends RoomDatabase {

    private static volatile ApplicationDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract ProductDao productDao();

    public static ApplicationDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ApplicationDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ApplicationDatabase.class, "Inventory.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
