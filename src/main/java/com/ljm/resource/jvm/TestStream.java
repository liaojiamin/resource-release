package com.ljm.resource.jvm;

import com.ljm.resource.jvm.vo.FmResult;
import com.sun.org.apache.bcel.internal.generic.FMUL;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author liaojiamin
 * @Date:Created in 10:23 2021/6/23
 */
public class TestStream {

    public static void main(String[] args) {
        testStreamFlagMap();
    }

    /**
     * Stream 流操作
     *
     * 类似Rxjava中的流操作
     * */
    public static void streamBuildList(){
        //stream 快速成成list
        List<Integer> ofList = Stream.of(1,2,3,4,5).collect(Collectors.toList());
        //安指定规律生成流
        List<Integer> iteraterList = Stream.iterate(1, (x) -> x+2).limit(100).collect(Collectors.toList());
        //生成随机数流
        List<Double> generateList = Stream.generate(Math::random).limit(199).collect(Collectors.toList());
    }

    /**
     * Stream 流中间操作
     * distinct 更具hashCode，equals 反复去重
     * filter过滤
     * skip 跳过n个元素
     * limit获取n个元素
     * */
    public static void streamTest(){
        //生成随机数流
        List<Double> generateList = Stream.generate(Math::random).limit(199).collect(Collectors.toList());
        List<Double> myDubleLIst = generateList.stream().filter(d -> d > 0.5)
                .distinct()
                .skip(4)
                .limit(20).collect(Collectors.toList());
        myDubleLIst.forEach(System.out::println);
    }

    /**
     * Stream map表达式映射
     * */
    public static void testStreamMap(List<FmResult> list){
        //传递一个函数给map，他会映射到每一个元素上
        Set<String> resultlist = list.stream().map(result -> result.getPlatform().replace(",", "")).collect(Collectors.toSet());
        //复杂表达式处理
        List<FmResult> fmResults = list.stream().map( result -> {
            result.setPlatform(result.getPlatform().substring(0,1));
            return result;
        }).collect(Collectors.toList());
    }

    /**
     * Stream peek消费元素
     * peek 和map操作一样，当map接受的是一个Consumer 表达式没有返回值，map接受的是Function表达式有返回值
     * */
    public static void testStreamPeek(){
        FmResult result1 = new FmResult(1,"aa");
        FmResult result2 = new FmResult(2,"bb");
        FmResult result3 = new FmResult(3,"aa");
        FmResult result4 = new FmResult(4,"dd");
        List<FmResult> fmResults = Arrays.asList(result1,result2, result3, result4);
        List<FmResult> newResults = fmResults.stream().peek(result -> result.setFmName("123123")).collect(Collectors.toList());
        for (FmResult newResult: newResults) {
            System.out.println(newResult.getSortNo() + ": "+ newResult.getPlatform() + ": "+ newResult.getFmName());
        }
    }

    /**
     * Stream flatMapToInt,flatMapToDouble,flatMapToLong
     * 一对多，将一个元素拆分成多个元素，流元素变多，map是一对一
     * */
    public static void testStreamFlagMap(){
        FmResult result1 = new FmResult(1,"aa");
        FmResult result2 = new FmResult(2,"bb");
        FmResult result3 = new FmResult(3,"aa");
        FmResult result4 = new FmResult(4,"dd");
        List<FmResult> fmResults = Arrays.asList(result1,result2, result3, result4);
        IntStream platformStream = fmResults.stream().map(FmResult::getPlatform).flatMapToInt(String::chars);
        int[] ints = platformStream.toArray();
        System.out.println(ints.length +"  :"+ ints[0]);

    }

    /**
     * Stream 流的终止
     *   allMatch：接收一个 Predicate 函数，当流中每个元素都符合该断言时才返回true，否则返回false
     *   noneMatch：接收一个 Predicate 函数，当流中每个元素都不符合该断言时才返回true，否则返回false
     *   anyMatch：接收一个 Predicate 函数，只要流中有一个元素满足该断言则返回true，否则返回false
     *   findFirst：返回流中第一个元素
     *   findAny：返回流中的任意元素
     *   count：返回流中元素的总个数
     *   max：返回流中元素最大值
     *   min：返回流中元素最小值
     * */
    public static void testStreamTermal(){
        List<String>  strList = Arrays.asList("a2","a1","b4","c1","d5","a6");
        boolean allMatch = strList.stream().allMatch(str -> str.equals("a1"));
        boolean noneMatch = strList.stream().noneMatch(str -> str.equals("x"));
        boolean anyMatch = strList.stream().anyMatch(str -> str.equals("a1"));

        String findFirst = strList.stream().findFirst().get();
        String findAny = strList.stream().findAny().get();

        Long count = strList.stream().count();
        String max = strList.stream().max(String::compareTo).get();
        String min = strList.stream().min(String::compareTo).get();

    }

