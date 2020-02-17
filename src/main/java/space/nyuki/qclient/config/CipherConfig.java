package space.nyuki.qclient.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import space.nyuki.qclient.utils.MD5Util;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class CipherConfig {
	@Value("${front.key}")
	private String key;
	@Value("${front.iv}")
	private String iv;

	@Bean
	@Qualifier("decrypt")
	public Cipher cipher1() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
		SecretKey secretKey = new SecretKeySpec(MD5Util.md5crypt(key).getBytes(), "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(MD5Util.getHalfMD5(iv).getBytes());
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
		return cipher;
	}
	@Bean
	@Qualifier("encrypt")
	public Cipher cipher2() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
		SecretKey secretKey = new SecretKeySpec(MD5Util.md5crypt(key).getBytes(), "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(MD5Util.getHalfMD5(iv).getBytes());
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
		return cipher;
	}

	@Bean(name = "taskExecutor")
	public ThreadPoolTaskExecutor getAsyncThreadPoolTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(20);
		taskExecutor.setMaxPoolSize(200);
		taskExecutor.setQueueCapacity(25);
		taskExecutor.setKeepAliveSeconds(200);
		taskExecutor.setThreadNamePrefix("cipher-");
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		taskExecutor.setAwaitTerminationSeconds(60);
		taskExecutor.initialize();
		return taskExecutor;
	}
}
