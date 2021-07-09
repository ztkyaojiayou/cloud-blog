package com.jianzh5.blog.map;

import com.google.common.collect.Maps;
import org.apache.commons.collections4.MapUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author jam
 * @date 2021/7/5 10:49 上午
 * 参考文章：baeldung.com/java-list-to-map
 */
public class ListToMapTest {
    List<Aniaml> aniamlList;

    @BeforeEach
    private void  init(){
        Aniaml aniaml0 = new Aniaml(0,"aniaml0");
        Aniaml aniaml1 = new Aniaml(1,"aniaml1");
        Aniaml aniaml2 = new Aniaml(2,"aniaml2");
        Aniaml aniaml3 = new Aniaml(3,"aniaml3");
        Aniaml aniaml4 = new Aniaml(3,"aniaml4");
        aniamlList = Lists.newArrayList(aniaml0,aniaml1,aniaml2,aniaml3,aniaml4);
    }

    @Test
    public void convertListBeforeJava8(){
        Map<Integer,Aniaml> map = new HashMap<>();
        for (Aniaml aniaml : aniamlList){
            map.put(aniaml.getId(),aniaml);
        }

        assertTrue(map.values().containsAll(aniamlList));
    }

    @Test
    //java.lang.IllegalStateException
    public void convertListAfterJava8(){
        Map<Integer,Aniaml> map = aniamlList.stream()
                .collect(Collectors.toMap(Aniaml::getId, Function.identity()));
        assertTrue(map.values().containsAll(aniamlList));
    }

    @Test
    //java.lang.IllegalArgumentException
    public void convertListWithGuava(){
        Map<Integer,Aniaml> map = Maps.uniqueIndex(aniamlList,Aniaml::getId);
        assertTrue(map.values().containsAll(aniamlList));
    }


    @Test
    public void convertListApacheCommons4(){
        Map<Integer,Aniaml> map = new HashMap<>();
        MapUtils.populateMap(map,aniamlList,Aniaml::getId);

        assertTrue(map.values().containsAll(aniamlList));
    }



}
