package com.rate_limiter.SlidingWindow;

import com.rate_limiter.RateLimiter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindow implements RateLimiter {
    Queue<Long> slidingWindow;
    int timeWindowInSeconds;
    int bucketCapacity;

    public SlidingWindow(int timeWindowInSeconds, int bucketCapacity) {
        this.timeWindowInSeconds = timeWindowInSeconds;
        this.bucketCapacity = bucketCapacity;
        slidingWindow = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean grantAccess() {
        long currentTimestamp = System.currentTimeMillis();
        checkAndUpdate(currentTimestamp);

        if(slidingWindow.size() < this.bucketCapacity) {
            slidingWindow.offer(currentTimestamp);
            return true;
        }
        return false;
    }

    private void checkAndUpdate(long currentTimestamp) {
        if(slidingWindow.isEmpty()) {
            return;
        }

        long calculatedTime = (currentTimestamp - slidingWindow.peek())/1000;

        while(calculatedTime >= timeWindowInSeconds) {
            slidingWindow.poll();
            if(slidingWindow.isEmpty()) {
                break;
            }
            calculatedTime = (currentTimestamp - slidingWindow.peek())/1000;
        }
    }
}
