package com.androidlec.addressbook.LJH;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

public class LJH_InsertNetworkTask extends AsyncTask<Integer, String, Void> { // <Params, Progress, Result> 타입이다. 잊지말고! ,, 백그라운드 값에 따라 리턴값이 달라짐. get() 값도 달라짐.... void 여서 void.

    private Context context;
    private String mAddr;
    private ProgressDialog progressDialog; // 가시적으로도 좋고, 에러 확인 겸 ㅎ

    // 생성자 만들어지... 2개만 쓴다고 했으니깐.
    // 생성자.
    public LJH_InsertNetworkTask(Context context, String mAddr) {
        this.context = context;
        this.mAddr = mAddr;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Dialog");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        try {
            URL url = new URL(mAddr); // 타고 나가야지.
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // cast.
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) { // 실행 됨.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}//----
