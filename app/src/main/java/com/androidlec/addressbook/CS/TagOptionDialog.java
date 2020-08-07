package com.androidlec.addressbook.CS;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidlec.addressbook.R;
import com.androidlec.addressbook.SQLite.TagInfo;

import java.util.ArrayList;

public class TagOptionDialog extends AppCompatActivity {

    // xml
    private Button btn_cancel, btn_submit;
    private EditText et_red, et_orange, et_yellow, et_green, et_blue, et_purple, et_gray;

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
        // SQLite 초기화
        TagInfo tagInfo = new TagInfo(TagOptionDialog.this, "tag", null, 1);
        // SQLite 에서 데이터 불러오기
        SQLiteDatabase DB = tagInfo.getReadableDatabase();

        // 태그 리스트 불러오기.
        try {
            String QUERY = "SELECT tName FROM tag;";
            Cursor cursor = DB.rawQuery(QUERY, null);

            ArrayList<String> tNames = new ArrayList<String>();

            while (cursor.moveToNext()) {
                Log.e("TagOption", cursor.getString(0));
                tNames.add(cursor.getString(0));
            }

            et_red.setText(tNames.get(0));
            et_orange.setText(tNames.get(1));
            et_yellow.setText(tNames.get(2));
            et_green.setText(tNames.get(3));
            et_blue.setText(tNames.get(4));
            et_purple.setText(tNames.get(5));
            et_gray.setText(tNames.get(6));

            tagInfo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // 태그명 불러오기.

    private void onChangeTagName() {
        String[] tag = new String[7];
        tag[0] = et_red.getText().toString();
        tag[1] = et_orange.getText().toString();
        tag[2] = et_yellow.getText().toString();
        tag[3] = et_green.getText().toString();
        tag[4] = et_blue.getText().toString();
        tag[5] = et_purple.getText().toString();
        tag[6] = et_gray.getText().toString();

        // SQLite 초기화
        TagInfo tagInfo = new TagInfo(TagOptionDialog.this, "tag", null, 1);
        // SQLite 에서 데이터 불러오기
        SQLiteDatabase DB = tagInfo.getWritableDatabase();

        // 태그명 바꾸기.
        try {
            for (int i = 0 ; i < tag.length ; i++) {
                String QUERY = "UPDATE tag SET tName = '" + tag[i] + "' WHERE tSeqno = " + (i + 1) + ";";
                DB.execSQL(QUERY);
            }

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