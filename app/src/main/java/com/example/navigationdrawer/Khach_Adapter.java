package com.example.navigationdrawer;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Khach_Adapter extends RecyclerView.Adapter<Khach_Adapter.ViewHolder>

{
    ArrayList<Khach> listKhach = new ArrayList<>();
    Fragment_DanhSachKhach context;


    public Khach_Adapter(ArrayList<Khach> listKhach, Fragment_DanhSachKhach context){
        this.listKhach = listKhach;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khach, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Khach_Adapter.ViewHolder holder, final int position) {

        //vì bên fragment ds khách đã gán tên phòng vào ghi chú, nên bên này phải get từ ghi chú ra.)
        holder.txtTenPhong.setText(listKhach.get(position).getGhiChu()+"");
        holder.txtTenKhach.setText(listKhach.get(position).getTenKhach());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenPhong  = listKhach.get(position).getGhiChu();
                int idPhong = listKhach.get(position).getIdPhong();
                FragmentManager fragmentManager= context.getChildFragmentManager ();
                final FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                Fragment_KhachTro fragment_khachTro= new Fragment_KhachTro();
                Bundle bundle = new Bundle();
                bundle.putString("tenPhong", tenPhong);
                bundle.putInt("idPhong",idPhong);
                fragment_khachTro.setArguments(bundle);
                fragmentTransaction.replace(R.id.danhsachkhach, fragment_khachTro, null);
                fragmentTransaction.addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return listKhach.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView txtTenKhach,txtTenPhong,txtIdPhong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenKhach= (TextView) itemView.findViewById(R.id.txtTenKhach);
            txtTenPhong = (TextView) itemView.findViewById(R.id.txtTenPhong);
            //txtIdPhong = (TextView)itemView.findViewById(R.id.idPhong);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }
}
