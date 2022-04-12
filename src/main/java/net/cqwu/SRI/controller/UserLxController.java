package net.cqwu.SRI.controller;

import net.cqwu.SRI.entity.Achievement;
import net.cqwu.SRI.entity.Lp;
import net.cqwu.SRI.entity.Lx;
import net.cqwu.SRI.entity.Users;
import net.cqwu.SRI.service.UserLxService;
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
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Controller
public class UserLxController {
    /**
     * 格式化日期对象
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 业务层接口
     */
    @Autowired
    private UserLxService userLxService;

    /**
     * 查询立项入口
     */
    @RequestMapping("SelectLx")
    public String SelectLx(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        List<Lx> llist = null;
        List<String> lbtime = new ArrayList<>();
        List<String> letime = new ArrayList<>();
        if ("admin".equals(user.getUtype())) {
            llist = userLxService.selectLx();
            for (Lx l : llist) {
                lbtime.add(sdf.format(l.getLbtime()));
                letime.add(sdf.format(l.getLetime()));
            }
        } else {
            llist = userLxService.selectLx(user.getUid());
            for (Lx l : llist) {
                lbtime.add(sdf.format(l.getLbtime()));
                letime.add(sdf.format(l.getLetime()));
            }
        }
        model.addAttribute("llist", llist);
        model.addAttribute("lbtime", lbtime);
        model.addAttribute("letime", letime);
        return "main" + File.separator + "lx" + File.separator + "SelectLx";
    }

    /**
     * 条件查询立项
     */
    @RequestMapping("SelectLxInfo")
    public String SelectLxInfo(Model model, HttpSession session, String lid, String lname, String ltype) {
        Users user = (Users) session.getAttribute("user");
        List<Lx> llist = null;
        List<String> lbtime = new ArrayList<>();
        List<String> letime = new ArrayList<>();
        Lx lx = new Lx();
        lx.setLid(lid);
        lx.setLname(lname);
        lx.setLtype(ltype);
        lx.setUid(user.getUid());
        llist = userLxService.selectLx(lx, user.getUtype());
        for (Lx l : llist) {
            lbtime.add(sdf.format(l.getLbtime()));
            letime.add(sdf.format(l.getLetime()));
        }
        model.addAttribute("llist", llist);
        model.addAttribute("lbtime", lbtime);
        model.addAttribute("letime", letime);
        model.addAttribute("lid", lid);
        model.addAttribute("lname", lname);
        model.addAttribute("ltype", ltype);
        return "main" + File.separator + "lx" + File.separator + "SelectLx";
    }

    /**
     * 增加立项入口
     */
    @RequestMapping("AddLx")
    public String AddLx() {
        return "main" + File.separator + "lx" + File.separator + "AddLx";
    }

