package com.example.navigationdrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Update extends Fragment {
    TextView txtInfo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_update, container,false);

        txtInfo =  (TextView)view.findViewById(R.id.txtInfo);

        String versionName = BuildConfig.VERSION_NAME;

        txtInfo.setText("Version: "+versionName);

        return view;
    }
}
