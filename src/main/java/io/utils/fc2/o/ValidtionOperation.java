package io.utils.fc2.o;

import org.testng.Assert;

public class ValidtionOperation {
	public static boolean verifyMsg(String actRes, String expRes) {
		try {
			Assert.assertEquals(actRes, expRes);

			return true;
		} catch (Error rv) {
			return false;

		}

	}

}
