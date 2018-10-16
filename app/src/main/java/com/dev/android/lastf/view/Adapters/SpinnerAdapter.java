package com.dev.android.lastf.view.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dev.android.lastf.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 13/10/18.
 */
public class SpinnerAdapter extends ArrayAdapter<String>{
    private List<String> objects = new ArrayList<>();

    public SpinnerAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
        this.objects = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public void setGendersist(List<String> gendersist) {
        this.objects = gendersist;
        notifyDataSetChanged();
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_spinner_gender, parent, false);
        }
        TextView label = convertView.findViewById(R.id.tv_spinnervalue);
        label.setText(objects.get(position).toUpperCase());
        return convertView;
    }

}