package com.project.contact.model;

import com.project.contact.dao.PhoneEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwl on 2016/4/24.
 */
public class Phone {
    private int id;
    private int userId;
    private String number;
    private String name;
    private String pinyin;
    private String shengmu;
    private String email;
    private String qq;
    private Timestamp createAt;
    private String number2;
    private String workAddress;
    private String homeAddress;
    private String birthday;
    private String homePage;
    private String postCode;
    private String image;
    private String mark;

    private Session session;
    SessionFactory sessionFactory;
    private Criteria crit;
    private Transaction transaction;

    public Phone() {
        Configuration cfg = new Configuration().configure();
        StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        StandardServiceRegistry sr = srb.build();
        sessionFactory = cfg.buildSessionFactory(sr);
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        crit = session.createCriteria(PhoneEntity.class);
    }

    public void finalize() {
        //transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public List<PhoneEntity> getPhoneBySearch(String search, Integer userId) {
        Disjunction dis = Restrictions.disjunction();
        dis.add(Restrictions.like("name", "%" + search + "%")).add(Restrictions.like("number", "%" + search + "%")).add(Restrictions.like("number2", "%" + search + "%")).add(Restrictions.like("pinyin", "%" + search + "%")).add(Restrictions.like("shengmu", "%" + search + "%")).add(Restrictions.like("number2", "%" + search + "%"));
        List<PhoneEntity> list = crit.add(dis).add(Restrictions.eq("userId", userId)).addOrder(Property.forName("pinyin").asc()).list();
        return list;
    }

    public PhoneEntity getPhoneById(Integer id) {
        return (PhoneEntity) session.get(PhoneEntity.class, id);
    }

    public List<PhoneEntity> getPhoneListById(Integer id) {
        List<PhoneEntity> list = new ArrayList<>();
        list.add((PhoneEntity) session.get(PhoneEntity.class, id));
        return list;
    }
    public List<PhoneEntity> getAll() {
        List<PhoneEntity> list = crit.addOrder(Property.forName("pinyin").asc()).list();
        return list;
    }

    public List<PhoneEntity> getAllByUserId(Integer userId) {
        List<PhoneEntity> list = crit.add(Restrictions.eq("userId", userId)).addOrder(Property.forName("pinyin").asc()).list();
        return list;
    }

    public boolean updatePhone(PhoneEntity phoneEntity) {
        //TODO
        phoneEntity = copyProperties(phoneEntity);
        session.update(phoneEntity);
        transaction.commit();
        return true;
    }

    private PhoneEntity copyProperties(PhoneEntity phoneEntity) {
        if (number != null && !number.isEmpty()) {
            phoneEntity.setNumber(number);
        }
        if (number2 != null && !number2.isEmpty()) {
            phoneEntity.setNumber2(number2);
        }
        if (name != null && !name.isEmpty()) {
            phoneEntity.setName(name);
        }
        if (pinyin != null && !pinyin.isEmpty()) {
            phoneEntity.setPinyin(pinyin);
        }
        if (shengmu != null && !shengmu.isEmpty()) {
            phoneEntity.setShengmu(shengmu);
        }
        if (email != null && !email.isEmpty()) {
            phoneEntity.setEmail(email);
        }
        if (qq != null && !qq.isEmpty()) {
            phoneEntity.setQq(qq);
        }
        if (workAddress != null && !workAddress.isEmpty()) {
            phoneEntity.setWorkAddress(workAddress);
        }
        if (homeAddress != null && !homeAddress.isEmpty()) {
            phoneEntity.setHomeAddress(homeAddress);
        }
        if (homePage != null && !homePage.isEmpty()) {
            phoneEntity.setHomePage(homePage);
        }
        if (birthday != null && !birthday.isEmpty()) {
            phoneEntity.setBirthday(birthday);
        }
        if (postCode != null && !postCode.isEmpty()) {
            phoneEntity.setPostCode(postCode);
        }
        if (image != null && !image.isEmpty()) {
            phoneEntity.setImage(image);
        }
        if (mark != null && !mark.isEmpty()) {
            phoneEntity.setMark(mark);
        }
        return phoneEntity;
    }

    public boolean deletePhoneById(Integer id, Integer userId) {
        crit.setMaxResults(1);
        crit.setFirstResult(0);
        List<PhoneEntity> list = new ArrayList<>();
        list = crit.add(Restrictions.eq("id", id)).add(Restrictions.eq("userId", userId)).list();
        if (list == null || list.isEmpty()) {
            return false;
        } else {
            session.delete(list.get(0));
            transaction.commit();
            return true;
        }
    }

    public boolean addPhone(PhoneEntity phoneEntity) {
        session.save(phoneEntity);
        transaction.commit();
        return true;
    }
    public boolean addPhoneList(List<PhoneEntity> list,int userId) {
        for(int i=0;i<list.size();i++) {
            PhoneEntity p = list.get(i);
            p.setUserId(userId);
            session.save(p);
        }
        transaction.commit();
        return true;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getShengmu() {
        return shengmu;
    }

    public void setShengmu(String shengmu) {
        this.shengmu = shengmu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Criteria getCrit() {
        return crit;
    }

    public void setCrit(Criteria crit) {
        this.crit = crit;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

}
