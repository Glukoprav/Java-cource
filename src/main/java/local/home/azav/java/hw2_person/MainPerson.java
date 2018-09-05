package local.home.azav.java.hw2_person;

// Для теста Person
// Проверяем методы женитьбы (marry) и развода (divorce)

public class MainPerson {
    static void outer(String str) {
        System.out.println(str);
    }

    static void outerPar(Person p1, Person p2) {
        outer(p1.getName() + " on " + p1.getNameSpouse());
        outer(p2.getName() + " on " + p2.getNameSpouse());
    }

    static Boolean marryPar(Person p1, Person p2) { return p1.marry(p2);}

    public static void main(String[] args) {
        Person pm1 = new Person(true, "Vasya");
        Person pw1 = new Person(false, "Masha");
        outer("1 step");
        outerPar(pm1,pw1);

        outer("2 step");
        marryPar(pm1,pw1);
            outerPar(pm1,pw1);

        outer("3 step");
        if (marryPar(pm1,pw1)) {
            outer("yes");
        } else {
            outer("no");
        }
        outerPar(pm1,pw1);

        outer("4 step");
        Person pm2 = new Person(true, "Fedya");
        Person pw2 = new Person(false, "Glasha");
        outer(marryPar(pm2,pm1).toString());
        outerPar(pm2,pw1);
        outerPar(pm1,pw2);

        outer("5 step");
        outer(marryPar(pm2,pw1).toString());
        outerPar(pm2,pw1);
        outerPar(pm1,pw2);

        outer("6 step");
        outer(marryPar(pw2,pm1).toString());
        outerPar(pm2,pw1);
        outerPar(pm1,pw2);

        outer("7 step");
        outer(marryPar(pw2,pm2).toString());
        outerPar(pm2,pw1);
        outerPar(pm1,pw2);
    }
}