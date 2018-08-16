package local.home.azav.java.hw2_person;

// Для теста Person
// Проверяем методы женитьбы (marry) и развода (divorce)

public class MainPerson {
    private static void outer(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        Person pm1 = new Person(true, "Vasya");
        Person pw1 = new Person(false, "Masha");
        outer("1 step");
        outer(pm1.getName() + " on " + pm1.getNameSpouse());
        outer(pw1.getName() + " on " + pw1.getNameSpouse());

        outer("2 step");
        if (pm1.marry(pw1)) {
            outer(pm1.getName() + " on " + pm1.getNameSpouse());
            outer(pw1.getName() + " on " + pw1.getNameSpouse());
        } else {
            outer("not marry");
            outer(pm1.getName() + " on " + pm1.getNameSpouse());
            outer(pw1.getName() + " on " + pw1.getNameSpouse());
        }

        outer("3 step");
        if (pm1.marry(pw1)) {
            outer(pm1.getName() + " on " + pm1.getNameSpouse());
            outer(pw1.getName() + " on " + pw1.getNameSpouse());
        } else {
            outer("no");
            outer(pm1.getName() + " on " + pm1.getNameSpouse());
            outer(pw1.getName() + " on " + pw1.getNameSpouse());
        }

        outer("4 step");
        Person pm2 = new Person(true, "Fedya");
        Person pw2 = new Person(false, "Glasha");
        System.out.println(pm2.marry(pm1));
        outer(pm2.getName() + " on " + pm2.getNameSpouse());
        outer(pw1.getName() + " on " + pw1.getNameSpouse());
        outer(pm1.getName() + " on " + pm1.getNameSpouse());
        outer(pw2.getName() + " on " + pw2.getNameSpouse());

        outer("5 step");
        System.out.println(pm2.marry(pw1));
        outer(pm2.getName() + " on " + pm2.getNameSpouse());
        outer(pw1.getName() + " on " + pw1.getNameSpouse());
        outer(pm1.getName() + " on " + pm1.getNameSpouse());
        outer(pw2.getName() + " on " + pw2.getNameSpouse());

        outer("6 step");
        System.out.println(pw2.marry(pm1));
        outer(pm2.getName() + " on " + pm2.getNameSpouse());
        outer(pw1.getName() + " on " + pw1.getNameSpouse());
        outer(pm1.getName() + " on " + pm1.getNameSpouse());
        outer(pw2.getName() + " on " + pw2.getNameSpouse());

        outer("7 step");
        System.out.println(pw2.marry(pm2));
        outer(pm2.getName() + " on " + pm2.getNameSpouse());
        outer(pw1.getName() + " on " + pw1.getNameSpouse());
        outer(pm1.getName() + " on " + pm1.getNameSpouse());
        outer(pw2.getName() + " on " + pw2.getNameSpouse());
    }
}