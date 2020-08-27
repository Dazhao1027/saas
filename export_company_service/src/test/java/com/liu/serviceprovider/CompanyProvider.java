package com.liu.serviceprovider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class CompanyProvider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ca = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");

        System.in.read();
    }
}
