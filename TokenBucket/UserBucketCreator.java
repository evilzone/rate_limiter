package com.rate_limiter.TokenBucket;

import java.util.HashMap;
import java.util.Map;

public class UserBucketCreator {

    Map<Integer, TokenBucket> bucket;

    public UserBucketCreator(int id) {
        bucket = new HashMap<>();
        TokenBucket tokenBucket = new TokenBucket(10, 10);
        bucket.put(id, tokenBucket);
    }

    public void accessApplication(int id) {
        if(bucket.get(id).grantAccess()) {
            System.out.println(Thread.currentThread().getName() + " -> able to grant access to the application");
        } else {
            System.out.println(Thread.currentThread().getName() + " -> too many request, try later");
        }
    }

}
