//package com.example.navigationdrawer;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.FragmentManager;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.view.ContextMenu;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Adapter;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONObject;
//
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class MonanAdapter extends RecyclerView.Adapter<MonanAdapter.ViewHolder> {
//    ArrayList<DataMon> dataMons = new ArrayList<>();
//    Context context;
//
//
//
//    public MonanAdapter(ArrayList<DataMon> dataMons, Context context) {
//        this.dataMons = dataMons;
//        this.context = context;
//
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View itemview = layoutInflater.inflate(R.layout.item_monan, parent, false);
//        return new ViewHolder(itemview);
//
//    }
//
//
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
//        holder.txtmonan.setText(dataMons.get(position).getTen());
//        holder.txtgia.setText(String.valueOf(dataMons.get(position).getGia()));
//
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                AlertDialog.Builder xacNhanXoa = new AlertDialog.Builder(context);
//                xacNhanXoa.setTitle("Thông báo");
//                xacNhanXoa.setMessage("Bạn chắc chắn muốn xóa ???");
//
//                xacNhanXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(context, "Co Xoa", Toast.LENGTH_SHORT).show();
//
//                        String id = String.valueOf(dataMons.get(position).getID());
//                        deleteMon(id);
//                    }
//                });
//                xacNhanXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(context, "Hủy xóa", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                xacNhanXoa.show();
//                return true;
//            }
//        });
//
//        holder.imgedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String id = String.valueOf(dataMons.get(position).getID());
////                String tenMon = String.valueOf(dataMons.get(position).getTen());
////                String gia = String.valueOf(dataMons.get(position).getGia());
//
//
//
//
//                Intent intent =new Intent(context,UpdateActivity.class);
//                intent.putExtra("id",id);
//                context.startActivity(intent);
//            }
//        });
//    }
//
//
//
//    @Override
//    public int getItemCount() {
//        return dataMons.size();
//    }
//
//
//    public void deleteMon(final String id)
//    {
//        String url = "http://192.168.124.1/monan/delete.php";
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(context, response.toString().trim(), Toast.LENGTH_SHORT).show();
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        },new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                System.out.println("Lỗi Server, thử lại sau");
//            }
//        }){
//            @Override
//            protected Map<String,String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("id", id);
//                return params;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }
//
//
//
//
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
//        TextView txtmonan;
//        TextView txtgia;
//        ImageView imgedit;
//
//
//        ContextMenu a;
//     public ViewHolder(final View itemview) {
//            super(itemview);
//            txtmonan = (TextView) itemview.findViewById(R.id.txtmonan);
//            txtgia = (TextView) itemview.findViewById(R.id.txtgia);
//            imgedit =(ImageView) itemview.findViewById(R.id.imgedit);
//
//
//
//            itemview.setOnCreateContextMenuListener(this);
//        }
//
//        @Override
//        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//
//        }
//    }
//}
