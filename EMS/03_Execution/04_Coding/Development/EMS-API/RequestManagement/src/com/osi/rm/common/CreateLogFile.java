package com.osi.rm.common;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class CreateLogFile
{
    private static PatternLayout patternLayout = new PatternLayout("%d{ISO8601}\t%p\t%c\t%m%n");
    public static Logger getLogger(String fileName, Class clazz) throws Exception
        {
    		Logger logger = Logger.getLogger(clazz);
            FileAppender appender = new DailyRollingFileAppender(patternLayout, fileName, "'.'yyyy-MM-dd");
            appender.setImmediateFlush(true);
            logger.addAppender(appender);
            logger.setLevel(Level.INFO);
            return logger;
        }
}