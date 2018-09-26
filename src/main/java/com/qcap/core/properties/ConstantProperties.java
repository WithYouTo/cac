package com.qcap.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "constant")
@PropertySource("classpath:/config/constant-config.properties")//配置文件路径
public class ConstantProperties {
	
	//酒店接口
	private String appKey;
	private String appSercet;
	private String versions;
	private String format;
	private String local;

	private String usercode;
	private String password;
	private String hotelGroupId;
	
	private String gcLoginPath;
	private String gcLoginConnet;
	//face++ 
	private String faceAppKey;
	private String faceAppSercet;
	private String faceUrl;
	//自助机调起支付回调url
	private String ccmQrcode;
	private String ccmCallback;
	private String privateKey;
	private String publicKey;
	//公安接口
	private String policeUrl;
    private String policeCode; //治安管理系统10位编码
	//sms接口
    private String smstoSend;
    private String smstoLogin; 
    private String smsqrcode;
    private String smsqrpolling; 
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppSercet() {
		return appSercet;
	}
	public void setAppSercet(String appSercet) {
		this.appSercet = appSercet;
	}
	public String getVersions() {
		return versions;
	}
	public void setVersions(String versions) {
		this.versions = versions;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHotelGroupId() {
		return hotelGroupId;
	}
	public void setHotelGroupId(String hotelGroupId) {
		this.hotelGroupId = hotelGroupId;
	}
	public String getGcLoginPath() {
		return gcLoginPath;
	}
	public void setGcLoginPath(String gcLoginPath) {
		this.gcLoginPath = gcLoginPath;
	}
	public String getGcLoginConnet() {
		return gcLoginConnet;
	}
	public void setGcLoginConnet(String gcLoginConnet) {
		this.gcLoginConnet = gcLoginConnet;
	}
	public String getFaceAppKey() {
		return faceAppKey;
	}
	public void setFaceAppKey(String faceAppKey) {
		this.faceAppKey = faceAppKey;
	}
	public String getFaceAppSercet() {
		return faceAppSercet;
	}
	public void setFaceAppSercet(String faceAppSercet) {
		this.faceAppSercet = faceAppSercet;
	}
	public String getFaceUrl() {
		return faceUrl;
	}
	public void setFaceUrl(String faceUrl) {
		this.faceUrl = faceUrl;
	}
	public String getCcmQrcode() {
		return ccmQrcode;
	}
	public void setCcmQrcode(String ccmQrcode) {
		this.ccmQrcode = ccmQrcode;
	}
	public String getCcmCallback() {
		return ccmCallback;
	}
	public void setCcmCallback(String ccmCallback) {
		this.ccmCallback = ccmCallback;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getPoliceUrl() {
		return policeUrl;
	}
	public void setPoliceUrl(String policeUrl) {
		this.policeUrl = policeUrl;
	}
	public String getPoliceCode() {
		return policeCode;
	}
	public void setPoliceCode(String policeCode) {
		this.policeCode = policeCode;
	}
	public String getSmstoSend() {
		return smstoSend;
	}
	public void setSmstoSend(String smstoSend) {
		this.smstoSend = smstoSend;
	}
	public String getSmstoLogin() {
		return smstoLogin;
	}
	public void setSmstoLogin(String smstoLogin) {
		this.smstoLogin = smstoLogin;
	}
	public String getSmsqrcode() {
		return smsqrcode;
	}
	public void setSmsqrcode(String smsqrcode) {
		this.smsqrcode = smsqrcode;
	}
	public String getSmsqrpolling() {
		return smsqrpolling;
	}
	public void setSmsqrpolling(String smsqrpolling) {
		this.smsqrpolling = smsqrpolling;
	}
	

}
