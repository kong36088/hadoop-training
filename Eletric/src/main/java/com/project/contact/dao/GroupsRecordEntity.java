package com.project.contact.dao;

import javax.persistence.*;

/**
 * Created by jwl on 2016/4/29.
 */
@Entity
@Table(name = "groups_record", schema = "java_contact", catalog = "")
public class GroupsRecordEntity {
    private int id;
    private int userId;
    private int groupId;
    private int phoneId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "group_id", nullable = false)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "phone_id", nullable = false)
    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsRecordEntity that = (GroupsRecordEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (groupId != that.groupId) return false;
        if (phoneId != that.phoneId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + groupId;
        result = 31 * result + phoneId;
        return result;
    }

    @Override
    public String toString() {
        return "GroupsRecordEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", groupId=" + groupId +
                ", phoneId=" + phoneId +
                '}';
    }
}
