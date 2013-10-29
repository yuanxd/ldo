$(function() {
	var setting = {
		view : {
			showLine : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : onClick
		}
	};
	/** 功能菜单数据 */
	var functionTreeNodes = [ {
		id : 1,
		pId : 0,
		name : "测试",
		open : true
	}, {
		id : 2,
		pId : 1,
		name : "Jcrop",
		attributes : {
			url : ctx + "/test/jcrop"
		}
	} ];
	var systemConfigTreeNodes = [ {
		id : 1,
		pId : 0,
		name : "菜单配置",
		open : true
	} ];

	function onClick(event, treeId, treeNode, clickFlag) {
		if ($("#tabs").tabs('exists', treeNode.name)) {
			$('#tabs').tabs('select', treeNode.name);
		} else {
			$('#tabs')
					.tabs(
							'add',
							{
								title : treeNode.name,
								closable : true,
								content : '<iframe width="100%" height="100%" frameborder="0" src='
										+ treeNode.attributes.url
										+ ' style="width:100%;height:100%;"></iframe>'
							});
		}

	}
	$.fn.zTree.init($("#functionTree"), setting, functionTreeNodes);

	$.fn.zTree.init($("#systemConfigTree"), setting, systemConfigTreeNodes);
	// 在右边center区域打开菜单，新增tab
	function open(text, url, useIframe) {
		if ($("#tabs").tabs('exists', text)) {
			$('#tabs').tabs('select', text);
		} else {
			$('#tabs')
					.tabs(
							'add',
							{
								title : text,
								closable : true,
								content : '<iframe width="100%" height="100%" frameborder="0" src='
										+ url
										+ ' style="width:100%;height:100%;"></iframe>'
							});
		}
	}

	// 绑定tabs的右键菜单
	$("#tabs").tabs({
		onContextMenu : function(e, title) {
			e.preventDefault();
			$('#tabsMenu').menu('show', {
				left : e.pageX,
				top : e.pageY
			}).data("tabTitle", title);
		}
	});

	// 实例化menu的onClick事件
	$("#tabsMenu").menu({
		onClick : function(item) {
			closeTab(this, item.name);
		}
	});

	// 几个关闭事件的实现
	function closeTab(menu, type) {
		var curTabTitle = $(menu).data("tabTitle");
		var tabs = $("#tabs");

		if (type === "close") {
			tabs.tabs("close", curTabTitle);
			return;
		}

		var allTabs = tabs.tabs("tabs");
		var closeTabsTitle = [];

		$.each(allTabs, function() {
			var opt = $(this).panel("options");
			if (opt.closable && opt.title != curTabTitle && type === "Other") {
				closeTabsTitle.push(opt.title);
			} else if (opt.closable && type === "All") {
				closeTabsTitle.push(opt.title);
			}
		});

		for ( var i = 0; i < closeTabsTitle.length; i++) {
			tabs.tabs("close", closeTabsTitle[i]);
		}
	}
});