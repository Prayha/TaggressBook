package com.androidlec.addressbook.addr_jhj;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

public class Jhj_Address_Delete_NetworkTask extends AsyncTask<Integer, String, Void> {

    Context context;
    String mAddr;
    ProgressDialog progressDialog;

    public Jhj_Address_Delete_NetworkTask(Context context, String mAddr) {
        this.context = context;
        this.mAddr = mAddr;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        try {
            URL url = new URL(mAddr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ProgressDialog 설정 --------------------------------------------
    // 데이터를 받고있을때
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Dialogue()");
        progressDialog.setMessage("down.....");
        progressDialog.show();
    }

    // 데이터가 바뀌엇을때
    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

    }

    // 데이터가 완료되었을때
    @Override
    protected void onPostExecute(Void o) {
        super.onPostExecute(o);
        progressDialog.dismiss();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
    // ProgressDialog 설정끝 --------------------------------------------
}
