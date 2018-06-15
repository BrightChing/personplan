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
public class PlanDAO {

    public void savePlan(Plan plan){
        Session session = HibernateUtils.getSession();
        session.save(plan);
    }

    public List<Plan> listAll(String userId){
        List<Plan> planList =new ArrayList<>();
        Session session = HibernateUtils.getSession();
     List list =  session.createQuery("from Plan where userId = ?").setParameter(0, userId).getResultList();
        for (Object o:list) {
            planList.add((Plan) o);
        }
      return  planList;
    }

    public void deletePlan(Plan plan){
        Session session = HibernateUtils.getSession();
        session.delete(plan);
    }

    public void updatePlan(Plan plan){
        Session session = HibernateUtils.getSession();
        session.update(plan);
    }
}
