package com.project.contact.model;

import com.project.contact.dao.GroupsEntity;
import com.project.contact.dao.GroupsRecordEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwl on 2016/4/25.
 */
public class GroupsRecord {
    private int id;
    private int userId;
    private int groupId;
    private int phoneId;

    private Session session;
    SessionFactory sessionFactory;
    private Criteria crit;
    private Transaction transaction;

    public GroupsRecord() {
        Configuration cfg = new Configuration().configure();
        StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        StandardServiceRegistry sr = srb.build();
        sessionFactory = cfg.buildSessionFactory(sr);
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        crit = session.createCriteria(GroupsRecordEntity.class);
    }

    public void finalize() throws Throwable {
        //
        super.finalize();
        session.close();
        sessionFactory.close();
    }

    public List<GroupsRecordEntity> getGroupsRecordByGroupId(Integer groupId,Integer userId){
        List<GroupsRecordEntity> list= crit.add(Restrictions.eq("groupId",groupId)).add(Restrictions.eq("userId",userId)).list();
        if(list.isEmpty()||list==null){
            return null;
        }else{
            return list;
        }
    }

    public boolean addGroupsRecord(Integer userId,GroupsRecordEntity groupsRecordEntity){
        groupsRecordEntity.setUserId(userId);
        session.save(groupsRecordEntity);
        transaction.commit();
        return true;
    }

    public boolean deleteGroupsRecordByGroupId(Integer groupId, Integer userId) {
        List<GroupsRecordEntity> list=new ArrayList<>();
        list = crit.add(Restrictions.eq("groupId", groupId)).add(Restrictions.eq("userId", userId)).list();
        if ( list == null) {
            return false;
        }
        for (GroupsRecordEntity v : list) {
            session.delete(v);
        }
        transaction.commit();
        return true;
    }

    public boolean deleteGroupsRecordByPhoneId(Integer phoneId, Integer userId) {
        List<GroupsRecordEntity> list=new ArrayList<>();
        list = crit.add(Restrictions.eq("phoneId", phoneId)).add(Restrictions.eq("userId", userId)).list();
        if ( list == null) {
            return false;
        }
        for (GroupsRecordEntity v : list) {
            session.delete(v);
        }
        transaction.commit();
        return true;
    }

    public List<GroupsRecordEntity> getGroupsRecordByPhoneId(Integer phoneId){
        List<GroupsRecordEntity>list=crit.add(Restrictions.eq("phoneId",phoneId)).list();
        return list;
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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }
}
