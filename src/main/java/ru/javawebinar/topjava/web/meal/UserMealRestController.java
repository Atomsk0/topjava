package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserMealServiceImpl;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */

@Controller
public class UserMealRestController {
    private static final Logger LOG = LoggerFactory.getLogger(UserMealServiceImpl.class);
    private UserMealService service;

    public List<UserMeal> getAll() {
        return service.getAll(LoggedUser.id());
    }

    public UserMeal get(int mealId) {
        return service.get(LoggedUser.id(), mealId);
    }

    public List<UserMealWithExceed> get(LocalDateTime startDate, LocalDateTime startTime, LocalDateTime endDate, LocalDateTime endTime) {
        return UserMealsUtil.getFilteredWithExceeded(this.getAll(), startTime.toLocalTime(), endTime.toLocalTime(), LoggedUser.getCaloriesPerDay());
    }

    public UserMeal create(UserMeal userMeal) {
        return service.save(LoggedUser.id(), userMeal);
    }

    public void delete(int userId, int mealId) {
        service.delete(userId, mealId);
    }

    public void update(int userId, UserMeal userMeal) {
        LOG.info(" in controller User id is" + userId);
        service.update(userId, userMeal);
    }

}
