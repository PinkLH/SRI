package net.cqwu.SRI.controller;

import net.cqwu.SRI.entity.Awork;
import net.cqwu.SRI.entity.Swork;
import net.cqwu.SRI.entity.Users;
import net.cqwu.SRI.service.UserAworkService;
import net.cqwu.SRI.service.UserSworkService;
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
import java.util.*;

@Controller
public class UserWorkController {
    /**
     * 格式化日期对象
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 业务层接口
     */
    @Autowired
    private UserSworkService userSworkService;
    @Autowired
    private UserAworkService userAworkService;

    /**
     * 查询著作入口
     */
    @RequestMapping("SelectWork")
    public String SelectWork(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        this.SelectSwork(model, user);
        this.SelectAwork(model, user);
        return "main" + File.separator + "work" + File.separator + "SelectWork";
    }

    /**
     * 查询软件著作入口
     */
    private void SelectSwork(Model model, Users user) {
        List<Swork> swlist;
        if ("admin".equals(user.getUtype())) {
            swlist = userSworkService.selectSwork();
        } else {
            swlist = userSworkService.selectSwork(user.getUid());
        }
        model.addAttribute("swlist", swlist);
    }

    /**
     * 查询学术著作入口
     */
    private void SelectAwork(Model model, Users user) {
        List<Awork> awlist;
        List<String> awtime = new ArrayList<>();
        if ("admin".equals(user.getUtype())) {
            awlist = userAworkService.selectAwork();
            for (Awork a : awlist) {
                awtime.add(sdf.format(a.getAwtime()));
            }
        } else {
            awlist = userAworkService.selectAwork(user.getUid());
            for (Awork a : awlist) {
                awtime.add(sdf.format(a.getAwtime()));
            }
        }
        model.addAttribute("awtime", awtime);
        model.addAttribute("awlist", awlist);
    }

    /**
     * 条件查询著作
     */
    @RequestMapping("SelectWorkInfo")
    public String SelectWorkInfo(String wname, String wtype, Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        this.SelectSworkInfo(model, user, wname);
        this.SelectAworkInfo(model, user, wname);
        model.addAttribute("wtype", wtype);
        model.addAttribute("wname", wname);
        return "main" + File.separator + "work" + File.separator + "SelectWork";
    }

    /**
     * 条件查询软件著作
     */
    private void SelectSworkInfo(Model model, Users user, String wname) {
        List<Swork> swlist;
        swlist = userSworkService.selectSwork(wname, user.getUtype(), user.getUid());
        model.addAttribute("swlist", swlist);
    }

    /**
     * 条件查询学术著作
     */
    private void SelectAworkInfo(Model model, Users user, String wname) {
        List<Awork> awlist;
        List<String> awtime = new ArrayList<>();
        awlist = userAworkService.selectAwork(wname, user.getUtype(), user.getUid());
        for (Awork a : awlist) {
            awtime.add(sdf.format(a.getAwtime()));
        }
        model.addAttribute("awtime", awtime);
        model.addAttribute("awlist", awlist);
    }

    /**
     * 修改软件著作入口
     */
    @RequestMapping("UpdateSwork")
    public String UpdateSwork(Model model, @RequestParam("swid") String swid) {
        Swork swork = userSworkService.selectSworkBySwid(swid);
        model.addAttribute("swork", swork);
        return "main" + File.separator + "work" + File.separator + "UpdateSwork";
    }

    /**
     * 修改学术著作入口
     */
    @RequestMapping("UpdateAwork")
    public String UpdateAwork(Model model, @RequestParam("awid") int awid) {
        Awork awork = userAworkService.selectAwork(awid);
        Calendar cal = Calendar.getInstance();
        cal.setTime(awork.getAwtime());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        model.addAttribute("awork", awork);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        return "main" + File.separator + "work" + File.separator + "UpdateAwork";
    }

