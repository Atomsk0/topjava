package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */

@Service
public interface UserMealService {

    public UserMeal save(int userId, UserMeal userMeal);

    public void delete(int userId, int mealId);

    public UserMeal get(int userId, int meaId);

    public List<UserMeal> getAll(int userId);

    public void update(int userId, UserMeal userMeal);

}
