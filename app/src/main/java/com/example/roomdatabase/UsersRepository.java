package com.example.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UsersRepository {

    private UsersDao usersDao;
    private Database database;
    private LiveData<List<Users>> usersList;

    public UsersRepository(Application application) {
        database = Database.getDatabase(application);
        usersDao = database.usersDao();
        usersList = usersDao.getAllUsers();
    }

    public LiveData<List<Users>> getAllUsers(){
        return usersDao.getAllUsers();
    }


    public void insertUsers(final Users users){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.usersDao().insertUsers(users);
                return null;
            }
        }.execute();
    }

    public void deleteUser(final Users users){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.usersDao().deleteUser(users);
                return null;
            }
        }.execute();
    }


    public void updateUser(final Users users){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.usersDao().updateUser(users);
                return null;
            }
        }.execute();
    }

}
