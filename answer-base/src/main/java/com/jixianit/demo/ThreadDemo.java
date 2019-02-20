package com.jixianit.demo;

/**
 * 缺少了一个同步的处理，导致多个线程出现了不同步问题，在线程运行的时候继续追加synchronized同步代码块。
 */
public class ThreadDemo {
    public static void main(String[] args) throws Exception {
        Resource res = new Resource();
        SubThread st = new SubThread(res);
        AddThread at = new AddThread(res);
        new Thread(at, "加法线程 - A").start();
        new Thread(at, "加法线程 - B").start();
        new Thread(at, "加法线程 - C").start();
        new Thread(st, "减法线程 - X").start();
        new Thread(st, "减法线程 - Y").start();
        new Thread(st, "减法线程 - Z").start();
    }
}

class Resource {    // 定义一个操作的资源
    private int num = 0; // 这个要进行加减操作的数据
    private boolean flag = true; // 加减的切换
    // flag = true：表示可以进行加法操作，但是无法进行减法操作；
    // flag = false：表示可以进行减法操作，但是无法进行加法操作。

    public synchronized void add() throws Exception { // 执行加法操作
        if (this.flag == false) {    // 现在需要执行的是减法操作，加法操作要等待
            super.wait();
        }
        Thread.sleep(500);
        this.num++;
        System.out.println("【加法操作 - " + Thread.currentThread().getName() + "】num = " + this.num);
        this.flag = false; // 加法操作执行完毕，需要执行减法处理
        super.notifyAll(); // 唤醒全部等待线程
    }

    public synchronized void sub() throws Exception { // 执行减法操作
        if (this.flag == true) {    // 减法操作需要等待
            super.wait();
        }
        Thread.sleep(2000);
        this.num--;
        System.out.println("【减法操作 - " + Thread.currentThread().getName() + "】num = " + this.num);
        this.flag = true;
        super.notifyAll();
    }
}

class AddThread implements Runnable {
    private Resource resource;

    public AddThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int x = 0; x < 50; x++) {
            try {
                synchronized (this) {
                    this.resource.add();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class SubThread implements Runnable {
    private Resource resource;

    public SubThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int x = 0; x < 50; x++) {
            try {
                synchronized (this) {
                    this.resource.sub();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}