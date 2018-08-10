package local.home.azav.java.hw25_spring_hibernate.dao;

import local.home.azav.java.hw25_spring_hibernate.model.Recipe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;

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
        List<Recipe> recipeListList = session.createQuery("from RECIPES").list();
        session.close();
        return recipeListList;
    }

    /**
     * Взять рецепт по иденту блюда
     */
    @Override
    public List<Recipe> getById(int dishesid) {
        Session session = this.sessionFactory.openSession();
        String str = "Select r from recipes r where dishesid=" + dishesid;
        List<Recipe> recipeListList = session.createQuery(str).list();
        session.close();
        return recipeListList;
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
        StringBuilder stringBuilder = new StringBuilder("from recipes where dishesId=").append(intId);
        Session session = this.sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(stringBuilder.toString(),Recipe.class);
        tx1.commit();
        session.close();
    }
}
