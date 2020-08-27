package com.liu.web;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.junit.Test;

import java.util.UUID;

public class test {

    @Test
    public void md5(){
        String encododePwd = new Md5Hash("1").toString();
        System.out.println("encododePwd = " + encododePwd);
    }

    @Test
    public void md5salt(){
        String salt="saas@export.com";
        String encododePwd = new Md5Hash("1", salt).toString();
        System.out.println("encododePwd = " + encododePwd);
    }

    @Test
    public void md5saltIteration(){
        String salt ="liu";
        String pwd = new Md5Hash("1", salt, 5).toString();
        System.out.println("pwd = " + pwd);
    }

    @Test
    public void md5saltRandom(){
        String salt=UUID.randomUUID().toString();
        System.out.println("salt = " + salt);
        String encododePwd  = new Md5Hash("1",salt).toString();
        System.out.println(encododePwd);
    }


    @Test
    public void test() {
        System.out.println(new Sha1Hash("1").toString()); // 40
        System.out.println(new Sha256Hash("1").toString());//64
        System.out.println(new Sha512Hash("1").toString());//128
    }


}
