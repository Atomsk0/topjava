package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    //null if not from this user
    UserMeal save(Integer userId, UserMeal userMeal);

    //false if not found or not from this user
    boolean delete(int userId, int userMealId);

    //null if not found or not from this user
    UserMeal get(int userId, int userMealId);

    Collection<UserMeal> getAll(int userId);
}
