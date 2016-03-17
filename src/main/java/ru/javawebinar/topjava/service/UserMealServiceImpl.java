package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryUserMealRepositoryImpl;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {
    private static final Logger LOG = LoggerFactory.getLogger(UserMealServiceImpl.class);

    @Autowired
    private UserMealRepository repository;


    public UserMeal save(int userId, UserMeal userMeal) {

        return ExceptionUtil.check(repository.save(userId, userMeal),userId);
    }

    public void delete(int userId, int mealId) {
        ExceptionUtil.check(repository.delete(userId, mealId), mealId);
    }

    public UserMeal get(int userId, int mealId) throws NotFoundException {
        return ExceptionUtil.check(repository.get(userId, mealId), mealId);
    }



    public List<UserMeal> getAll(int userId) {
        return (List<UserMeal>) repository.getAll(userId);
    }

    public void update(int userId, UserMeal userMeal) {
        LOG.info(" in service User id is" + userId);
        ExceptionUtil.check(repository.save(userId, userMeal), LoggedUser.id());
    }

}
