package local.home.azav.java.hw25_spring_hibernate.dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecipesDAOTest {

    @Test
    public void getAll() {

    }

    @Test
    public void getById() {
    }

    @Test
    public void insertIngredient() {
    }

    @Test
    public void deleteRecipe() {
    }
}

//    @Mock
//    Service service;
//
//    @Mock
//    JdbcTemplate jdbcTemplate;
//
//
//    @Test
//    public void testGetUserNames() {
//
//        List<String> userNames = new ArrayList<String>();
//        userNames.add("bob");
//
//        when(service.getJdbcTemplate()).thenReturn(jdbcTemplate);
//        when(jdbcTemplate.query(anyString(), anyObject()).thenReturn(userNames);
//
//        String retVal = Class.getUserNames("test");
//        assertEquals("bob", retVal);
//    }