package com.androidlec.addressbook.SH_adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidlec.addressbook.R;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private String[] spinnerNames;
    private TypedArray spinnerImages;
    private Context mContext;
    private LayoutInflater inflater;

    public CustomSpinnerAdapter(@NonNull Context context, String[] names, TypedArray images) {
        super(context, R.layout.spinner_row);
        this.spinnerNames = names;
        this.spinnerImages = images;
        this.mContext = context;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return spinnerNames.length;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder mViewHolder = new ViewHolder();

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.spinner_row, parent, false);

            mViewHolder.mImage = convertView.findViewById(R.id.iv_spn_tag_image);
            mViewHolder.mName = convertView.findViewById(R.id.tv_spn_tag_name);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.mImage.setImageResource(spinnerImages.getResourceId(position, -1));
        mViewHolder.mName.setText(spinnerNames[position]);

        return convertView;
    }

    private static class ViewHolder {
        ImageView mImage;
        TextView mName;
    }
}
