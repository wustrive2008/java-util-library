package com.wustrive.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ListUtils extends org.apache.commons.collections.ListUtils {

	/**
	 * 去除Integer集合中重复的值
	 * 
	 * @param ids
	 * @return
	 */
	public static Collection<Integer> removeRepeat(Collection<Integer> ids) {
		if (null != ids && !ids.isEmpty()) {
			Set<Integer> set = new HashSet<Integer>();
			set.addAll(ids);

			Collection<Integer> ins = new ArrayList<Integer>();
			ins.addAll(set);

			return ins;
		} else {
			return null;
		}
	}

	/**
	 * list去重复
	 * 
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> List<T> removeRepeat(List<T> list) {
		if (null == list) {
			return null;
		}
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < list.size(); i++) {
			if (Collections.frequency(result, list.get(i)) < 1) {
				result.add(list.get(i));
			}
		}
		return result;
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @param objects
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> objects) {
		if (null != objects && !objects.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
