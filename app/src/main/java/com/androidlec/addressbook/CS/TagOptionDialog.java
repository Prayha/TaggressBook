package com.androidlec.addressbook.CS;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidlec.addressbook.LJH.LJH_InsertNetworkTask;
import com.androidlec.addressbook.LJH.LJH_TagNetwork;
import com.androidlec.addressbook.R;
import com.androidlec.addressbook.StaticData;

import java.util.ArrayList;

public class TagOptionDialog extends AppCompatActivity {

    // xml
    private Button btn_cancel, btn_submit;
    private EditText et_red, et_orange, et_yellow, et_green, et_blue, et_purple, et_gray;

    // jsp주소
    private String urlAddr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tag_option);

        // 키보드 화면 가림막기
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // 초기화
        init();

        // 클릭 리스너
        btn_cancel.setOnClickListener(onClickListener);
        btn_submit.setOnClickListener(onClickListener);
    } // onCreate

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    } // 바깥레이어 클릭시 안닫히게

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    } // 백버튼 막기

    private void init() {
        btn_cancel = findViewById(R.id.dialog_tag_option_btn_cancel);
        btn_submit = findViewById(R.id.dialog_tag_option_btn_submit);
        et_red = findViewById(R.id.tag_option_et_name_red);
        et_orange = findViewById(R.id.tag_option_et_name_orange);
        et_yellow = findViewById(R.id.tag_option_et_name_yellow);
        et_green = findViewById(R.id.tag_option_et_name_green);
        et_blue = findViewById(R.id.tag_option_et_name_blue);
        et_purple = findViewById(R.id.tag_option_et_name_purple);
        et_gray = findViewById(R.id.tag_option_et_name_gray);

        // 태그 불러오기.
        onTagList();
    } // 초기화

    private void onTagList() {
        urlAddr = StaticData.DB_URL + "tagList.jsp?";
        urlAddr = urlAddr + "id=" + StaticData.USER_ID;

        // 태그 리스트 불러오기.
        try {
            LJH_TagNetwork tagListNetworkTask = new LJH_TagNetwork(TagOptionDialog.this, urlAddr);
            Object obj = tagListNetworkTask.execute().get();
            ArrayList<String> tNames = (ArrayList<String>) obj; // cast.

            et_red.setText(tNames.get(0));
            et_orange.setText(tNames.get(1));
            et_yellow.setText(tNames.get(2));
            et_green.setText(tNames.get(3));
            et_blue.setText(tNames.get(4));
            et_purple.setText(tNames.get(5));
            et_gray.setText(tNames.get(6));

        } catch (Exception e) {
            e.printStackTrace();
        }
    } // 태그명 불러오기.

    private void onChangeTagName() {
        String tag1 = et_red.getText().toString();
        String tag2 = et_orange.getText().toString();
        String tag3 = et_yellow.getText().toString();
        String tag4 = et_green.getText().toString();
        String tag5 = et_blue.getText().toString();
        String tag6 = et_purple.getText().toString();
        String tag7 = et_gray.getText().toString();

        urlAddr = StaticData.DB_URL + "tagChange.jsp?";
        urlAddr = urlAddr + "id=" + StaticData.USER_ID + "&tag1=" + tag1 + "&tag2=" + tag2 + "&tag3=" + tag3 + "&tag4=" + tag4 + "&tag5=" + tag5 + "&tag6=" + tag6 + "&tag7=" + tag7;

        // 태그명 바꾸기.
        try {
            LJH_InsertNetworkTask tagChangeNetworkTask = new LJH_InsertNetworkTask(TagOptionDialog.this, urlAddr);
            tagChangeNetworkTask.execute().get();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "태그명이 변경되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    } // 태그명 바꾸기.

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dialog_tag_option_btn_cancel:
                    finish();
                    break;
                case R.id.dialog_tag_option_btn_submit:
                    onChangeTagName();
                    break;
            }
        }
    };

}//----