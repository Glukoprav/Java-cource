package local.home.azav.java.hw25_spring_hibernate.dao;

import local.home.azav.java.hw25_spring_hibernate.model.Recipe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        List<Recipe> personList = session.createQuery("from RECIPES").list();
        session.close();
        return personList;
    }

    /**
     * Взять рецепт по иденту блюда
     */
    @Override
    public List<Recipe> getById(int dishesid) {
        Session session = this.sessionFactory.openSession();
        List<Recipe> personList = session.createQuery("Select r from recipes r where dishesid= :dishesid", Recipe.class).setParameter("dishesid", dishesid);
                //session.createSQLQuery("Select * from recipes where dishesid=" + dishesid).list();
        session.close();
        return personList;
    }

    /**
     * Добавление ингредиента к блюду по иденту
     */
    @Override
    public int insertIngredient(int intId, String newName, int intValue) {
        Recipe recipe = new Recipe(intId,newName,intValue);
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(recipe);
        tx.commit();
        session.close();
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.update("INSERT INTO recipes (dishesid, ingredient, value) VALUES(?,?,?)", intId, newName, intValue);
    }

    /**
     * Удаление всех ингредиентов у блюда по иденту
     */
    @Override
    public int deleteRecipe(int intId) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.update("delete from recipes where dishesid = ?", intId);
    }

    private class RecipeRowMapper implements RowMapper<Recipe> {
        public Recipe mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Recipe(resultSet.getInt("dishesid"),
                    resultSet.getString("ingredient"),
                    resultSet.getInt("value"));
        }
    }
}
