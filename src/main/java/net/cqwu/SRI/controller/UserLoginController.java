package net.cqwu.SRI.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.cqwu.SRI.util.UpfilePath;
import net.cqwu.SRI.util.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

import net.cqwu.SRI.entity.Users;
import net.cqwu.SRI.service.UserLoginService;

@Controller
public class UserLoginController {
	
	/**
	 * 业务层接口
	 */
	@Autowired
	private UserLoginService userLoginService;
	
	/**
	 * 登录入口
	 */
	@RequestMapping("login")
	public String login() {
		return "Login";
	}
	
	/**
	 * 注销
	 */
	@RequestMapping("loginout")
	public String login(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
	
	/**
	 * ajax判断用户登录
	 */
	@RequestMapping(value = "loginformAjax", method = RequestMethod.POST)
	public void loginformAjax(HttpSession session, String number, String upwd, String utype, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject jsonObject=new JSONObject();
		number = number.trim();
		Users user = userLoginService.login(number, upwd, utype);
		if(user == null) {
			jsonObject.put("info", "loginError");
			jsonObject.put("msg", "登录信息有误！");
		}else {
			session.setAttribute("user", user);
		}
		out.println(jsonObject);
		out.close();
	}
	
	/**
	 * 登录
	 */
	@RequestMapping("loginform")
	public String loginform(HttpSession session) {
		if (session.getAttribute("user") == null){
			return "redirect:login";
		}else {
			return "Index";
		}
	}
	
	/**
	 * 修改用户信息入口
	 */
	@RequestMapping("UpdateUserInfo")
	public String UpdateUserInfo() {
		return "main" + File.separator + "index" + File.separator + "UpdateUserInfo";
	}
	
	/**
	 * 修改用户信息
	 */
	@RequestMapping("UpdateUserInfoForm")
	public void UpdateUserInfoForm(HttpServletResponse response, String uid, String utel, String upwd) throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Users user = new Users();
		user.setUid(uid);
		user.setUtel(utel);
		user.setUpwd(upwd);
		if(userLoginService.updateUserInfo(user)) {
			out.println("<script type=\"text/javascript\">\r\n"
					+ "alert(\"修改成功！\");"
					+ "parent.location.reload();\r\n"
					+ "</script>");
		}else {
			out.println("<script type=\"text/javascript\">\r\n"
					+ "alert(\"修改失败！\");"
					+ "window.location.href = \"UpdateUserInfo\""
					+ "</script>");
		}
		out.close();
	}
	
	/**
	 * 主页图表信息
	 */
	@RequestMapping("UpdateIndex")
	public String UpdateIndex(Model model ,HttpSession session) {
		Calendar cal = Calendar.getInstance();
		Users user = (Users)session.getAttribute("user");
		String uid = user.getUid();
		String utype = user.getUtype();
		int[] num = userLoginService.selectAchievementCount(uid, utype, cal.get(Calendar.YEAR));
		List<Integer> intNum = Arrays.stream(num).boxed().collect(Collectors.toList());
		model.addAttribute("achievementNum", intNum);
		
		num = userLoginService.selectLxCount(uid, utype, cal.get(Calendar.YEAR));
		intNum = Arrays.stream(num).boxed().collect(Collectors.toList());
		model.addAttribute("lxNum", intNum);
		
		num = userLoginService.selectHxCount(uid, utype, cal.get(Calendar.YEAR));
		intNum = Arrays.stream(num).boxed().collect(Collectors.toList());
		model.addAttribute("hxNum", intNum);
		
		num = userLoginService.selectPatentCount(uid, utype, cal.get(Calendar.YEAR));
		intNum = Arrays.stream(num).boxed().collect(Collectors.toList());
		model.addAttribute("patentNum", intNum);
		
		num = userLoginService.selectThesisCount(uid, utype, cal.get(Calendar.YEAR));
		intNum = Arrays.stream(num).boxed().collect(Collectors.toList());
		model.addAttribute("thesisNum", intNum);
		
		num = userLoginService.selectWorkCount(uid, utype, cal.get(Calendar.YEAR));
		intNum = Arrays.stream(num).boxed().collect(Collectors.toList());
		model.addAttribute("workNum", intNum);
		
		return "main" + File.separator + "index" + File.separator + "UpdateIndex";
	}

	/**
	 * 下载所有的信息
	 */
	@RequestMapping("DownloadAllInfo")
	public void DownloadAllInfo(HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {
		String syspath = UpfilePath.getSRIPath(request);
		Users user = (Users)session.getAttribute("user");
		String uid = user.getUid();
		String utype = user.getUtype();
		String userPath = syspath + uid + "\\";
		//设置文件下载头
		if("user".equals(utype)){
			response.addHeader("Content-Disposition", "attachment;filename="+uid+"_SRI.zip");
		}else {
			response.addHeader("Content-Disposition", "attachment;filename=SRI.zip");
		}
		//设置文件ContentType类型，自动判断下载文件类型
		response.setContentType("multipart/form-data");
		OutputStream out = response.getOutputStream();
		ZipUtil zipUtil = new ZipUtil();
		if ("user".equals(utype)){
			zipUtil.toZip(userPath, out, true);
		} else {
			zipUtil.toZip(syspath, out, true);
		}
		out.close();
	}
}
