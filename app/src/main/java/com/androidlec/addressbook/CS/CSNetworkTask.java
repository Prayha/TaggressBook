package com.androidlec.addressbook.CS;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

public class CSNetworkTask extends AsyncTask<Integer, String, Void> {

    private Context context;
    private String mAddr;
    private ProgressDialog progressDialog;

    public CSNetworkTask(Context context, String mAddr) {
        this.context = context;
        this.mAddr = mAddr;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please Wait...");
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
            URL url = new URL(mAddr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
