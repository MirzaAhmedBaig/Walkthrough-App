package com.mirza.frainz.frainz_assignment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


@SuppressLint("ValidFragment")
public class PageContents extends Fragment {

    TextView label;
    View v;
    String data = "";

    @SuppressLint("ValidFragment")
    public PageContents(String data) {
        this.data = data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.page_contents, container, false);
        label = (TextView) v.findViewById(R.id.no);
        label.setText("" + data);
        return v;
    }

}
