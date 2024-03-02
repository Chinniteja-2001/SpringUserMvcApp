package org.jsp.usermvcdemo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.usermvcdemo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private EntityManager entityManager;

	
	
	public User saveUser(User u) {
		entityManager.persist(u);
		EntityTransaction transaction=entityManager.getTransaction();
		transaction.begin();
		transaction.commit();
		return u;
	}
	
	public User updateUser(User u) {
		User u1=findById(u.getId());
		if(u!=null) {
			EntityTransaction transaction=entityManager.getTransaction();
			u.setName(u.getName());
			u.setGender(u.getGender());
			u.setPhone(u.getPhone());
			u.setEmail(u.getEmail());
			u.setPassword(u.getPassword());
			transaction.begin();
			transaction.commit();
			return u;
			
		}
		return null;
	}
	
	public User findById(int id) {
		return entityManager.find(User.class, id);
	}
	
	public boolean deleteUser(int id) {
		User u=entityManager.find(User.class,id);
		if(u!=null) {
			EntityTransaction transaction=entityManager.getTransaction();
			entityManager.remove(u);
			transaction.begin();
			transaction.commit();
			return true;
		}
		return false;
	}
	
	public User verifyById(int id,String password) {
		Query q=entityManager.createQuery("select u from User u where u.id=?1 and u.password?=2");
		q.setParameter(1, id);
		q.setParameter(2, password);
		try {
			return (User)q.getResultList();
		}catch(NoResultException e) {
			return null;
		}
	}
	
	public User verifyUser(String email,String password) {
		Query q=entityManager.createQuery("select u from User u where u.email=?1 and u.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			User u=(User)q.getSingleResult();
			return u;
		}catch(NoResultException e) {
			return null;
		}
	}
	
	public User verifyUser(long phone,String password) {
		Query q=entityManager.createQuery("select u from User u where u.phone=?1 and u.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			User u=(User)q.getSingleResult();
			return u;
		}catch(NoResultException e) {
			return null;
		}
	}
	
}
