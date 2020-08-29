package com.androidlec.addressbook.Activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.androidlec.addressbook.R;
import com.androidlec.addressbook.StaticData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

public class ContactListAdapter extends BaseAdapter {

    private Context mContext;
    private int layout;
    private ArrayList<PhoneBook> data;
    private LayoutInflater inflater;

    ImageView ctivpfimage;
    TextView cttvname, cttvphone, cttvemail;
    CheckBox ctcbselect;

    public ContactListAdapter(Context mContext, int layout, ArrayList<PhoneBook> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getId();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
        }

        ctivpfimage = convertView.findViewById(R.id.cl_tv_ContactPFImage);
        cttvname = convertView.findViewById(R.id.cl_tv_ContactName);
        cttvphone = convertView.findViewById(R.id.cl_tv_ContactPhone);
        cttvemail = convertView.findViewById(R.id.cl_tv_ContactEmail);
        ctcbselect = convertView.findViewById(R.id.cl_cb_contactSelec);

        Log.e("Adapter",data.get(position).getName());

        cttvname.setText(data.get(position).getName());
        cttvphone.setText(data.get(position).getTel());
        if (data.get(position).getEmail().length() == 0) {
            cttvemail.setText("-");
        } else {
            cttvemail.setText(data.get(position).getEmail());
        }

        //String url = StaticData.BASE_URL + data.get(position).getAimage();

        //이미지 보여주기
//        Glide.with(mContext)
//                .load(url)
//                .apply(new RequestOptions().circleCrop())
//                .placeholder(R.drawable.ic_outline_emptyimage)
//                .into(ctivpfimage);

        return convertView;
    }
}
