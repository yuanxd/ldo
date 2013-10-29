package com.win.ldo.dao.impl.index;

import org.springframework.stereotype.Component;

import com.win.ldo.dao.impl.BaseDAOImpl;
import com.win.ldo.dao.index.TestDAO;
import com.win.ldo.entity.Leave;

@Component
public class TestDAOImpl extends BaseDAOImpl<Leave, String> implements TestDAO {

}
