package sz.zp.cks.test;

public class MyThreadPrinter2 implements Runnable {
    private String name;
    private Object prev;
    private Object self;

    private MyThreadPrinter2(String name, Object prev, Object self) {
        this.name = name;
        this.prev = prev;
        this.self = self;
        System.out.println(name+"赋值了prev和self");
    }
    @Override
    public  void run() {

        int count = 10;
        while (count > 0) {
            synchronized (prev) {
                System.out.print("prev"+count);
                synchronized (self) {
                    System.out.print(name);
                    count--;
                    System.out.print("self"+count);
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        MyThreadPrinter2 pa = new MyThreadPrinter2("A", c, a);
        MyThreadPrinter2 pb = new MyThreadPrinter2("B", a, b);
        MyThreadPrinter2 pc = new MyThreadPrinter2("C", b, c);


        new Thread(pa).start();
        Thread.sleep(1000);  //确保按顺序A、B、C执行
        new Thread(pb).start();
        Thread.sleep(1000);
        new Thread(pc).start();
        Thread.sleep(1000);
    }
}

