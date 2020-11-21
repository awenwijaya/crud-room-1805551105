package com.example.roomdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersAdapterVH> {

    private List<Users> usersList;
    private Context context;
    private ItemClicked itemClicked;

    public UsersAdapter(ItemClicked itemClicked) {
        this.itemClicked = itemClicked;
    }

    public void setData(List<Users> usersList){
        this.usersList = usersList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public UsersAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new UsersAdapter.UsersAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_users,null));
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapterVH holder, int position) {

        final Users users = usersList.get(position);
        String username = users.getUsername();

        holder.users.setText(username);
        holder.imageOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //popmenu
                showPopup(view,users);

            }
        });



    }


    public void showPopup(View view, final Users users){
        PopupMenu popupMenu = new PopupMenu(context,view);
        popupMenu.inflate(R.menu.menu_options);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.delete:
                        itemClicked.deleteClicked(users);
                        break;

                    case R.id.update:
                      itemClicked.updateClicked(users);

                        break;
                }

                return false;
            }
        });

        popupMenu.show();

    }


    public interface ItemClicked{
        void updateClicked(Users users);
        void deleteClicked(Users users);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class UsersAdapterVH extends RecyclerView.ViewHolder {

        TextView users;
        ImageView imageOptions;

        public UsersAdapterVH(@NonNull View itemView) {
            super(itemView);

            users = itemView.findViewById(R.id.users_row);
            imageOptions = itemView.findViewById(R.id.menuOption);
        }
    }
}
