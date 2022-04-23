package net.cqwu.SRI.controller;

import net.cqwu.SRI.entity.Pp;
import net.cqwu.SRI.entity.Patent;
import net.cqwu.SRI.entity.Users;
import net.cqwu.SRI.service.UserPatentService;
import net.cqwu.SRI.util.ExcelExport;
import net.cqwu.SRI.util.UpfilePath;
import net.cqwu.SRI.util.ZipUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Controller
public class UserPatentController {

    /**
     * 格式化日期对象
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 业务层接口
     */
    @Autowired
    private UserPatentService userPatentService;

    /**
     * 查询专利入口
     */
    @RequestMapping("SelectPatent")
    public String SelectPatent(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        List<Patent> plist = null;
        List<String> ptime = new ArrayList<>();
        if ("admin".equals(user.getUtype())) {
            plist = userPatentService.selectPatent();
            for (Patent p : plist) {
                ptime.add(sdf.format(p.getPtime()));
            }
        } else {
            plist = userPatentService.selectPatent(user.getUid());
            for (Patent p : plist) {
                ptime.add(sdf.format(p.getPtime()));
            }
        }
        model.addAttribute("plist", plist);
        model.addAttribute("ptime", ptime);
        return "main" + File.separator + "patent" + File.separator + "SelectPatent";
    }

    /**
     * 条件查询专利
     */
    @RequestMapping("SelectPatentInfo")
    public String SelectPatentInfo(Model model, HttpSession session, String pid, String pname) {
        Users user = (Users) session.getAttribute("user");
        List<Patent> plist;
        List<String> ptime = new ArrayList<>();
        Patent patent = new Patent();
        patent.setPid(pid);
        patent.setPname(pname);
        patent.setUid(user.getUid());
        plist = userPatentService.selectPatent(patent, user.getUtype());
        for (Patent p : plist) {
            ptime.add(sdf.format(p.getPtime()));
        }
        model.addAttribute("plist", plist);
        model.addAttribute("ptime", ptime);
        model.addAttribute("pid", pid);
        model.addAttribute("pname", pname);
        return "main" + File.separator + "patent" + File.separator + "SelectPatent";
    }

    /**
     * 增加专利入口
     */
    @RequestMapping("AddPatent")
    public String AddPatent() {
        return "main" + File.separator + "patent" + File.separator + "AddPatent";
    }

