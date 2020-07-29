package com.androidlec.addressbook.Activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidlec.addressbook.LJH.LJH_InsertNetworkTask;
import com.androidlec.addressbook.LJH.LJH_LoginNetworkTask;
import com.androidlec.addressbook.R;
import com.androidlec.addressbook.StaticData;

public class RegisterActivity extends AppCompatActivity {

    // xml Field
    private EditText et_regId, et_regPw, et_regPwOk;
    private Button regBtn;
    private TextView backLogin;

    // 기타 Field
    private String regId, regPw, regPwOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 키보드 화면 가림막기
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        // 초기화
        init();

    } // onCreate

    private void init() {
        et_regId = findViewById(R.id.register_et_id);
        et_regPw = findViewById(R.id.register_et_password);
        et_regPwOk = findViewById(R.id.register_et_passwordOK);

        regBtn = findViewById(R.id.register_btn_register);
        regBtn.setOnClickListener(onClickListener);

        backLogin = findViewById(R.id.register_tv_login);
        backLogin.setOnClickListener(onClickListener);
    } // 초기화

    private void blankChk() {
        // 회원가입 이메일 포맷체크, 비밀번호 6자리체크
        if (!Patterns.EMAIL_ADDRESS.matcher(regId).matches()) {
            // set error and focus to email edittext
            // --------------- 대화상자 띄우기 ---------------------------------------------------------
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("올바른 형식의 이메일 주소를 설정해주세요.")
                    .setCancelable(false)
                    .setNegativeButton("확인", null)
                    .show();
            // -------------------------------------------------------------------------------------
            et_regId.setError("Invalid Email");
            et_regId.setFocusable(true);
        } else if (regPw.length() < 6) {
            // --------------- 대화상자 띄우기 ---------------------------------------------------------
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("비밀번호를 6자 이상으로 설정해주세요.")
                    .setCancelable(false)
                    .setNegativeButton("확인", null)
                    .show();
            // -------------------------------------------------------------------------------------
            et_regPw.setError("Password length at least 6 characters");
            et_regPw.setFocusable(true);
        } else {
            pwChk(); // 패스워드 확인.
        }
    } // id, pw 유효성검사

    private void pwChk() {
        regPwOk = et_regPwOk.getText().toString();

        if (regPw.equals(regPwOk)) {
            idDoubleChk(); // 아이디 중복 체크.
            // connectRegData(); // 회원가입 DB 연결.
        } else {
            // --------------- 대화상자 띄우기 ---------------------------------------------------------
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("비밀번호가 일치하지 않습니다.")
                    .setCancelable(false)
                    .setNegativeButton("확인", null)
                    .show();
            // -------------------------------------------------------------------------------------
        }
    } // 비밀번호, 비밀번호 확인 체크

    private void idDoubleChk() {
        String urlAddr = StaticData.DB_URL + "idDoubleChk.jsp?";
        urlAddr = urlAddr + "id=" + regId;

        try {
            LJH_LoginNetworkTask idDoubleChkNetworkTask = new LJH_LoginNetworkTask(RegisterActivity.this, urlAddr);
            int idDoubleChk = idDoubleChkNetworkTask.execute().get();

            if (idDoubleChk == 1) {
                Toast.makeText(RegisterActivity.this, "이미 존재하는 이메일 입니다.", Toast.LENGTH_SHORT).show();
            } else {
                // 아이디 중복체크 완료.
                register(); // 회원가입.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // 아이디 중복 체크

    private void register() {
        String urlAddr = StaticData.DB_URL + "register.jsp?";
        urlAddr = urlAddr + "id=" + regId + "&pw=" + regPw;

        String urlAddrTag = StaticData.DB_URL + "tagInsert.jsp?id=" + regId;

        connectRegisterData(urlAddr);
        connectTagInsertData(urlAddrTag);

        Toast.makeText(RegisterActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    } // 회원가입

    private void connectRegisterData(String urlAddr) { // NetworkTask Async 방식. == permission 줘야 하는구나!
        try {
            LJH_InsertNetworkTask insertNetworkTask = new LJH_InsertNetworkTask(RegisterActivity.this, urlAddr);
            insertNetworkTask.execute().get();

        } catch (Exception e) {
            e.printStackTrace();
        }
    } // 유저정보등록

    private void connectTagInsertData(String urlAddrTag) {
        try {
            LJH_InsertNetworkTask tagInsertNetworkTask = new LJH_InsertNetworkTask(RegisterActivity.this, urlAddrTag);
            tagInsertNetworkTask.execute().get();

        } catch (Exception e) {
            e.printStackTrace();
        }
    } // 태그정보등록

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.register_tv_login:
                    finish();
                    break;
                case R.id.register_btn_register:
                    regId = et_regId.getText().toString();
                    regPw = et_regPw.getText().toString();
                    blankChk();
                    break;
            }
        }
    };

}//---