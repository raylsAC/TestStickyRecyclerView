package com.example.zhangzk.teststickyrecyclerview.adapter;

import android.support.v7.widget.RecyclerView;

import com.example.zhangzk.teststickyrecyclerview.bean.SelectCountryBean;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by zhangzk on 2016/8/12.
 */
public abstract class CountryBaseAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    private ArrayList<SelectCountryBean> items = new ArrayList<>();

    public CountryBaseAdapter() {
        setHasStableIds(true);
    }

//    public void add(SelectCountryBeanobject) {
//        items.add(object);
//        notifyDataSetChanged();
//    }
//
//    public void add(int index, SelectCountryBean object) {
//        items.add(index, object);
//        notifyDataSetChanged();
//    }

    public void addAll(Collection<SelectCountryBean> collection) {
        if (collection != null) {
            items.clear();
            items.addAll(collection);
            notifyDataSetChanged();
        }
    }

//    public void addAll(SelectCountryBean... items) {
//        addAll(Arrays.asList(items));
//    }
//
//    public void clear() {
//        items.clear();
//        notifyDataSetChanged();
//    }
//
//    public void remove(SelectCountryBean object) {
//        items.remove(object);
//        notifyDataSetChanged();
//    }

    public SelectCountryBean getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
