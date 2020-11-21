package com.example.roomdatabase;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ViewModel extends AndroidViewModel {

    UsersRepository usersRepository;
    LiveData<List<Users>> userList;

    public ViewModel(@NonNull Application application) {
        super(application);
        usersRepository = new UsersRepository(application);
        userList = usersRepository.getAllUsers();

    }


    public LiveData<List<Users>> getAllUsers(){
        return  usersRepository.getAllUsers();
    }

    public void insertUsers(Users users){
        usersRepository.insertUsers(users);
    }


    public void updateUser(Users users){
        usersRepository.updateUser(users);
    }

    public void deleteUser(Users users){
        usersRepository.deleteUser(users);
    }

}
