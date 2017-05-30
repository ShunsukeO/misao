package com.example.sun.loginsystem;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Owner on 2017/05/30.
 */

public class DatabaseHelper extends AsyncTask<String, Void, String>{
    Context context;
    DatabaseHelper(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://192.168.1.108:8080/shun/LoginSystem/register.php";
//        String reg_url = "http://10.0.1.118:8080/shun/LoginSystem/register.php";
        String login_url = "http://192.168.1.108:8080/shun/LoginSystem/login.php";
//        String login_url = "http://10.0.1.118:8080/shun/LoginSystem/login.php";
        String method = params[0];

        if(method.equals("REGISTER")){
            String name = params[1];
            String birthday = params[2];
            String phone = params[3];
            String password = params[4];

            try{
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream OS = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name, "UTF-8")+"&"+
                        URLEncoder.encode("birthday", "UTF-8")+"="+URLEncoder.encode(birthday, "UTF-8")+"&"+
                        URLEncoder.encode("phone", "UTF-8")+"="+URLEncoder.encode(phone, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                IS.close();

                return "Registered Successfully...";
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }else if(method.equals("LOGIN")){
            String name = params[1];
            String password = params[2];

            try{
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream os = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line + "\n");
                }

                String result = sb.toString();
                bufferedReader.close();
                is.close();
                httpURLConnection.disconnect();
                return  result;
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result){
        if(result.equals("Registered Successfully...")){
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }else{
            String s = result.trim();
            if(s.equalsIgnoreCase("not found")){
                Toast.makeText(context, "Result Not Found Try Again...", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            }
        }
    }
}