    /**
     * 修改软件著作
     */
    @RequestMapping(value = "UpdateSworkInfo", method = RequestMethod.POST)
    public void UpdateSworkInfo(Swork swork, String oldswid, HttpServletResponse response, HttpServletRequest request, MultipartFile uploadFile) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String syspath = UpfilePath.getSRIPath(request);
        String path = swork.getUid() + File.separator + "著作" + File.separator + "软件著作" + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        String path1 = syspath + swork.getUid() + File.separator;
        if (!new File(path1).exists()) {
            new File(path1).mkdir();
        }
        path1 = path1 + "著作" + File.separator;
        if (!new File(path1).exists()) {
            new File(path1).mkdir();
        }
        path1 = path1 + "软件著作" + File.separator;
        String filename = uploadFile.getOriginalFilename();
        String fname = filename.substring(0, filename.lastIndexOf("."));
        String fsuffix = filename.substring(filename.lastIndexOf("."));
        filename = fname + "_" + UUID.randomUUID() + fsuffix;
        File filepath = new File(path1);
        File file = new File(path1 + filename);
        File oldfile = new File(syspath + swork.getSwaddress());
        if (filepath.exists()) {
            uploadFile.transferTo(file);
        } else {
            filepath.mkdir();
            uploadFile.transferTo(file);
        }
        swork.setSwaddress(path + filename);

