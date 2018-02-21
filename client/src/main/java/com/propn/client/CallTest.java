package com.propn.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Multi threads to call http services
 * 
 * @author Lei
 *
 */
public class CallTest {

	private static String URL = "https://www.baidu.com/";
	private static int count = 0;// job finished threads
	private static ThreadLocal<Integer> time = new ThreadLocal<Integer>();

	private static final int jobs = 200;// jobs per thread
	private static final int threads = 50;

	public static void main(String[] args) {

		int processors = Runtime.getRuntime().availableProcessors();
		ExecutorService threadPool = Executors.newFixedThreadPool(processors);// thread pools

		long startTime = System.currentTimeMillis();
		

		for (int i = 0; i < threads; i++) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					time.set(0);
					
					while (time.get() <= jobs) {
						String rst = HttpUtil.httpGetRequest(URL);// Call Rest Service
						time.set(time.get().intValue() + 1);
					}

					synchronized (this) {
						System.out.println("Thread " + Thread.currentThread().getId() + " finish " + jobs + " jobs in "
								+ (System.currentTimeMillis() - startTime) / 1000 + " seconds.");
						count++;
					}

				}
			});
		}

		while (count < threads) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		threadPool.shutdown();
		System.out.println("All Jobs done in :" + (System.currentTimeMillis() - startTime) / 1000 + " seconds");
		System.exit(0);

	}

}
