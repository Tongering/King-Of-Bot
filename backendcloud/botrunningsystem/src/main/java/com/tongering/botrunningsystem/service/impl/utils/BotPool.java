package com.tongering.botrunningsystem.service.impl.utils;

import com.tongering.botrunningsystem.service.impl.utils.Bot;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BotPool extends Thread{

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition(); // 条件变量
    private final Queue<Bot> bots = new LinkedList<>();
    public void addBot(Integer userId, String botCode, String input){
        lock.lock();
        try {
            bots.add(new Bot(userId, botCode, input));
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
    private void consume(Bot bot){
        Consumer consumer = new Consumer();
        consumer.startTimeout(2000, bot);
    }

    @Override
    public void run() {
        while(true){
            lock.lock();
            if(bots.isEmpty()){ // 当bots为空，则需要把当前线程阻塞住
                try {
                    condition.await(); // 阻塞住当前线程，直到当前线程被唤醒(执行signal)或者被销毁
                                       // await方法自动包含解锁操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    lock.unlock();
                    break;
                }
            } else {
                Bot bot = bots.remove();
                lock.unlock();

                consume(bot); // 比较耗时，故放在lock后面
            }
        }
    }
}
