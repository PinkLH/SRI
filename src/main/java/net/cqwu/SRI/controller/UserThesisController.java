package net.cqwu.SRI.controller;

import net.cqwu.SRI.entity.Thesis;
import net.cqwu.SRI.entity.Users;
import net.cqwu.SRI.service.UserThesisService;
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
public class UserThesisController {

    /**
     * 格式化日期对象
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 业务层接口
     */
    @Autowired
    private UserThesisService userThesisService;

    /**
     * 查询论文入口
     */
    @RequestMapping("SelectThesis")
    public String SelectThesis(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        List<Thesis> tlist;
        List<String> ttime = new ArrayList<>();
        if ("admin".equals(user.getUtype())) {
            tlist = userThesisService.selectThesis();
            for (Thesis t : tlist) {
                ttime.add(sdf.format(t.getTtime()));
            }
        } else {
            tlist = userThesisService.selectThesis(user.getUid());
            for (Thesis t : tlist) {
                ttime.add(sdf.format(t.getTtime()));
            }
        }
        model.addAttribute("ttime", ttime);
        model.addAttribute("tlist", tlist);
        return "main" + File.separator + "thesis" + File.separator + "SelectThesis";
    }

    /**
     * 条件查询论文
     */
    @RequestMapping("SelectThesisInfo")
    public String SelectThesisInfo(Model model, HttpSession session, String tname, String ttype) {
        Users user = (Users) session.getAttribute("user");
        List<Thesis> tlist;
        List<String> ttime = new ArrayList<>();
        Thesis thesis = new Thesis();
        thesis.setTname(tname);
        thesis.setTtype(ttype);
        thesis.setUid(user.getUid());
        tlist = userThesisService.selectThesis(thesis, user.getUtype());
        for (Thesis t : tlist) {
            ttime.add(sdf.format(t.getTtime()));
        }
        model.addAttribute("ttime", ttime);
        model.addAttribute("tlist", tlist);
        model.addAttribute("tname", tname);
        model.addAttribute("ttype", ttype);
        return "main" + File.separator + "thesis" + File.separator + "SelectThesis";
    }

    /**
     * 增加论文入口
     */
    @RequestMapping("AddThesis")
    public String AddThesis() {
        return "main" + File.separator + "thesis" + File.separator + "AddThesis";
    }

