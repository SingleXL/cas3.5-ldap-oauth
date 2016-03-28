package com.oauth.qq;

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

public class QQProvider extends BaseOAuth20Provider {

	
	@Override
	protected void internalInit() {
		OAuthConfig oAuthConfig = new OAuthConfig(key, secret, callbackUrl, SignatureType.Header, null, null);
		service = new QQService(new QQApi20(), oAuthConfig);
	}

	@Override
	protected String getProfileUrl() {
		return QQApi20.BASE_URL + "user/get_user_info";
	}

	protected String getOpenIdUrl() {
		return QQApi20.BASE_URL + "oauth2.0/me";
	}

	@Override
	public UserProfile getUserProfile(Token accessToken) {
		String openIdUrl = getOpenIdUrl();
		
		String body = sendRequestForProfile(accessToken, openIdUrl);
		if (body == null) {
			return null;
		}

		String str = body.replace("callback( ", "").replace(" );", "");

		JsonNode json = JsonHelper.getFirstNode(str);
		JsonNode clientid = json.get("client_id");
		JsonNode openid = json.get("openid");

		if (openid == null) {
			throw new OAuthException(
					"Response body is incorrect. Can't extract a openid from this: '" + body + "'",
					null);
		}

		String url = getProfileUrl() + "?oauth_consumer_key=" + clientid.getTextValue() + "&openid="
				+ openid.getTextValue();

		body = sendRequestForProfile(accessToken, url);
		final UserProfile profile = extractUserProfile(body);
		profile.setId(openid.getTextValue());
		
		return profile;
	}

	@Override
	protected UserProfile extractUserProfile(String body) {
		Gson gson = new Gson();
		Map<String, String> profiles = new HashMap<String, String>();
		profiles = gson.fromJson(body, new TypeToken<Map<String, String>>(){}.getType() );
		UserProfile userProfile = new UserProfile();
		userProfile.addAttribute("nickname", profiles.get("nickname"));
		return userProfile;
	}

}
