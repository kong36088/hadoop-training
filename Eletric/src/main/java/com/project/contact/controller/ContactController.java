package com.project.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by jwl on 2016/4/22.
 */
@Controller
@RequestMapping(value = "/contact")
public class ContactController {
    /**
     * 联系人页面
     *
     * @return view
     */
    @RequestMapping(value = "/phone", method = RequestMethod.GET)
    public String indexPhone() {
        return "phone";
    }

    /**
     * 分组管理页面
     *
     * @return view
     */
    @RequestMapping(value = "/groupsManage", method = RequestMethod.GET)
    public String indexGroups() {
        return "groupsManage";
    }

    /**
     * 修改密码
     *
     * @return view
     */
    @RequestMapping(value = "/changePass", method = RequestMethod.GET)
    public String indexChangePass() {
        return "changePass";
    }

    @RequestMapping(value = "/addPhone", method = RequestMethod.GET)
    public String addPhone() {
        return "addPhone";
    }

    @RequestMapping(value = "/phoneDetail", method = RequestMethod.GET)
    public String phoneDetail() {
        return "phoneDetail";
    }

    @RequestMapping(value = "/phoneModify", method = RequestMethod.GET)
    public String phoneModify() {
        return "phoneModify";
    }

    @RequestMapping(value = "/phoneByGroup", method = RequestMethod.GET)
    public String phoneByGroup() {
        return "phoneByGroup";
    }

    //数据导入导出
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String data() {
        return "data";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("username");
        httpSession.removeAttribute("userId");
        return "redirect:/login/login";
    }
}