    /**
     * 增加论文
     */
    @RequestMapping(value = "AddThesisInfo", method = RequestMethod.POST)
    public void AddThesisInfo(Thesis thesis, HttpServletResponse response, HttpSession session, HttpServletRequest request, MultipartFile uploadFile,
                              String year, String month, String day) throws IOException, ParseException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String uid = ((Users) session.getAttribute("user")).getUid();
        String ttime = year + "-" + month + "-" + day;
        String syspath = UpfilePath.getSRIPath(request);
        String path = uid + File.separator + "论文" + File.separator +  thesis.getTtype() + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + uid;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + File.separator + "论文" + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        syspath = syspath + thesis.getTtype() + File.separator;
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
        thesis.setTtime(sdf.parse(ttime));
        thesis.setTaddress(path + filename);
        thesis.setUid(uid);
        if (userThesisService.AddThesis(thesis)) {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"添加成功！\");"
                    + "window.location.href = \"SelectThesis\""
                    + "</script>");
        } else {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"添加失败！\");"
                    + "window.location.href = \"AddThesis\""
                    + "</script>");
        }
        out.close();
    }

    /**
     * 修改论文入口
     */
    @RequestMapping("UpdateThesis")
    public String UpdateThesis(Model model, @RequestParam("tid") int tid) {
        Thesis thesis = userThesisService.selectThesis(tid);
//		thesis.setTaddress(thesis.getTaddress().replaceAll("\\\\", "\\\\\\\\"));
        Calendar cal = Calendar.getInstance();
        cal.setTime(thesis.getTtime());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        model.addAttribute("thesis", thesis);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        return "main" + File.separator + "thesis" + File.separator + "UpdateThesis";
    }

    /**
     * 修改论文
     */
    @RequestMapping(value = "UpdateThesisInfo", method = RequestMethod.POST)
    public void UpdateThesisInfo(Thesis thesis, HttpServletResponse response, HttpServletRequest request, MultipartFile uploadFile,
                                 String year, String month, String day) throws IOException, ParseException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String ttime = year + "-" + month + "-" + day;
        String syspath = UpfilePath.getSRIPath(request);
        String path = thesis.getUid() + File.separator + "论文" + File.separator + thesis.getTtype() + File.separator;
        if (!new File(syspath).exists()) {
            new File(syspath).mkdir();
        }
        String path1 = syspath + thesis.getUid();
        if (!new File(path1).exists()) {
            new File(path1).mkdir();
        }
        path1 = path1 + File.separator + "论文" + File.separator;
        if (!new File(path1).exists()) {
            new File(path1).mkdir();
        }
        path1 = path1 + thesis.getTtype() + File.separator;
        String filename = uploadFile.getOriginalFilename();
        String fname = filename.substring(0, filename.lastIndexOf("."));
        String fsuffix = filename.substring(filename.lastIndexOf("."));
        filename = fname + "_" + UUID.randomUUID() + fsuffix;
        File filepath = new File(path1);
        File file = new File(path1 + filename);
        File oldfile = new File(syspath + thesis.getTaddress());
        if (filepath.exists()) {
            uploadFile.transferTo(file);
        } else {
            filepath.mkdir();
            uploadFile.transferTo(file);
        }
        thesis.setTtime(sdf.parse(ttime));
        thesis.setTaddress(path + filename);
        if (oldfile.exists()) {
            if (oldfile.delete()) {
                if (userThesisService.UpdateThesis(thesis)) {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改成功！\");"
                            + "window.location.href = \"SelectThesis\""
                            + "</script>");
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"修改失败！\");"
                            + "window.location.href = \"SelectThesis\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改失败！\");"
                        + "window.location.href = \"SelectThesis\""
                        + "</script>");
            }
        } else {
            if (userThesisService.UpdateThesis(thesis)) {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改成功！\");"
                        + "window.location.href = \"SelectThesis\""
                        + "</script>");
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"修改失败！\");"
                        + "window.location.href = \"SelectThesis\""
                        + "</script>");
            }
        }
        out.close();
    }

    /**
     * 删除论文
     */
    @RequestMapping("DeleteThesis")
    public void DeleteThesis(HttpServletRequest request, HttpServletResponse response, @RequestParam("tid") int tid) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String syspath = UpfilePath.getSRIPath(request);
        File file = new File(syspath + userThesisService.selectThesis(tid).getTaddress());

        if (userThesisService.DeleteThesis(tid)) {
            if (file.exists()) {
                if (file.delete()) {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"删除成功！\");"
                            + "window.location.href = \"SelectThesis\""
                            + "</script>");
                } else {
                    out.println("<script type=\"text/javascript\">\r\n"
                            + "alert(\"删除失败！\");"
                            + "window.location.href = \"SelectThesis\""
                            + "</script>");
                }
            } else {
                out.println("<script type=\"text/javascript\">\r\n"
                        + "alert(\"删除成功！\");"
                        + "window.location.href = \"SelectThesis\""
                        + "</script>");
            }
        } else {
            out.println("<script type=\"text/javascript\">\r\n"
                    + "alert(\"删除失败！\");"
                    + "window.location.href = \"SelectThesis\""
                    + "</script>");
        }
        out.close();
    }

    /**
     * 下载某一个论文
     */
    @RequestMapping("DownloadThesisByTid")
    public void DownlodThesisByTid(@RequestParam("tid") int tid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String syspath = UpfilePath.getSRIPath(request);
        String path = syspath + userThesisService.selectThesis(tid).getTaddress();
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
                    "window.location.href = \"SelectThesis\"" +
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
     * 下载所有的论文
     */
    @RequestMapping("DownloadThesis")
    public void DownloadThesis(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String syspath = UpfilePath.getSRIPath(request);
        Users user = (Users) session.getAttribute("user");
        String utype = user.getUtype();
        String uid = user.getUid();
        String userPath = syspath + uid + File.separator + "论文" + File.separator;
        //设置文件下载头
        if ("user".equals(utype)) {
            response.addHeader("Content-Disposition", "attachment;filename=" + uid + "_Thesis.zip");
        } else {
            response.addHeader("Content-Disposition", "attachment;filename=Thesis.zip");
        }
        //设置文件ContentType类型，自动判断下载文件类型
        response.setContentType("multipart/form-data");
        OutputStream out = response.getOutputStream();
        ZipUtil zipUtil = new ZipUtil();
        if ("user".equals(utype)) {
            zipUtil.toZip(userPath, out, true);
        } else {
            List<Thesis> thesisList = userThesisService.selectThesis();
            List<File> afileList = new ArrayList<>();
            for (Thesis a : thesisList) {
                afileList.add(new File(syspath + a.getTaddress()));
            }
            zipUtil.toZip(afileList, out);
        }
        out.close();
    }

    /**
     * 导出论文的excel
     */
    @RequestMapping("ExportThesisExcel")
    public void ExportThesisExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Users user = (Users) session.getAttribute("user");

        ExcelExport<Thesis> excel = new ExcelExport<Thesis>();
        //表格的头部信息
        String[] headers = {"论文名称", "发表时间", "论文类别"};
        //从数据库查到的数据
        List<Thesis> list;
        if ("admin".equals(user.getUtype())) {
            list = userThesisService.selectExcelThesis();
            String mimeType = request.getServletContext().getMimeType("论文.xls");
            excel.exportExcel("论文.xls", headers, list, response, mimeType);
        } else {
            list = userThesisService.selectExcelThesis(user.getUid());
            String mimeType = request.getServletContext().getMimeType("论文.xls");
            excel.exportExcel(user.getUid() + "_论文.xls", headers, list, response, mimeType);
        }


    }
    
}