    /**
     * 添加专利
     */
    @RequestMapping(value = "AddPatentInfo", method = RequestMethod.POST)
    public void AddPatentInfo(Patent patent, HttpServletResponse response, HttpSession session, HttpServletRequest request, MultipartFile uploadFile,
                              String[] ppid, String[] ppname, String year, String month, String day) throws IOException, ParseException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String uid = ((Users) session.getAttribute("user")).getUid();
        String ptime = year + "-" + month + "-" + day;
        String syspath = UpfilePath.getSRIPath(request);
        String path = uid + File.separator + "专利" + File.separator + patent.getPstate() + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + uid;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + File.separator + "专利" + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + patent.getPstate() + File.separator;
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
        patent.setPtime(sdf.parse(ptime));
        patent.setPaddress(path + filename);
        patent.setUid(uid);
        List<Pp> pps = new ArrayList<>();
        for (int i = 0; i < ppid.length; i++) {
            pps.add(new Pp(ppid[i], ppname[i], patent.getPid()));
        }
        if (userPatentService.AddPatent(patent)) {
            if (userPatentService.AddPp(pps)) {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"添加成功！\");"
                        + "window.location.href = \"SelectPatent\""
                        + "</script>");
            } else {
                userPatentService.DeletePatent(patent.getPid());
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"添加失败！\");"
                        + "window.location.href = \"AddPatent\""
                        + "</script>");
            }
        } else {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"添加失败！\");"
                    + "window.location.href = \"AddPatent\""
                    + "</script>");
        }
        out.close();
    }

    /**
     * 删除专利
     */
    @RequestMapping("DeletePatent")
    public void DeletePatent(HttpServletRequest request, HttpServletResponse response, @RequestParam("pid") String pid) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String syspath = UpfilePath.getSRIPath(request);
        File file = new File(syspath + userPatentService.selectPatentByPid(pid).getPaddress());
        if (userPatentService.DeletePp(pid)) {
            if (userPatentService.DeletePatent(pid)) {
                if (file.exists()) {
                    if (file.delete()) {
                        out.println("<script type=\"text/javascript\">\r\n"
                                + "alert(\"删除成功！\");"
                                + "window.location.href = \"SelectPatent\""
                                + "</script>");
                    } else {
                        out.println("<script type=\"text/javascript\">\r\n"
                                + "alert(\"删除失败！\");"
                                + "window.location.href = \"SelectPatent\""
                                + "</script>");
                    }
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"删除成功！\");"
                            + "window.location.href = \"SelectPatent\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"删除失败！\");"
                        + "window.location.href = \"SelectPatent\""
                        + "</script>");
            }
        } else {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"删除失败！\");"
                    + "window.location.href = \"SelectPatent\""
                    + "</script>");
        }
        out.close();
    }

    /**
     * 修改专利入口
     */
    @RequestMapping("UpdatePatent")
    public String UpdatePatent(Model model, @RequestParam("pid") String pid) {
        Patent patent = userPatentService.selectPatentByPid(pid);
        List<Pp> pps = userPatentService.selectPp(pid);
        Calendar cal = Calendar.getInstance();
        cal.setTime(patent.getPtime());
        int pyear = cal.get(Calendar.YEAR);
        int pmonth = cal.get(Calendar.MONTH) + 1;
        int pday = cal.get(Calendar.DATE);
        model.addAttribute("patent", patent);
        model.addAttribute("pps", pps);
        model.addAttribute("pyear", pyear);
        model.addAttribute("pmonth", pmonth);
        model.addAttribute("pday", pday);
        return "main" + File.separator + "patent" + File.separator + "UpdatePatent";
    }

    /**
     * 修改专利
     */
    @RequestMapping(value = "UpdatePatentInfo", method = RequestMethod.POST)
    public void UpdatePatentInfo(Patent patent, HttpServletResponse response, HttpServletRequest request, MultipartFile uploadFile,
                                 String oldPid, String[] ppid, String[] ppname, String year, String month, String day) throws IOException, ParseException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String ptime = year + "-" + month + "-" + day;
        String syspath = UpfilePath.getSRIPath(request);
        String path = patent.getUid() + File.separator + "专利" + File.separator + patent.getPstate() + File.separator;
        String path1 = syspath + patent.getUid();
        if (!new File(path1).exists()) {
            new File(path1).mkdir();
        }
        path1 = path1 + File.separator + "专利" + File.separator;
        if (!new File(path1).exists()) {
            new File(path1).mkdir();
        }
        path1 = path1 + patent.getPstate() + File.separator;
        String filename = uploadFile.getOriginalFilename();
        String fname = filename.substring(0, filename.lastIndexOf("."));
        String fsuffix = filename.substring(filename.lastIndexOf("."));
        filename = fname + "_" + UUID.randomUUID() + fsuffix;
        File filepath = new File(path1);
        File file = new File(path1 + filename);
        File oldfile = new File(syspath + patent.getPaddress());
        if (filepath.exists()) {
            uploadFile.transferTo(file);
        } else {
            filepath.mkdir();
            uploadFile.transferTo(file);
        }
        patent.setPtime(sdf.parse(ptime));
        patent.setPaddress(path + filename);
        List<Pp> pps = new ArrayList<>();
        for (int i = 0; i < ppid.length; i++) {
            pps.add(new Pp(ppid[i], ppname[i], patent.getPid()));
        }
        if (oldfile.exists()) {
            if (oldfile.delete()) {
                if (userPatentService.UpdatePp(pps, oldPid)) {
                    if (userPatentService.UpdatePatent(patent, oldPid)) {
                        out.println("<script type=\"text/javascript\">\r\n"
                                + "alert(\"修改成功！\");"
                                + "window.location.href = \"SelectPatent\""
                                + "</script>");
                    } else {
                        out.println("<script type=\"text/javascript\">\r\n"
                                + "alert(\"修改失败！\");"
                                + "window.location.href = \"SelectPatent\""
                                + "</script>");
                    }
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改失败！\");"
                            + "window.location.href = \"SelectPatent\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改失败！\");"
                        + "window.location.href = \"SelectPatent\""
                        + "</script>");
            }
        } else {
            if (userPatentService.UpdatePp(pps, oldPid)) {
                if (userPatentService.UpdatePatent(patent, oldPid)) {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改成功！\");"
                            + "window.location.href = \"SelectPatent\""
                            + "</script>");
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改失败！\");"
                            + "window.location.href = \"SelectPatent\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改失败！\");"
                        + "window.location.href = \"SelectPatent\""
                        + "</script>");
            }
        }
        out.close();
    }

    /**
     * 下载专利
     */
    @RequestMapping("DownloadPatentByPid")
    public void DownloadPatentByPid(@RequestParam("pid") String pid, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String syspath = UpfilePath.getSRIPath(request);
        String path = syspath + userPatentService.selectPatentByPid(pid).getPaddress();
        String filenameUUID = path.substring(path.lastIndexOf(File.separator) + 1);
        String filename = filenameUUID.substring(0, filenameUUID.lastIndexOf("_")) + filenameUUID.substring(filenameUUID.lastIndexOf("."));
        //得到要下载的文件
        File file = new File(path);
        if (!file.exists()) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print("<html>" +
                    "<body>" +
                    "<script type='text/javascript'>" +
                    "alert('您要下载的资源已被删除！');" +
                    "window.location.href = \"SelectPatent\"" +
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
     * 下载所有的专利
     */
    @RequestMapping("DownloadPatent")
    public void DownloadPatent(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String syspath = UpfilePath.getSRIPath(request);
        Users user = (Users) session.getAttribute("user");
        String utype = user.getUtype();
        String uid = user.getUid();
        String userPath = syspath + uid + File.separator + "专利" + File.separator;
        //设置文件下载头
        if ("user".equals(utype)) {
            response.addHeader("Content-Disposition", "attachment;filename=" + uid + "_Patent.zip");
        } else {
            response.addHeader("Content-Disposition", "attachment;filename=Patent.zip");
        }
        //设置文件ContentType类型，自动判断下载文件类型
        response.setContentType("multipart/form-data");
        OutputStream out = response.getOutputStream();
        ZipUtil zipUtil = new ZipUtil();
        if ("user".equals(utype)) {
            zipUtil.toZip(userPath, out, true);
        } else {
            List<Patent> patentList = userPatentService.selectPatent();
            List<File> pfileList = new ArrayList<>();
            for (Patent p : patentList) {
                pfileList.add(new File(syspath + p.getPaddress()));
            }
            zipUtil.toZip(pfileList, out);
        }
        out.close();
    }


    /**
     * 导出专利的Excel
     */
    @RequestMapping("ExportPatentExcel")
    public void ExportPatentExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Users user = (Users) session.getAttribute("user");

        ExcelExport<Patent> excel = new ExcelExport<Patent>();

        String[] headers = {"专利号/申请号", "专利名称", "专利申请时间/授权时间", "专利状态", "专利权人"};

        List<Patent> list;
        if ("admin".equals(user.getUtype())) {
            list = userPatentService.selectExcelPatent();
            String mimeType = request.getServletContext().getMimeType("专利.xls");
            excel.exportExcel("专利.xls", headers, list, response, mimeType);
        } else {
            list = userPatentService.selectExcelPatent(user.getUid());
            String mimeType = request.getServletContext().getMimeType("专利.xls");
            excel.exportExcel(user.getUid() + "_专利.xls", headers, list, response, mimeType);
        }
    }

}
