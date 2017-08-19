package com.mirza.frainz.frainz_assignment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

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
    EditText email, password, fname, lname, remail, rpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        loginForm = findViewById(R.id.loginFrame);
        signUpForm = findViewById(R.id.signUpFrame);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        remail = (EditText) findViewById(R.id.remail);
        rpassword = (EditText) findViewById(R.id.rpass);

        register = (Button) findViewById(R.id.button);
        register.startAnimation(AnimationUtils.loadAnimation(MainActivity.context, R.anim.shake));
        signin = (Button) findViewById(R.id.button2);
        indicator = (DotIndicator) findViewById(R.id.indicator);
        pager = (ViewPager) findViewById(R.id.pager);
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
        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Please fill all info", Toast.LENGTH_LONG).show();
            return;
        } else {
            AsyncLogin asyncLogin = new AsyncLogin();
            asyncLogin.execute(email.getText().toString(), password.getText().toString(), "http://insecgps.tk/insec/f_login.php");

        }
    }

    public void newAccount(View v) {
        if (fname.getText().toString().isEmpty() || lname.getText().toString().isEmpty() || remail.getText().toString().isEmpty() || rpassword.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Please fill the info", Toast.LENGTH_LONG).show();
            return;
        } else {
            AsyncRegister asyncRegister = new AsyncRegister();
            asyncRegister.execute(remail.getText().toString(), rpassword.getText().toString(), fname.getText().toString(), lname.getText().toString(), "http://insecgps.tk/insec/f_register.php");

        }
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

    public class AsyncLogin extends AsyncTask<String, Void, String> {

        String userEmail;
        String password;
        ProgressDialog load;
        String err = "";


        @Override
        protected void onPreExecute() {
            load = new ProgressDialog(MainActivity.this);
            load.setMessage("loading...");
            load.show();
            load.setCancelable(false);


        }

        @Override
        protected String doInBackground(String... params) {
            String result = "", line;
            try {
                userEmail = params[0];
                password = params[1];
                URL url = new URL(params[2]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String data = URLEncoder.encode("u", "UTF-8") + "=" + URLEncoder.encode(userEmail, "UTF-8") + "&" +
                        URLEncoder.encode("p", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.flush();
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = bufferedReader.readLine()) != null) {
                    result = result + line;
                }
                inputStream.close();
                JSONObject jsonRootObject = new JSONObject(result);
                result = (String) jsonRootObject.get("t");

                return result;
            } catch (UnsupportedEncodingException e) {
                err = err + e;
                e.printStackTrace();
            } catch (ProtocolException e) {
                err = err + e;
                e.printStackTrace();
            } catch (MalformedURLException e) {
                err = err + e;
                e.printStackTrace();
            } catch (IOException e) {
                err = err + e;
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "something went wrong" + err;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("s")) {
                Intent i = new Intent(MainActivity.this, Home.class);
                startActivity(i);
                finish();
                Toast.makeText(getBaseContext(), "Welcome to Frainz", Toast.LENGTH_LONG).show();
                load.dismiss();
            } else {
                Toast.makeText(getBaseContext(), "Please check email Id and password...!", Toast.LENGTH_LONG).show();
                load.dismiss();
            }

        }

    }

    public class AsyncRegister extends AsyncTask<String, Void, String> {

        String fname;
        String lname;
        String userEmail;
        String password;
        ProgressDialog load;
        String err = "";


        @Override
        protected void onPreExecute() {
            load = new ProgressDialog(MainActivity.this);
            load.setMessage("loading...");
            load.show();
            load.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "", line;
            try {
                userEmail = params[0];
                password = params[1];
                fname = params[2];
                lname = params[3];
                URL url = new URL(params[4]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String data = URLEncoder.encode("fn", "UTF-8") + "=" + URLEncoder.encode(fname, "UTF-8") + "&" +
                        URLEncoder.encode("ln", "UTF-8") + "=" + URLEncoder.encode(lname, "UTF-8") + "&" +
                        URLEncoder.encode("u", "UTF-8") + "=" + URLEncoder.encode(userEmail, "UTF-8") + "&" +
                        URLEncoder.encode("p", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.flush();
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = bufferedReader.readLine()) != null) {
                    result = result + line;
                }
                inputStream.close();
                JSONObject jsonRootObject = new JSONObject(result);
                result = (String) jsonRootObject.get("t");

                return result;
            } catch (UnsupportedEncodingException e) {
                err = err + e;
                e.printStackTrace();
            } catch (ProtocolException e) {
                err = err + e;
                e.printStackTrace();
            } catch (MalformedURLException e) {
                err = err + e;
                e.printStackTrace();
            } catch (IOException e) {
                err = err + e;
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "something went wrong" + err;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("s")) {
                Intent i = new Intent(MainActivity.this, Home.class);
                startActivity(i);
                finish();
                Toast.makeText(getBaseContext(), "Welcome to Frainz", Toast.LENGTH_LONG).show();
                load.dismiss();
            } else if (result.equals("x")) {
                Toast.makeText(getBaseContext(), "Please try again", Toast.LENGTH_LONG).show();
                load.dismiss();
            } else {
                Toast.makeText(getBaseContext(), "Email Id already exist...!", Toast.LENGTH_LONG).show();
                load.dismiss();
            }

        }

    }


}
