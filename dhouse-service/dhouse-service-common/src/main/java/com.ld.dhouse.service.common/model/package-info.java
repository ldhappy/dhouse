package com.ld.dhouse.service.common.model;
/**
 * 此包主要用于封装数据模型
 * DO(Data Object):与数据库表结构一一对应，通过DAO层向上传输数据对象(do不能为包名，包名为data)
 * DTO(Data Transfer Object):数据传输对象，Service和Manager向外传输的对象
 * BO(Business Object):业务对象。可以有Service层输出的封装业务逻辑的对象
 * Query:数据查询对象，各层接收上层的查询请求。注：超过2个参数的查询封装，禁止使用map类来传输
 * VO(View Object):显示层对象，通常是Web向模板渲染引擎层传输的对象
 * 梁聃 2017/12/21 9:36
 */