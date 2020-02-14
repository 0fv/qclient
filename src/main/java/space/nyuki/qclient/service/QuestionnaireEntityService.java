package space.nyuki.qclient.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import space.nyuki.qclient.exception.AccessDeniedException;
import space.nyuki.qclient.exception.QuestionnaireAnsweredException;
import space.nyuki.qclient.exception.QuestionnaireNotFoundException;
import space.nyuki.qclient.exception.TypeErrorException;
import space.nyuki.qclient.pojo.Member;
import space.nyuki.qclient.pojo.QuestionnaireEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class QuestionnaireEntityService {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	@SneakyThrows
	public QuestionnaireEntity getDataById(String id) {
		String o = redisTemplate.opsForValue().get("entity:" + id);
		if (o != null) {
			return objectMapper.readValue(o.getBytes(), QuestionnaireEntity.class);
		} else {
			throw new QuestionnaireNotFoundException();
		}
	}


	public QuestionnaireEntity getDataById(String id, boolean noMember) {
		final QuestionnaireEntity data = getDataById(id);
		if (Objects.isNull(data)) {
			throw new QuestionnaireNotFoundException();
		}
		if (noMember && (data.getIsAnonymous() == 1)) {
			throw new AccessDeniedException();
		}
		return data;
	}

	public QuestionnaireEntity getDataById(String id, String mId) {
		answeredCheck(id, mId);
		QuestionnaireEntity data = getDataById(id, false);
		if (data.getIsAnonymous() == 1) {
			List<Member> members = data.getMembers();
			System.out.println(data);
			List<String> collect = members.stream()
					.map(Member::getId)
					.filter(s -> s.equals(mId)).collect(Collectors.toList());
			if (collect.isEmpty()) {
				throw new AccessDeniedException();
			}
		} else {
			throw new TypeErrorException();
		}
		return data;
	}

	public QuestionnaireEntity getDataById(String id, boolean noMember, String fingerPrint) {
		answeredCheck(id, fingerPrint);
		return getDataById(id, noMember);
	}

	public void answeredCheck(String id, String mId) {
		String resultId = "result:" + id;
		List<String> range = redisTemplate.opsForList().range(resultId, 0, -1);
		if (range != null && range.contains(mId)) {
			throw new QuestionnaireAnsweredException();
		}
	}
}
