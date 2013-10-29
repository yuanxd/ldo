/**
 * 请假流程任务办理
 */
$(document).ready(function() {
	$("#apply").click(handle);
});

/**
 * 办理流程
 */
function handle() {
	alert(1);
	// 发送任务完成请求
    $.post(ctx + '/leave/apply', {
    	form: '{variables:[{"key":"123","value":"456"},{"key":"1230","value":"4560"}]}'
    }, function(resp) {
		$.unblockUI();
        if (resp == 'success') {
            alert('任务完成');
            location.reload();
        } else {
            alert('操作失败!');
        }
    });
}