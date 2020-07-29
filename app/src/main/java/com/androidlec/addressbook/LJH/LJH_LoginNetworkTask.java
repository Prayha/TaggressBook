package com.androidlec.addressbook.LJH;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LJH_LoginNetworkTask extends AsyncTask<Integer, String, Integer> {

    private Context context;
    private String mAddr;
    private ProgressDialog progressDialog;
    private int loginChk;

    public LJH_LoginNetworkTask(Context context, String mAddr) {
        this.context = context;
        this.mAddr = mAddr;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Dialog");
        progressDialog.setMessage("Please wait a moment..");
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        progressDialog.dismiss();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    // 중요한 건, doInBackground 죠.
    @Override
    protected Integer doInBackground(Integer... integers) {
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
                    stringBuffer.append(strline).append("\n");
                } // 와일문 끝나면 다 가져왔다~.

                loginChk = Integer.parseInt(stringBuffer.toString().trim());

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

        return loginChk; // 다 해놓고 리턴 안하면 꽝이죠~. 이제 parser에 대해서 정리만 해주면 됨.
    }


}//----
