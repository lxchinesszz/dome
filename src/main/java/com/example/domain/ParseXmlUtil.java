package com.example.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by liuxin on 16/12/23.
 * 本类提供两个方法
 * 1.java生成xml
 * 2.xml转换java
 */
public class ParseXmlUtil {
    private final static Logger logger = LoggerFactory.getLogger(ParseXmlUtil.class);
    private static ParseXmlUtil parseXmlUtil;
    private static JAXBContext context;

    /**
     * @param object 实现xml注解的对象
     * @return 返回请求的xml
     */
    public static String parseToXml(Object object) {
        StringWriter sw = null;
        try {
            context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
            sw = new StringWriter();
            marshaller.marshal(object, sw);
            logger.info(sw.toString().replace("standalone=\"yes\"", "standalone=\"no\""));
            return sw.toString().replace("standalone=\"yes\"", "standalone=\"no\"");
        } catch (JAXBException j) {
            logger.error("转换xml出现问题，检查honeybee.ggzf.utils.ParseXmlUtil" + j.getMessage());
        }
        return object.toString() + "检查是否使用javax.xml.bind.annotation";
    }

    /**
     * @param xmlstr 工行明文xml字符串
     * @return 返回响应对象
     */
    public static Bosb2c xmlToJavaOject(String xmlstr) {
        try {
            context = JAXBContext.newInstance(Bosb2c.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            //TODO 处理xml字符串，转换Java
            Bosb2c bosb2c = new Bosb2c();
            bosb2c = (Bosb2c) unmarshaller.unmarshal(new StringReader(xmlstr));
            return bosb2c;
        } catch (JAXBException j) {
            logger.error(ParseXmlUtil.class.getPackage() + "【xml转换java类失败】");
        }
        return null;
    }

}
