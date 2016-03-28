/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 
 * (the "License"); you may not use this file except in compliance with the License. You may obtain 
 * a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * 
 * =================================================================================================
 * 
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 * 
 * +------------------------------------------------------------------------------------------------+
 * | License: http://oauth-client.buession.com.cn/LICENSE 											|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2014 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.oauth.qq;

import java.util.UUID;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.utils.OAuthEncoder;

/**
 * QQ OAuth 2.0 API
 * 
 * @author Yong.Teng <webmaster@buession.com>
 */
public class QQApi20 extends DefaultApi20 {

	public final static String BASE_URL = "https://graph.qq.com/";

	public final static String ACCESS_TOKEN_URL = BASE_URL + "oauth2.0/token";

	public final static String AUTHORIZATION_URL = BASE_URL
			+ "oauth2.0/authorize?client_id=%s&redirect_uri=%s&response_type=code&state=%s";

	@Override
	public String getAccessTokenEndpoint() {
		return ACCESS_TOKEN_URL;
	}

	@Override
	public String getAuthorizationUrl(OAuthConfig config) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");

		if (config.hasScope()) {
			return String.format(AUTHORIZATION_URL + "&scope=%s", config.getApiKey(),
					OAuthEncoder.encode(config.getCallback()), uuid,
					OAuthEncoder.encode(config.getScope()));
		} else {
			return String.format(AUTHORIZATION_URL, config.getApiKey(),
					OAuthEncoder.encode(config.getCallback()), uuid);
		}
	}

}