package com.wenjun.oa.service.impl;

import com.wenjun.oa.base.BaseDaoImpl;
import com.wenjun.oa.bean.Department;
import com.wenjun.oa.bean.LeaveBean;
import com.wenjun.oa.bean.User;
import com.wenjun.oa.service.WorkflowService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by wangli0 on 2017/4/14.
 * github https://github.com/wangli0
 * blog http://www.jianshu.com/u/79a88a044955
 * website: http://need88.com
 */
@Service
@Transactional
public class WorkflowServiceImpl extends BaseDaoImpl<LeaveBean> implements WorkflowService {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Class getEntityClass() {
        return LeaveBean.class;
    }


    @Override
    public void submit(LeaveBean leave, Department depart) {
        leave.setStatus(LeaveBean.STATUS_NOMAL);
        leave.setUpdateTime(new Date());
        update(leave);

    }


    @Override
    public List<LeaveBean> getLeaveListByUser(Long id) {
        return getCurrentSession()
                .createQuery("FROM LeaveBean lea WHERE lea.userId = ?")
                .setParameter(0,id)
                .list();

    }

    //获取审批人(同部门 且不包含自己 )
    @Override
    public List<User> getApproversByDepart(User user, Department department) {
        List<User> users = getCurrentSession().createQuery("FROM User u WHERE u.department = ? AND u!= ?")
                .setParameter(0, department)
                .setParameter(1, user)
                .list();

        return users;
    }


    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }



}
