package com.jianzh5.blog.map;

import com.google.common.collect.Maps;
import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author jam
 * @date 2021/7/5 11:25 上午
 */
public class ConvertListToMapService {

    /**
     * convertListBeforeJava8
     * @param list
     * @return
     */
    public Map<Integer,Aniaml> convertListBeforeJava8(List<Aniaml> list){
        Map<Integer,Aniaml> map = new HashMap<>();
        for (Aniaml aniaml : list){
            map.put(aniaml.getId(),aniaml);
        }

        return map;
    }

    /**
     * convertListAfterJava8
     * @param list
     * @return
     */
    public Map<Integer,Aniaml> convertListAfterJava8(List<Aniaml> list){
        Map<Integer,Aniaml> map = list.stream()
                .collect(Collectors.toMap(Aniaml::getId, Function.identity()));
        return  map;
    }


    /**
     * convertListWithGuava
     * @param list
     * @return
     */
    public Map<Integer,Aniaml> convertListWithGuava(List<Aniaml> list){
        Map<Integer,Aniaml> map = Maps.uniqueIndex(list,Aniaml::getId);
        return  map;
    }


    /**
     * convertListApacheCommons4
     * @param list
     * @return
     */
    public Map<Integer,Aniaml> convertListApacheCommons4(List<Aniaml> list){
        Map<Integer,Aniaml> map = new HashMap<>();
        MapUtils.populateMap(map,list,Aniaml::getId);
        return  map;
    }

}
