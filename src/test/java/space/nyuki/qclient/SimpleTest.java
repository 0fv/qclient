package space.nyuki.qclient;

import org.junit.jupiter.api.Test;
import space.nyuki.qclient.pojo.answer.AnswerCell;
import space.nyuki.qclient.pojo.answer.Choice;


public class SimpleTest {
	@Test
	public void test1() {
		AnswerCell a = new Choice();
		AnswerCell b = new Choice();
		System.out.println(a.equals(b));
	}
}
