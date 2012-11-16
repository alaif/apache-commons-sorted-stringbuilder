package org.apache.commons.lang.builder;

import org.apache.commons.lang.builder.AlphaFieldSortingReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ToStringBuilderAlphaSorted extends ToStringBuilder {
	public ToStringBuilderAlphaSorted(Object object) {
		super(object);
	}

	public ToStringBuilderAlphaSorted(Object object, ToStringStyle style) {
		super(object, style);
	}

	public ToStringBuilderAlphaSorted(Object object, ToStringStyle style, StringBuffer buffer) {
		super(object, style, buffer);
	}

	public static String reflectionToString(Object object) {
		return AlphaFieldSortingReflectionToStringBuilder.toString(object);
	}

	public static String reflectionToString(Object object, ToStringStyle style) {
		return AlphaFieldSortingReflectionToStringBuilder.toString(object, style);
	}

	public static String reflectionToString(Object object, ToStringStyle style, boolean outputTransients) {
		return AlphaFieldSortingReflectionToStringBuilder.toString(object, style, outputTransients);
	}

	public static String reflectionToString(
			Object object,
			ToStringStyle style,
			boolean outputTransients,
			Class reflectUpToClass) {
		return AlphaFieldSortingReflectionToStringBuilder.toString(object, style, outputTransients, false, reflectUpToClass);
	}
}
