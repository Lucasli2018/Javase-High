package com.lucas.javase.mavendemo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	static final Log log=LogFactory.getLog(App.class);
    public static void main( String[] args )
    {
    	log.info("日志信息");
        System.out.println( "Hello World!" );
    }
    
    public static int sum(int a,int b){
    	return a+b;
    }
}
