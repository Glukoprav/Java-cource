package local.home.azav.java.hw25_spring_hibernate.dao;

import local.home.azav.java.hw25_spring_hibernate.model.Recipe;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Класс работы с рецептами
 */
public class RecipesDAO implements IRecipesDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Взять все строки ингредиентов
     */
    @Override
    public List<Recipe> getAll() {
        Session session = this.sessionFactory.openSession();
        List<Recipe> recipeListList = session.createQuery("from Recipe").list();
        session.close();
        return recipeListList;
    }

    /**
     * Взять рецепт по иденту блюда
     */
    @Override
    public List<Recipe> getById(int dishesid) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("Select r from Recipe r where dishesid=?");
        query = query.setParameter(0,dishesid);
        List<Recipe> recipeList = query.list();
        session.close();
        return recipeList;
    }

    /**
     * Добавление ингредиента к блюду по иденту
     */
    @Override
    public void insertIngredient(int intId, String newName, int intValue) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(new Recipe(intId, newName, intValue));
        tx.commit();
        session.close();
    }

    /**
     * Удаление всех ингредиентов у блюда по иденту
     */
    @Override
    public void deleteRecipe(int intId) {
        StringBuilder stringBuilder = new StringBuilder("from Recipe where dishesId=").append(intId);
        Session session = this.sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(stringBuilder.toString(),Recipe.class);
        tx1.commit();
        session.close();
    }
}
