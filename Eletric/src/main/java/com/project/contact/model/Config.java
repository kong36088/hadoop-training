package com.project.contact.model;

import com.project.contact.dao.ConfigEntity;
import com.project.contact.dao.PhoneEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by jwl on 2016/4/26.
 */
public class Config {
    private int id;
    private int userId;
    private String value;

    private Session session;
    SessionFactory sessionFactory;
    private Criteria crit;
    private Transaction transaction;

    public Config() {
        Configuration cfg = new Configuration().configure();
        StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        StandardServiceRegistry sr = srb.build();
        sessionFactory = cfg.buildSessionFactory(sr);
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        crit = session.createCriteria(ConfigEntity.class);
    }

    public ConfigEntity getConfigByUserId(Integer userId){
        return (ConfigEntity) session.get("userId",userId);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
