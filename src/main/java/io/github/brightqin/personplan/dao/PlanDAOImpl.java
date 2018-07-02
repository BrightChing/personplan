package io.github.brightqin.personplan.dao;

import io.github.brightqin.personplan.entity.Plan;
import io.github.brightqin.personplan.util.HibernateUtils;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * 计划的数据库操作
 *
 * @author brightqin
 * @date 2018/6/13
 */
public class PlanDAOImpl implements PlanDAO {
    @Override
    public void savePlan(Plan plan) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            session.save(plan);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Plan> listAllByUserId(String userId) {
        List<Plan> planList = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        List list = session.createQuery("from Plan where user.userId = :userId").setParameter("userId", userId).getResultList();
        for (Object o : list) {
            planList.add((Plan) o);
        }
        return planList;
    }

    @Override
    public void deletePlan(Plan plan) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            session.delete(plan);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePlan(Plan plan) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            session.update(plan);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
