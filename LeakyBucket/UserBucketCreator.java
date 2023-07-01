package com.rate_limiter.LeakyBucket;

import java.util.HashMap;
import java.util.Map;

public class UserBucketCreator {

    Map<Integer, LeakyBucket> bucket;

    public UserBucketCreator(int id) {
        bucket = new HashMap<>();
        LeakyBucket leakyBucket = new LeakyBucket(10);
        bucket.put(id, leakyBucket);
    }

    public void accessApplication(int id) {
        if(bucket.get(id).grantAccess()) {
            System.out.println(Thread.currentThread().getName() + " -> able to grant access to the application");
        } else {
            System.out.println(Thread.currentThread().getName() + " -> too many request, try later");
        }
    }

}
