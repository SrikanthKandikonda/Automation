package io.utils.fc2.o;

import java.util.Random;

public class RandomData {

	public String randomData(String randomData) {
		Random random = new Random();
		return randomData + " " + random.nextInt(10000);
	}

	public String randomStringData(int min, int max) {
		Random random = new Random();
		int randomTwoDigit = random.nextInt((max - min) + 1) + min;
		String stringRandomTwoDigit = "" + randomTwoDigit;
		return stringRandomTwoDigit;
	}

	public int randomIntegerData(int min, int max) {
		Random random = new Random();
		int randomTwoDigit = random.nextInt((max - min) + 1) + min;
		return randomTwoDigit;
	}

	public String randomTenDigitData() {
		long randomTenDigit = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String stringRandomTenDigit = "" + randomTenDigit;
		return stringRandomTenDigit;
	}

	public String randomEmail(String email) {
		Random random = new Random();
		return email + random.nextInt(10000) + "@email.com";
	}

	public String randomWeb() {
		Random random = new Random();
		return "www.Web" + random.nextInt(10000) + ".com";
	}

	public String randomTenDigitNumberStartsWith(long number) {
		String value = number + "000000000L";
		number = Long.parseLong(value);
		long randomTenDigit = (long) (Math.random() * 100000 + number);
		String stringRandomTenDigit = "" + randomTenDigit;
		return stringRandomTenDigit;
	}

}