    /**
     * Stream 排序处理
     * */
    public static void testStreamSort(){
        //String 自己已经实现了Compareable接口
        List<String>  strList = Arrays.asList("a2","a1","b4","c1","d5","a6");
        strList.stream().sorted().forEach(System.out::print);
        System.out.println();
        //自实现Compareable接口
        FmResult result1 = new FmResult(1,"aa");
        FmResult result2 = new FmResult(2,"bb");
        FmResult result3 = new FmResult(3,"aa");
        FmResult result4 = new FmResult(4,"dd");
        List<FmResult> fmResults = Arrays.asList(result1,result2, result3, result4);
        List<FmResult> sortResults = fmResults.stream().sorted(
                (k1,k2)->{
                    if(k1.getPlatform().equals(k2.getPlatform())){
                        return k1.getSortNo()-k2.getSortNo();
                    }else {
                        return k1.getPlatform().compareTo(k2.getPlatform());
                    }
                }
        ).collect(Collectors.toList());
        for (FmResult sortResult : sortResults) {
            System.out.println(sortResult.getSortNo() + ": "+ sortResult.getPlatform());
        }
    }


    /**
     * Stream 表达式list -->map
     * (key1, key2) -> key1 作用在于如果存在相同id的数据，取第一个
     * */
    public static Map<Long, FmResult> streamListTOMap(List<FmResult> list){
        return list.stream().collect(Collectors.toMap(FmResult::getId, Function.identity(), (key1, key2) -> key1));
    }

    /**
     * Stream 表达式 list --> map groupingBy分组
     * */
    public static void streamGroupingBy(List<FmResult> list){
        Map<String, List<FmResult>> fmMap = list.stream().collect(Collectors.groupingBy(FmResult::getPlatform));
        //按排平台分组，并返回数量
        Map<String, Long> fmCountMap = list.stream().collect(Collectors.groupingBy(FmResult::getPlatform, Collectors.counting()));
    }

    /**
     * Stream partitioningBy分区
     * 按排序值 > 2 分组
     * */
    public static Map<Boolean, List<FmResult>> streamPartitioningBy(List<FmResult> list){
        return list.stream().collect(Collectors.partitioningBy(x -> x.getSortNo() > 2));
    }

    /**
     * Stream 连接 joining
     * */
    public  static void streamJoining(){
        FmResult result1 = new FmResult(1,"aa");
        FmResult result2 = new FmResult(2,"bb");
        FmResult result3 = new FmResult(3,"aa");
        FmResult result4 = new FmResult(4,"dd");
        List<FmResult> fmResults = Arrays.asList(result1,result2, result3, result4);
        String platform = fmResults.stream().map(result -> result.getPlatform()).collect(Collectors.joining(","));
        System.out.println(platform);
    }

    /**
     * Stream 规约 reducing
     * */
    public static void streamReducing(){
        FmResult result1 = new FmResult(1,"aa");
        FmResult result2 = new FmResult(2,"bb");
        FmResult result3 = new FmResult(3,"aa");
        FmResult result4 = new FmResult(4,"dd");
        List<FmResult> fmResults = Arrays.asList(result1,result2, result3, result4);
        Optional<Integer> sumSortNo = fmResults.stream().map(result ->result.getSortNo()).collect(Collectors.reducing(Integer::sum));
        System.out.println(sumSortNo.get());
    }

    /**
     * stream Collectors.averagingDouble()、Collectors.averagingInt()、Collectors.averagingLong() ： 计算平均数
     * */
    public static void streamAveraging(){
        FmResult result1 = new FmResult(1,"aa");
        FmResult result2 = new FmResult(2,"bb");
        FmResult result3 = new FmResult(3,"aa");
        FmResult result4 = new FmResult(4,"dd");
        List<FmResult> fmResults = Arrays.asList(result1,result2, result3, result4);
        Double avgSortNo = fmResults.stream().collect(Collectors.averagingInt(FmResult::getSortNo));
    }


}
