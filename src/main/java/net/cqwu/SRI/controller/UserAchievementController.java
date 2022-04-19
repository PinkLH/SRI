package net.cqwu.SRI.controller;

import java.io.*;
import java.net.URLEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.cqwu.SRI.util.ExcelExport;
import net.cqwu.SRI.util.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.cqwu.SRI.util.UpfilePath;
import net.cqwu.SRI.entity.Achievement;
import net.cqwu.SRI.entity.Users;
import net.cqwu.SRI.service.UserAchievementService;

@Controller
public class UserAchievementController {
    /**
     * 格式化日期对象
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 业务层接口
     */
    @Autowired
    private UserAchievementService userAchievementService;

    /**
     * 查询成果获奖入口
     */
    @RequestMapping("SelectAchievement")
    public String SelectAchievement(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        List<Achievement> alist;
        List<String> atime = new ArrayList<>();
        if ("admin".equals(user.getUtype())) {
            alist = userAchievementService.selectAchievement();
            for (Achievement a : alist) {
                atime.add(sdf.format(a.getAtime()));
            }
        } else {
            alist = userAchievementService.selectAchievement(user.getUid());
            for (Achievement a : alist) {
                atime.add(sdf.format(a.getAtime()));
            }
        }
        model.addAttribute("atime", atime);
        model.addAttribute("alist", alist);
        return "main" + File.separator + "achievement" + File.separator + "SelectAchievement";
    }

    /**
     * 条件查询成果获奖
     */
    @RequestMapping("SelectAchievementInfo")
    public String SelectAchievementInfo(Model model, HttpSession session, String aname, String alevel, String atype) {
        Users user = (Users) session.getAttribute("user");
        List<Achievement> alist;
        List<String> atime = new ArrayList<>();
        Achievement achievement = new Achievement();
        achievement.setAname(aname);
        achievement.setAlevel(alevel);
        achievement.setAtype(atype);
        achievement.setUid(user.getUid());
        alist = userAchievementService.selectAchievement(achievement, user.getUtype());
        for (Achievement a : alist) {
            atime.add(sdf.format(a.getAtime()));
        }
        model.addAttribute("atime", atime);
        model.addAttribute("alist", alist);
        model.addAttribute("aname", aname);
        model.addAttribute("alevel", alevel);
        model.addAttribute("atype", atype);

        return "main" + File.separator + "achievement" + File.separator + "SelectAchievement";
    }

    /**
     * 增加成果获奖入口
     */
    @RequestMapping("AddAchievement")
    public String AddAchievement() {
        return "main" + File.separator + "achievement" + File.separator + "AddAchievement";
    }

    /**
     * 增加成果获奖
     */
    @RequestMapping(value = "AddAchievementInfo", method = RequestMethod.POST)
    public void AddAchievementInfo(Achievement achievement, HttpServletResponse response, HttpSession session, HttpServletRequest request, MultipartFile uploadFile,
                                   String year, String month, String day) throws IllegalStateException, IOException, ParseException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String uid = ((Users) session.getAttribute("user")).getUid();
        String atime = year + "-" + month + "-" + day;
        String syspath = UpfilePath.getSRIPath(request);
        String path = uid + File.separator + "成果获奖" + File.separator + achievement.getAlevel() + File.separator + achievement.getAtype() + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + uid;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + File.separator + "成果获奖" + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + achievement.getAlevel();
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + File.separator + achievement.getAtype() + File.separator;
        String filename = uploadFile.getOriginalFilename();
        String fname = filename.substring(0, filename.lastIndexOf("."));
        String fsuffix = filename.substring(filename.lastIndexOf("."));
        filename = fname + "_" + UUID.randomUUID() + fsuffix;
        File filepath = new File(syspath);
        File file = new File(syspath + filename);
        if (filepath.exists()) {
            uploadFile.transferTo(file);
        } else {
            filepath.mkdir();
            uploadFile.transferTo(file);
        }
        achievement.setAtime(sdf.parse(atime));
        achievement.setAaddress(path + filename);
        achievement.setUid(uid);
        if (userAchievementService.AddAchievement(achievement)) {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"添加成功！\");"
                    + "window.location.href = \"SelectAchievement\""
                    + "</script>");
        } else {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"添加失败！\");"
                    + "window.location.href = \"AddAchievement\""
                    + "</script>");
        }

        out.close();
