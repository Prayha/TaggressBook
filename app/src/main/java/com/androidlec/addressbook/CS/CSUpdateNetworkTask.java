package com.androidlec.addressbook.CS;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.androidlec.addressbook.SH_dto.Address;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CSUpdateNetworkTask extends AsyncTask<Integer, String, Address> {

    private Context context;
    private String mAddr;
    private ProgressDialog progressDialog;
    private Address result;

    public CSUpdateNetworkTask(Context context, String mAddr) {
        this.context = context;
        this.mAddr = mAddr;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("불러오는 중...");
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Address address) {
        super.onPostExecute(address);
        progressDialog.dismiss();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Address doInBackground(Integer... integers) {
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

                parser(stringBuffer.toString());

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
        return result;
    }

    private void parser(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);

            int aseqno = Integer.parseInt(jsonObject.getString("aSeqno"));
            String aname = jsonObject.getString("aName");
            String aimage = jsonObject.getString("aImage");
            String aphone = jsonObject.getString("aPNum");
            String aemail = jsonObject.getString("aEmail");
            String amemo = jsonObject.getString("aMemo");
            String atag = jsonObject.getString("aTag");

            result = new Address(aseqno, aname, aimage, aphone, aemail, amemo, atag);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
