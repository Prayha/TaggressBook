package com.androidlec.addressbook.LJH;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LJH_TagNetwork extends AsyncTask<Integer, String, Object> {

    private Context context;
    private String mAddr;
    private ArrayList<String> tNames;
    private ProgressDialog progressDialog;

    public LJH_TagNetwork(Context context, String mAddr) {
        this.context = context;
        this.mAddr = mAddr;
        this.tNames = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Dialog");
        progressDialog.setMessage("Get...");
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Object doInBackground(Integer... integers) {
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            URL url = new URL(mAddr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000); // 10 seconds.

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                inputStream = httpURLConnection.getInputStream(); // 이때 데이터 가져오는 겁니다.
                inputStreamReader = new InputStreamReader(inputStream); // 가져온거를 리더에 넣어야겠죠.
                bufferedReader = new BufferedReader(inputStreamReader); // 버퍼드리더에 넣어야합니다.

                while (true) {
                    String strline = bufferedReader.readLine();
                    if (strline == null) break; // 브레이크 만나면 와일문 빠져나간다.
                    stringBuffer.append(strline + "\n");
                } // 와일문 끝나면 다 가져왔다~.

                // 파싱.
                parser(stringBuffer.toString()); // 아직 안만들었어요~~~ But, 파씽 하겠다~.

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (inputStream != null) inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return tNames;
    }


    private void parser(String s) { // 스트링 하나만 가져오죠.

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("tag")); // students_info 에 있는걸 가져와라.
            tNames.clear(); // 깨끗하게.

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                String tName = jsonObject1.getString("tName");

                tNames.add(tName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}//----
