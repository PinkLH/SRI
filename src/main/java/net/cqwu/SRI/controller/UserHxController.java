package net.cqwu.SRI.controller;

import net.cqwu.SRI.entity.Hx;
import net.cqwu.SRI.entity.Users;
import net.cqwu.SRI.service.UserHxService;
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
public class UserHxController {
    /**
     * 格式化日期对象
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 业务层接口
     */
    @Autowired
    private UserHxService userHxService;

    /**
     * 查询横项入口
     */
    @RequestMapping("SelectHx")
    public String SelectHx(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        List<Hx> hlist;
        List<String> hbtime = new ArrayList<>();
        List<String> hetime = new ArrayList<>();
        if ("admin".equals(user.getUtype())) {
            hlist = userHxService.selectHx();
            for (Hx hx : hlist) {
                hbtime.add(sdf.format(hx.getHbtime()));
                hetime.add(sdf.format(hx.getHetime()));
            }
        } else {
            hlist = userHxService.selectHx(user.getUid());
            for (Hx hx : hlist) {
                hbtime.add(sdf.format(hx.getHbtime()));
                hetime.add(sdf.format(hx.getHetime()));
            }
        }
        model.addAttribute("hlist", hlist);
        model.addAttribute("hbtime", hbtime);
        model.addAttribute("hetime", hetime);
        return "main" + File.separator + "hx" + File.separator + "SelectHx";
    }

    /**
     * 条件查询横项
     */
    @RequestMapping("SelectHxInfo")
    public String SelectHxInfo(Model model, HttpSession session, String hname, String hobject) {
        Users user = (Users) session.getAttribute("user");
        List<Hx> hlist;
        List<String> hbtime = new ArrayList<>();
        List<String> hetime = new ArrayList<>();
        Hx hx = new Hx();
        hx.setHname(hname);
        hx.setHobject(hobject);
        hx.setUid(user.getUid());
        hlist = userHxService.selectHx(hx, user.getUtype());
        for (Hx h : hlist) {
            hbtime.add(sdf.format(h.getHbtime()));
            hetime.add(sdf.format(h.getHetime()));
        }
        model.addAttribute("hlist", hlist);
        model.addAttribute("hbtime", hbtime);
        model.addAttribute("hetime", hetime);
        model.addAttribute("hname", hname);
        model.addAttribute("hobject", hobject);
        return "main" + File.separator + "hx" + File.separator + "SelectHx";
    }

    /**
     * 增加横项入口
     */
    @RequestMapping("AddHx")
    public String AddHx() {
        return "main" + File.separator + "hx" + File.separator + "AddHx";
    }

