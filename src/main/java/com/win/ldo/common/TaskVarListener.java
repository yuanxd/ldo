package com.win.ldo.common;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;

/**
 * �������ü�����
 * 
 * @author Ԭ����
 * 
 */
public class TaskVarListener implements TaskListener, ExecutionListener {
	// key-��֯
	public static final String KEYORG = "org";
	// ��������
	private Expression varName = null;
	// ����ֵ
	private Expression varValue = null;

	public Expression getVarName() {
		return varName;
	}

	public Expression getVarValue() {
		return varValue;
	}

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		execution.setVariableLocal(varName.getValue(execution).toString(),
				varValue.getValue(execution).toString());
	}

	@Override
	public void notify(DelegateTask delegateTask) {
		delegateTask.setVariableLocal(
				varName.getValue(delegateTask).toString(),
				varValue.getValue(delegateTask).toString());
	}

	public void setVarName(Expression varName) {
		this.varName = varName;
	}

	public void setVarValue(Expression varValue) {
		this.varValue = varValue;
	}

}
