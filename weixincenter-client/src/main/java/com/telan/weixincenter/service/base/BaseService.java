package com.telan.weixincenter.service.base;

import java.io.Serializable;
import java.util.List;


public interface BaseService<T> {

	public  int delete(Serializable id);

	public  Integer insert(T record);

	public  T selectById(Serializable id);

	public  int update(T record);
    
	public  List<T> getAll(T record);
	
}
