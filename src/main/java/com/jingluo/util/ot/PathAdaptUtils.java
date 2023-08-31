package com.jingluo.util.ot;

import org.apache.commons.lang3.StringUtils;

/**
 * 自适应mac/linux/windows等路径
 */
public class PathAdaptUtils {
    private static final String OS_KEY = "os.name";
    private static final String SPIT_1 = "\\";   //\\
    private static final String SPIT_2 = "/";  // /


    public static void main(String[] args) {
        String path = "D:/user/app\\haha\\exe.file";
        System.out.println(adaptPath(path));
    }

    public static String adaptPath(String path){
        if(StringUtils.isEmpty(path)){
            return null;
        }
        SystemTypeEnum systemTypeEnum = getOsType();
        if(systemTypeEnum == SystemTypeEnum.WIN){
            return handleWindowsPathStr(path);
        }
        if(systemTypeEnum == SystemTypeEnum.MAC || systemTypeEnum == SystemTypeEnum.LINUX){
            return handleMacOrLinuxPathStr(path);
        }
        return "";
    }

    private static String handleMacOrLinuxPathStr(String path) {
        return handleAndReplaceAllPathStr(path,SPIT_1,SPIT_2);
    }

    private static String handleAndReplaceAllPathStr(String path ,String targetStr ,String replaceStr){
        int indexOf = path.indexOf(targetStr);
        if(indexOf == (-1)) {
            return path;
        }
        path = path.replaceAll(targetStr,replaceStr);
        return handleWindowsPathStr(path);
    }

    /**
     * 处理windows的path
     * @param path
     * @return
     */
    private static String handleWindowsPathStr(String path) {
        return handleAndReplaceAllPathStr(path,SPIT_2,SPIT_1);
    }

    /**
     * 获取当前系统类型
     * @return
     */
    private static SystemTypeEnum getOsType(){
        String osName = System.getProperty(OS_KEY);
        // 当前系统为Windows操作系统
        if(osName.startsWith(SystemTypeEnum.WIN.osName)) {
            return SystemTypeEnum.WIN;
        }
        // 当前系统为Linux操作系统
        if(osName.startsWith(SystemTypeEnum.LINUX.osName)) {
            return SystemTypeEnum.LINUX;
        }
        // 当前系统为Linux操作系统
        if(osName.startsWith(SystemTypeEnum.MAC.osName)) {
            return SystemTypeEnum.MAC;
        }
        if(osName.contains(SystemTypeEnum.SOLARIS.osName)
                || osName.contains(SystemTypeEnum.SUNOS.osName)
                || osName.contains(SystemTypeEnum.AIX.osName)
                || osName.contains(SystemTypeEnum.FREEBSD.osName)
                || osName.contains(SystemTypeEnum.OPENBSD.osName)
                || osName.contains(SystemTypeEnum.NETBSD.osName)) {
            return SystemTypeEnum.OTHER;
        }
        return null;
    }

    /**
     * 系统类型枚举类
     */
    enum SystemTypeEnum {
        WIN("Win"), //windows系统
        LINUX("Linux"), //linux系统
        MAC("Mac OS"),//mac系统
        OTHER("Other"), //其他系统
        SOLARIS("Solaris"), //其他系统
        FREEBSD("FreeBSD"), //其他系统
        SUNOS("SunOS"), //其他系统
        AIX("AIX"), //其他系统
        NETBSD("NetBSD"), //其他系统
        OPENBSD("OpenBSD"), //其他系统
        ;
        SystemTypeEnum(String osName) {
            this.osName = osName;
        }
        public String osName;
    }
}
