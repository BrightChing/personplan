package io.github.brightqin.personplan.dao;

import io.github.brightqin.personplan.entity.Plan;
import io.github.brightqin.personplan.entity.Step;
import io.github.brightqin.personplan.util.HibernateUtils;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brightqin
 * @date 2018/6/18
 */
public class StepDAOImpl implements StepDAO {
    @Override
    public void addStep(Step step) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            session.save(step);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Step> loadSteps(Plan plan) {
        List<Step> steps = null;
        try (Session session = HibernateUtils.getSession()) {
            List list = session.createQuery("from Step where plan.planId = ?").setParameter(0, 1L).getResultList();
            steps = new ArrayList<>();
            for (Object s : list) {
                steps.add((Step) s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return steps;
    }

    @Override
    public void deleteStep(Step step) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            session.delete(step);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStep(Step step) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            session.update(step);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
