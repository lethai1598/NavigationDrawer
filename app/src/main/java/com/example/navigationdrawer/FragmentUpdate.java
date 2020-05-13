//package com.example.navigationdrawer;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class FragmentUpdate extends AppCompatActivity {
//    EditText tenedt,giaedt;
//    Button btnsua,btnquaylai;
//    int id =0;
//    String urlGetData="http://192.168.124.1/monan/update.php";
//
//    public FragmentUpdate() {
//        // Required empty public constructor
//    }
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_update, container, false);
//        tenedt=rootView.findViewById(R.id.edten);
//        giaedt=rootView.findViewById(R.id.edgia);
//        btnsua=rootView.findViewById(R.id.btnthem);
//
//        btnsua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String ten=tenedt.getText().toString().trim();
//                String gia=giaedt.getText().toString().trim();
//
//                if (ten.isEmpty()||gia.isEmpty()){
//                    Toast.makeText(getActivity(),"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
//                }else{
//                    editMon(urlGetData);
//                }
//            }
//        });
//        return rootView;
//    }
//    private void editMon(String url){
//        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response.trim().equals("Success")) {
//                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getActivity(), MainActivity.class));
//                } else {
//                    Toast.makeText(getActivity(), "Lỗi thêm dữ liệu", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(),"Xảy ra lỗi",Toast.LENGTH_SHORT).show();
//            }
//
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> param =new HashMap<>();
//                param.put("id",String.valueOf(id));
//                param.put("tenmon",tenedt.getText().toString().trim());
//                param.put("gia",giaedt.getText().toString().trim());
//                return param;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }
//}