    /**
     * 添加立项
     */
    @RequestMapping(value = "AddLxInfo", method = RequestMethod.POST)
    public void AddLxInfo(Lx lx, HttpServletResponse response, HttpSession session, HttpServletRequest request, MultipartFile uploadFile, String[] lpid, String[] lpname,
                          String lbyear, String lbmonth, String lbday, String leyear, String lemonth, String leday) throws IOException, ParseException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String uid = ((Users) session.getAttribute("user")).getUid();
        String lbtime = lbyear + "-" + lbmonth + "-" + lbday;
        String letime = leyear + "-" + lemonth + "-" + leday;
        String syspath = UpfilePath.getSRIPath(request);
        String path = uid + File.separator + "立项" + File.separator + lx.getLtype() + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + uid;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + File.separator + "立项" + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + lx.getLtype() + File.separator;
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
        lx.setLbtime(sdf.parse(lbtime));
        lx.setLetime(sdf.parse(letime));
        lx.setLaddress(path + filename);
        lx.setUid(uid);
        List<Lp> lps = new ArrayList<>();
        for (int i = 0; i < lpid.length; i++) {
            lps.add(new Lp(lpid[i], lpname[i], lx.getLid()));
        }
        if (userLxService.AddLx(lx)) {
            if (userLxService.AddLp(lps)) {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"添加成功！\");"
                        + "window.location.href = \"SelectLx\""
                        + "</script>");
            } else {
                userLxService.DeleteLx(lx.getLid());
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"添加失败！\");"
                        + "window.location.href = \"AddLx\""
                        + "</script>");
            }
        } else {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"添加失败！\");"
                    + "window.location.href = \"AddLx\""
                    + "</script>");
        }
        out.close();
    }

    /**
     * 删除立项
     */
    @RequestMapping("DeleteLx")
    public void DeleteLx(HttpServletRequest request, HttpServletResponse response, @RequestParam("lid") String lid) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String syspath = UpfilePath.getSRIPath(request);
        File file = new File(syspath + userLxService.selectLxByLid(lid).getLaddress());
        if (userLxService.DeleteLp(lid)) {
            if (userLxService.DeleteLx(lid)) {
                if (file.exists()) {
                    if (file.delete()) {
                        out.println("<script type=\"text/javascript\">\r\n"
                                + "alert(\"删除成功！\");"
                                + "window.location.href = \"SelectLx\""
                                + "</script>");
                    } else {
                        out.println("<script type=\"text/javascript\">\r\n"
                                + "alert(\"删除失败！\");"
                                + "window.location.href = \"SelectLx\""
                                + "</script>");
                    }
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"删除成功！\");"
                            + "window.location.href = \"SelectLx\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"删除失败！\");"
                        + "window.location.href = \"SelectLx\""
                        + "</script>");
            }
        } else {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"删除失败！\");"
                    + "window.location.href = \"SelectLx\""
                    + "</script>");
        }
        out.close();
    }

    /**
     * 修改立项入口
     */
    @RequestMapping("UpdateLx")
    public String UpdateLx(Model model, @RequestParam("lid") String lid) {
        Lx lx = userLxService.selectLxByLid(lid);
        List<Lp> lps = userLxService.selectLp(lid);
        Calendar cal = Calendar.getInstance();
        cal.setTime(lx.getLbtime());
        int lbyear = cal.get(Calendar.YEAR);
        int lbmonth = cal.get(Calendar.MONTH) + 1;
        int lbday = cal.get(Calendar.DATE);
        cal.setTime(lx.getLetime());
        int leyear = cal.get(Calendar.YEAR);
        int lemonth = cal.get(Calendar.MONTH) + 1;
        int leday = cal.get(Calendar.DATE);
        model.addAttribute("lx", lx);
        model.addAttribute("lps", lps);
        model.addAttribute("lbyear", lbyear);
        model.addAttribute("lbmonth", lbmonth);
        model.addAttribute("lbday", lbday);
        model.addAttribute("leyear", leyear);
        model.addAttribute("lemonth", lemonth);
        model.addAttribute("leday", leday);
        return "main" + File.separator + "lx" + File.separator + "UpdateLx";
    }

    /**
     * 修改立项
     */
    @RequestMapping(value = "UpdateLxInfo", method = RequestMethod.POST)
    public void UpdateLxInfo(Lx lx, HttpServletResponse response, HttpServletRequest request, MultipartFile uploadFile, String oldLid, String[] lpid, String[] lpname,
                             String lbyear, String lbmonth, String lbday, String leyear, String lemonth, String leday) throws IOException, ParseException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String lbtime = lbyear + "-" + lbmonth + "-" + lbday;
        String letime = leyear + "-" + lemonth + "-" + leday;
        String syspath = UpfilePath.getSRIPath(request);
        String path = lx.getUid() + File.separator + "立项" + File.separator + lx.getLtype() + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        String path1 = syspath + lx.getUid();
        if (!new File(path1).exists()) {
            new File(path1).mkdir();
        }
        path1 = path1 + File.separator + "立项" + File.separator;
        if (!new File(path1).exists()) {
            new File(path1).mkdir();
        }
        path1 = path1 + lx.getLtype() + File.separator;
        String filename = uploadFile.getOriginalFilename();
        String fname = filename.substring(0, filename.lastIndexOf("."));
        String fsuffix = filename.substring(filename.lastIndexOf("."));
        filename = fname + "_" + UUID.randomUUID() + fsuffix;
        File filepath = new File(path1);
        File file = new File(path1 + filename);
        File oldfile = new File(syspath + lx.getLaddress());
        if (filepath.exists()) {
            uploadFile.transferTo(file);
        } else {
            filepath.mkdir();
            uploadFile.transferTo(file);
        }
        lx.setLbtime(sdf.parse(lbtime));
        lx.setLetime(sdf.parse(letime));
        lx.setLaddress(path + filename);
        List<Lp> lps = new ArrayList<>();
        for (int i = 0; i < lpid.length; i++) {
            lps.add(new Lp(lpid[i], lpname[i], lx.getLid()));
        }
        if (oldfile.exists()) {
            if (oldfile.delete()) {
                if (userLxService.UpdateLp(lps, oldLid)) {
                    if (userLxService.UpdateLx(lx, oldLid)) {
                        out.println("<script type=\"text/javascript\">\r\n"
                                + "alert(\"修改成功！\");"
                                + "window.location.href = \"SelectLx\""
                                + "</script>");
                    } else {
                        out.println("<script type=\"text/javascript\">\r\n"
                                + "alert(\"修改失败！\");"
                                + "window.location.href = \"SelectLx\""
                                + "</script>");
                    }
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改失败！\");"
                            + "window.location.href = \"SelectLx\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改失败！\");"
                        + "window.location.href = \"SelectLx\""
                        + "</script>");
            }
        } else {
            if (userLxService.UpdateLp(lps, oldLid)) {
                if (userLxService.UpdateLx(lx, oldLid)) {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改成功！\");"
                            + "window.location.href = \"SelectLx\""
                            + "</script>");
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改失败！\");"
                            + "window.location.href = \"SelectLx\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改失败！\");"
                        + "window.location.href = \"SelectLx\""
                        + "</script>");
            }
        }
        out.close();
    }

    /**
     * 下载立项
     */
    @RequestMapping("DownloadLxByLid")
    public void DownloadLxByLid(@RequestParam("lid") String lid, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String syspath = UpfilePath.getSRIPath(request);
        String path = syspath + userLxService.selectLxByLid(lid).getLaddress();
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
                    "window.location.href = \"SelectLx\"" +
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
     * 下载所有的立项
     */
    @RequestMapping("DownloadLx")
    public void DownloadLx(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String syspath = UpfilePath.getSRIPath(request);
        Users user = (Users) session.getAttribute("user");
        String utype = user.getUtype();
        String uid = user.getUid();
        String userPath = syspath + uid + File.separator + "立项" + File.separator;
        //设置文件下载头
        if ("user".equals(utype)) {
            response.addHeader("Content-Disposition", "attachment;filename=" + uid + "_Lx.zip");
        } else {
            response.addHeader("Content-Disposition", "attachment;filename=Lx.zip");
        }
        //设置文件ContentType类型，自动判断下载文件类型
        response.setContentType("multipart/form-data");
        OutputStream out = response.getOutputStream();
        ZipUtil zipUtil = new ZipUtil();
        if ("user".equals(utype)) {
            zipUtil.toZip(userPath, out, true);
        } else {
            List<Lx> lxList = userLxService.selectLx();
            List<File> lfileList = new ArrayList<>();
            for (Lx l : lxList) {
                lfileList.add(new File(syspath + l.getLaddress()));
            }
            zipUtil.toZip(lfileList, out);
        }
        out.close();
    }
}
