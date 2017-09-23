package com.wustrive.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamTest {
    /**
     * 过滤出大于等于50的数
     */
    public static void fun1(){
        int[] a = {1, 20, 63, 39, 40, 32, 89};
        Arrays.stream(a).filter(x -> (x >= 50)).forEach(System.out::println);
    }
    
    /**
     * 判断是否所有数字都大于50
     * @return
     */
    public static boolean fun2(){
        int[] a = {1, 20, 63, 58, 185, 60, 59, 20};
        return Arrays.stream(a).allMatch(p -> p > 50);
    }
    
    /**
     * 输出下列两个集合中的存在的相同字符串
     */
    public static void fun3(){
        String[] s1 = {"qwer", "asdf", "zxcv", "fgh", "jum", "rfv"};
        String[] s2 = {"edc", "zse", "asdf", "zxcv", "yhnj"};
        Arrays.stream(s1).forEach(x -> {
            Arrays.stream(s2).filter(y -> y.equals(x)).forEach(System.out::println);
        });
    }
    
    /**
     * 将下列集合中的所有数字乘以10,并得到新集合
     */
    private static void fun4() {
        Integer[] nums = {1, 20, 63, 58, 185, 60, 59, 20};
        Integer[] num = Arrays.stream(nums).map(n -> n * 10).toArray(Integer[]::new);
        Arrays.stream(num).forEach(System.out::println);
    }
    
    
    /**
     * 取出下列集合中的第3到7个值，加上5，得到新的集合[1, 20, 63, 58, 185, 60, 59, 20]
     */
    private static void fun5() {
        Integer[] nums = {1, 20, 63, 58, 185, 60, 59, 20};
        Integer[] num = Arrays.stream(nums).skip(2).limit(5).map(x -> x + 5).toArray(Integer[]::new);
        Arrays.stream(num).forEach(System.out::println);
    }

    
    /**
     * 输出下列集合的最小值[1, 20, 63, 58, 185, 60, 59, 20]
     */
    private static void fun6() {
        int[] nums = {1, 20, 63, 58, 185, 60, 59, 20};
        System.out.println(Arrays.stream(nums).min().getAsInt());
    }
    
    /**
     * 对下列集合进行从小到大排序[1, 20, 63, 58, 185, 60, 59, 20]
     */
    private static void fun7() {
        int[] nums = {1, 20, 63, 58, 185, 60, 59, 20};
        Arrays.stream(nums).sorted().forEach(System.out::println);
    }
    
    /**
     * 集合合并：["qwe","dfg","sss"],["qwe","dfg","sss"],["qwe","dfg","sss"]
     * 合并为["qwe","dfg","sss","qwe","dfg","sss","qwe","dfg","sss"]
     */
    private static void fun8() {
        String[] str1 = {"qwe", "dfg", "sss"};
        String[] str2 = {"rty", "ghkj", "fghh"};
        String[] str3 = {"gtb", "ssd", "dg"};
        ArrayList<String> results = new ArrayList<>();
        Stream.of(str1, str2, str3).forEach(p -> {
            Stream.of(p).forEach(x -> results.add(x));
        });
        results.stream().forEach(System.out::println);
    }
    
    /**
     * 取出下列集合中大于50的，并得到新的集合[1, 20, 63, 58, 185, 60, 59, 20]
     */
    private static void fun9() {
        Integer[] nums = {1, 20, 63, 58, 185, 60, 59, 20};
        Integer[] num = Arrays.stream(nums).filter(p -> p > 50).toArray(Integer[]::new);
        Arrays.stream(num).forEach(System.out::println);
    }

    /**
     * 将下列名字转换为大写，再排序输出（"Fred Edwards", "Anna Cox", "Deborah Patterson", "Ruth Torres", "Shawn Powell"）
     */
    private static void fun10() {
        String[] str = {"Fred Edwards", "Anna Cox", "Deborah Patterson", "Ruth Torres", "Shawn Powell"};
        Arrays.stream(str).map(p -> p.toUpperCase()).sorted().forEach(System.out::println);
    }
    
    public static void main(String[] args) {
        fun1();  //如何控制输出格式
        System.out.println(fun2());
        System.out.println("------fun3-----");
        fun3();
        System.out.println("------fun4-----");
        fun4();
        System.out.println("------fun5-----");
        fun5();
        System.out.println("------fun6-----");
        fun6();
        System.out.println("------fun7-----");
        fun7();
        System.out.println("------fun8-----");
        fun8();
        System.out.println("------fun9-----");
        fun9();
        System.out.println("------fun10-----");
        fun10();
    }
}
