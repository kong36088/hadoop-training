package com.project.contact.controller;

import org.junit.Test;

import javax.smartcardio.Card;
import java.io.*;

/**
 * Created by jwl on 2016/4/26.
 */
public class TestController {
    @Test
    public void testGenerateCsv(){
        String str="1,aaaa,null,aaa,aaa,aaa,null,null,null,null,null,null,null,/uploads/head/146211381954829db520f4bfbfbed3058ef1f7cf0f736aec31fc6.jpg,null,";
        str = str.replaceAll("null", "");
        System.out.println(str);
    }
}
