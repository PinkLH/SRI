package net.cqwu.SRI.controller;

import net.cqwu.SRI.entity.Awork;
import net.cqwu.SRI.entity.Swork;
import net.cqwu.SRI.entity.Users;
import net.cqwu.SRI.service.UserAworkService;
import net.cqwu.SRI.service.UserSworkService;
import net.cqwu.SRI.util.UpfilePath;
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
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
    public String SelectWorkInfo(String wname, Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        this.SelectSworkInfo(model, user, wname);
        this.SelectAworkInfo(model, user, wname);
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
        swork.setSwtime(new Date());
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


}
