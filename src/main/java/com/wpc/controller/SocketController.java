/**
 * 文件名：IndexController.java
 *
 * 描述：此处填写文件的描述信息
 * 
 * 日期：2015年12月31日
 * 
 * 本系统是商用软件，未经授权擅自复制或传播本程序的部分或全部将是非法的
 *
 * Copyright(C) WEAVER Corporation 2015 
 *
 */
package com.wpc.controller;

import com.wpc.sys.model.User;
import com.wpc.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * <dl>
 * Class Description
 * <dd>项目名称：springmvc
 * <dd>类名称：IndexController
 * <dd>类描述：概述类的作用
 * <dd>创建人：王鹏程
 * <dd>创建时间：2015年12月31日 上午9:43:33
 * <dd>修改人：无
 * <dd>修改时间：无
 * <dd>修改备注：无
 * </dl>
 * 
 * @author weaver
 * @see
 * @version 1.0
 * 
 */
@Controller
@RequestMapping("/socket")
public class SocketController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "socket/hello";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login() {
		return "socket/socket";
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Long uid) {
		return "socket/talk";
	}

	@MessageMapping("/change-notice")
	@SendTo("/topic/notice")
	public String greeting(String value) {
		return value;
	}

	@MessageMapping("/user-change-notice")
	@SendToUser("/topic/notice")
	public String greeting2(String value, Principal principal) {
		return value + principal.getName();
	}

	@MessageMapping("/queryUsers")
	@SendTo("/topic/queryUsers")
	public List<User> queryUsers(String value) {
		return userService.queryAll();
	}

}
