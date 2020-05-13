package com.example.navigationdrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_DanhSachKhach extends Fragment {
    String urlGetKhach="http://192.168.56.1:8080/QLPhongTro/HienThiDanhSachKhach.php";
    String urlGetPhong="http://192.168.56.1:8080/QLPhongTro/HienThiDanhSachPhong.php";

    ArrayList<Khach> listKhach = new ArrayList<>();
    ArrayList<Phong> listPhong = new ArrayList<>();
    Khach_Adapter adapter;
    RecyclerView recyclerView;

    public Fragment_DanhSachKhach() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_danhsachkhach, container, false);
        recyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerViewListKhach);
        getListPhong(urlGetPhong);
        HienThiDanhSachKhach(urlGetKhach);
        adapter = new Khach_Adapter(listKhach, Fragment_DanhSachKhach.this);

        LinearLayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    public void HienThiDanhSachKhach(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i=0;i<response.length();i++){
                    try {

                        JSONObject object = response.getJSONObject(i);
                        Khach k = new Khach();
                        k.setIdPhong(object.getInt("idPhong"));

                        for(Phong p : listPhong)
                        {
                            if(k.getIdPhong()==p.getId())
                            {
                                //gán tạm tên phòng vào ghi chú để qua Khach_Adapter gán vào txtTeen phòng
                                k.setGhiChu(p.getTenPhong());
                            }
                        }

                        k.setTenKhach(object.getString("tenKhach"));
                        listKhach.add(k);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Lỗi Server",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }


    public void getListPhong(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());

        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i=0;i<response.length();i++){
                    try {

                        JSONObject object = response.getJSONObject(i);
                        listPhong.add(new Phong(
                                object.getInt("id"),
                                object.getString("tenPhong"),
                                object.getString("moTa"),
                                object.getInt("giaPhong")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Lỗi Server",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
