package com.project.contact.model;

import com.project.contact.dao.GroupsEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwl on 2016/4/24.
 */
public class Groups {
    private int id;
    private int userId;
    private String name;

    private Session session;
    SessionFactory sessionFactory;
    private Criteria crit;
    private Transaction transaction;

    public Groups() {
        Configuration cfg = new Configuration().configure();
        StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        StandardServiceRegistry sr = srb.build();
        sessionFactory = cfg.buildSessionFactory(sr);
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        crit = session.createCriteria(GroupsEntity.class);
    }

    public void finalize() throws Throwable {
        //
        super.finalize();
        session.close();
        sessionFactory.close();
    }

    public GroupsEntity getGroupById(Integer id) {
        /*crit.setMaxResults(1);
        crit.setFirstResult(0);
        List<GroupsEntity>list=crit.add(Restrictions.eq("id",id)).list();
        if(list==null||list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }*/
        return (GroupsEntity) session.get(GroupsEntity.class, id);
    }

    public void addGroup(Integer userId, String name) {
        GroupsEntity groupsEntity = new GroupsEntity();
        groupsEntity.setName(name);
        groupsEntity.setUserId(userId);
        session.save(groupsEntity);
        transaction.commit();
    }

    public boolean updateGroup() {
        GroupsEntity groupsEntity = (GroupsEntity) session.get(GroupsEntity.class, id);
        groupsEntity.setName(name);
        session.update(groupsEntity);
        transaction.commit();
        return true;
    }

    public boolean deleteGroupById(Integer id, Integer userId) {
        crit.setMaxResults(1);
        crit.setFirstResult(0);
        List<GroupsEntity> list = new ArrayList<>();
        list = crit.add(Restrictions.eq("id", id)).add(Restrictions.eq("userId", userId)).list();
        if (list == null || list.isEmpty()) {
            return false;
        } else {
            session.delete(list.get(0));
            transaction.commit();
            return true;
        }
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupsEntity> getGroupsByUserId(Integer userId) {
        List<GroupsEntity> list = crit.add(Restrictions.eq("userId", userId)).list();
        return list;
    }

}
