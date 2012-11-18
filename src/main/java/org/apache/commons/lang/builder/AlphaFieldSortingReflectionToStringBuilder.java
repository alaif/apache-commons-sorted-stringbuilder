package org.apache.commons.lang.builder;

import org.apache.commons.lang.builder.ToStringStyle;

import java.lang.reflect.Field;
import java.util.Comparator;

public class AlphaFieldSortingReflectionToStringBuilder extends SortingReflectionToStringBuilder {

	public AlphaFieldSortingReflectionToStringBuilder(Object object) {
		super(object);
		initAlphaComparator();
	}

	public AlphaFieldSortingReflectionToStringBuilder(Object object, ToStringStyle style) {
		super(object, style);
		initAlphaComparator();
	}

	public AlphaFieldSortingReflectionToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer) {
		super(object, style, buffer);
		initAlphaComparator();
	}

	@Deprecated
	public AlphaFieldSortingReflectionToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer, Class reflectUpToClass, boolean outputTransients) {
		super(object, style, buffer, reflectUpToClass, outputTransients);
		initAlphaComparator();
	}

	public AlphaFieldSortingReflectionToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer, Class reflectUpToClass, boolean outputTransients, boolean outputStatics) {
		super(object, style, buffer, reflectUpToClass, outputTransients, outputStatics);
		initAlphaComparator();
	}

	public static String toString(Object object, ToStringStyle style, boolean outputTransients, boolean outputStatics,
								  Class reflectUpToClass) {
		return new AlphaFieldSortingReflectionToStringBuilder(
				object,
				style,
				null,
				reflectUpToClass,
				outputTransients,
				outputStatics
		).toString();
	}

	public static String toString(Object object) {
		return toString(object, null, false, false, null);
	}

	public static String toString(Object object, ToStringStyle style) {
		return toString(object, style, false, false, null);
	}

	public static String toString(Object object, ToStringStyle style, boolean outputTransients) {
		return toString(object, style, outputTransients, false, null);
	}

	public static String toString(Object object, ToStringStyle style, boolean outputTransients, boolean outputStatics) {
		return toString(object, style, outputTransients, outputStatics, null);
	}

	public static String toString(Object object, ToStringStyle style,
								  boolean outputTransients, Class reflectUpToClass)
	{
		return new AlphaFieldSortingReflectionToStringBuilder(object, style, null, reflectUpToClass, outputTransients).toString();
	}

	private static final class DefaultComparator implements Comparator<Field> {

		@Override
		public int compare(Field a, Field b) {
			return a.getName().compareTo(b.getName());
		}
	}

	private void initAlphaComparator() {
		this.setComparator(new DefaultComparator());
	}
}
