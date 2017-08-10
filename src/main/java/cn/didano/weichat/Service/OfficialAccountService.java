package cn.didano.weichat.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.didano.weichat.dao.Hand_officialAccountMapper;
import cn.didano.weichat.model.Hand_officialAccount;

@Service
public class OfficialAccountService {
	private static final Logger log = LoggerFactory.getLogger(OfficialAccountService.class);
	@Autowired
	private Hand_officialAccountMapper hand_officialAccountMapper;
	
	public List<Hand_officialAccount> getAllwxOfficialAccount() {
		List<Hand_officialAccount> storage = hand_officialAccountMapper.getAllwxOfficialAccount();
		log.info("OfficialAccountService 获取storage的第一个data:"+storage.get(0).getOriginalId());
		return storage;
	}
	
}
