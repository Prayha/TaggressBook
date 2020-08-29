package com.androidlec.addressbook.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.androidlec.addressbook.R;

import java.util.ArrayList;

public class GetContactActivity extends AppCompatActivity {

    //리스트뷰
    private ListView listView;
    private static ArrayList<PhoneBook> data;
    private ContactListAdapter adapter;
    private Context context;

    //Permission
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    //추가 버튼
    private TextView addContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getcontact);

        init();

        getContacts(this);

        adapter = new ContactListAdapter(GetContactActivity.this, R.layout.contact_list_layout, data);
        listView.setAdapter(adapter);
    }

    private void init() {
        // xml 초기화
        listView = findViewById(R.id.gc_lv_contactList);

        // 추가하기 버튼
        addContacts = findViewById(R.id.gc_tv_addContacts);

    } // 초기화

    public void getContacts(Context context){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            // 데이터베이스 혹은 content resolver 를 통해 가져온 데이터를 적재할 저장소를 먼저 정의
            data = new ArrayList<PhoneBook>();

            // 1. Resolver 가져오기(데이터베이스 열어주기)
            // 전화번호부에 이미 만들어져 있는 ContentProvider 를 통해 데이터를 가져올 수 있음
            // 다른 앱에 데이터를 제공할 수 있도록 하고 싶으면 ContentProvider 를 설정
            // 핸드폰 기본 앱 들 중 데이터가 존재하는 앱들은 Content Provider 를 갖는다
            // ContentResolver 는 ContentProvider 를 가져오는 통신 수단
            ContentResolver resolver = context.getContentResolver();

            // 2. 전화번호가 저장되어 있는 테이블 주소값(Uri)을 가져오기
            Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

            // 3. 테이블에 정의된 칼럼 가져오기
            // ContactsContract.CommonDataKinds.Phone 이 경로에 상수로 칼럼이 정의
            String[] projection = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID // 인덱스 값, 중복될 수 있음 -- 한 사람 번호가 여러개인 경우
                    , ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    , ContactsContract.CommonDataKinds.Phone.NUMBER
                    , ContactsContract.CommonDataKinds.Email.ADDRESS
                    , ContactsContract.CommonDataKinds.GroupMembership.GROUP_ROW_ID};

            // 4. ContentResolver로 쿼리를 날림 -> resolver 가 provider 에게 쿼리하겠다고 요청
            Cursor cursor = resolver.query(phoneUri, projection, null, null, null);

            // 4. 커서로 리턴된다. 반복문을 돌면서 cursor 에 담긴 데이터를 하나씩 추출
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    // 4.1 이름으로 인덱스를 찾아준다
                    int idIndex = cursor.getColumnIndex(projection[0]); // 이름을 넣어주면 그 칼럼을 가져와준다.
                    int nameIndex = cursor.getColumnIndex(projection[1]);
                    int numberIndex = cursor.getColumnIndex(projection[2]);
                    int emailIndex = cursor.getColumnIndex(projection[3]);
                    int groupIndex = cursor.getColumnIndex(projection[4]);
                    // 4.2 해당 index 를 사용해서 실제 값을 가져온다.
                    String id = cursor.getString(idIndex);
                    String name = cursor.getString(nameIndex);
                    String number = cursor.getString(numberIndex);
                    String email = cursor.getString(emailIndex);
                    String group = cursor.getString(groupIndex);

                    PhoneBook phoneBook = new PhoneBook();
                    phoneBook.setId(id);
                    phoneBook.setName(name);
                    phoneBook.setTel(number);
                    phoneBook.setEmail(email);
                    phoneBook.setGroup(group);


                    data.add(phoneBook);
                }
            }
            // 데이터 계열은 반드시 닫아줘야 한다.
            cursor.close();
        }

    }

}