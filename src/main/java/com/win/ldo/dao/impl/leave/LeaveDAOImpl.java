package com.win.ldo.dao.impl.leave;

import org.springframework.stereotype.Component;

import com.win.ldo.dao.impl.BaseDAOImpl;
import com.win.ldo.dao.leave.LeaveDAO;
import com.win.ldo.entity.Leave;

@Component
public class LeaveDAOImpl extends BaseDAOImpl<Leave, String> implements
		LeaveDAO {
}
