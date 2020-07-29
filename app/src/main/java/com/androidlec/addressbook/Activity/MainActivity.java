package com.androidlec.addressbook.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.androidlec.addressbook.CS.CSNetworkTask;
import com.androidlec.addressbook.CS.TagOptionDialog;
import com.androidlec.addressbook.LJH.LJH_SettingActivity;
import com.androidlec.addressbook.LJH.LJH_TagNetwork;
import com.androidlec.addressbook.R;
import com.androidlec.addressbook.SH_adapter.AddressListAdapter;
import com.androidlec.addressbook.SH_adapter.CustomSpinnerAdapter;
import com.androidlec.addressbook.SH_dto.Address;
import com.androidlec.addressbook.SH_network.NetworkTask;
import com.androidlec.addressbook.StaticData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // xml
    private TextView pre_cmt;
    private TextView tv_noList;

    // 액션바
    private ActionBar actionBar;

    // 스피너
    private Spinner spinner_tags;
    public static String[] spinnerNames;
    private ArrayList<String> tNames;
    public static TypedArray tagImages;
    private int spinnerPosition;

    // 리스트뷰
    private ArrayList<Address> data;
    private AddressListAdapter adapter;
    private ListView listView;
    private TextView tv_listFooter;

    String urlAddr;

    // 플로팅버튼
    private FloatingActionButton fladdBtn;

    // 뒤로가기 버튼
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 키보드 화면 가림막기
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        // 초기화
        init();

        //스피너
        Spinner_List();

        //플로팅버튼
        fladdBtn.setOnClickListener(onClickListener);

        // 액션바
        actionBar.setTitle("내 주소록");

        // 리스트뷰 클릭 리스너
        listView.setOnItemClickListener(lvOnItemClickListener);
        listView.setOnItemLongClickListener(itemLongClickListener);

    } // onCreate

    @Override
    protected void onResume() {
        super.onResume();
        Spinner_List();
    } // onResume

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(onQueryTextListener);

        return true;
    } // 메뉴생성

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_optionTag:
                startActivity(new Intent(MainActivity.this, TagOptionDialog.class));
                break;
            case R.id.menu_setting:
                startActivity(new Intent(MainActivity.this, LJH_SettingActivity.class));
                break;
            case R.id.menu_logout:
                StaticData.USER_ID = "";
                removeAutoLogin();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    } // 메뉴 클릭 리스너

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
        // xml 초기화
        spinner_tags = findViewById(R.id.main_sp_taglist);
        listView = findViewById(R.id.main_lv_addresslist);
        fladdBtn = findViewById(R.id.main_fab_add);
        tv_noList = findViewById(R.id.main_tv_noList);

        // listView에 footer 추가.
        listView.addFooterView(getLayoutInflater().inflate(R.layout.address_list_footer, null, false));
        tv_listFooter = findViewById(R.id.listView_footer);

        // 리소스에서 불러오기
        Resources res = getResources();
        spinnerNames = res.getStringArray(R.array.maintaglist);
        tagImages = res.obtainTypedArray(R.array.tag_array);

        actionBar = getSupportActionBar();
        data = new ArrayList<>();
    } // 초기화

    private void Spinner_List() {
        spinnerNames[0] = "전체보기";

        // 태그 불러오기.
        onTagList();

        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(MainActivity.this, spinnerNames, tagImages);
        spinner_tags.setAdapter(customSpinnerAdapter);
        spinner_tags.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    urlAddr = StaticData.DB_URL + "address_list_select.jsp?userid=" + StaticData.USER_ID;
                    spinnerPosition = 0;
                } else {
                    urlAddr = StaticData.DB_URL + "address_list_selectedspinner.jsp?userid=" + StaticData.USER_ID + "&aTag=" + position;
                    spinnerPosition = position;
                }
                connectGetData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    } // 스피너 리스트

    private void connectGetData() {
        try {
            NetworkTask networkTask = new NetworkTask(MainActivity.this, urlAddr);
            Object obj = networkTask.execute().get();
            data = (ArrayList<Address>) obj;

            if (data.size() == 0) {
                tv_noList.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            } else {
                tv_noList.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                tv_listFooter.setText(data.size() + "개의 연락처");
                adapter = new AddressListAdapter(MainActivity.this, R.layout.address_list_layout, data);
                listView.setAdapter(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }  // connectGetData

    private void onTagList() {
        urlAddr = StaticData.DB_URL + "tagList.jsp?";
        urlAddr = urlAddr + "id=" + StaticData.USER_ID;

        try {
            LJH_TagNetwork tagListNetworkTask = new LJH_TagNetwork(MainActivity.this, urlAddr);
            Object obj = tagListNetworkTask.execute().get();
            tNames = new ArrayList<>();
            tNames = (ArrayList<String>) obj; // cast.

            spinnerNames[1] = tNames.get(0);
            spinnerNames[2] = tNames.get(1);
            spinnerNames[3] = tNames.get(2);
            spinnerNames[4] = tNames.get(3);
            spinnerNames[5] = tNames.get(4);
            spinnerNames[6] = tNames.get(5);
            spinnerNames[7] = tNames.get(6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // 태그 리스트 불러오기

    private void deleteFromDB(int seq) {
        String urlAddr = StaticData.DB_URL + "csDeleteAddressBook.jsp?";

        urlAddr = urlAddr + "seq=" + seq;

        try {
            CSNetworkTask csNetworkTask = new CSNetworkTask(MainActivity.this, urlAddr);
            csNetworkTask.execute().get(); // doInBackground 의 리턴값
            onResume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // 연락처 삭제

    private void removeAutoLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("autoLogin", false); // key, value를 이용하여 저장하는 형태

        editor.apply();
    } // 자동로그인 데이터 지우기

    SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            if (spinnerPosition == 0) {
                urlAddr = StaticData.DB_URL + "address_list_search.jsp?userid=" + StaticData.USER_ID + "&search=" + query;
            } else {
                urlAddr = StaticData.DB_URL + "csAddress_list_search.jsp?userid=" + StaticData.USER_ID + "&search=" + query + "&aTag=" + spinnerPosition;
            }
            connectGetData();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            //안함
            return false;
        }
    };

    FloatingActionButton.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        }
    };

    ListView.OnItemClickListener lvOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView cmt = view.findViewById(R.id.tv_addresslist_cmt);
            if (position != data.size()) {
                if (pre_cmt != null) {
                    pre_cmt.setVisibility(View.GONE);
                }
                pre_cmt = cmt;
                cmt.setVisibility(View.VISIBLE);
            }
        }
    };

    ListView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            if (position != data.size()) {
                String[] options = {"전화걸기", "연락처 수정", "연락처 삭제"};

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // 전화걸기
                                String tel = "tel:" + data.get(position).getAphone();
                                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                                break;
                            case 1: // 연락처 수정
                                Intent updateIntent = new Intent(getApplicationContext(), UpdateActivity.class);
                                updateIntent.putExtra("seq", data.get(position).getAseqno());
                                startActivity(updateIntent);
                                break;
                            case 2: // 연락처 삭제
                                deleteFromDB(data.get(position).getAseqno());
                                break;
                        }
                    }
                });
                builder.create().show();
            }
            return true;
        }
    };

}//----

