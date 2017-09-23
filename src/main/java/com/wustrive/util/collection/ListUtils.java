package com.wustrive.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @Title: ListUtils.java
 * @ClassName: com.wustrive.util.collection.ListUtils
 * @Description: TODO
 *
 * Copyright  2015-2017 维创盈通 - Powered By 研发中心 V1.0.0
 * @author wustrive
 * @date 2017年3月11日 上午9:57:11
 */
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
	
	/**
	 * List分页
	 * @param list
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public static <T> List<T> getSubByPage(List<T> list,int page,int pageSize){
		int total = list.size();
		int fromIndex = page * pageSize;
		int toIndex = (page + 1) * pageSize;
		if(fromIndex > total - 1){
			fromIndex = 0;
		}
		if(toIndex > total -1 ){
			toIndex = total;
		}
		
		return list.subList(fromIndex, toIndex);
	}
	
}
