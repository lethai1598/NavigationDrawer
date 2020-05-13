package com.example.navigationdrawer;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_QLPhong_DanhSachPhong extends Fragment {
    String url="http://192.168.56.1:8080/QLPhongTro/HienThiDanhSachPhong.php";

    ArrayList<Phong> listPhong = new ArrayList<>();
    ArrayList<Integer> arrayPhong = new ArrayList<>();
    Phong_Adapter adapter;
    RecyclerView recyclerView;

    public Fragment_QLPhong_DanhSachPhong() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_qlphong_danhsachphong, container, false);
        recyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerView);

        HienThiDanhSachPhong(url);
        adapter = new Phong_Adapter(listPhong, Fragment_QLPhong_DanhSachPhong.this);

        LinearLayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
    public void HienThiDanhSachPhong(String url){
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
    public void XoaPhong(final String id)
    {
        String url = "http://192.168.56.1:8080/QLPhongTro/XoaPhong.php";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), response.toString().trim(), Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Lỗi Server", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() {
                Map<String, String> params = new HashMap<String,String>();
                params.put("id", id);
                return params;




            }
        };
        requestQueue.add(stringRequest);
    }

}
