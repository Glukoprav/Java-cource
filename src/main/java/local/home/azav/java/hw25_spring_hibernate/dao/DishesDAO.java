package local.home.azav.java.hw25_spring_hibernate.dao;

import local.home.azav.java.hw25_spring_hibernate.model.Dish;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Класс работы с наименованиями блюд
 */
public class DishesDAO implements IDishesDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Получение списка всех блюд
     */
    @Override
    public List<Dish> getAll() {
        Session session = this.sessionFactory.openSession();
        List<Dish> dishesList = session.createQuery("from Dish").list();
        session.close();
        return dishesList;
    }

    /**
     * Получение блюда по имени или части имени
     */
    @Override
    public List<Dish> getByName(String name) {
        StringBuilder stringBuilder = new StringBuilder("from Dish where name like '%").append(name).append("%'");
        Session session = this.sessionFactory.openSession();
        List<Dish> dishesList = session.createQuery(stringBuilder.toString()).list();
        session.close();
        return dishesList;
    }

    /**
     * Получение блюда по идентификатору
     */
    @Override
    public Dish getById(int dishesId) {
        try (Session session = this.sessionFactory.openSession()) {
            return session.get(Dish.class, dishesId);
        }
    }

    /**
     * Добавление нового блюда
     */
    @Override
    public void insertDish(String newName) {
        Session session = this.sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(new Dish(newName));
        tx1.commit();
        session.close();
    }

    /**
     * Удаление блюда со всеми ингредиентами
     */
    @Override
    public void deleteDish(int dishesId) {
        StringBuilder stringBuilder = new StringBuilder("from Dish where dishesId=").append(dishesId);
        Session session = this.sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(stringBuilder.toString(),Dish.class);
        tx1.commit();
        session.close();
    }
}