        if (oldfile.exists()) {
            if (oldfile.delete()) {
                if (userSworkService.UpdateSwork(swork, oldswid)) {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改成功！\");"
                            + "window.location.href = \"SelectWork\""
                            + "</script>");
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改失败！\");"
                            + "window.location.href = \"SelectWork\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改失败！\");"
                        + "window.location.href = \"SelectWork\""
                        + "</script>");
            }
        } else {
            if (userSworkService.UpdateSwork(swork, oldswid)) {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改成功！\");"
                        + "window.location.href = \"SelectWork\""
                        + "</script>");
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改失败！\");"
                        + "window.location.href = \"SelectWork\""
                        + "</script>");
            }
        }
        out.close();
    }

    /**
     * 修改学术著作
     */
    @RequestMapping(value = "UpdateAworkInfo", method = RequestMethod.POST)
    public void UpdateAworkInfo(Awork awork, HttpServletResponse response, HttpServletRequest request, MultipartFile uploadFile,
                                String year, String month, String day) throws IOException, ParseException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String syspath = UpfilePath.getSRIPath(request);
        String awtime = year + "-" + month + "-" + day;
        String path = awork.getUid() + File.separator + "著作" + File.separator + "学术著作" + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        String path1 = syspath + awork.getUid() + File.separator;
        if (!new File(path1).exists()) {
            new File(path1).mkdir();
        }
        path1 = path1 + "著作" + File.separator;
        if (!new File(path1).exists()) {
            new File(path1).mkdir();
        }
        path1 = path1 + "学术著作" + File.separator;
        String filename = uploadFile.getOriginalFilename();
        String fname = filename.substring(0, filename.lastIndexOf("."));
        String fsuffix = filename.substring(filename.lastIndexOf("."));
        filename = fname + "_" + UUID.randomUUID() + fsuffix;
        File filepath = new File(path1);
        File file = new File(path1 + filename);
        File oldfile = new File(syspath + awork.getAwaddress());
        if (filepath.exists()) {
            uploadFile.transferTo(file);
        } else {
            filepath.mkdir();
            uploadFile.transferTo(file);
        }
        awork.setAwtime(sdf.parse(awtime));
        awork.setAwaddress(path + filename);

        if (oldfile.exists()) {
            if (oldfile.delete()) {
                if (userAworkService.UpdateAwork(awork)) {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改成功！\");"
                            + "window.location.href = \"SelectWork\""
                            + "</script>");
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改失败！\");"
                            + "window.location.href = \"SelectWork\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改失败！\");"
                        + "window.location.href = \"SelectWork\""
                        + "</script>");
            }
        } else {
            if (userAworkService.UpdateAwork(awork)) {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改成功！\");"
                        + "window.location.href = \"SelectWork\""
                        + "</script>");
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改失败！\");"
                        + "window.location.href = \"SelectWork\""
                        + "</script>");
            }
        }
        out.close();

    }


    /**
     * 增加著作入口
     */
    @RequestMapping("AddWork")
    public String AddWork() {
        return "main" + File.separator + "work" + File.separator + "AddWork";
    }

    /**
     * 增加著作
     */
    @RequestMapping(value = "AddWorkInfo", method = RequestMethod.POST)
    public void AddWorkInfo(HttpServletResponse response, HttpSession session, HttpServletRequest request, MultipartFile uploadFile,
                            Swork swork, Awork awork, String year, String month, String day, String wtype) throws ParseException, IOException {
        if ("0".equals(wtype)) {
            Date swtime = new Date();
            swork.setSwtime(swtime);
            this.AddSworkInfo(swork, response, session, request, uploadFile);
        } else {
            String awtime = year + "-" + month + "-" + day;
            awork.setAwtime(sdf.parse(awtime));
            this.AddAworkInfo(awork, response, session, request, uploadFile);
        }
    }

    /**
     * 增加软件著作
     */
    private void AddSworkInfo(Swork swork, HttpServletResponse response, HttpSession session, HttpServletRequest request, MultipartFile uploadFile) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        if(userSworkService.selectSworkBySwid(swork.getSwid()) != null){
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"已经存在此软件著作，添加失败！\");"
                    + "window.location.href = \"AddWork\""
                    + "</script>");
            out.close();
            return;
        }


        String uid = ((Users) session.getAttribute("user")).getUid();
        String syspath = UpfilePath.getSRIPath(request);
        String path = uid + File.separator + "著作" + File.separator + "软件著作" + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + uid + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + "著作" + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + "软件著作" + File.separator;
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
        swork.setSwaddress(path + filename);
        swork.setUid(uid);
        if (userSworkService.AddSwork(swork)) {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"添加成功！\");"
                    + "window.location.href = \"SelectWork\""
                    + "</script>");
        } else {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"添加失败！\");"
                    + "window.location.href = \"AddWork\""
                    + "</script>");
        }
        out.close();
    }

    /**
     * 增加学术著作
     */
    private void AddAworkInfo(Awork awork, HttpServletResponse response, HttpSession session, HttpServletRequest request, MultipartFile uploadFile) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String uid = ((Users) session.getAttribute("user")).getUid();
        String syspath = UpfilePath.getSRIPath(request);
        String path = uid + File.separator + "著作" + File.separator + "学术著作" + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + uid + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + "著作" + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + "学术著作" + File.separator;
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
        awork.setAwaddress(path + filename);
        awork.setUid(uid);
        if (userAworkService.AddAwork(awork)) {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"添加成功！\");"
                    + "window.location.href = \"SelectWork\""
                    + "</script>");
        } else {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"添加失败！\");"
                    + "window.location.href = \"AddWork\""
                    + "</script>");
        }
        out.close();
    }

    /**
     * 删除一个软件著作
     */
    @RequestMapping("DeleteSwork")
    public void DeleteSwork(HttpServletRequest request, HttpServletResponse response, @RequestParam("swid") String swid) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String syspath = UpfilePath.getSRIPath(request);
        File file = new File(syspath + userSworkService.selectSworkBySwid(swid).getSwaddress());

        if (userSworkService.DeleteSwork(swid)) {
            if (file.exists()) {
                if (file.delete()) {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"删除成功！\");"
                            + "window.location.href = \"SelectWork\""
                            + "</script>");
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"删除失败！\");"
                            + "window.location.href = \"SelectWork\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"删除成功！\");"
                        + "window.location.href = \"SelectWork\""
                        + "</script>");
            }
        } else {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"删除失败！\");"
                    + "window.location.href = \"SelectWork\""
                    + "</script>");
        }
        out.close();
    }

    /**
     * 删除一个学术著作
     */
    @RequestMapping("DeleteAwork")
    public void DeleteAwork(HttpServletRequest request, HttpServletResponse response, @RequestParam("awid") int awid) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String syspath = UpfilePath.getSRIPath(request);
        File file = new File(syspath + userAworkService.selectAwork(awid).getAwaddress());
        if (userAworkService.DeleteAwork(awid)) {
            if (file.exists()) {
                if (file.delete()) {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"删除成功！\");"
                            + "window.location.href = \"SelectWork\""
                            + "</script>");
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"删除失败！\");"
                            + "window.location.href = \"SelectWork\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"删除成功！\");"
                        + "window.location.href = \"SelectWork\""
                        + "</script>");
            }
        } else {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"删除失败！\");"
                    + "window.location.href = \"SelectWork\""
                    + "</script>");
        }
        out.close();
    }

    /**
     * 下载某个软件著作
     */
    @RequestMapping("DownloadSworkBySwid")
    public void DownloadSworkBySwid(@RequestParam("swid") String swid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String syspath = UpfilePath.getSRIPath(request);
        String path = syspath + userSworkService.selectSworkBySwid(swid).getSwaddress();
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
        String filenameUUID = path.substring(path.lastIndexOf(File.separator) + 1);
        String filename = filenameUUID.substring(0, filenameUUID.lastIndexOf("_")) + filenameUUID.substring(filenameUUID.lastIndexOf("."));
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
     * 下载某个学术著作
     */
    @RequestMapping("DownloadAworkByAwid")
    public void DownloadAworkByAwid(@RequestParam("awid") int awid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String syspath = UpfilePath.getSRIPath(request);
        String path = syspath + userAworkService.selectAwork(awid).getAwaddress();
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
        String filenameUUID = path.substring(path.lastIndexOf(File.separator) + 1);
        String filename = filenameUUID.substring(0, filenameUUID.lastIndexOf("_")) + filenameUUID.substring(filenameUUID.lastIndexOf("."));
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
     * 下载所有的著作
     */
    @RequestMapping("DownloadWork")
    public void DownloadWork(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String syspath = UpfilePath.getSRIPath(request);
        Users user = (Users) session.getAttribute("user");
        String utype = user.getUtype();
        String uid = user.getUid();
        String userPath = syspath + uid + File.separator + "著作" + File.separator;
        //设置文件下载头
        if ("user".equals(utype)) {
            response.addHeader("Content-Disposition", "attachment;filename=" + uid + "_Work.zip");
        } else {
            response.addHeader("Content-Disposition", "attachment;filename=Work.zip");
        }
        //设置文件ContentType类型，自动判断下载文件类型
        response.setContentType("multipart/form-data");
        OutputStream out = response.getOutputStream();
        ZipUtil zipUtil = new ZipUtil();
        if ("user".equals(utype)) {
            zipUtil.toZip(userPath, out, true);
        } else {
            List<Swork> sworkList = userSworkService.selectSwork();
            List<Awork> aworkList = userAworkService.selectAwork();
            List<File> wfileList = new ArrayList<>();
            for (Swork sw : sworkList) {
                wfileList.add(new File(syspath + sw.getSwaddress()));
            }
            for (Awork aw : aworkList) {
                wfileList.add(new File(syspath + aw.getAwaddress()));
            }
            zipUtil.toZip(wfileList, out);
        }
        out.close();
    }

    /**
     * 导出软件著作Excel表格
     */
    @RequestMapping("ExportSworkExcel")
    public void ExportSworkExcel(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Users user = (Users) session.getAttribute("user");

        ExcelExport<Swork> swExcel = new ExcelExport<Swork>();
//        ExcelExport<Awork> awExcel = new ExcelExport<Awork>();

        String[] headers = {"软件名称", "登记号", "著作权人"};

        List<Swork> swList;

        if ("admin".equals(user.getUtype())) {
            swList = userSworkService.selectExcelSwork();
            String mimeType = request.getServletContext().getMimeType("软件著作.xls");
            swExcel.exportExcel("软件著作.xls", headers, swList, response, mimeType);
        } else {
            swList = userSworkService.selectExcelSwork(user.getUid());
            String mimeType = request.getServletContext().getMimeType("软件著作.xls");
            swExcel.exportExcel(user.getUid() + "_软件著作.xls", headers, swList, response, mimeType);
        }


    }


    /**
     * 导出学术著作Excel表格
     */
    @RequestMapping("ExportAworkExcel")
    public void ExportAworkExcel(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Users user = (Users) session.getAttribute("user");

        ExcelExport<Awork> awExcel = new ExcelExport<Awork>();

        String[] headers = {"著作名称", "出版日期", "出版社", "编辑人"};

        List<Awork> awList;

        if ("admin".equals(user.getUtype())) {
            awList = userAworkService.selectExcelAwork();
            String mimeType = request.getServletContext().getMimeType("学术著作.xls");
            awExcel.exportExcel("学术著作.xls", headers, awList, response, mimeType);
        } else {
            awList = userAworkService.selectExcelAwork(user.getUid());
            String mimeType = request.getServletContext().getMimeType("学术著作.xls");
            awExcel.exportExcel(user.getUid() + "_学术著作.xls", headers, awList, response, mimeType);
        }
    }


}
