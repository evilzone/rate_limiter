package com.rate_limiter.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class UserBucketCreator {

    Map<Integer, SlidingWindow> userBucket;

    public UserBucketCreator(int id) {
        this.userBucket = new HashMap<>();
        userBucket.put(id, new SlidingWindow(1, 10));
    }

    public void accessApplication(int id) {
        if(userBucket.get(id).grantAccess()) {
            System.out.println(Thread.currentThread().getName() + " -> able to grant access to the application");
        } else {
            System.out.println(Thread.currentThread().getName() + " -> too many request, try later");
        }
    }
}
