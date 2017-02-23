package com.example.mock;

import com.example.domain.Bosb2c;
import com.example.domain.ParseXmlUtil;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JacksonJsonParser;

import static org.mockito.Mockito.*;

/**
 * Created by liuxin on 17/1/17.
 * 模拟银行返回的信息
 */
public class MoniBankRet {
    private final static Logger log = LoggerFactory.getLogger(MoniBankRet.class);

    @Test
    public void bankRet() {
        Teacher teacher = mock(Teacher.class);
        when(teacher.getName()).thenReturn("lixiaolong");
        Assert.assertEquals("lixiaolong", teacher.getName());
        verify(teacher, times(1));
    }

    /**
     * 上行实时退款相应xml解析正常
     */
    @Test
    public void xml() {
        String xmls = "<?xml version=\"1.0\" encoding=\"GB2312\"?>\n" +
                "<BOSB2C>" +
                "<retCode>0000</retCode>" +
                "<errMsg>成功</errMsg>" +
                "<opResult>" +
                "<merchantID>123789</merchantID>" +
                "<orderResult>0</orderResult>" +
                "<comment>成功</comment>" +
                "<signData>qhX49UwlgDDVjkBxEepxjISUoV5uT3QRMXJYgJ5D6NQd+bNeIC0TbsauDYKjAyR/aBwqKLcMbLjZnLBdYb2k8X7NtP/pnjoitqYGqIFpL35vO3P6PL7AozneXrpMkDa8lGsHJ0JAbrQaoI6wQZ6xnZ311cvxSLjt1RZ6DbAavBM=</signData>" +
                "</opResult>" +
                "</BOSB2C>";
        RefundXml refundXml = mock(RefundXml.class);
        when(refundXml.getXml()).thenReturn(xmls);
        Bosb2c bosb2c = ParseXmlUtil.xmlToJavaOject(refundXml.getXml());
        log.info(new Gson().toJson(bosb2c));
    }
}
