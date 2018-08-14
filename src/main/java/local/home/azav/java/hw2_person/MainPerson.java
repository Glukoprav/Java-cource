package local.home.azav.java.hw2_person;

// Для теста Person
// Проверяем методы женитьбы (marry) и развода (divorce)

public class MainPerson {
    public static void main(String[] args) {
        Person pm1 = new Person(true, "Vasya");
        Person pw1 = new Person(false, "Masha");
        System.out.println("1 step");
        System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
        System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());

        System.out.println("2 step");
        if (pm1.marry(pw1)) {
            System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
            System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
        } else {
            System.out.println("not marry");
            System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
            System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
        }

        System.out.println("3 step");
        if (pm1.marry(pw1)) {
            System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
            System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
        } else {
            System.out.println("no");
            System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
            System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
        }

        System.out.println("4 step");
        Person pm2 = new Person(true, "Fedya");
        Person pw2 = new Person(false, "Glasha");
        System.out.println(pm2.marry(pm1));
        System.out.println(pm2.getName() + " on " + pm2.getNameSpouse());
        System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
        System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
        System.out.println(pw2.getName() + " on " + pw2.getNameSpouse());

        System.out.println("5 step");
        System.out.println(pm2.marry(pw1));
        System.out.println(pm2.getName() + " on " + pm2.getNameSpouse());
        System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
        System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
        System.out.println(pw2.getName() + " on " + pw2.getNameSpouse());

        System.out.println("6 step");
        System.out.println(pw2.marry(pm1));
        System.out.println(pm2.getName() + " on " + pm2.getNameSpouse());
        System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
        System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
        System.out.println(pw2.getName() + " on " + pw2.getNameSpouse());

        System.out.println("7 step");
        System.out.println(pw2.marry(pm2));
        System.out.println(pm2.getName() + " on " + pm2.getNameSpouse());
        System.out.println(pw1.getName() + " on " + pw1.getNameSpouse());
        System.out.println(pm1.getName() + " on " + pm1.getNameSpouse());
        System.out.println(pw2.getName() + " on " + pw2.getNameSpouse());
    }
}