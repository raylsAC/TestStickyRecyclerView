package com.example.zhangzk.teststickyrecyclerview.pinyin;


import com.example.zhangzk.teststickyrecyclerview.bean.SelectCountryBean;

import java.util.Comparator;

/**
 * Created by zhangzk on 2016/8/12.
 */
public class PinyinComparator implements Comparator<SelectCountryBean> {

	public int compare(SelectCountryBean o1, SelectCountryBean o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
