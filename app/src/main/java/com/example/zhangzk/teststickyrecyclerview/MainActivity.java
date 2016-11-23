package com.example.zhangzk.teststickyrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zhangzk.teststickyrecyclerview.adapter.SelectCountryAdapter;
import com.example.zhangzk.teststickyrecyclerview.adapter.expandRecyclerviewadapter.StickyRecyclerHeadersDecoration;
import com.example.zhangzk.teststickyrecyclerview.bean.SelectCountryBean;
import com.example.zhangzk.teststickyrecyclerview.pinyin.CharacterParser;
import com.example.zhangzk.teststickyrecyclerview.pinyin.PinyinComparator;
import com.example.zhangzk.teststickyrecyclerview.view.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView mCountryList;
    private SideBar mSideBar;

    private SelectCountryAdapter mCountryListAdapter;
    private CharacterParser mCharacterParser;
    private PinyinComparator mPinyinComparator;

    private List<SelectCountryBean> mCountryInfos = new ArrayList<SelectCountryBean>();  //选择country集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCountryList = (RecyclerView) findViewById(R.id.view_select_country_pop_list);
        mSideBar = (SideBar) findViewById(R.id.view_select_country_pop_sidebar);

        mCharacterParser = CharacterParser.getInstance();
        mPinyinComparator = new PinyinComparator();

        initCountryData();

        mCountryListAdapter = new SelectCountryAdapter(this, mCountryInfos);


        //索引点击监听
        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                int position = mCountryListAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mCountryList.getLayoutManager().scrollToPosition(position);
                }

            }
        });

        //配置recyclerview
        int orientation = LinearLayoutManager.VERTICAL;
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, orientation, false);
        mCountryList.setLayoutManager(layoutManager);

        mCountryList.setAdapter(mCountryListAdapter);

        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(mCountryListAdapter);
        mCountryList.addItemDecoration(headersDecor);
        mCountryListAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });

        //国家list item的点击
        mCountryListAdapter.setOnSelectCountryItemListener(new SelectCountryAdapter.onSelectCountryItemListener() {
            @Override
            public void onItemClick(SelectCountryBean bean) {
                for (SelectCountryBean info : mCountryInfos) {
                    if (bean.getId() == info.getId()) {
                        info.setSelect(true);
                    }else {
                        info.setSelect(false);
                    }
                }
                mCountryListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initCountryData() {
        for (int i = 0; i < 20 ; i++) {
            SelectCountryBean bean = new SelectCountryBean();
            bean.setId(i);
            bean.setSelect(false);
//            String pinyin;
            switch (i) {
                case 0:
                    bean.setCountryName("a");
                    break;
                case 1:
                    bean.setCountryName("阿");
                    break;
                case 2:
                    bean.setCountryName("bc");
                    break;
                case 3:
                    bean.setCountryName("Bosico");
                    break;
                case 4:
                    bean.setCountryName("aksk");
                    break;
                default:
                    bean.setCountryName("edison");
                    break;
            }

            String pinyin = mCharacterParser.getSelling(bean.getCountryName());//汉字转成拼音
            String sortString = pinyin.substring(0, 1).toUpperCase();

            //设置首字母
            if (sortString.matches("[A-Z]")) {
                bean.setSortLetters(sortString.toUpperCase());
            } else {
                bean.setSortLetters("#");
            }
            mCountryInfos.add(bean);
        }
        Collections.sort(mCountryInfos, mPinyinComparator);//排序
    }
}
