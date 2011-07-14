package com.rorotika.cm.core.dto;

import javax.jws.WebService;

@WebService
public interface CmApplicationWs {
	String sayHi(String text);
	CmApplicationDto getCmApplication();
	void updateCmApplication(CmApplicationDto cmApplicationDto);
}