//		return "redirect:SelectAchievement";
    }

    /**
     * 修改成果获奖入口
     */
    @RequestMapping("UpdateAchievement")
    public String UpdateAchievement(Model model, @RequestParam("aid") int aid) {
        Achievement achievement = userAchievementService.selectAchievement(aid);
//		achievement.setAaddress(achievement.getAaddress().replaceAll("\\\\", "\\\\\\\\"));
        Calendar cal = Calendar.getInstance();
        cal.setTime(achievement.getAtime());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        model.addAttribute("achievement", achievement);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        return "main" + File.separator + "achievement" + File.separator + "UpdateAchievement";
    }

    /**
     * 修改成果获奖
     */
    @RequestMapping(value = "UpdateAchievementInfo", method = RequestMethod.POST)
    public void UpdateAchievementInfo(Achievement achievement, HttpServletResponse response, HttpServletRequest request, MultipartFile uploadFile,
                                      String year, String month, String day) throws IllegalStateException, IOException, ParseException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String atime = year + "-" + month + "-" + day;
        String syspath = UpfilePath.getSRIPath(request);
        String path = achievement.getUid() + File.separator + "成果获奖" + File.separator + achievement.getAlevel() + File.separator + achievement.getAtype() + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        String path1 = syspath + achievement.getUid();
        if (!new File(path1).exists()) {
            new File(path1).mkdir();
        }
        path1 = path1 + File.separator + "成果获奖" + File.separator;
        if (!new File(path1).exists()) {
            new File(path1).mkdir();
        }
        path1 = path1 + achievement.getAlevel();
        if (!new File(path1).exists()) {
            new File(path1).mkdir();
        }
        path1 = path1 + File.separator + achievement.getAtype() + File.separator;
        String filename = uploadFile.getOriginalFilename();
        String fname = filename.substring(0, filename.lastIndexOf("."));
        String fsuffix = filename.substring(filename.lastIndexOf("."));
        filename = fname + "_" + UUID.randomUUID() + fsuffix;
        File filepath = new File(path1);
        File file = new File(path1 + filename);
        File oldfile = new File(syspath + achievement.getAaddress());
        if (filepath.exists()) {
            uploadFile.transferTo(file);
        } else {
            filepath.mkdir();
            uploadFile.transferTo(file);
        }
        achievement.setAtime(sdf.parse(atime));
        achievement.setAaddress(path + filename);
        if (oldfile.exists()) {
            if (oldfile.delete()) {
                if (userAchievementService.UpdateAchievement(achievement)) {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改成功！\");"
                            + "window.location.href = \"SelectAchievement\""
                            + "</script>");
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改失败！\");"
                            + "window.location.href = \"SelectAchievement\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改失败！\");"
                        + "window.location.href = \"SelectAchievement\""
                        + "</script>");
            }
        } else {
            if (userAchievementService.UpdateAchievement(achievement)) {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改成功！\");"
                        + "window.location.href = \"SelectAchievement\""
                        + "</script>");
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改失败！\");"
                        + "window.location.href = \"SelectAchievement\""
                        + "</script>");
            }
        }

        out.close();
