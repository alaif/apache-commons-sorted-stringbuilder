package org.apache.commons.lang.builder;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ToStringBuilderAlphaSortedTest {

	class TestFriendlyStyle extends ToStringStyle {

		private static final long serialVersionUID = 1L;

		TestFriendlyStyle() {
			super();
			setUseShortClassName(true);
			setUseIdentityHashCode(false);
		}

	}


	@Test
	public void simpleOne() {
		ToStringStyle style = new TestFriendlyStyle();
		MrBean bean = new MrBean();
		bean.gamma = "X";
		bean.alpha = "Z";
		bean.beta = "Y";
		ToStringBuilderAlphaSorted.reflectionToString(bean, style);
		assertEquals(
				ToStringBuilderAlphaSorted.reflectionToString(bean, style),
				"MrBean[alpha=Z,beta=Y,gamma=X]"
		);
	}
}
