package com.example.roomdatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UsersAdapter.ItemClicked {

    ViewModel userViewModel;

    UsersAdapter usersAdapter;

    RecyclerView recyclerView;

    Button btnNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        usersAdapter = new UsersAdapter(this);
        recyclerView = findViewById(R.id.recyclerView);
        btnNewUser = findViewById(R.id.btnNewUser);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel.getAllUsers().observe(this, new Observer<List<Users>>() {
            @Override
            public void onChanged(List<Users> users) {

                if(users.size() > 0){
                    usersAdapter.setData(users);
                    recyclerView.setAdapter(usersAdapter);
                }

            }
        });

        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUsers(MainActivity.this);
            }
        });
    }


    public void addUsers(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View view1 = getLayoutInflater().inflate(R.layout.row_add_uses,null);

        Button addUser = view1.findViewById(R.id.btnAddUser);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText edUsers = view1.findViewById(R.id.edUsers);
                Users users = new Users();
                users.setUsername(edUsers.getText().toString());

                userViewModel.insertUsers(users);


            }
        });

        builder.setView(view1);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();



    }



    public void updateUsers(final Users users){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View view1 = getLayoutInflater().inflate(R.layout.row_add_uses,null);
        final EditText edUsers = view1.findViewById(R.id.edUsers);

        edUsers.setText(users.getUsername());

        Button addUser = view1.findViewById(R.id.btnAddUser);

        addUser.setText("Update");
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users.setUsername(edUsers.getText().toString());
                userViewModel.updateUser(users);
            }
        });

        builder.setView(view1);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();



    }


    @Override
    public void updateClicked(Users users) {
        updateUsers(users);
    }

    @Override
    public void deleteClicked(Users users) {

        userViewModel.deleteUser(users);
    }
}
