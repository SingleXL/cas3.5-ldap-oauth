package com.oauth.dao;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao {

	@Autowired
	private LdapTemplate ldapTemplate;

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	public boolean updatePerson(Person vo) {
		try {
			ldapTemplate.modifyAttributes("cn=" + vo.getCn() + ",ou=people,dc=gvsun,dc=net", new ModificationItem[] { new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("displayName", vo.getDisplayName())), });
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	public Person getPersonByUid(String uid) {
		String filter = "(&(objectclass=inetOrgPerson)(uid=" + uid + "))";
		@SuppressWarnings("unchecked")
		List<Person> list = ldapTemplate.search("dc=gvsun,dc=net", filter, new PersonAttributesMapper());
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Person> getAllPersons() {
		return ldapTemplate.search("dc=gvsun,dc=net", "(objectclass=person)", new PersonAttributesMapper());
	}

	private class PersonAttributesMapper implements AttributesMapper {
		public Object mapFromAttributes(Attributes attrs) throws NamingException {
			Person person = new Person();
			person.setUid((String) attrs.get("uid").get());
			person.setCn((String) attrs.get("cn").get());

			Attribute nameAttribute = attrs.get("displayName");
			if (nameAttribute != null) {
				person.setDisplayName((String) attrs.get("displayName").get());
			} else {
				person.setDisplayName("");
			}

			return person;
		}

	}

}