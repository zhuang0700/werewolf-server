package com.telan.werewolf.utils.conventor;

import org.springframework.data.redis.connection.convert.ListConverter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiwenliang on 17/5/31.
 */
public class ListConvertor{

    public static <T> List convertList(List<T> list, AbstractConvertor convertor) {
        if(CollectionUtils.isEmpty(list)) {
            return null;
        }
        List newList = new ArrayList();
        for(T t : list) {
            newList.add(convertor.convert(t));
        }
        return newList;
    }

    public static <S, T> List<T> convertList(List<S> list, ListConverter<S,T> listConverter) {
        return listConverter.convert(list);
    }
}
