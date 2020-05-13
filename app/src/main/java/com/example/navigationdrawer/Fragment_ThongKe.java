package com.example.navigationdrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

public class Fragment_ThongKe extends ListFragment {
    ArrayList<Integer> arrayDaThue = new ArrayList<>();
    ArrayList<Integer> arrayChuaThue = new ArrayList<>();
    TextView txtTrangThai;
    int check = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
        txtTrangThai = (TextView) view.findViewById(R.id.txtTrangThai);
        return view;
    }

}
