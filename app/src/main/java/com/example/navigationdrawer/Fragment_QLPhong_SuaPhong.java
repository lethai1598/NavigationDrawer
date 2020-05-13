package com.example.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Fragment_QLPhong_SuaPhong extends Fragment {

    EditText edtTenPhongSua,edtMoTaSua,edtGiaPhongSua;
    Button btnSuaPhong,btnTroVe1;
    TextView id;
    String url = "http://192.168.56.1:8080/QLPhongTro/SuaPhong.php";
    public Fragment_QLPhong_SuaPhong(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container ,Bundle savedInstanceState ){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_qlphong_suaphong, container, false);
        id = rootView.findViewById(R.id.id);
        edtTenPhongSua = rootView.findViewById(R.id.edtTenPhongSua);
        edtMoTaSua = rootView.findViewById(R.id.edtMoTaSua);
        edtGiaPhongSua = rootView.findViewById(R.id.edtGiaPhongSua);
        btnSuaPhong = rootView.findViewById(R.id.btnSuaPhong);
        btnTroVe1 = rootView.findViewById(R.id.btnTroVe1);
        Bundle bundle = getArguments();
        Toast.makeText(getContext(), "Vào trang cập nhật", Toast.LENGTH_LONG).show();

        if(bundle!=null){
            id.setText(bundle.getString("id"));
            edtTenPhongSua.setText(bundle.getString("ten"));
            edtMoTaSua.setText(bundle.getString("moTa"));
            edtGiaPhongSua.setText(bundle.getString("gia"));

        }
        btnSuaPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenPhong = edtTenPhongSua.getText().toString().trim();
                String moTa = edtMoTaSua.getText().toString().trim();
                String giaPhong =  edtGiaPhongSua.getText().toString().trim();

                if (tenPhong.isEmpty()||giaPhong.isEmpty()||moTa.isEmpty()){
                    Toast.makeText(getActivity(),"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }else{
                    SuaPhong(url);
                }
            }
        });
        btnTroVe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return rootView;
    }

    private void SuaPhong(String url){
             RequestQueue requestQueue= Volley.newRequestQueue(getContext());
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override

                public void onResponse(String response) {

                    if (response.trim().equals("Success")) {
                        Toast.makeText(getContext(), "Cập nhất thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), MainActivity.class));
                    } else {
                        Toast.makeText(getContext(), "Lỗi cập nhất", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Loi server", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> param =new HashMap<>();
                    param.put("id", id.getText().toString().trim());
                    param.put("tenPhong",edtTenPhongSua.getText().toString().trim());
                    param.put("moTa",edtMoTaSua.getText().toString().trim());
                    param.put("giaPhong",edtGiaPhongSua.getText().toString().trim());
                    return param;
                }
            };
            requestQueue.add(stringRequest);

    }
}
