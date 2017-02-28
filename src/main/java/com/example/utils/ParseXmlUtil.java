package com.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
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
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            sw = new StringWriter();
            marshaller.marshal(object, sw);
            //logger.info(sw.toString().replace("standalone=\"yes\"", ""));
            return sw.toString().replace("standalone=\"yes\"", "");
        } catch (JAXBException j) {
            logger.error("转换xml出现问题，检查honeybee.ggzf.utils.ParseXmlUtil" + j.getMessage());
        }
        return object.toString() + "检查是否使用javax.xml.bind.annotation";
    }

    /**
     * @param xmlstr 工行明文xml字符串,忽略命名空间
     * @return 返回响应对象
     */
    public static <T> T xmlToJavaOject(String xmlstr, Class<T> c) {
        T t = null;
        try {
            t = c.newInstance();
            context = JAXBContext.newInstance(t.getClass());
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SAXParserFactory sax = SAXParserFactory.newInstance();
            sax.setNamespaceAware(false);
            org.xml.sax.XMLReader xmlReader = sax.newSAXParser().getXMLReader();
            Source source = new SAXSource(xmlReader, new InputSource(new StringReader(xmlstr)));
            //处理xml字符串，转换Java
            t = (T) unmarshaller.unmarshal(source);
        } catch (IllegalAccessException ill) {
            logger.error(ill.getMessage());
        } catch (InstantiationException in) {
            logger.error(in.getMessage());
        } catch (JAXBException j) {
            logger.error("xml转换对象出现问题，检查:{}", j.getMessage());
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
        } catch (SAXException sx) {
            logger.error(sx.getMessage());
        }
        return t;
    }

}
