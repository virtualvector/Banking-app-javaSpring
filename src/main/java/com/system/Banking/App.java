package com.system.Banking;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Banking application 
 * 
 *
 */

public class App {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");

		BankDao dao = (BankDao) context.getBean("bankDao");

//		dao.insert(new User("1003004","raju","pass",100, "init-balance-"+Integer.toString(100)));
//		dao.insert(new User("1003005","ramesh","pass",200, "init-balance-"+Integer.toString(200)));
//		dao.updateBalance("1003004", 10, 1);
//		dao.updateBalance("1003004", 20, 0);
//		dao.transfer("1003004", "1003005", 25);
		
//	dao.delete("1003004");
		for(User user: dao.getAllUsers()) {
        	System.out.println(user);
        	//System.out.println(user.getTransaction());
        }
//        
        System.out.println("");
        
        //System.out.println(dao.getBalance("1003001"));

	}
}
