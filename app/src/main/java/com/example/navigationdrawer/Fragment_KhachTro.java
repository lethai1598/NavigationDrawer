package com.example.navigationdrawer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fragment_KhachTro extends Fragment {
    EditText editTextTen,editTextDiaChi,editGhiChu,editTextSdt,editTextCmtnd,editidkhach;
    TextView txtTieuDe;
    Button buttonThem,buttonXoa,buttonTrove;
    String url = "http://192.168.56.1:8080/QLPhongTro/HienThiDanhSachKhach.php";
    String Geturl="http://192.168.56.1:8080/QLPhongTro/ThemKhach.php";
    String urLXoa="http://192.168.56.1:8080/QLPhongTro/XoaKhach.php";


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_khachtro, container, false);
        editTextTen= (EditText)view.findViewById(R.id.editTextTen);
        editTextDiaChi= (EditText)view.findViewById(R.id.editTextDiaChi);
        editGhiChu= (EditText)view.findViewById(R.id.editGhiChu);
        editTextSdt= (EditText)view.findViewById(R.id.editTextSdt);
        editTextCmtnd= (EditText)view.findViewById(R.id.editTextCmtnd);
        txtTieuDe = (TextView)view.findViewById(R.id.txtTieuDe);
        buttonThem = (Button)view.findViewById(R.id.buttonThem);
        editidkhach=(EditText)view.findViewById(R.id.editidkhach);

        buttonXoa = (Button) view.findViewById(R.id.buttonXoa);

        Bundle bundle = getArguments();

        String tenPhong = bundle.getString("tenPhong");
        final int idPhong = bundle.getInt("idPhong");


        txtTieuDe.setText("Phòng "+tenPhong);


        hienThiKhach(url,idPhong);

        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tenKhach = editTextTen.getText().toString().trim();
                String sdt = editTextSdt.getText().toString().trim();
                String diaChi =  editTextDiaChi.getText().toString().trim();
                String soCmtnd = editTextCmtnd.getText().toString().trim();
                String ghiChu = editGhiChu.getText().toString().trim();

                if (tenKhach.isEmpty()||sdt.isEmpty()||diaChi.isEmpty()||soCmtnd.isEmpty()||ghiChu.isEmpty()){
                    Toast.makeText(getActivity(),"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }else{

                    themKhach(Geturl,idPhong);
                    hienThiKhach(url,idPhong);
//                    buttonXoa.setEnabled(true);
                }

            }
        });

        buttonXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder xacNhanXoa = new AlertDialog.Builder(getContext());
                xacNhanXoa.setTitle("Thông báo");
                xacNhanXoa.setMessage("Bạn chắc chắn muốn xóa ???");

                xacNhanXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String idKhach = editidkhach.getText().toString().trim();
                        xoaKhach(urLXoa,idKhach);
                        editTextTen.setText("");
                        editTextSdt.setText("");
                        editTextCmtnd.setText("");
                        editTextDiaChi.setText("");
                        editGhiChu.setText("");
                        editidkhach.setText("");

                        hienThiKhach(url,idPhong);
//                        buttonXoa.setEnabled(false);
//                        buttonThem.setEnabled(true);

                    }
                });
                xacNhanXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                xacNhanXoa.show();
            }
        });


        return view;
    }
    public void hienThiKhach(String url, final int idPhong){
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response) {
                int checkShow = 0;
                for (int i=0;i<response.length();i++){

                    try {
                        JSONObject object = response.getJSONObject(i);
                        if(idPhong == object.getInt("idPhong"))
                        {
                            checkShow++;
                            editTextTen.setText(object.getString("tenKhach"));
                            editTextSdt.setText(object.getString("sdt"));
                            editTextCmtnd.setText(object.getString("soCmtnd"));
                            editTextDiaChi.setText(object.getString("diaChi"));
                            editGhiChu.setText(object.getString("ghiChu"));
                            editidkhach.setText(object.getString("id"));

//                            buttonThem.setEnabled(false);
//                            buttonXoa.setEnabled(true);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
//                if (checkShow==0)
//                {
//                    buttonThem.setEnabled(true);
//                    buttonXoa.setEnabled(false);
//                }else{
//                    buttonThem.setEnabled(false);
//                    buttonXoa.setEnabled(true);
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Lỗi Server",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public void themKhach(String Geturl, final int idPhong){
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Geturl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Success")) {
                    Toast.makeText(getActivity(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
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
                param.put("idPhong", idPhong+"");
                param.put("tenKhach",editTextTen.getText().toString().trim());
                param.put("sdt",editTextSdt.getText().toString().trim());
                param.put("diaChi",editTextDiaChi.getText().toString().trim());
                param.put("soCmtnd",editTextCmtnd.getText().toString().trim());
                param.put("ghiChu",editGhiChu.getText().toString().trim());
                return param;
            }
        };
        requestQueue.add(stringRequest);

    }
    public void xoaKhach(String url,final String idKhach){
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
                params.put("idKhach", idKhach);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
