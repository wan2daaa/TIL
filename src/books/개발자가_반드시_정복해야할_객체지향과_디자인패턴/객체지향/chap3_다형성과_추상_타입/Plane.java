package books.개발자가_반드시_정복해야할_객체지향과_디자인패턴.객체지향.chap3_다형성과_추상_타입;

public class Plane {
    public void fly() {
        System.out.println("날아갑니다.");
    }
}

interface Turbo {
    void boost();
}

class TurboPlane extends Plane implements Turbo {

    @Override
    public void boost() {
        System.out.println("가속합니다!");
    }
}

class Main {

    public static void main(String[] args) {
        TurboPlane tp = new TurboPlane();
//        tp.fly();
//        tp.boost();

        Plane p = tp;
        p.fly();


        Turbo t = tp;

        t.boost();
    }
}
