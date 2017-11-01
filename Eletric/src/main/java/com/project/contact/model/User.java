package com.project.contact.model;

import com.project.contact.dao.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by jwl on 2016/4/21.
 */
public class User {
    private int id;
    private String username;
    private String password;

    private Session session;
    SessionFactory sessionFactory;
    private Criteria crit;
    private Transaction transaction;

    public User() {
        Configuration cfg = new Configuration().configure();
        StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        StandardServiceRegistry sr = srb.build();
        sessionFactory = cfg.buildSessionFactory(sr);
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        crit = session.createCriteria(UserEntity.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity getUserByUsername(String username) {
        crit.setMaxResults(1);
        crit.setFirstResult(0);
        List<UserEntity> list = crit.add(Restrictions.eq("username", username)).list();
        if(list==null||list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }
    //更新密码
    public boolean updateUser(UserEntity userEntity){
        session.update(userEntity);
        transaction.commit();
        return true;
    }

    public boolean addUser(UserEntity userEntity){
        session.save(userEntity);
        transaction.commit();
        return true;
    }
    public void finalize() {
        //transaction.commit();
        session.close();
        sessionFactory.close();
    }

}
