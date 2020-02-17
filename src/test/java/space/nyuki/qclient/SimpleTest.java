package space.nyuki.qclient;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import space.nyuki.qclient.pojo.answer.AnswerCell;
import space.nyuki.qclient.pojo.answer.Choice;
import space.nyuki.qclient.utils.MD5Util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SimpleTest {
	@Test
	public void test1() {
		AnswerCell a = new Choice();
		AnswerCell b = new Choice();
		System.out.println(a.equals(b));
	}

	@SneakyThrows
	@Test
	public void test2() {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("HMACSHA1");
		keyGenerator.init(160);
		SecretKey secretKey = keyGenerator.generateKey();
		System.out.println(new String(secretKey.getEncoded()));
		System.out.println(secretKey.getEncoded().length);
		for (byte b : secretKey.getEncoded()) {
			System.out.println(b);
		}
		System.out.println("qwerqwerqwerqwerqwer".getBytes().length);
	}

	@Test
	public void test3() {
		String ning123 = MD5Util.getHalfMD5("ning123");
		System.out.println(ning123.length());
	}

	@Test
	public void test4() {
		List<Integer> f = new ArrayList<>();
		Integer[] x = {1, 2, 2};
		boolean b = Arrays.asList(x).containsAll(f);
		System.out.println(b);
	}
}
