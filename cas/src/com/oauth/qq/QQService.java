package com.oauth.qq;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuth20ServiceImpl;

public class QQService extends OAuth20ServiceImpl{

	DefaultApi20 api20 = null;
	OAuthConfig cfg = null;
	
	public QQService(DefaultApi20 api, OAuthConfig config) {
		super(api, config);
		api20 = api;
		cfg = config;
	}

	@Override
	public Token getAccessToken(final Token requestToken, final Verifier verifier) {
		String getAccessTokenUrl = api20.getAccessTokenEndpoint();
		final OAuthRequest request = new OAuthRequest(Verb.GET, getAccessTokenUrl);

		request.addQuerystringParameter(OAuthConstants.CLIENT_ID,cfg.getApiKey());
		request.addQuerystringParameter(OAuthConstants.CLIENT_SECRET, cfg.getApiSecret());
		request.addQuerystringParameter(OAuthConstants.CODE, verifier.getValue());
		request.addQuerystringParameter(OAuthConstants.REDIRECT_URI, cfg.getCallback());
		request.addQuerystringParameter("grant_type", "authorization_code");

		if (cfg.hasScope() == true) {
			request.addQuerystringParameter(OAuthConstants.SCOPE, cfg.getScope());
		}

		final Response response = request.send();
		return api20.getAccessTokenExtractor().extract(response.getBody());
	}
	
}
