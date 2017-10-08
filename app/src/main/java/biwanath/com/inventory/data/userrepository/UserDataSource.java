package biwanath.com.inventory.data.userrepository;


import biwanath.com.inventory.data.userrepository.User;
import io.reactivex.Flowable;

/**
 * Access point for managing user data.
 */
public interface UserDataSource {

    /**
     * Gets the user from the data source.
     *
     * @return the user from the data source.
     */
    Flowable<User> getUser();

    /**
     * Inserts the user into the data source, or, if this is an existing user, updates it.
     *
     * @param user the user to be inserted or updated.
     */
    void insertOrUpdateUser(User user);

    /**
     * Deletes all users from the data source.
     */
    void deleteAllUsers();
}