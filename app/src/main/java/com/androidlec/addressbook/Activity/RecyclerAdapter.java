package com.androidlec.addressbook.Activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidlec.addressbook.R;
import com.androidlec.addressbook.StaticData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Address> data;
    private Context context;


    public ImageView ivpfimage;
    public TextView tvname;
    public TextView tvphone;
    public TextView tvemail;
    public ImageView ivpftag1;
    public ImageView ivpftag2;
    public ImageView ivpftag3;
    public TextView tvcmt;



    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {


        ViewHolder(View itemView) {
            super(itemView) ;
            ivpfimage = itemView.findViewById(R.id.iv_addresslist_pfimage);
            tvname = itemView.findViewById(R.id.tv_addresslist_name);
            tvphone = itemView.findViewById(R.id.tv_addresslist_phone);
            tvemail = itemView.findViewById(R.id.tv_addresslist_email);
            ivpftag1 = itemView.findViewById(R.id.iv_addresslist_tag1);
            ivpftag2 = itemView.findViewById(R.id.iv_addresslist_tag2);
            ivpftag3 = itemView.findViewById(R.id.iv_addresslist_tag3);
            tvcmt = itemView.findViewById(R.id.tv_addresslist_cmt);


        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    RecyclerAdapter(ArrayList<Address> list) {
        data = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.address_list_layout, parent, false) ;
        RecyclerAdapter.ViewHolder vh = new RecyclerAdapter.ViewHolder(view) ;




        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
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
        Glide.with(context)
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


    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return data.size() ;
    }
}
