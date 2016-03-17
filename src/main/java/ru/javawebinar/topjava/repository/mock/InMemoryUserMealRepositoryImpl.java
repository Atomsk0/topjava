package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.09.2015.
 */

@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserMealRepositoryImpl.class);

    private Map<Integer, Map<Integer, UserMeal>> repository = new ConcurrentHashMap<>();
    private Map<Integer, UserMeal> userMealMap = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    private boolean isMealBelongToUser(int userId, int userMealId) {
        if (repository.size() == 0) return true;
        for (Map.Entry<Integer, UserMeal> entry : repository.get(userId).entrySet()) {
            if (entry.getKey() == userMealId) {
                return true;
            }
        }
        return false;
    }

    {
        UserMealsUtil.MEAL_LIST.forEach((UserMeal userMeal) -> save(1, userMeal));
    }

    @Override
    public UserMeal save(Integer userId, UserMeal userMeal) {
        LOG.info("save in repository: " + userId + " " + userMeal.toString());

        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
            userMealMap.put(userMeal.getId(), userMeal);


        } else if (isMealBelongToUser(userId, userMeal.getId())){

            userMealMap.put(userMeal.getId(), userMeal);
            LOG.info("put in usermealmap, meal id is " + userMeal.getId());

        }
        repository.put(userId, userMealMap);

        LOG.info("repo size is " + repository.size());
        LOG.info("usermealmap size is " + userMealMap.size());

        return isMealBelongToUser(userId, userMeal.getId()) ? userMeal : null;
    }

    @Override
    public boolean delete(int userId, int userMealId) {
        return isMealBelongToUser(userId, userMealId) ? repository.get(userId).remove(userMealId) != null : false;


    }

    @Override
    public UserMeal get(int userId, int userMealId) {
        return isMealBelongToUser(userId, userMealId) ? repository.get(userId).get(userMealId) : null;
    }

    @Override
    public Collection<UserMeal> getAll(int userId) {
        List<UserMeal> mealList = new ArrayList<>(repository.get(userId).values());
        Collections.sort(mealList, (o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()));

        return mealList;
    }
}

