package com.wustrive.util.badwordfilter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BadWordInit {
	public static final String BADWORD_FILENAME = "words.properties"; 
	private static  HashMap sensitiveWordMap;
	private static final  String ENCODING = "utf8";    //字符编码

	private	BadWordInit(){
		
	}
	
	private static class HolderClass {
		 private final static BadWordInit badWordInit = new BadWordInit(); 
	}
	
	public static BadWordInit getInstance() {  
        return HolderClass.badWordInit;  
    }  


	public Map initKeyWord(){
		
		try {
			//读取敏感词库
			Set<String> keyWordSet = readSensitiveWordFile();
			//将敏感词库加入到HashMap中
			addSensitiveWordToHashMap(keyWordSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sensitiveWordMap;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
		String key = null;  
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		//迭代keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while(iterator.hasNext()){
			key = iterator.next();    //关键字
			nowMap = sensitiveWordMap;
			for(int i = 0 ; i < key.length() ; i++){
				char keyChar = key.charAt(i);       //转换成char型
				Object wordMap = nowMap.get(keyChar);       //获取
				
				if(wordMap != null){        //如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				}
				else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String,String>();
					newWorMap.put("isEnd", "0");     //不是最后一个
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}
				
				if(i == key.length() - 1){
					nowMap.put("isEnd", "1");    //最后一个
				}
			}
		}
	}
	
	private Set<String> readSensitiveWordFile() throws Exception{
		Set<String> set = null;
		
		BufferedReader read = new BufferedReader(new InputStreamReader(BadWordInit.class.getClassLoader().getResourceAsStream(BadWordInit.BADWORD_FILENAME),ENCODING));
		try {
			set = new HashSet<String>();
			BufferedReader bufferedReader = new BufferedReader(read);
			String txt = null;
			while((txt = bufferedReader.readLine()) != null){    //读取文件，将文件内容放入到set中
				set.add(txt);
		    }
		
		} catch (Exception e) {
			throw e;
		}finally{
			read.close();     //关闭文件流
		}
		return set;
	}
}
