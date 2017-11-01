package com.project.contact.controller;

import com.project.contact.dao.GroupsEntity;
import com.project.contact.dao.GroupsRecordEntity;
import com.project.contact.dao.PhoneEntity;
import com.project.contact.dao.UserEntity;
import com.project.contact.model.Groups;
import com.project.contact.model.GroupsRecord;
import com.project.contact.model.Phone;
import com.project.contact.model.User;
import com.project.contact.object.*;

import net.sourceforge.cardme.io.CompatibilityMode;
import org.springframework.context.ApplicationContext;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwl on 2016/4/24.
 */
@Controller
@RequestMapping(value = "/data")
public class DataController {
    /* ----------------Groups------------------------ */
    //OK
    @RequestMapping(value = "/group", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getAllGroups(Groups groups, HttpSession httpSession) {
        List<GroupsEntity> list = groups.getGroupsByUserId((Integer) httpSession.getAttribute("userId"));
        String json = new ParseJson().groupsToJson(list);
        return json;
    }

    @RequestMapping(value = "/group/{id}", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getGroupById(@PathVariable("id") Integer id, Groups groups, HttpSession httpSession) {
        List<GroupsEntity> list = new ArrayList<>();
        list.add(groups.getGroupById(id));
        String json = new ParseJson().groupsToJson(list);
        return json;
    }

    //OK
    @RequestMapping(value = "/group", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addGroups(@RequestParam(value = "name") String name, HttpSession httpSession) {
        Groups groups = new Groups();
        if ("".equals(name) || name == null) {
            return "0";
        } else {
            groups.addGroup((int) httpSession.getAttribute("userId"), name);
            return "1";
        }
    }

    @RequestMapping(value = "/group", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String changeGroup(@ModelAttribute Groups groups) {
        if (groups.updateGroup()) {
            return "1";
        } else {
            return "0";
        }
    }

    //OK
    @RequestMapping(value = "/group", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String deleteGroup(@RequestParam(value = "id", required = true) Integer id, HttpSession httpSession) {
        int userId;
        userId = (int) httpSession.getAttribute("userId");
        Groups groups = new Groups();
        GroupsRecord groupsRecord = new GroupsRecord();
        if (groups.deleteGroupById(id, userId) && groupsRecord.deleteGroupsRecordByGroupId(id, userId)) {
            return "1";
        } else {
            return "0";
        }
    }
    /* ---------------------------------------- */

    /* ----------------Phone------------------------ */
    //OK
    @RequestMapping(value = "/phone", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getPhone(HttpSession httpSession, Phone phone, HttpServletRequest httpServletRequest) throws IOException {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        List<PhoneEntity> list = phone.getAllByUserId(userId);
        String json = new ParseJson().phoneToJson(list);
        return json;
    }

    @RequestMapping(value = "/phone/{id}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getPhoneById(@PathVariable("id") Integer id, HttpSession httpSession, Phone phone, HttpServletRequest httpServletRequest) throws IOException {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        List<PhoneEntity> list = phone.getPhoneListById(id);
        String json = new ParseJson().phoneToJsonDetail(list);
        return json;
    }

    //OK
    @RequestMapping(value = "/phone", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addPhone(@ModelAttribute PhoneEntity phoneEntity, HttpSession httpSession) {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        phoneEntity.setUserId(userId);
        Phone phone = new Phone();
        //转拼音
        String name = phoneEntity.getName();
        if (name != null && !"".equals(name) && !name.isEmpty()) {
            phoneEntity.setPinyin(new ParsePinYin().getFullSpell(name));
            phoneEntity.setShengmu(new ParsePinYin().getFirstSpell(name));
        }
        if (phone.addPhone(phoneEntity)) {
            return "1";
        } else {
            return "0";
        }
    }

    //OK
    @RequestMapping(value = "/phone", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String changePhone(@ModelAttribute Phone phone, HttpSession httpSession) {
        Integer id = phone.getId();
        Integer userId = (Integer) httpSession.getAttribute("userId");
        PhoneEntity phoneEntity = phone.getPhoneById(id);
        if (phoneEntity == null || phoneEntity.equals(null)) {
            return "0";
        }
        //转拼音
        String name = phone.getName();
        if (name != null || !"".equals(name)) {
            phone.setPinyin(new ParsePinYin().getFullSpell(name));
            phoneEntity.setShengmu(new ParsePinYin().getFirstSpell(name));
        }
        if (phone.updatePhone(phoneEntity)) {
            return "1";
        } else {
            return "0";
        }
    }

    //OK
    @RequestMapping(value = "/phone", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String deletePhone(@RequestParam(value = "id", required = true) Integer id, HttpSession httpSession) {
        int userId;
        userId = (int) httpSession.getAttribute("userId");
        Phone phone = new Phone();
        GroupsRecord groupsRecord = new GroupsRecord();
        if (phone.deletePhoneById(id, userId) && groupsRecord.deleteGroupsRecordByPhoneId(id, userId)) {
            return "1";
        } else {
            return "0";
        }
    }

    //OK
    @RequestMapping(value = "/searchPhone", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String searchPhone(@RequestParam("search") String search, HttpSession httpSession, Phone phone, HttpServletRequest httpServletRequest) throws IOException {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        search = search.toLowerCase();
        List<PhoneEntity> list;
        if ("".equals(search) || search == "") {
            list = phone.getAllByUserId(userId);
        } else {
            list = phone.getPhoneBySearch(search, userId);
        }
        String json = new ParseJson().phoneToJson(list);
        return json;
    }
    /* ---------------------------------------- */

    /* ----------------GroupsRecord------------------------ */
    //OK 获取分组内号码
    @RequestMapping(value = "/groupPhone", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String phoneByGroup(@RequestParam("id") Integer id, HttpSession httpSession) {
        int userId = (int) httpSession.getAttribute("userId");
        GroupsRecord groupsRecord = new GroupsRecord();
        ParseGroup parseGroup = new ParseGroup();
        ParseJson parseJson = new ParseJson();
        List<PhoneEntity> list = parseGroup.getGroupAll(id, userId);
        String json = parseJson.phoneToJson(list);
        return json;
    }

    //OK 增加联系人到分组
    @RequestMapping(value = "/groupsRecord", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addGroupsRecord(@ModelAttribute GroupsRecordEntity groupsRecordEntity, HttpSession httpSession) {
        int userId = (int) httpSession.getAttribute("userId");
        GroupsRecord groupsRecord = new GroupsRecord();
        if (groupsRecord.addGroupsRecord(userId, groupsRecordEntity)) {
            return "1";
        } else {
            return "0";
        }
    }

    //删除phone的全部分组
    @RequestMapping(value = "/groupsRecord", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addGroupsRecord(@RequestParam("id") Integer id, HttpSession httpSession) {
        int userId = (int) httpSession.getAttribute("userId");
        GroupsRecord groupsRecord = new GroupsRecord();
        if (groupsRecord.deleteGroupsRecordByPhoneId(id, userId)) {
            return "1";
        } else {
            return "0";
        }
    }

    /* ---------------------------------------- */
    /* ----------------GroupsRecord------------------------ */
    @RequestMapping(value = "/config", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getConfig(HttpSession httpSession) {
        Integer userId = (Integer) httpSession.getAttribute("userId");
        return "";
    }

    /* ----------------GroupsRecord------------------------ */

    //OK
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String upload(@RequestParam("weixin_image") MultipartFile multipartFile) throws IOException {
        JSONObject res = new JSONObject();
        if (!multipartFile.isEmpty()) {
            String preName = "/uploads/head/";
            String subName = System.currentTimeMillis() + multipartFile.getOriginalFilename();
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File("I:\\hadoop\\share\\AddressBookSystem\\src\\main\\webapp\\WEB-INF\\uploads\\head\\", subName));
            res.put("url", preName+subName);
            res.put("status", 1);
        } else {
            res.put("msg", "上传失败");
            res.put("status", 0);
        }
        return res.toString();
    }

    @RequestMapping(value = "/changePass", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String post(@RequestParam("opass") String opass, @RequestParam("newpass") String newPass, Model model, HttpSession httpSession) throws IOException {
        //Integer userId=(Integer)httpSession.getAttribute("userId");
        String username = (String) httpSession.getAttribute("username");
        opass = new ParseMD5().parseStrToMd5L32(opass);
        newPass = new ParseMD5().parseStrToMd5L32(newPass);
        User user = new User();
        UserEntity realUser = user.getUserByUsername(username);
        if (opass.equals(realUser.getPassword())) {
            realUser.setPassword(newPass);
            user.updateUser(realUser);
            return "1";
        } else {
            return "0";
        }
    }

    /* ----------------upload------------------------ */
    @RequestMapping(value = "/downloadCsv", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String downloadCsv(HttpSession httpSession){
        int userId=(int)httpSession.getAttribute("userId");
        String preName = "/uploads/file/";
        String subName = System.currentTimeMillis() +".csv";
        Phone phone=new Phone();
        List<PhoneEntity> list=new ArrayList<>();
        list=phone.getAllByUserId(userId);
        JSONObject res=new JSONObject();
        ParseCsv parseCsv = new ParseCsv();
        if(parseCsv.generateCsv(new File(httpSession.getServletContext().getRealPath("/") + "\\WEB-INF\\uploads\\file\\", subName),list)){
            res.put("url", preName + subName);
            res.put("status", 1);
        }else{
            res.put("msg", "下载失败");
            res.put("status", 0);
        }
        return res.toString();
    }


    @RequestMapping(value = "/uploadCsv", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String uploadCsv(@RequestParam("weixin_image") MultipartFile multipartFile, HttpSession httpSession) throws IOException {
        JSONObject res = new JSONObject();
        if (!multipartFile.isEmpty()) {
            String preName = "/uploads/file/";
            String subName = System.currentTimeMillis() + multipartFile.getOriginalFilename();
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(httpSession.getServletContext().getRealPath("/") + "\\WEB-INF\\uploads\\file\\", subName));
            res.put("msg", "导入成功");
            res.put("status", 1);
            //导入
            Integer userId = (Integer) httpSession.getAttribute("userId");
            Phone phone = new Phone();
            ParseCsv parseCsv = new ParseCsv();
            File f=new File(httpSession.getServletContext().getRealPath("/") + "\\WEB-INF\\uploads\\file\\", subName);
            List<PhoneEntity> imports = parseCsv.importCsv(f);
            f.delete();
            phone.addPhoneList(imports, userId);
        } else {
            res.put("msg", "上传失败");
            res.put("status", 0);
        }
        return res.toString();
    }
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String uploadVCard( HttpSession httpSession) throws IOException {
        ParseVcard parseVcard=new ParseVcard();
        parseVcard.setCompatibilityMode(CompatibilityMode.RFC2426);
        String subName="javaContact.vcf";
        parseVcard.parseVCard(new File(httpSession.getServletContext().getRealPath("/") + "\\WEB-INF\\uploads\\file\\", subName));
        return "";
    }
    /* ---------------------------------------- */

    /* ---------------------------------------- */
    @RequestMapping(value = "/getUserId", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getUserId(HttpSession httpSession){
        Integer userId=new Integer((int)httpSession.getAttribute("userId"));
        return userId.toString();
    }

}