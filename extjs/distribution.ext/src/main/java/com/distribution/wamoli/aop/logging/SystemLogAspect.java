package com.distribution.wamoli.aop.logging;


import org.springframework.stereotype.Component;

/**
 *
 *
 * http://tiangai.iteye.com/blog/2103708
 */
//@Aspect
@Component
public class SystemLogAspect {
//    //注入Service用于把日志保存数据库
//	@Autowired
//	private SystemLogService logService;
//    //本地异常日志记录对象
//    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
//
//    //Service层切点
//    @Pointcut("@annotation(com.yihere.common.interceptor.log.annotation.SystemServiceLog)")
//    public void serviceAspect() {
//    }
//
//    //Controller层切点
//    @Pointcut("@annotation(com.yihere.common.interceptor.log.annotation.SystemControllerLog)")
//    public void controllerAspect() {
//    }
//
//    /**
//     * 前置通知 用于拦截Controller层记录用户的操作
//     *
//     * @param joinPoint 切点
//     */
//    @Before("controllerAspect()")
//    public void doBefore(JoinPoint joinPoint) {
//
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
//        //读取session中的用户
//        UserBean userBean = (UserBean) request.getSession().getAttribute(Globals.SYSTEM_USER);
//        //请求的IP
//        String ip =getIpAddr(request);
//        try {
//            //*========控制台输出=========*//
////            System.out.println("=====前置通知开始=====");
////            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
////            System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));
////            System.out.println("请求人:" + userBean.getName());
////            System.out.println("请求IP:" + ip);
//            //*========数据库日志=========*//
//          //  FSystemlog log = SpringContextHolder.getBean("logxx");
//
//
//            FSystemlog log = (FSystemlog) Local.getBean("fSystemlog");
//            log.setDescription(getControllerMethodDescription(joinPoint));
//            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            log.setType("0");
//            log.setId(IdGen.uuid());
//            log.setIp(ip);
//            log.setExceptioncode("");
//            log.setExceptiondetail("");
//            log.setParams("");
//            log.setCreater(userBean.getName());
//            log.setCreatedate(DateUtils.getCurrentDate());
//            //保存数据库
//            logService.add(log);
//            System.out.println("=====前置通知结束=====");
//        } catch (Exception e) {
//            //记录本地异常日志
//            logger.error("==前置通知异常==");
//            logger.error("异常信息:{}", e.getMessage());
//        }
//    }
//
//    /**
//     * 异常通知 用于拦截service层记录异常日志
//     *
//     * @param joinPoint
//     * @param e
//     */
//    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
//    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
//        //读取session中的用户
//        UserBean userBean = (UserBean) request.getSession().getAttribute(Globals.SYSTEM_USER);
//        //获取请求ip
//        String ip =getIpAddr(request);
//        //获取用户请求方法的参数并序列化为JSON格式字符串
//        String params = "";
//        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
//            for (int i = 0; i < joinPoint.getArgs().length; i++) {
//                params += JsonUtil.toJsonString(joinPoint.getArgs()[i]) + ";";
//            }
//        }
//        try {
//              /*========控制台输出=========*/
////            System.out.println("=====异常通知开始=====");
////            System.out.println("异常代码:" + e.getClass().getName());
////            System.out.println("异常信息:" + e.getMessage());
////            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
////            System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));
////            System.out.println("请求人:" + userBean.getName());
////            System.out.println("请求IP:" + ip);
////            System.out.println("请求参数:" + params);
//
//               /*==========数据库日志=========*/
//
//
//            FSystemlog log = (FSystemlog) Local.getBean("fSystemlog");
//
//            log.setDescription(getServiceMthodDescription(joinPoint));
//            log.setExceptioncode(e.getClass().getName());
//            log.setType("1");
//            log.setExceptiondetail(e.getMessage());
//            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            log.setParams(params);
//            log.setCreater(userBean.getName());
//            log.setCreatedate(DateUtils.getCurrentDate());
//            log.setIp(ip);
//            log.setId(IdGen.uuid());
//            //保存数据库
//            logService.add(log);
//            System.out.println("=====异常通知结束=====");
//        } catch (Exception ex) {
//            //记录本地异常日志
//            logger.error("==异常通知异常==");
//            logger.error("异常信息:{}", ex.getMessage());
//        }
//         /*==========记录本地异常日志==========*/
//     //   logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);
//
//    }
//
//
//    /**
//     * 获取注解中对方法的描述信息 用于service层注解
//     *
//     * @param joinPoint 切点
//     * @return 方法描述
//     * @throws Exception
//     */
//    public static String getServiceMthodDescription(JoinPoint joinPoint)
//            throws Exception {
//        String targetName = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        Object[] arguments = joinPoint.getArgs();
//        Class targetClass = Class.forName(targetName);
//        Method[] methods = targetClass.getMethods();
//        String description = "";
//        for (Method method : methods) {
//            if (method.getName().equals(methodName)) {
//                Class[] clazzs = method.getParameterTypes();
//                if (clazzs.length == arguments.length) {
//                    description = method.getAnnotation(SystemServiceLog.class).description();
//                    break;
//                }
//            }
//        }
//        return description;
//    }
//
//    /**
//     * 获取注解中对方法的描述信息 用于Controller层注解
//     *
//     * @param joinPoint 切点
//     * @return 方法描述
//     * @throws Exception
//     */
//    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
//        String targetName = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        Object[] arguments = joinPoint.getArgs();
//        Class targetClass = Class.forName(targetName);
//        Method[] methods = targetClass.getMethods();
//        String description = "";
//        for (Method method : methods) {
//            if (method.getName().equals(methodName)) {
//                Class[] clazzs = method.getParameterTypes();
//                if (clazzs.length == arguments.length) {
//                    description = method.getAnnotation(SystemControllerLog.class).description();
//                    break;
//                }
//            }
//        }
//        return description;
//    }
//
//
//
//    public String getIpAddr(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }
}
