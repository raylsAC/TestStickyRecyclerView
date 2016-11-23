
package com.example.zhangzk.teststickyrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhangzk.teststickyrecyclerview.R;
import com.example.zhangzk.teststickyrecyclerview.adapter.expandRecyclerviewadapter.StickyRecyclerHeadersAdapter;
import com.example.zhangzk.teststickyrecyclerview.bean.SelectCountryBean;

import java.util.List;


/**
 * Created by zhangzk on 2016/8/12.
 */
public class SelectCountryAdapter extends CountryBaseAdapter<SelectCountryAdapter.SelectViewHolder>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    private List<SelectCountryBean> mLists;
    private Context mContext;
    private onSelectCountryItemListener mCallBack;

    public SelectCountryAdapter(Context ct, List<SelectCountryBean> mLists) {
        this.mLists = mLists;
        this.mContext = ct;
        this.addAll(mLists);
    }

    @Override
    public SelectCountryAdapter.SelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select_country, parent, false);
        return new SelectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SelectCountryAdapter.SelectViewHolder holder, final int position) {
        holder.mName.setText(getItem(position).getCountryName());

        if(getItem(position).isSelect()){
            holder.mName.setTextColor(mContext.getResources().getColor(R.color.blue_text));
            holder.mName.setBackgroundColor(mContext.getResources().getColor(R.color.gray_f0));
        }else{
            holder.mName.setTextColor(mContext.getResources().getColor(R.color.black_transparent_33));
            holder.mName.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        holder.mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallBack != null) {
                    mCallBack.onItemClick(getItem(position));
                }
            }
        });

    }

    @Override
    public long getHeaderId(int position) {

        return getItem(position).getSortLetters().charAt(0);

    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select_country_header, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        String showValue = String.valueOf(getItem(position).getSortLetters().charAt(0));
        textView.setText(showValue);
    }


    public int getPositionForSection(char section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = mLists.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;

    }

    public class SelectViewHolder extends RecyclerView.ViewHolder {

        public TextView mName;

        public SelectViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.item_select_country_name);

        }
    }

    public interface onSelectCountryItemListener{
        void onItemClick(SelectCountryBean bean);
    }

    public void setOnSelectCountryItemListener(onSelectCountryItemListener listener){
        this.mCallBack = listener;
    }
}
