package com.telan.weixincenter.event.parser;

import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.apache.commons.lang3.StringUtils;

import java.io.Writer;
import java.util.regex.Pattern;

public class WeixinXppDriver extends XppDriver {
	@Override
	public HierarchicalStreamWriter createWriter(Writer out) {
		return new PrettyPrintWriter(out) {
		//	boolean cdata = false;
//			Class<?> targetClass = null;
//			Class<?> preClass = null;
//			Field currentField = null;

			@Override
			public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
				super.startNode(name, clazz);
				
				// 业务处理，对于用XStreamCDATA标记的Field，需要加上CDATA标签
//				String nodeName = name;
//				XStreamAlias xStreamAlias = currentField.getAnnotation(XStreamAlias.class);
//				if(xStreamAlias != null){
//					nodeName = xStreamAlias.value();
//				}
//				if (!name.equals("xml")) {
//					// 当前需要处理的节点的class对象
//					currentField = getCurrentField(targetClass, name,preClass);
//					preClass = currentField.getType();
//					cdata = existsCDATA(currentField, name);
//				} else {
//					targetClass = clazz;
//					preClass = clazz;
//				}
			}

			@Override
			protected void writeText(QuickWriter writer, String text) {
				if (isNeedCDATA(text)) {
					writer.write(createDATA(text));
				} else {
					writer.write(text);
				}
				
			//	writer.write(createDATA(text));
			}
		};
	}

	// private static boolean needCDATA(Class<?> targetClass, String fieldAlias,
	// Class<?> currentClass) {
	// boolean cdata = false;
	// // first, scan self
	// cdata = existsCDATA(targetClass, fieldAlias);
	// if (cdata)
	// return cdata;
	// // if cdata is false, scan supperClass until java.lang.Object
	// Class<?> superClass = targetClass.getSuperclass();
	// while (!superClass.equals(Object.class)) {
	// cdata = existsCDATA(superClass, fieldAlias);
	// if (cdata)
	// return cdata;
	// superClass = superClass.getClass().getSuperclass();
	// }
	//
	// if (currentClass != null) {
	// return existsCDATA(currentClass, fieldAlias);
	// }
	//
	// return false;
	// }
	//
	// private static boolean existsCDATA(Field currentField, String fieldAlias)
	// {
	// XStreamAlias xStreamAlias =
	// currentField.getAnnotation(XStreamAlias.class);
	// if (xStreamAlias != null && !StringUtils.isEmpty(xStreamAlias.value())) {
	// return true;
	// }
	//
	// return false;
	// }
	//
	// private static boolean existsCDATA(Class<?> clazz, String fieldAlias) {
	// // scan fields
	// Field[] fields = clazz.getDeclaredFields();
	// for (Field field : fields) {
	// // 1. exists XStreamCDATA
	// if (field.getAnnotation(XStreamCDATA.class) != null) {
	// XStreamAlias xStreamAlias = field.getAnnotation(XStreamAlias.class);
	// // 2. exists XStreamAlias
	// if (null != xStreamAlias) {
	// if (fieldAlias.equals(xStreamAlias.value()))// matched
	// return true;
	// } else {// not exists XStreamAlias
	// if (fieldAlias.equals(field.getName()))
	// return true;
	// }
	// }
	// }
	// return false;
	// }

	private static String createDATA(String text) {

		return "<![CDATA[" + text + "]]>";
	}

	// private static Field getCurrentField(Class<?> clazz, String
	// fieldAlias,Class<?> preClazz) {
	// // scan fields
	// Field[] fields = clazz.getDeclaredFields();
	// Field targetField = null;
	// for (Field field : fields) {
	// String fieldName = field.getName();
	// if (field.getAnnotation(XStreamAlias.class) != null) {
	// XStreamAlias xStreamAlias = field.getAnnotation(XStreamAlias.class);
	// if (null != xStreamAlias && fieldAlias.equals(xStreamAlias.value())) {
	// fieldName = xStreamAlias.value();
	// }
	// }
	//
	// if (fieldName.equals(fieldAlias)) {
	// targetField = field;
	// break;
	// }
	// }
	//
	// Class<?> superClass = clazz.getSuperclass();
	// while (targetField == null && !superClass.equals(Object.class)) {
	// targetField = getCurrentField(superClass, fieldAlias,preClazz);
	// superClass = superClass.getClass().getSuperclass();
	// }
	//
	// if(targetField == null && preClazz != null){
	// Field[] preClazzFields = preClazz.getDeclaredFields();
	// for (Field field : preClazzFields) {
	// String fieldName = field.getName();
	// if (field.getAnnotation(XStreamAlias.class) != null) {
	// XStreamAlias xStreamAlias = field.getAnnotation(XStreamAlias.class);
	// if (null != xStreamAlias && fieldAlias.equals(xStreamAlias.value())) {
	// fieldName = xStreamAlias.value();
	// }
	// }
	//
	// if (fieldName.equals(fieldAlias)) {
	// targetField = field;
	// break;
	// }
	// }
	// }
	//
	// return targetField;
	// }

	// private static boolean isBasicType(Class<?> clazz){
	//// if("java.lang.String".equals(clazz.getName()) || "java.lang.Integer"){
	////
	//// }
	// }

	private static boolean isNeedCDATA(String text) {
		boolean result = false;
		if (!StringUtils.isEmpty(text)) {
			// 浮点型判断
			Pattern patternInt = Pattern.compile("[0-9]*(\\.?)[0-9]*");
			// 整型判断
			Pattern patternFloat = Pattern.compile("[0-9]+");
			// 如果是整数或浮点数 就不要加[CDATA[]了
			if (patternInt.matcher(text).matches() || patternFloat.matcher(text).matches()) {
				result = false;
			} else {
				result = true;
			}
		}

		return result;
	}
}
