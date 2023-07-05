package com.rate_limiter.SlidingWindow;

import com.rate_limiter.LeakyBucket.UserBucketCreator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {

    public static void main(String[] args) {
        com.rate_limiter.LeakyBucket.UserBucketCreator userBucketCreator = new UserBucketCreator(1);
        ExecutorService executorService = Executors.newFixedThreadPool(12);

        for(int i = 0; i < 12; i++) {
            executorService.submit(() -> userBucketCreator.accessApplication(1));
        }

        executorService.shutdown();
    }
}