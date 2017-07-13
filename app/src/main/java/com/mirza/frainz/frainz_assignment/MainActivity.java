package com.mirza.frainz.frainz_assignment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private Button register, signin;
    MyPagerAdapter adapter;
    DotIndicator indicator;
    public static Context context;
    TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        register = (Button) findViewById(R.id.button);
        register.startAnimation(AnimationUtils.loadAnimation(MainActivity.context, R.anim.shake));
        signin = (Button) findViewById(R.id.button2);
        indicator = (DotIndicator) findViewById(R.id.indicator);
        pager = (ViewPager) findViewById(R.id.pager);
        view= (TextView) findViewById(R.id.textView);
        final int resID = getResources().getIdentifier("border", "drawable", "com.mirza.frainz.frainz_assignment");

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(adapter.getCount());
        pager.setPageTransformer(true, new ZoomOutPageTransformer());
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageScrollStateChanged(int state) {

            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @SuppressLint("SetTextI18n")
            public void onPageSelected(int position) {
                if (position == 0) {
                    indicator.setSelectedDotColor(Color.BLACK);
                    indicator.setUnselectedDotColor(Color.BLACK);
                    signin.setTextColor(Color.rgb(0, 132, 191));
                    register.setBackgroundColor(Color.rgb(0, 132, 191));
                } else {
                    indicator.setSelectedDotColor(Color.WHITE);
                    indicator.setUnselectedDotColor(Color.WHITE);
                    signin.setTextColor(Color.WHITE);
                    register.setBackgroundResource(resID);
                }
                indicator.setSelectedItem(position, true);
                View anim = findViewById(R.id.relativeLayout);
                anim.startAnimation(AnimationUtils.loadAnimation(MainActivity.context, R.anim.shake));
            }

        });


    }

    public void register(View v) {
        register.startAnimation(AnimationUtils.loadAnimation(MainActivity.context, R.anim.popup));
    }




    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 4;
        }

        public Fragment getItem(int pos) {
            switch (pos) {
                case 0:
                    return Span.newInstance(0);
                case 1:
                    return Span.newInstance(1);
                case 2:
                    return Span.newInstance(2);
                default:
                    return Span.newInstance(3);
            }
        }
    }


}
