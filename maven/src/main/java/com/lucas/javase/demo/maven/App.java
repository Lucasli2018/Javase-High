package com.lucas.javase.demo.maven;

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
        System.out.println( "Hello World!" );
        log.info("可以写日志啦，好开心");
    }
}
