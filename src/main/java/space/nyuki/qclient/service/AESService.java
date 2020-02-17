package space.nyuki.qclient.service;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import space.nyuki.qclient.group.GroupView;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.util.concurrent.Future;

@Service
public class AESService<T> {
	@Autowired
	@Qualifier("decrypt")
	private Cipher decryptCipher;
	@Autowired
	@Qualifier("encrypt")
	private Cipher encryptCipher;
	@Autowired
	private ObjectMapper objectMapper;

	@SneakyThrows
	@Async("taskExecutor")
	public Future<String> encrypt(T t,Class<?> view) {
		byte[] bytes1 = objectMapper.writerWithView(view).writeValueAsBytes(t);
		byte[] bytes = encryptCipher.doFinal(bytes1);
		return new AsyncResult<>(DatatypeConverter.printHexBinary(bytes));
	}

	@SneakyThrows
	@Async("taskExecutor")
	public Future<T> decrypt(String s, Class<? extends T> type) {
		byte[] bytes = DatatypeConverter.parseHexBinary(s);
		byte[] bytes1 = decryptCipher.doFinal(bytes);
		T t = objectMapper.readValue(bytes1, type);
		return new AsyncResult<>(t);
	}
}
