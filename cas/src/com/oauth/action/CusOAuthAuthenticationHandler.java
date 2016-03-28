/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.oauth.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.apache.commons.lang.StringUtils;
import org.aspectj.weaver.ast.HasAnnotation;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.support.oauth.authentication.principal.OAuthCredentials;
import org.scribe.model.Token;
import org.scribe.up.profile.UserProfile;
import org.scribe.up.provider.OAuthProvider;
import org.scribe.up.session.HttpUserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.context.ExternalContextHolder;

import com.oauth.dao.Person;
import com.oauth.dao.PersonDao;

/**
 * This handler authenticates OAuth credentials : it uses them to get an access
 * token to get the user profile returned by the provider for an authenticated
 * user.
 * 
 * @author Jerome Leleu
 * @since 3.5.0
 */
public final class CusOAuthAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {

	private PersonDao personDao;

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	private static final Logger logger = LoggerFactory.getLogger(CusOAuthAuthenticationHandler.class);

	@NotNull
	private List<OAuthProvider> providers;

	public boolean supports(Credentials credentials) {
		return credentials != null && (OAuthCredentials.class.isAssignableFrom(credentials.getClass()));
	}

	@Override
    protected boolean doAuthentication(Credentials credentials) throws AuthenticationException {
        OAuthCredentials credential = (OAuthCredentials) credentials;
        logger.debug("credential : {}", credential);
        
        String providerType = credential.getProviderType();
        logger.debug("providerType : {}", providerType);
        // get provider
        OAuthProvider provider = null;
        for (OAuthProvider aProvider : providers) {
            if (StringUtils.equals(providerType, aProvider.getType())) {
                provider = aProvider;
                break;
            }
        }
        logger.debug("provider : {}", provider);
        
        // get access token
        HttpServletRequest request = (HttpServletRequest) ExternalContextHolder.getExternalContext().getNativeRequest();
        Token accessToken = provider.getAccessToken(new HttpUserSession(request.getSession()), credential);
        logger.debug("accessToken : {}", accessToken);
        // and user profile
        UserProfile userProfile = provider.getUserProfile(accessToken);
        logger.debug("userProfile : {}", userProfile);
        
        if (userProfile != null && StringUtils.isNotBlank(userProfile.getId())) {
            userProfile.addAttribute("access_token", accessToken.getToken());
            
            
            
            //openId = displayname
            
            String openId = userProfile.getId();
            String uid = "";
    		List<Person> persons = personDao.getAllPersons();
    		for (Iterator<Person> iterator2 = persons.iterator(); iterator2.hasNext();) {
    			Person person = iterator2.next();
    			
    			String din = person.getDisplayName();
    			if (din!=null && din.contains(openId)) {
    				uid = person.getUid();
    			}
    		}
    		Map<String, Object> attrs = userProfile.getAttributes();
    		attrs.put("uid", uid);
    		credential.setUserAttributes(attrs);
    		
            
            credential.setUserId(userProfile.getId());
            return true;
        } else {
            return false;
        }
    }

	public void setProviders(List<OAuthProvider> providers) {
		this.providers = providers;
	}
}
