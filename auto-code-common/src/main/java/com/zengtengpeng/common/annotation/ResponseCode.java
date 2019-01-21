package com.zengtengpeng.common.annotation;

public enum ResponseCode  {
	SUCCESS("0","成功"),
	FAIL("1","失败"),
	LOGIN_UNUSERNAME("10001","用户名不存在"),
	LOGIN_IMAGECODE("10003","图形验证不正确"),
	LOGIN_UNPASSWORD("10002","密码不正确"),
	;

    private String code;
	private String desc;
	 ResponseCode(String code, String desc){
		this.code=code;
		this.desc=desc;
	}

	public String code(){
		return this.code;
	}

	public String desc(){
		return this.desc;
	}

}
