package com.distribution.wamoli.common.utils;


public class Globals {

    /**
     * 系统用户存在context中的key
     **/
    public static final String SYSTEM_USER = "SUB";

    /**
     * 系统用户JS对象形式存在context中的key
     **/
    public static final String JS_SYSTEM_USER = "JS_SUB";

    /**
     * 系统用户JS对象形式存在context中的key
     **/
    public static final String BASE_PATH = "basePath";


    public static final String DB_NAME = "mysql";

    /**
     * 基础标签 - 语言
     */
    public static final String COOKIE_LANGUAGE = "BASEFLAG_CookieLanguage";

    /**
     * 国际化文件资源
     **/
    public static final String LANGUAGE_MESSAGE = "LANGUAGE_MESSAGE";

    public static final String FILE_DOWNLOAD_ERROR = "FILE_DOWNLOAD_ERROR";

    /**
     * 用户空间目录名
     **/
    public static final String USER_SPACE = "users";

    /**
     * 临时空间目录名
     */
    public static final String USER_TEMPSPACE = "temp";

    /**
     * 系统公共模版目录名
     */
    public static final String USER_TEMPLATESPACE = "template";

    /**
     * 附件目录名
     */
    public static final String FILEATTACH_SPACE = "fileattach";

    /**
     * 系统头像存储目录名
     */
    public static final String PHOTO_SPACE = "photo";

    /**
     * 系统pdf预览存储目录名
     */
    public static final String PDF_SPACE = "pdf";

    /**
     * 文档资料目录名
     */
    public static final String DOCUMENT_SPACE = "document";

    /**
     * 系统首页
     **/
    public static final String USER_LOGIN_BASEPATH = "/index.html";

    /**
     * 400错误跳转
     **/
    public static final String ERROR_400_BASEPATH = "/classic/resources/html/error/400.html";

    /**
     * 404错误跳转
     **/
    public static final String ERROR_404_BASEPATH = "/classic/resources/html/error/404.html";

    /**
     * 505错误跳转
     **/
    public static final String ERROR_405_BASEPATH = "/classic/resources/html/error/405.html";

    /**
     * Download错误跳转
     **/
    public static final String ERROR_DOWNLOAD_BASEPATH = "/classic/resources/html/error/d_load_error.html";

    /**
     * 过滤地址
     **/
    public static final String[] NOFILTERS = new String[]{
            "index.html",
            "hyApp/index.html",                        //前台登录
            "login/validate.do",                //前台登录验证请求地址
            "hyApp/login/validate.do",
            "platform/personnel/getphoto.do",  //头像获取
            "hyApp/platform/personnel/getphoto.do",
    };

    private Globals() {
    }

}
