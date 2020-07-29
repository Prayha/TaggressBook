package com.androidlec.addressbook.SH_adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidlec.addressbook.Activity.MainActivity;
import com.androidlec.addressbook.R;
import com.androidlec.addressbook.SH_dto.Address;
import com.androidlec.addressbook.StaticData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class AddressListAdapter extends BaseAdapter {

    private Context mContext;
    private int layout;
    private ArrayList<Address> data;
    private LayoutInflater inflater;

    public ImageView ivpfimage;
    public TextView tvname;
    public TextView tvphone;
    public TextView tvemail;
    public ImageView ivpftag1;
    public ImageView ivpftag2;
    public ImageView ivpftag3;
    public TextView tvcmt;

    public AddressListAdapter(Context mContext, int layout, ArrayList<Address> data) {
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
        return data.get(position).getAseqno();
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

        ivpfimage = convertView.findViewById(R.id.iv_addresslist_pfimage);
        tvname = convertView.findViewById(R.id.tv_addresslist_name);
        tvphone = convertView.findViewById(R.id.tv_addresslist_phone);
        tvemail = convertView.findViewById(R.id.tv_addresslist_email);
        ivpftag1 = convertView.findViewById(R.id.iv_addresslist_tag1);
        ivpftag2 = convertView.findViewById(R.id.iv_addresslist_tag2);
        ivpftag3 = convertView.findViewById(R.id.iv_addresslist_tag3);
        tvcmt = convertView.findViewById(R.id.tv_addresslist_cmt);


        tvname.setText(data.get(position).getAname());
        tvphone.setText(data.get(position).getAphone());
        if (data.get(position).getAemail().length() == 0) {
            tvemail.setText("-");
        } else {
            tvemail.setText(data.get(position).getAemail());
        }
        if (data.get(position).getAmemo().length() == 0) {
            tvcmt.setText("-");
        } else {
            tvcmt.setText(data.get(position).getAmemo());
        }

        String url = StaticData.BASE_URL + data.get(position).getAimage();

        //이미지 보여주기
        Glide.with(mContext)
                .load(url)
                .apply(new RequestOptions().circleCrop())
                .placeholder(R.drawable.ic_outline_emptyimage)
                .into(ivpfimage);

        //Tag color 보여주기
        String[] tags = data.get(position).getAtag().split(",");
        TypedArray tagImages = MainActivity.tagImages;

        switch (tags.length) {
            case 0:
                ivpftag1.setImageResource(tagImages.getResourceId(0, 0));
                ivpftag2.setImageResource(tagImages.getResourceId(0, 0));
                ivpftag3.setImageResource(tagImages.getResourceId(0, 0));
                break;
            case 1:
                ivpftag1.setImageResource(tagImages.getResourceId(Integer.parseInt(tags[0]), 0));
                ivpftag2.setImageResource(tagImages.getResourceId(0, 0));
                ivpftag3.setImageResource(tagImages.getResourceId(0, 0));
                break;
            case 2:
                ivpftag1.setImageResource(tagImages.getResourceId(Integer.parseInt(tags[0]), 0));
                ivpftag2.setImageResource(tagImages.getResourceId(Integer.parseInt(tags[1]), 0));
                ivpftag3.setImageResource(tagImages.getResourceId(0, 0));
                break;
            case 3:
                ivpftag1.setImageResource(tagImages.getResourceId(Integer.parseInt(tags[0]), 0));
                ivpftag2.setImageResource(tagImages.getResourceId(Integer.parseInt(tags[1]), 0));
                ivpftag3.setImageResource(tagImages.getResourceId(Integer.parseInt(tags[2]), 0));
                break;
        }

        return convertView;
    }
}
