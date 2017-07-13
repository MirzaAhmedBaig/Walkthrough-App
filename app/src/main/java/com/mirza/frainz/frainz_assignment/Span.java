package com.mirza.frainz.frainz_assignment;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Span extends Fragment {

    private View v, anim;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        switch (getArguments().getInt("frame")) {
            case 0:
                v = inflater.inflate(R.layout.home_span, container, false);
                return v;
            case 1:
                v = inflater.inflate(R.layout.frame1, container, false);
                return v;
            case 2:
                v = inflater.inflate(R.layout.frame2, container, false);
                return v;
            default:
                v = inflater.inflate(R.layout.frame3, container, false);
                return v;
        }

    }

    public static Span newInstance(int frame) {

        Span f = new Span();
        Bundle b = new Bundle();
        b.putInt("frame", frame);
        f.setArguments(b);

        return f;
    }
}