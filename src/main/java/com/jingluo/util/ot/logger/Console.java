package com.jingluo.util.ot.logger;

/**
 * 简单日志工具
 * @ClassName Logger
 * @Author oldTree
 * @Date 2023/8/22
 * @Version 1.0
 */
@SuppressWarnings("all")
public interface Console {
    MessageHandler<Object> messageHandler= new MessageHandler<>();
    static void info(Integer value){
        info(String.valueOf(value.intValue()));
    }
    static void info(Long value){
        info(String.valueOf(value.longValue()));
    }
    static void info(Double value){
        info(String.valueOf(value.doubleValue()));
    }
    static void info(Float value){
        info(String.valueOf(value.floatValue()));
    }
    static void info(String message){
        Logger.DEFAULT.logger.print(
                messageHandler.handlerMessage(
                        LogLevelEnum.INFO,
                        messageHandler.messageFormat(
                                MessageHandler.PlaceString.INFO_PREFIX.value ,
                                message ,
                                MessageHandler.PlaceString.EMPTY.value
                        )));
        Logger.DEFAULT.logger.println(MessageHandler.PlaceString.EMPTY.value);
    }
    static void info(String caseMsg , Object... resources) {
        Logger.DEFAULT.logger.print(messageHandler.handlerCaseMessage(LogLevelEnum.INFO,caseMsg,resources));
        Logger.DEFAULT.logger.println(MessageHandler.PlaceString.EMPTY.value);
    }

    static void debug(Integer value){
        debug(String.valueOf(value.intValue()));
    }
    static void debug(Long value){
        debug(String.valueOf(value.longValue()));
    }
    static void debug(Double value){
        debug(String.valueOf(value.doubleValue()));
    }
    static void debug(Float value){
        debug(String.valueOf(value.floatValue()));
    }
    static void debug(String message){
        Logger.DEFAULT.logger.print(
                messageHandler.handlerMessage(
                        LogLevelEnum.DEBUG,
                        messageHandler.messageFormat(
                                MessageHandler.PlaceString.DEBUG_PREFIX.value ,
                                message ,
                                MessageHandler.PlaceString.EMPTY.value
                        )));
        Logger.DEFAULT.logger.println(MessageHandler.PlaceString.EMPTY.value);
    }
    static void debug(String caseMsg,Object... resources){
        Logger.DEFAULT.logger.print(messageHandler.handlerCaseMessage(LogLevelEnum.DEBUG,caseMsg,resources));
        Logger.DEFAULT.logger.println(MessageHandler.PlaceString.EMPTY.value);
    }

    static void warn(Integer value){
        warn(String.valueOf(value.intValue()));
    }
    static void warn(Long value){
        warn(String.valueOf(value.longValue()));
    }
    static void warn(Double value){
        warn(String.valueOf(value.doubleValue()));
    }
    static void warn(Float value){
        warn(String.valueOf(value.floatValue()));
    }
    static void warn(String message){
        Logger.DEFAULT.logger.print(
                messageHandler.handlerMessage(
                        LogLevelEnum.WARN,
                        messageHandler.messageFormat(
                                MessageHandler.PlaceString.WARN_PREFIX.value ,
                                message ,
                                MessageHandler.PlaceString.EMPTY.value
                        )));
        Logger.DEFAULT.logger.println(MessageHandler.PlaceString.EMPTY.value);
    }
    static void warn(String caseMsg ,Object... resources){
        Logger.DEFAULT.logger.print(messageHandler.handlerCaseMessage(LogLevelEnum.WARN,caseMsg,resources));
        Logger.DEFAULT.logger.println(MessageHandler.PlaceString.EMPTY.value);
    }
    static void error(Integer value){
        error(String.valueOf(value.intValue()));
    }
    static void error(Long value){
        error(String.valueOf(value.longValue()));
    }
    static void error(Double value){
        error(String.valueOf(value.doubleValue()));
    }
    static void error(Float value){
        error(String.valueOf(value.floatValue()));
    }
    static void error(String message) {
        Logger.DEFAULT.logger.print(
                messageHandler.handlerMessage(
                        LogLevelEnum.ERROR,
                        messageHandler.messageFormat(
                                MessageHandler.PlaceString.ERROR_PREFIX.value ,
                                message ,
                                MessageHandler.PlaceString.EMPTY.value
                        )));
        Logger.DEFAULT.logger.println(MessageHandler.PlaceString.EMPTY.value);
    }
    static void error(String caseMsg ,Object... resources){
        Logger.DEFAULT.logger.print(messageHandler.handlerCaseMessage(LogLevelEnum.ERROR,caseMsg,resources));
        Logger.DEFAULT.logger.println(MessageHandler.PlaceString.EMPTY.value);
    }


    static void fatal(Integer value){
        fatal(String.valueOf(value.intValue()));
    }
    static void fatal(Long value){
        fatal(String.valueOf(value.longValue()));
    }
    static void fatal(Double value){
        fatal(String.valueOf(value.doubleValue()));
    }
    static void fatal(Float value){
        fatal(String.valueOf(value.floatValue()));
    }
    static void fatal(String message) {
        Logger.DEFAULT.logger.print(
                messageHandler.handlerMessage(
                        LogLevelEnum.FATAL,
                        messageHandler.messageFormat(
                                MessageHandler.PlaceString.FATAL_PREFIX.value ,
                                message ,
                                MessageHandler.PlaceString.EMPTY.value
                        )));
        Logger.DEFAULT.logger.println(MessageHandler.PlaceString.EMPTY.value);
    }
    static void fatal(String caseMsg ,Object... resources){
        Logger.DEFAULT.logger.print(messageHandler.handlerCaseMessage(LogLevelEnum.FATAL,caseMsg,resources));
        Logger.DEFAULT.logger.println(MessageHandler.PlaceString.EMPTY.value);
    }
    void print(String message);
    void println(String message);

    /**
     * 日志枚举
     */
    enum Logger{
        DEFAULT(new ConsoleImpl());
        Logger(Console logger) {
            this.logger = logger;
        }
        public final Console logger;
    }

    /**
     * 日志级别枚举
     */
    enum LogLevelEnum{
        INFO(FontColorEnum.WHITE),
        DEBUG(FontColorEnum.GREEN),
        WARN(FontColorEnum.YELLOW),
        ERROR(FontColorEnum.RED),
        FATAL(FontColorEnum.RED),
        ;
        public final FontColorEnum level;
        LogLevelEnum(FontColorEnum level) {
            this.level = level;
        }
    }
    /**
     * 字体颜色枚举
     */
    enum FontColorEnum {
        RED("red","\033[31m","\033[0m"),
        GREEN("green","\033[32m","\033[0m"),
        YELLOW("yellow","\033[33m","\033[0m"),
        BLUE("blue","\033[34m","\033[0m"),
        PURPLE("purple","\033[35m","\033[0m"),
        CYAN("cyan","\033[36m","\033[0m"),
        PALE("pale","\033[37m","\033[0m"),
        WHITE("white","\033[38m","\033[0m");
        public final String colorKey;
        public final String start;
        public final String end;
        FontColorEnum(String colorKey,String start,String end){
            this.colorKey = colorKey;
            this.start = start;
            this.end = end;
        }
    }
}
