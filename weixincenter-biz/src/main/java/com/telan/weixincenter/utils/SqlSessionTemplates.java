package com.telan.weixincenter.utils;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

public class SqlSessionTemplates extends SqlSessionTemplate {

	public SqlSessionTemplates(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
		
	}
	@Override
	public void close() {
//		System.out.println( "0000000000" );
	}
}
