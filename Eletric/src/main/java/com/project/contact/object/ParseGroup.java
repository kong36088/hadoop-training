package com.project.contact.object;

import com.project.contact.dao.GroupsEntity;
import com.project.contact.dao.GroupsRecordEntity;
import com.project.contact.dao.PhoneEntity;
import com.project.contact.model.Groups;
import com.project.contact.model.GroupsRecord;
import com.project.contact.model.Phone;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jwl on 2016/4/25.
 */
public class ParseGroup {
    /**
     * 返回联系人所属组名
     * @param phoneEntity
     * @return String
     */
    public String parsePhoneGroup(PhoneEntity phoneEntity){
        int id=phoneEntity.getId();
        GroupsRecord groupsRecord=new GroupsRecord();
        Groups groups=new Groups();
        String tmp="";
        List<GroupsRecordEntity> list=groupsRecord.getGroupsRecordByPhoneId(id);
        for(int i=0;i<list.size();i++){
            GroupsRecordEntity v=list.get(i);
            if(v!=null){
                int groupId=v.getGroupId();
                GroupsEntity g=groups.getGroupById(groupId);
                tmp+=g.getName()+" ";
            }
        }
        return tmp;
    }

    //获取组内所有phone
    public List<PhoneEntity> getGroupAll(Integer groupId,Integer userId){
        Phone phone=new Phone();
        GroupsRecord groupsRecord=new GroupsRecord();
        List<PhoneEntity> data=new ArrayList<>();
        List<GroupsRecordEntity>list=groupsRecord.getGroupsRecordByGroupId(groupId,userId);
        for(int i=0;i<list.size();i++){
            GroupsRecordEntity v=list.get(i);
            if(v!=null){
                PhoneEntity p=phone.getPhoneById(v.getPhoneId());
                data.add(p);
            }
        }
        return data;
    }
}
