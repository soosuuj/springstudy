package com.gdu.app02.xml01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainWrapper {
  
  public static void ex01() {
    AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml01/appCtx.xml");
    Contact c = ctx.getBean("contact", Contact.class);
    Address a = ctx.getBean("address", Address.class);
    Person p = ctx.getBean("person", Person.class);
    

    System.out.println(p.getName() + ", " + p.getContact() + ", " + p.getAddress());
    
    ctx.close();
  }
  

  public static void main(String[] args) {
    ex01();

  }

}
