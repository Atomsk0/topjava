package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.UserMealRepositoryImplement;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Максим on 08.03.2016.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    private UserMealRepository repository = new UserMealRepositoryImplement();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");


        if (action==null) {
            LOG.info("get all meals");
            request.setAttribute("mealList", UserMealsUtil.getFilteredMealsWithExceeded(UserMealsUtil.MEAL_LIST,
                    LocalTime.of(00, 0), LocalTime.of(23, 0), 2000));
            request.getRequestDispatcher("/mealList.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int id = getId(request);
            repository.delete(id);
            LOG.info("Delete {}", id);
            response.sendRedirect("mealList.jsp");
        } else {
            final UserMeal meal = action.equals("create") ? new UserMeal(LocalDateTime.now(), "", 1000) :
                    repository.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/mealEdit.jsp").forward(request, response);
        }



//        response.sendRedirect("mealList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        UserMeal meal = new UserMeal(id.isEmpty()? null : Integer.valueOf(id), LocalDateTime
                .parse(req.getParameter("dateTime")), req.getParameter("description"), Integer
                .valueOf(req.getParameter("calories")));
        repository.save(meal);
        resp.sendRedirect("meals");
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);

    }
}
