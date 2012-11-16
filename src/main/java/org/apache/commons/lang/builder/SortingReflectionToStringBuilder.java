package org.apache.commons.lang.builder;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

public class SortingReflectionToStringBuilder extends ReflectionToStringBuilder {
	private Comparator comparator;

	public SortingReflectionToStringBuilder(Object object) {
		super(object);
	}

	public SortingReflectionToStringBuilder(Object object, ToStringStyle style) {
		super(object, style);
	}

	public SortingReflectionToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer) {
		super(object, style, buffer);
	}

	@Deprecated
	public SortingReflectionToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer, Class reflectUpToClass, boolean outputTransients) {
		super(object, style, buffer, reflectUpToClass, outputTransients);
	}

	public SortingReflectionToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer, Class reflectUpToClass, boolean outputTransients, boolean outputStatics) {
		super(object, style, buffer, reflectUpToClass, outputTransients, outputStatics);
	}

	public void setComparator(Comparator cmp){
		comparator = cmp;
	}

	//Body of the following method copied from the apache-commons implementation. Just few tweaks made.
	@Override
	protected void appendFieldsIn(Class clazz) {
		if (clazz.isArray()) {
			this.reflectionAppendArray(this.getObject());
			return;
		}
		Field[] fields = clazz.getDeclaredFields();
		if (comparator != null) {
			Arrays.sort(fields, comparator);
		}
		AccessibleObject.setAccessible(fields, true);
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			if (this.accept(field)) {
				try {
					// Warning: Field.get(Object) creates wrappers objects
					// for primitive types.
					Object fieldValue = this.getValue(field);
					this.append(fieldName, fieldValue);
				} catch (IllegalAccessException ex) {
					//this can't happen. Would get a Security exception
					// instead
					//throw a runtime exception in case the impossible
					// happens.
					throw new InternalError("Unexpected IllegalAccessException: " + ex.getMessage());
				}
			}
		}
	}


	/**
	 * <p>
	 * Gets the String built by this builder.
	 * </p>
	 *
	 * @return the built string
	 */
	public String toString() {
		if (this.getObject() == null) {
			return this.getStyle().getNullText();
		}
		Class clazz = this.getObject().getClass();
		this.appendFieldsIn(clazz);
		while (clazz.getSuperclass() != null && clazz != this.getUpToClass()) {
			clazz = clazz.getSuperclass();
			this.appendFieldsIn(clazz);
		}
		return super.toString();
	}
}
