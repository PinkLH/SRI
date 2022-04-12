package net.cqwu.SRI.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 文件操作工具类
 */
public class UpfilePath {
    /**
     * 求文件保存的根目录
     */
    public static String getSRIPath(HttpServletRequest request){
        String syspath = request.getSession().getServletContext().getRealPath("");
        syspath = syspath.replaceAll("SRI", "");
        return syspath.substring(0, syspath.length() - 1) + "SRI_upload" + File.separator;
    }
}
