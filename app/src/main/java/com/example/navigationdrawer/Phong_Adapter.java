package com.example.navigationdrawer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Phong_Adapter extends RecyclerView.Adapter<Phong_Adapter.ViewHolder> {
    ArrayList<Phong> listPhong = new ArrayList<>();
    Fragment_QLPhong_DanhSachPhong context;

    public  Phong_Adapter(ArrayList<Phong> listPhong, Fragment_QLPhong_DanhSachPhong context)
    {
        this.listPhong = listPhong;
     this.context= context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phong, null);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtTenPhong.setText(listPhong.get(position).getTenPhong());
        holder.txtMoTa.setText(listPhong.get(position).getMoTa());
        holder.txtGiaPhong.setText(String.valueOf(listPhong.get(position).getGiaPhong()));

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("sua "+listPhong.get(position).getId());\
                FragmentManager fragmentManager= context.getChildFragmentManager ();
                final FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                Fragment_QLPhong_SuaPhong fragment_qlPhong_suaPhong= new Fragment_QLPhong_SuaPhong();
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(listPhong.get(position).getId()));
                bundle.putString("ten", listPhong.get(position).getTenPhong());
                bundle.putString("moTa", listPhong.get(position).getMoTa());
                bundle.putString("gia", String.valueOf(listPhong.get(position).getGiaPhong()));
                fragment_qlPhong_suaPhong.setArguments(bundle);
                fragmentTransaction.add(R.id.danhsachphong, fragment_qlPhong_suaPhong, null);
                fragmentTransaction.addToBackStack(null).commit();

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder xacNhanXoa = new AlertDialog.Builder(view.getContext());
                xacNhanXoa.setTitle("Thông báo");
                xacNhanXoa.setMessage("Bạn chắc chắn muốn xóa ???");

                xacNhanXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = String.valueOf(listPhong.get(position).getId());
                        context.XoaPhong(id);

                        listPhong.remove(position);
                        notifyDataSetChanged();
                    }
                });
                xacNhanXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                xacNhanXoa.show();
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("sua "+listPhong.get(position).getId());\
                FragmentManager fragmentManager= context.getChildFragmentManager ();
                final FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                Fragment_KhachTro fragment_khachTro= new Fragment_KhachTro();
                Bundle bundle = new Bundle();
                bundle.putString("tenPhong", String.valueOf(listPhong.get(position).getTenPhong()));
                bundle.putInt("idPhong",listPhong.get(position).getId());
                fragment_khachTro.setArguments(bundle);
                fragmentTransaction.replace(R.id.danhsachphong, fragment_khachTro, null);
                fragmentTransaction.addToBackStack(null).commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return listPhong.size();
    }
    


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView txtTenPhong;
        TextView txtMoTa;
        TextView txtGiaPhong;
        ImageView imgEdit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTenPhong = (TextView) itemView.findViewById(R.id.txtTenPhong);
            txtMoTa = (TextView) itemView.findViewById(R.id.txtMoTa);
            txtGiaPhong =(TextView) itemView.findViewById(R.id.txtGiaPhong);
            imgEdit = (ImageView) itemView.findViewById(R.id.imgEdit);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }
}
