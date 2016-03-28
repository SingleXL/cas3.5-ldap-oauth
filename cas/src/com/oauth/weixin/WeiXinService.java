package com.oauth.weixin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuth20ServiceImpl;
import org.scribe.utils.Preconditions;

public class WeiXinService extends OAuth20ServiceImpl {

	DefaultApi20 api20 = null;
	OAuthConfig cfg = null;

	public WeiXinService(DefaultApi20 api, OAuthConfig config) {
		super(api, config);
		api20 = api;
		cfg = config;
	}

	@Override
	public Token getAccessToken(final Token requestToken, final Verifier verifier) {
		String getAccessTokenUrl = api20.getAccessTokenEndpoint();
		final OAuthRequest request = new OAuthRequest(Verb.GET, getAccessTokenUrl);

		request.addQuerystringParameter("appid", cfg.getApiKey());
		request.addQuerystringParameter("secret", cfg.getApiSecret());
		request.addQuerystringParameter(OAuthConstants.CODE, verifier.getValue());
		request.addQuerystringParameter("grant_type", "authorization_code");

		final Response response = request.send();
		
		return new WeiXinJsonTokenExtractor().extract(response.getBody());
	}

	class WeiXinJsonTokenExtractor {

		private Pattern accessTokenPattern = Pattern.compile("\"openid\":\\s*\"(\\S*?)\"");

		public Token extract(String response) {
			Preconditions.checkEmptyString(response, "Cannot extract a token from a null or empty String");
			Matcher matcher = accessTokenPattern.matcher(response);
			if (matcher.find()) {
				return new Token(matcher.group(1), "", response);
			} else {
				throw new OAuthException("Cannot extract an acces token. Response was: " + response);
			}
		}

	}
}
