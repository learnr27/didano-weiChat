package cn.didano.weichat.model;

public class Hand_officialAccount {

	private Integer officialAccountId;
	private Integer accountType;
	private Integer wxType;
	private String originalId;
	private String wxName;
	private String token; // 微信帐号自定义Token;
	private String appId; // 微信公众号的唯一标识;
	private String secret; // 微信公众号的appsecret;
	private String aesKey; // 微信加密密钥;
	private String accessToken;// 微信网页授权接口调用凭证;
	private String messageUrl;
	private String createTime;
	private String updateTime;
	private String deleteTage;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getOfficialAccountId() {
		return officialAccountId;
	}

	public void setOfficialAccountId(Integer officialAccountId) {
		this.officialAccountId = officialAccountId;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getWxType() {
		return wxType;
	}

	public void setWxType(Integer wxType) {
		this.wxType = wxType;
	}

	public String getOriginalId() {
		return originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	public String getWxName() {
		return wxName;
	}

	public void setWxName(String wxName) {
		this.wxName = wxName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	public String getMessageUrl() {
		return messageUrl;
	}

	public void setMessageUrl(String messageUrl) {
		this.messageUrl = messageUrl;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getDeleteTage() {
		return deleteTage;
	}

	public void setDeleteTage(String deleteTage) {
		this.deleteTage = deleteTage;
	}

}
