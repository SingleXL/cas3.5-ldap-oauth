package com.oauth.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.adaptors.ldap.BindLdapAuthenticationHandler;
import org.jasig.cas.support.oauth.authentication.principal.OAuthCredentials;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.oauth.constants.Constants;
import com.oauth.dao.Person;
import com.oauth.dao.PersonDao;

public class QQCheckAndBindAction extends AbstractAction {

	private PersonDao personDao;

	public PersonDao getPersonDao() {
		return personDao;
	}

	private BindLdapAuthenticationHandler ldapAuthenticationHandler;

	public BindLdapAuthenticationHandler getLdapAuthenticationHandler() {
		return ldapAuthenticationHandler;
	}

	public void setLdapAuthenticationHandler(BindLdapAuthenticationHandler ldapAuthenticationHandler) {
		this.ldapAuthenticationHandler = ldapAuthenticationHandler;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	protected Event doExecute(RequestContext context) throws Exception {

		/*
		 * steps
		 * 
		 * 1) 检查是否绑定
		 * 否：绑定页面
		 *  1） request中是否含有用户名和密码进行绑定
		 *  2） 不含有 跳转 绑定页面
		 * 
		 * 是：成功
		 */
		boolean isBind = false;
		
		MutableAttributeMap flowScope = context.getFlowScope();
		OAuthCredentials oAuthCredentials = (OAuthCredentials) flowScope.get(Constants.OAUTH_CREDENTIALS);
		
		String openId = oAuthCredentials.getUserId();

		List<Person> persons = personDao.getAllPersons();
		for (Iterator<Person> iterator2 = persons.iterator(); iterator2.hasNext();) {
			Person person = iterator2.next();
			
			String din = person.getDisplayName();
			if (din!=null && din.contains(openId)) {
				isBind = true;
			}
			
		}
		
		if (isBind) {
			flowScope.put(Constants.OAUTH_CREDENTIALS, oAuthCredentials);
			return success();
		}else {
			HttpServletRequest request = WebUtils.getHttpServletRequest(context);
			request.setAttribute("openId", openId);
			return error();
		}
	}

}
