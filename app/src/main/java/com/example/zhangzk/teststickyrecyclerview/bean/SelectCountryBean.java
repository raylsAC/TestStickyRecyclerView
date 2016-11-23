package com.example.zhangzk.teststickyrecyclerview.bean;

/**
 * Created by zhangzk on 2016/8/12.
 */
public class SelectCountryBean {

    private int id;
    private String countryName;
    private String sortLetters; //拼音首字母

    private boolean isSelect;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