//		return "redirect:SelectAchievement";
    }

    /**
     * 删除成果获奖
     */
    @RequestMapping("DeleteAchievement")
    public void DeleteAchievement(HttpServletRequest request, HttpServletResponse response, @RequestParam("aid") int aid) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String syspath = UpfilePath.getSRIPath(request);
        File file = new File(syspath + userAchievementService.selectAchievement(aid).getAaddress());

        if (userAchievementService.DeleteAchievement(aid)) {
            if (file.exists()) {
                if (file.delete()) {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"删除成功！\");"
                            + "window.location.href = \"SelectAchievement\""
                            + "</script>");
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"删除失败！\");"
                            + "window.location.href = \"SelectAchievement\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"删除成功！\");"
                        + "window.location.href = \"SelectAchievement\""
                        + "</script>");
            }
        } else {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"删除失败！\");"
                    + "window.location.href = \"SelectAchievement\""
                    + "</script>");
        }
        out.close();
    }

    /**
     * 下载某一个成果获奖
     */
    @RequestMapping("DownloadAchievementByAid")
    public void DownlodAchievementByAid(@RequestParam("aid") int aid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String syspath = UpfilePath.getSRIPath(request);
        String path = syspath + userAchievementService.selectAchievement(aid).getAaddress();
        String filenameUUID = path.substring(path.lastIndexOf(File.separator) + 1);
        String filename = filenameUUID.substring(0, filenameUUID.lastIndexOf("_")) + filenameUUID.substring(filenameUUID.lastIndexOf("."));
//        System.out.println(path);
        //得到要下载的文件
        File file = new File(path);
        if (!file.exists()) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print("<html>" +
                    "<body>" +
                    "<script type='text/javascript'>" +
                    "alert('您要下载的资源已被删除！');" +
                    "window.location.href = \"SelectAchievement\"" +
                    "</script>" +
                    "</body>" +
                    "</html>");
            response.getWriter().close();
//            System.out.println("您要下载的资源已被删除！！");
            return;
        }
        //转码，避免文件名中文乱码
        filename = URLEncoder.encode(filename, "UTF-8");
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        //设置文件ContentType类型，自动判断下载文件类型
        response.setContentType("multipart/form-data");
        // 读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(path);
        // 创建输出流
        OutputStream out = response.getOutputStream();
        // 创建缓冲区
        byte[] buffer = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        // 关闭输出流
        out.close();
    }

    /**
     * 下载所有的成果获奖
     */
    @RequestMapping("DownloadAchievement")
    public void DownloadAchievement(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String syspath = UpfilePath.getSRIPath(request);
        Users user = (Users) session.getAttribute("user");
        String utype = user.getUtype();
        String uid = user.getUid();
        String userPath = syspath + uid + File.separator + "成果获奖" + File.separator;
        //设置文件下载头
        if ("user".equals(utype)) {
            response.addHeader("Content-Disposition", "attachment;filename=" + uid + "_Achievement.zip");
        } else {
            response.addHeader("Content-Disposition", "attachment;filename=Achievement.zip");
        }
        //设置文件ContentType类型，自动判断下载文件类型
        response.setContentType("multipart/form-data");
        OutputStream out = response.getOutputStream();
        ZipUtil zipUtil = new ZipUtil();
        if ("user".equals(utype)) {
            zipUtil.toZip(userPath, out, true);
        } else {
            List<Achievement> achievementList = userAchievementService.selectAchievement();
            List<File> afileList = new ArrayList<>();
            for (Achievement a : achievementList) {
                afileList.add(new File(syspath + a.getAaddress()));
            }
            zipUtil.toZip(afileList, out);
        }
        out.close();
    }

    /**
     * 导出成果获奖的excel
     */
    @RequestMapping("ExportAchievementExcel")
    public void ExportAchievementExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Users user = (Users) session.getAttribute("user");

        ExcelExport<Achievement> excel = new ExcelExport<Achievement>();
        //表格的头部信息
        String[] headers = {"ID", "成果名称", "授予单位", "获得时间", "级别", "类型"};
        //从数据库查到的数据
        List<Achievement> list;
        if ("admin".equals(user.getUtype())) {
            list = userAchievementService.selectExcelAchievement();
            String mimeType = request.getServletContext().getMimeType("成果获奖.xls");
            excel.exportExcel("成果获奖.xls", headers, list, response, request, mimeType);
        } else {
            list = userAchievementService.selectExcelAchievement(user.getUid());
            String mimeType = request.getServletContext().getMimeType("成果获奖.xls");
            excel.exportExcel(user.getUid() + "_成果获奖.xls", headers, list, response, request, mimeType);
        }


    }

}
