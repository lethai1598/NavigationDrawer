package com.example.navigationdrawer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Fragment_main extends Fragment implements OnChartValueSelectedListener {
    @Nullable
    String url = "http://192.168.56.1:8080/QLPhongTro/layDuLieuVeBieuDo.php";
    private PieChart mChart;
    TextView txtSoLieu;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container,false);

        txtSoLieu = (TextView) view.findViewById(R.id.txtSoLieu);

        laySoLieuVeBieuDo(url);
        mChart = (PieChart)view.findViewById(R.id.piechart);
        //set chia giá trị theo tỉ lệ 100%
        mChart.setUsePercentValues(true);
        mChart.setRotationEnabled(true);
        mChart.setDescription(new Description());
        mChart.getDescription().setText("");
        mChart.setHoleRadius(35f);
        mChart.setTransparentCircleAlpha(0);
        mChart.setCenterText("Phòng trọ");
        mChart.setCenterTextSize(10);
        mChart.setDrawEntryLabels(true);
        mChart.setOnChartValueSelectedListener(this);
        return view;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(getContext(), " Số phòng: "+e.getY(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected() {
    }
    private static void VeBieuDo(PieChart pieChart,int phongTrong, int phongThue) {
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(phongTrong, "Phòng trống"));
        entries.add(new PieEntry(phongThue, "Phòng đã thuê"));

        PieDataSet set = new PieDataSet(entries, "");
        set.setSliceSpace(2);
        set.setValueTextSize(12);

        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        set.setColors(colors);

        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);

        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate();
    }


    public void laySoLieuVeBieuDo(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);

                        int tongPhong = object.getInt("tongPhong");
                        int slPhongDaThue = object.getInt("slPhongDaThue");
                        int slphongTrong = tongPhong - slPhongDaThue;

                        VeBieuDo(mChart,slphongTrong,tongPhong);
                        txtSoLieu.setText("Tất cả phòng: "+tongPhong+"\nĐã thuê: "+slPhongDaThue+"\nPhòng còn trống: "+slphongTrong);

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
