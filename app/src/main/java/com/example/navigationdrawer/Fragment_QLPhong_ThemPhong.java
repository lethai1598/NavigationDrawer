package com.example.navigationdrawer;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_QLPhong_ThemPhong extends Fragment {

    EditText edtTenPhong,edtMoTa,edtGiaPhong;
    String url = "http://192.168.56.1:8080/QLPhongTro/ThemPhong.php";


    public Fragment_QLPhong_ThemPhong() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_qlphong_themphong, container, false);

        edtTenPhong = rootView.findViewById(R.id.edtTenPhong);
        edtMoTa = rootView.findViewById(R.id.edtMoTa);
        edtGiaPhong = rootView.findViewById(R.id.edtGiaPhong);

        final Button btnThemPhong = rootView.findViewById(R.id.btnThemPhong);
        Button btnTroVe = rootView.findViewById(R.id.btnTroVe);



        btnThemPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenPhong = edtTenPhong.getText().toString().trim();
                String moTa = edtMoTa.getText().toString().trim();
                String giaPhong =  edtGiaPhong.getText().toString().trim();

                if (tenPhong.isEmpty()||giaPhong.isEmpty()||moTa.isEmpty()){
                    Toast.makeText(getActivity(),"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }else{
                    themPhong(url);
                }
            }
        });

        return rootView;
    }
    private void themPhong(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Success")) {
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                    edtTenPhong.setText("");
                    edtMoTa.setText("");
                    edtGiaPhong.setText("");
                } else {
                    Toast.makeText(getActivity(), "Lỗi thêm dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Xảy ra lỗi",Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param =new HashMap<>();
                param.put("tenPhong",edtTenPhong.getText().toString().trim());
                param.put("moTa",edtMoTa.getText().toString().trim());
                param.put("giaPhong",edtGiaPhong.getText().toString().trim());
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }



}
