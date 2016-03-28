package com.oauth.dao;

/**
 * 本测试类person对象来自schema文件的core.schema文件 objectClass为person，必填属性和可选属性也是根据该对象得到的。
 * Author:Ding Chengyun
 */

public class Person {

	@Override
	public String toString() {
		return "Person [uid=" + uid + ", displayName=" + displayName + "]";
	}

	private String cn;

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	private String uid;
	private String displayName;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}