package com.mirza.frainz.frainz_assignment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private Button register, signin;
    MyPagerAdapter adapter;
    DotIndicator indicator;
    public static Context context;
    TextView view, msg1, msg2, msg3;
    ImageView image;
    int resID;
    View anim, loginForm, signUpForm;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        loginForm = findViewById(R.id.loginFrame);
        signUpForm = findViewById(R.id.signUpFrame);

        register = (Button) findViewById(R.id.button);
        register.startAnimation(AnimationUtils.loadAnimation(MainActivity.context, R.anim.shake));
        signin = (Button) findViewById(R.id.button2);
        indicator = (DotIndicator) findViewById(R.id.indicator);
        pager = (ViewPager) findViewById(R.id.pager);
        view = (TextView) findViewById(R.id.textView);
        msg1 = (TextView) findViewById(R.id.textView10);
        resID = getResources().getIdentifier("border", "drawable", "com.mirza.frainz.frainz_assignment");

        animation = AnimationUtils.loadAnimation(MainActivity.context, R.anim.bounce);

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
                indicator.setSelectedDotColor(Color.WHITE);
                indicator.setUnselectedDotColor(Color.WHITE);
                signin.setTextColor(Color.WHITE);
                register.setBackgroundResource(resID);
                indicator.setSelectedItem(position, true);
                switch (position) {
                    case 0:
                        register = (Button) findViewById(R.id.button);
                        indicator.setSelectedDotColor(Color.BLACK);
                        indicator.setUnselectedDotColor(Color.BLACK);
                        signin.setTextColor(Color.rgb(0, 132, 191));
                        register.setBackgroundColor(Color.rgb(0, 132, 191));
                        register.startAnimation(AnimationUtils.loadAnimation(MainActivity.context, R.anim.shake));
                        break;
                    case 1:
                        anim = findViewById(R.id.relativeLayout);
                        anim.startAnimation(AnimationUtils.loadAnimation(MainActivity.context, R.anim.shake));
                        break;
                    case 2:

                        Animation animation1 = AnimationUtils.loadAnimation(MainActivity.context, R.anim.bounce);
                        image = (ImageView) findViewById(R.id.congoImage);
                        image.startAnimation(animation1);

                        Animation animation2 = AnimationUtils.loadAnimation(MainActivity.context, R.anim.bounce);
                        animation2.setStartOffset(600);
                        msg2 = (TextView) findViewById(R.id.textView11);
                        msg2.setAnimation(animation2);

                        Animation animation3 = AnimationUtils.loadAnimation(MainActivity.context, R.anim.bounce);
                        animation3.setStartOffset(1000);
                        msg3 = (TextView) findViewById(R.id.textView12);
                        msg3.setAnimation(animation3);
                        break;
                }

            }

        });


    }

    public void login(View v) {
        signUpForm.setVisibility(View.GONE);
        loginForm.setVisibility(View.VISIBLE);

    }

    public void register(View v) {
        loginForm.setVisibility(View.GONE);
        signUpForm.setVisibility(View.VISIBLE);
    }

    public void validate(View v) {
        Intent intent = new Intent(MainActivity.this, Home.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        loginForm.setVisibility(View.GONE);
        signUpForm.setVisibility(View.GONE);
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        public Fragment getItem(int pos) {
            switch (pos) {
                case 0:
                    return Span.newInstance(0);
                case 1:
                    return Span.newInstance(1);
                default:
                    return Span.newInstance(2);
            }
        }
    }


}
