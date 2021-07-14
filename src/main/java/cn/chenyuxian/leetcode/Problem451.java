package cn.chenyuxian.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Problem451 {

	public String frequencySort(String s) {
		char[] ch = s.toCharArray();
		Map<Character, Integer> sortedMap = new HashMap<>();
		int count = 1;
		for (int i = 0; i < s.length(); i++) {
			if (sortedMap.containsKey(ch[i])) {
				count = sortedMap.get(ch[i]);
				count++;
			}
			sortedMap.put(ch[i], count);
			count = 1;
		}
		List<Map.Entry<Character, Integer>> list = new ArrayList<>();
		
		return null;
	}
}
