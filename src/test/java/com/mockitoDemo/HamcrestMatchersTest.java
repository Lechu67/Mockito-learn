package com.mockitoDemo;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HamcrestMatchersTest {

	@Test
	public void test() {
		List<Integer> scores = Arrays.asList(1,3,4,5,666);
		assertThat(scores, hasSize(5));
		assertThat(scores, hasItems(1,3));
		assertThat(scores, everyItem(lessThan(1000)));
		//String
		assertThat("", isEmptyString());
		assertThat(null, isEmptyOrNullString());
		//Array
		Integer[] array = {1,2,3};
		assertThat(array, arrayWithSize(3));
		assertThat(array, arrayContainingInAnyOrder(2,3,1));
	}

}
