package com.oauth.weixin;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthConfig;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.up.profile.JsonHelper;
import org.scribe.up.profile.UserProfile;
import org.scribe.up.provider.BaseOAuth20Provider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WeiXinProvider extends BaseOAuth20Provider {

	
	@Override
	protected void internalInit() {
		OAuthConfig oAuthConfig = new OAuthConfig(key, secret, callbackUrl, SignatureType.Header, null, null);
		service = new WeiXinService(new WeiXin20(), oAuthConfig);
	}

	@Override
	protected String getProfileUrl() {
		return "https://api.weixin.qq.com/sns/userinfo";  
	}

	protected String getOpenIdUrl() {
		return WeiXin20.BASE_URL + "oauth2.0/me";
	}

	@Override
	public UserProfile getUserProfile(Token accessToken) {
		UserProfile profile = new UserProfile();
		profile.setId(accessToken.getToken());
		return profile;
		
	}

	@Override
	protected UserProfile extractUserProfile(String arg0) {
		return null;
	}


}
