package com.propn.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 * xx
 */
public class App {

	static Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		while (true) {
			log.debug("time:${1}", System.currentTimeMillis());
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info("Thread.currentThread().sleep(100)");
		}
	}
}
