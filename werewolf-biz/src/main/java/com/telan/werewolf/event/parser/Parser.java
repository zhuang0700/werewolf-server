package com.telan.werewolf.event.parser;

public interface Parser<T,U> {
	public T parseToBean(String content, ContentFormat format);
	
	public String parseFromBean(U bean, ContentFormat format);
}
