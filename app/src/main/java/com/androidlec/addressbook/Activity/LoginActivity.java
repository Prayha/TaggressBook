package com.androidlec.addressbook.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidlec.addressbook.LJH.LJH_LoginNetworkTask;
import com.androidlec.addressbook.R;
import com.androidlec.addressbook.StaticData;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    // xml Field
    private TextInputEditText et_id, et_password;
    private Button btn_login;
    private TextView tv_register;
    private CheckBox cb_autoLogin;

    // 뒤로가기 버튼
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 키보드 화면 가림막기
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        // 초기화
        init();

        // 클릭 리스너
        btn_login.setOnClickListener(onClickListener);
        tv_register.setOnClickListener(onClickListener);
    } // onCreate

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && (StaticData.FINISH_INTERVAL_TIME >= intervalTime)) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "뒤로가기를 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    } // 뒤로가기

    private void init() {
        et_id = findViewById(R.id.login_et_id);
        et_password = findViewById(R.id.login_et_password);
        btn_login = findViewById(R.id.login_btn_login);
        tv_register = findViewById(R.id.login_tv_register);
        cb_autoLogin = findViewById(R.id.login_cb_autoLogin);

        // 자동 로그인
        SharedPreferences sf = getSharedPreferences("sFile", MODE_PRIVATE);
        boolean autoLogin = sf.getBoolean("autoLogin", false);

        if(autoLogin){
            loginOk(sf.getString("id", ""));
        }

    } // 초기화

    private void idPwChk() {
        String idChk = et_id.getText().toString();
        String pwChk = et_password.getText().toString();

        // 로그인 이메일 포맷체크
        if (!Patterns.EMAIL_ADDRESS.matcher(idChk).matches()) {
            // invalid email pattern set error
            Toast.makeText(LoginActivity.this, "이메일 주소를 확인해주세요.", Toast.LENGTH_SHORT).show();
            et_id.setError("이메일 주소를 확인해주세요.");
            et_id.setFocusable(true);
        } else if (pwChk.length() < 6) {
            Toast.makeText(LoginActivity.this, "6자리 이상의 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            et_password.setError("6자리 이상의 비밀번호를 입력해주세요.");
            et_password.setFocusable(true);
        } else {
            connectLoginData(idChk, pwChk);
        }
    } // Id, Pw 유효성검사

    private void connectLoginData(String uId, String uPw) {
        String urlAddr = StaticData.DB_URL + "loginChk.jsp?";
        urlAddr = urlAddr + "id=" + uId + "&pw=" + uPw;

        try {
            LJH_LoginNetworkTask loginNetworkTask = new LJH_LoginNetworkTask(LoginActivity.this, urlAddr);
            int loginChk = loginNetworkTask.execute().get();
            if (loginChk == 0) {
                // 로그인 실패
                Toast.makeText(LoginActivity.this, "회원님의 이메일 주소 혹은 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
            } else {
                // 로그인 완료.
                loginOk(uId);
                Toast.makeText(LoginActivity.this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // 가입된 데이터 불러오기

    private void loginOk(String uId) {
        // 로그인 아이디 넘기기.
        StaticData.USER_ID = uId;
        // 로그인 스퀸스넘버 넘기기.
        getUSeqno(uId);
        // 자동 로그인
        if(cb_autoLogin.isChecked()){
            autoLogin();
        }

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    } // 로그인 성공

    private void autoLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("autoLogin", true); // key, value를 이용하여 저장하는 형태
        editor.putString("id", StaticData.USER_ID); // key, value를 이용하여 저장하는 형태
        editor.putInt("seq", StaticData.USER_SEQ); // key, value를 이용하여 저장하는 형태

        editor.apply();
    }

    private void getUSeqno(String uId) {
        String urlAddr = StaticData.DB_URL + "uSeqno.jsp?";
        urlAddr = urlAddr + "id=" + uId;

        try {
            LJH_LoginNetworkTask loginNetworkTask = new LJH_LoginNetworkTask(LoginActivity.this, urlAddr);
            StaticData.USER_SEQ = loginNetworkTask.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // Seq 구하기

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_btn_login:
                    idPwChk();
                    break;
                case R.id.login_tv_register:
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    break;
            }
        }
    };

}//----