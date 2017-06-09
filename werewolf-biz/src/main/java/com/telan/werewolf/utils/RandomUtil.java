package com.telan.werewolf.utils;

import com.telan.werewolf.game.domain.PlayerAction;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/19.
 */
public class RandomUtil {
    public static List<Integer> generateSeq(int start, int end, int num) {
        List<Integer> list = new ArrayList<>();
        for(int i = start;i<=end;i++) {
            list.add(i);
            num--;
            if(num <= 0) {
                break;
            }
        }
        Collections.shuffle(list);
        return list;
    }
}