    /**
     * 增加横项
     */
    @RequestMapping(value = "AddHxInfo", method = RequestMethod.POST)
    public void AddHxInfo(Hx hx, HttpServletResponse response, HttpSession session, HttpServletRequest request, MultipartFile uploadFile,
                          String hbyear, String hbmonth, String hbday, String heyear, String hemonth, String heday) throws IOException, ParseException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String uid = ((Users) session.getAttribute("user")).getUid();
        String hbtime = hbyear + "-" + hbmonth + "-" + hbday;
        String hetime = heyear + "-" + hemonth + "-" + heday;
        String syspath = UpfilePath.getSRIPath(request);
        String path = uid + File.separator + "横项" + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + uid;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + File.separator + "横项" + File.separator;
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
        hx.setUid(uid);
        hx.setHbtime(sdf.parse(hbtime));
        hx.setHetime(sdf.parse(hetime));
        hx.setHaddress(path + filename);
        if (userHxService.AddHx(hx)) {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"添加成功！\");"
                    + "window.location.href = \"SelectHx\""
                    + "</script>");
        } else {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"添加失败！\");"
                    + "window.location.href = \"AddHx\""
                    + "</script>");
        }
        out.close();
    }

    /**
     * 修改横项入口
     */
    @RequestMapping("UpdateHx")
    public String UpdateHx(Model model, @RequestParam("hid") int hid) {
        Hx hx = userHxService.selectHx(hid);
        Calendar cal = Calendar.getInstance();
        cal.setTime(hx.getHbtime());
        int hbyear = cal.get(Calendar.YEAR);
        int hbmonth = cal.get(Calendar.MONTH) + 1;
        int hbday = cal.get(Calendar.DATE);
        cal.setTime(hx.getHetime());
        int heyear = cal.get(Calendar.YEAR);
        int hemonth = cal.get(Calendar.MONTH) + 1;
        int heday = cal.get(Calendar.DATE);
        model.addAttribute("hx", hx);
        model.addAttribute("hbyear", hbyear);
        model.addAttribute("hbmonth", hbmonth);
        model.addAttribute("hbday", hbday);
        model.addAttribute("heyear", heyear);
        model.addAttribute("hemonth", hemonth);
        model.addAttribute("heday", heday);
        return "main" + File.separator + "hx" + File.separator + "UpdateHx";
    }

    /**
     * 修改横项
     */
    @RequestMapping(value = "UpdateHxInfo", method = RequestMethod.POST)
    public void UpdateHxInfo(Hx hx, HttpServletResponse response, HttpServletRequest request, MultipartFile uploadFile,
                             String hbyear, String hbmonth, String hbday, String heyear, String hemonth, String heday) throws IOException, ParseException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String hbtime = hbyear + "-" + hbmonth + "-" + hbday;
        String hetime = heyear + "-" + hemonth + "-" + heday;
        String syspath = UpfilePath.getSRIPath(request);
        String path = hx.getUid() + File.separator + "横项" + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        String path1 = syspath + hx.getUid();
        if (!new File(path1).exists()) {
            new File(path1).mkdir();
        }
        path1 = path1 + File.separator + "横项" + File.separator;
        String filename = uploadFile.getOriginalFilename();
        String fname = filename.substring(0, filename.lastIndexOf("."));
        String fsuffix = filename.substring(filename.lastIndexOf("."));
        filename = fname + "_" + UUID.randomUUID() + fsuffix;
        File filepath = new File(path1);
        File file = new File(path1 + filename);
        File oldfile = new File(syspath + hx.getHaddress());
        if (filepath.exists()) {
            uploadFile.transferTo(file);
        } else {
            filepath.mkdir();
            uploadFile.transferTo(file);
        }
        hx.setHbtime(sdf.parse(hbtime));
        hx.setHetime(sdf.parse(hetime));
        hx.setHaddress(path + filename);
        if (oldfile.exists()) {
            if (oldfile.delete()) {
                if (userHxService.UpdateHx(hx)) {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改成功！\");"
                            + "window.location.href = \"SelectHx\""
                            + "</script>");
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改失败！\");"
                            + "window.location.href = \"SelectHx\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改失败！\");"
                        + "window.location.href = \"SelectHx\""
                        + "</script>");
            }
        } else {
            if (userHxService.UpdateHx(hx)) {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改成功！\");"
                        + "window.location.href = \"SelectHx\""
                        + "</script>");
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改失败！\");"
                        + "window.location.href = \"SelectHx\""
                        + "</script>");
            }
        }
        out.close();
    }

    /**
     * 删除横项
     */
    @RequestMapping("DeleteHx")
    public void DeleteHx(HttpServletRequest request, HttpServletResponse response, @RequestParam("hid") int hid) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String syspath = UpfilePath.getSRIPath(request);
        File file = new File(syspath + userHxService.selectHx(hid).getHaddress());

        if (userHxService.DeleteHx(hid)) {
            if (file.exists()) {
                if (file.delete()) {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"删除成功！\");"
                            + "window.location.href = \"SelectHx\""
                            + "</script>");
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"删除失败！\");"
                            + "window.location.href = \"SelectHx\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"删除成功！\");"
                        + "window.location.href = \"SelectHx\""
                        + "</script>");
            }
        } else {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"删除失败！\");"
                    + "window.location.href = \"SelectHx\""
                    + "</script>");
        }
        out.close();

    }

    /**
     * 下载某个横项
     */
    @RequestMapping("DownlodHxByHid")
    public void DownlodHxByHid(@RequestParam("hid") int hid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String syspath = UpfilePath.getSRIPath(request);
        String path = syspath + userHxService.selectHx(hid).getHaddress();
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
                    "window.location.href = \"SelectHx\"" +
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
     * 下载全部横项
     */
    @RequestMapping("DownloadHx")
    public void DownloadHx(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String syspath = UpfilePath.getSRIPath(request);
        Users user = (Users) session.getAttribute("user");
        String utype = user.getUtype();
        String uid = user.getUid();
        String userPath = syspath + uid + File.separator + "横项" + File.separator;
        //设置文件下载头
        if ("user".equals(utype)) {
            response.addHeader("Content-Disposition", "attachment;filename=" + uid + "_Hx.zip");
        } else {
            response.addHeader("Content-Disposition", "attachment;filename=Hx.zip");
        }
        //设置文件ContentType类型，自动判断下载文件类型
        response.setContentType("multipart/form-data");
        OutputStream out = response.getOutputStream();
        ZipUtil zipUtil = new ZipUtil();
        if ("user".equals(utype)) {
            zipUtil.toZip(userPath, out, true);
        } else {
            List<Hx> hxList = userHxService.selectHx();
            List<File> afileList = new ArrayList<>();
            for (Hx hx : hxList) {
                afileList.add(new File(syspath + hx.getHaddress()));
            }
            zipUtil.toZip(afileList, out);
        }
        out.close();
    }

    /**
     * 导出横项的Excel
     */
    @RequestMapping("ExportHxExcel")
    public void ExportHxExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Users user = (Users) session.getAttribute("user");

        ExcelExport<Hx> excel = new ExcelExport<Hx>();

        String[] headers = {"合同名称", "合作对象", "开始时间", "结束时间", "经费(元)"};

        List<Hx> list;
        if ("admin".equals(user.getUtype())) {
            list = userHxService.selectExcelHx();
            String mimeType = request.getServletContext().getMimeType("横项.xls");
            excel.exportExcel("横项.xls", headers, list, response, mimeType);
        } else {
            list = userHxService.selectExcelHx(user.getUid());
            String mimeType = request.getServletContext().getMimeType("横项.xls");
            excel.exportExcel(user.getUid() + "_横项.xls", headers, list, response, mimeType);
        }
    }

}
