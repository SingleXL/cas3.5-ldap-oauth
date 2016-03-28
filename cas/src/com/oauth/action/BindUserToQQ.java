package com.oauth.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.adaptors.ldap.BindLdapAuthenticationHandler;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.oauth.dao.Person;
import com.oauth.dao.PersonDao;

/**
 * Servlet implementation class BindUserToQQ
 */
public class BindUserToQQ extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 获取ServletContext 再获取 WebApplicationContextUtils
			ServletContext servletContext = this.getServletContext();
			WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			BindLdapAuthenticationHandler handler = (BindLdapAuthenticationHandler) context.getBean("ldapAuthenticationHandler");
			PersonDao personDao = (PersonDao) context.getBean("personDao");

			//
			String openId = request.getParameter("openId");
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			UsernamePasswordCredentials credentials = new UsernamePasswordCredentials();
			credentials.setUsername(username);
			credentials.setPassword(password);
			boolean authenticate = false;
			try {
				authenticate = handler.authenticate(credentials);
			} catch (AuthenticationException e) {
				e.printStackTrace();
			}

			Person person = personDao.getPersonByUid(username);
			String din = person.getDisplayName();
			
			person.setDisplayName(din+";"+openId);
			personDao.updatePerson(person);

			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception e) {
			response.sendRedirect("binderror.jsp");
		}

		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
