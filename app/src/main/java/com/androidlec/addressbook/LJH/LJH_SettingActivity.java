package com.androidlec.addressbook.LJH;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidlec.addressbook.Activity.LoginActivity;
import com.androidlec.addressbook.Activity.MainActivity;
import com.androidlec.addressbook.Activity.RegisterActivity;
import com.androidlec.addressbook.R;
import com.androidlec.addressbook.StaticData;
import com.google.android.material.textfield.TextInputEditText;

public class LJH_SettingActivity extends AppCompatActivity {

    // xml
    private TextInputEditText et_id, et_password;
    private Button btn_change, btn_delete;

    // 네트워크 주소
    private String urlAddr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // 키보드 화면 가림막기
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        // 초기화
        init();

        // 클릭 리스너
        btn_change.setOnClickListener(onClickListener);
        btn_delete.setOnClickListener(onClickListener);
    } // onCreate

    private void init() {
        et_id = findViewById(R.id.setting_et_id);
        et_password = findViewById(R.id.setting_et_password);
        btn_change = findViewById(R.id.setting_btn_change);
        btn_delete = findViewById(R.id.setting_btn_delete);

        et_id.setText(StaticData.USER_ID);
    } // 초기화

    private void changePw() {
        String newPw = et_password.getText().toString();

        urlAddr = StaticData.DB_URL + "hangePw.jsp?";
        urlAddr = urlAddr + "id=" + StaticData.USER_ID + "&pw=" + newPw;

        connectChangePwData();
    } // 비밀번호 변경

    private void connectChangePwData() {
        try {
            LJH_InsertNetworkTask changePwNetworkTask = new LJH_InsertNetworkTask(LJH_SettingActivity.this, urlAddr);
            changePwNetworkTask.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // 비밀번호 변경 액션

    private void deleteId() {
        urlAddr = StaticData.DB_URL + "deleteId.jsp?";
        urlAddr = urlAddr + "id=" + StaticData.USER_ID;

        connectDeleteIdData();
    } // 회원 탈퇴

    private void connectDeleteIdData() {
        try {
            LJH_InsertNetworkTask deleteIdNetworkTask = new LJH_InsertNetworkTask(LJH_SettingActivity.this, urlAddr);
            deleteIdNetworkTask.execute().get();

        } catch (Exception e) {
            e.printStackTrace();
        }
    } // 회원 탈퇴 액션

    private void removeAutoLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("autoLogin", false); // key, value를 이용하여 저장하는 형태

        editor.apply();
    } // 자동로그인 데이터 지우기

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.setting_btn_change:
                    removeAutoLogin();
                    changePw();
                    Toast.makeText(LJH_SettingActivity.this, "비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LJH_SettingActivity.this, MainActivity.class));
                    finish();
                    break;
                case R.id.setting_btn_delete:
                    // --------------- 대화상자 띄우기 -------------------------------------------------------------
                    new AlertDialog.Builder(LJH_SettingActivity.this)
                            .setTitle("정말로 탈퇴하시겠습니까?")
                            .setCancelable(false)
                            .setNegativeButton("취소", null)
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    removeAutoLogin();
                                    deleteId();
                                    startActivity(new Intent(LJH_SettingActivity.this, LoginActivity.class));
                                    Toast.makeText(LJH_SettingActivity.this, "그동안 이용해주셔서 감사합니다.", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            })
                            .show();
                    // ---------------------------------------------------------------------------------------

                    break;
            }
        }
    };

}//----