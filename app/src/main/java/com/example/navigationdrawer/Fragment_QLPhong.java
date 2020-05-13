package com.example.navigationdrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Fragment_QLPhong extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlphong, container,false);
        ViewPager viewPager=view.findViewById(R.id.viewPager);
        TabLayout tabLayout=view.findViewById(R.id.tabLayout);
        addTab(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
    public  void addTab(final ViewPager viewPager){
        ViewPagerAdapter adapter=new ViewPagerAdapter(getChildFragmentManager());
        adapter.add(new Fragment_QLPhong_DanhSachPhong(),"Danh sách phòng");
        adapter.add(new Fragment_QLPhong_ThemPhong(),"Thêm phòng");
        viewPager.setAdapter(adapter);
    }
}
