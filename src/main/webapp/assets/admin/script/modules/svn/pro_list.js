/**
 * 项目用户页面
 * @author Hpboys
 * @version 1.0.0
 */
define(function(require, exports, module){
	//引入模块
	require('$');
	var util = require('util');
	var layer = require('layer');
	var template = require('template');
	var constant = require('constant');
	var pageService = require('page_service');

	var pj = $('.select_pj').val();
	var newPsw = $('#psw');

	/*var pageBean = pageService.init({
		url: 'pjUsrList?action=data&pj='+pj,
		isPagination: false,
		after:function(data){
			_scope.view.reBindEvent();
		}
	});*/

	//页面请求域
	var _scope = {
		view:{
			init:function(){

				_scope.view.reBindEvent();

				$('#defaultPswd').on('click', function(){
					var checked = $(this).get(0).checked;
					if(checked){
						newPsw.removeAttr('required');
					}else{
						newPsw.attr('required','');
					}
				});
			},
			reBindEvent:function(){
				$('.btn-remove').on('click', function(){
					var pj = $(this).attr('data-pj');
					var des = $(this).attr('data-des');
					_scope.methods.removePjUser(pj,des);
				});
			},
			editDialog:function(){
				//用户编辑层
				layer.open({
					type: 1
					,title: '添加项目用户'
					,area: ['650px', '350px']
					,shade: [0.3, '#393D49']
					,content: $('#submitForm')
				});
			}
		},
		methods:{
			removePjUser:function(pj,des){
				util.confirm({
					msg: '您确定删除 “'+des+'('+pj+')'+'”吗?',
					confirm:function(){
						// 请求
						$.post('pjRemoveHandler', {'pj': pj}, function(data){
							if(data.status){
								//pageBean.reload();
								util.showMsg(data.info);
							}else{
								util.showMsg(data.info, 3);
							}
						});
						// 关闭弹窗
						layer.closeAll();
					}
				});
			}
		}
	};

	_scope.view.init();
	//暴露方法到Window
	window.submitForm = _scope.methods.submitForm;
});



