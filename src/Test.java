import org.dom4j.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class Test {
    static String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
            "<CMBSDKPGK>\n" +
            "    <INFO>\n" +
            "        <DATTYP>2</DATTYP>\n" +
            "        <ERRMSG></ERRMSG>\n" +
            "        <FUNNAM>GetTransInfo</FUNNAM>\n" +
            "        <LGNNAM>鹰速01</LGNNAM>\n" +
            "        <RETCOD>0</RETCOD>\n" +
            "    </INFO>\n" +
            "    <NTQTSINFZ>\n" +
            "        <AMTCDR>D</AMTCDR>\n" +
            "        <APDFLG>Y</APDFLG>\n" +
            "        <ATHFLG>N</ATHFLG>\n" +
            "        <BBKNBR>21</BBKNBR>\n" +
            "        <BUSNAM>企业银行支付</BUSNAM>\n" +
            "        <C_ATHFLG>无</C_ATHFLG>\n" +
            "        <C_BBKNBR>上海</C_BBKNBR>\n" +
            "        <C_ETYDAT>2019年05月07日</C_ETYDAT>\n" +
            "        <C_GSBBBK></C_GSBBBK>\n" +
            "        <C_RPYBBK></C_RPYBBK>\n" +
            "        <C_TRSAMT>0.01</C_TRSAMT>\n" +
            "        <C_TRSAMTD>0.01</C_TRSAMTD>\n" +
            "        <C_TRSBLV>19,999.99</C_TRSBLV>\n" +
            "        <C_VLTDAT>2019年05月07日</C_VLTDAT>\n" +
            "        <ETYDAT>20190507</ETYDAT>\n" +
            "        <ETYTIM>153546</ETYTIM>\n" +
            "        <GSBBBK></GSBBBK>\n" +
            "        <INFFLG>1</INFFLG>\n" +
            "        <NAREXT>19S2005516698</NAREXT>\n" +
            "        <NARYUR>测试</NARYUR>\n" +
            "        <REFNBR>G6636100090755C</REFNBR>\n" +
            "        <REFSUB></REFSUB>\n" +
            "        <REQNBR>1645686275</REQNBR>\n" +
            "        <RPYACC>6214855136291875</RPYACC>\n" +
            "        <RPYADR>江苏南通</RPYADR>\n" +
            "        <RPYBBK></RPYBBK>\n" +
            "        <RPYBNK>招商银行</RPYBNK>\n" +
            "        <RPYNAM>钱培元</RPYNAM>\n" +
            "        <RSV30Z>**</RSV30Z>\n" +
            "        <RSV31Z>10</RSV31Z>\n" +
            "        <RSV50Z></RSV50Z>\n" +
            "        <TRSAMT>-0.01</TRSAMT>\n" +
            "        <TRSAMTD>0.01</TRSAMTD>\n" +
            "        <TRSANL>CPGATR</TRSANL>\n" +
            "        <TRSBLV>19999.99</TRSBLV>\n" +
            "        <TRSCOD>CPAA</TRSCOD>\n" +
            "        <VLTDAT>20190507</VLTDAT>\n" +
            "        <YURREF>222701440001</YURREF>\n" +
            "    </NTQTSINFZ>\n" +
            "    <NTQTSINFZ>\n" +
            "        <AMTCDR>D</AMTCDR>\n" +
            "        <APDFLG>Y</APDFLG>\n" +
            "        <ATHFLG>N</ATHFLG>\n" +
            "        <BBKNBR>21</BBKNBR>\n" +
            "        <BUSNAM>企业银行支付</BUSNAM>\n" +
            "        <C_ATHFLG>无</C_ATHFLG>\n" +
            "        <C_BBKNBR>上海</C_BBKNBR>\n" +
            "        <C_ETYDAT>2019年05月07日</C_ETYDAT>\n" +
            "        <C_GSBBBK></C_GSBBBK>\n" +
            "        <C_RPYBBK></C_RPYBBK>\n" +
            "        <C_TRSAMT>0.01</C_TRSAMT>\n" +
            "        <C_TRSAMTD>0.01</C_TRSAMTD>\n" +
            "        <C_TRSBLV>19,999.98</C_TRSBLV>\n" +
            "        <C_VLTDAT>2019年05月07日</C_VLTDAT>\n" +
            "        <ETYDAT>20190507</ETYDAT>\n" +
            "        <ETYTIM>154156</ETYTIM>\n" +
            "        <GSBBBK></GSBBBK>\n" +
            "        <INFFLG>1</INFFLG>\n" +
            "        <NAREXT>19S9005521415</NAREXT>\n" +
            "        <NARYUR>测试</NARYUR>\n" +
            "        <REFNBR>G6636100092137C</REFNBR>\n" +
            "        <REFSUB></REFSUB>\n" +
            "        <REQNBR>1645710495</REQNBR>\n" +
            "        <RPYACC>6230663831001470940</RPYACC>\n" +
            "        <RPYADR>江苏南通</RPYADR>\n" +
            "        <RPYBBK></RPYBBK>\n" +
            "        <RPYBNK>N</RPYBNK>\n" +
            "        <RPYNAM>钱培元</RPYNAM>\n" +
            "        <RSV30Z>**</RSV30Z>\n" +
            "        <RSV31Z>10</RSV31Z>\n" +
            "        <RSV50Z></RSV50Z>\n" +
            "        <TRSAMT>-0.01</TRSAMT>\n" +
            "        <TRSAMTD>0.01</TRSAMTD>\n" +
            "        <TRSANL>CPGATR</TRSANL>\n" +
            "        <TRSBLV>19999.98</TRSBLV>\n" +
            "        <TRSCOD>CPAA</TRSCOD>\n" +
            "        <VLTDAT>20190507</VLTDAT>\n" +
            "        <YURREF>222701440002</YURREF>\n" +
            "    </NTQTSINFZ>\n" +
            "    <NTQTSINFZ>\n" +
            "        <AMTCDR>C</AMTCDR>\n" +
            "        <APDFLG>Y</APDFLG>\n" +
            "        <ATHFLG></ATHFLG>\n" +
            "        <BBKNBR>21</BBKNBR>\n" +
            "        <CHKNBR></CHKNBR>\n" +
            "        <C_ATHFLG>无</C_ATHFLG>\n" +
            "        <C_BBKNBR>上海</C_BBKNBR>\n" +
            "        <C_ETYDAT>2019年05月07日</C_ETYDAT>\n" +
            "        <C_GSBBBK></C_GSBBBK>\n" +
            "        <C_RPYBBK></C_RPYBBK>\n" +
            "        <C_TRSAMT>0.01</C_TRSAMT>\n" +
            "        <C_TRSAMTC>0.01</C_TRSAMTC>\n" +
            "        <C_TRSBLV>19,999.99</C_TRSBLV>\n" +
            "        <C_VLTDAT>2019年05月07日</C_VLTDAT>\n" +
            "        <ETYDAT>20190507</ETYDAT>\n" +
            "        <ETYTIM>154402</ETYTIM>\n" +
            "        <GSBBBK></GSBBBK>\n" +
            "        <NAREXT>19R6003091506</NAREXT>\n" +
            "        <NARYUR>转账</NARYUR>\n" +
            "        <REFNBR>G0297900120966C</REFNBR>\n" +
            "        <REFSUB></REFSUB>\n" +
            "        <RPYACC>6214855136291875</RPYACC>\n" +
            "        <RPYADR>南通分行海门支行</RPYADR>\n" +
            "        <RPYBBK></RPYBBK>\n" +
            "        <RPYBNK>招商银行</RPYBNK>\n" +
            "        <RPYNAM>钱培元</RPYNAM>\n" +
            "        <RSV30Z>**</RSV30Z>\n" +
            "        <RSV31Z>10</RSV31Z>\n" +
            "        <RSV50Z></RSV50Z>\n" +
            "        <TRSAMT>0.01</TRSAMT>\n" +
            "        <TRSAMTC>0.01</TRSAMTC>\n" +
            "        <TRSANL>CPGATR</TRSANL>\n" +
            "        <TRSBLV>19999.99</TRSBLV>\n" +
            "        <TRSCOD>CPUA</TRSCOD>\n" +
            "        <VLTDAT>20190507</VLTDAT>\n" +
            "        <YURREF></YURREF>\n" +
            "    </NTQTSINFZ>\n" +
            "    <NTQTSINFZ>\n" +
            "        <AMTCDR>C</AMTCDR>\n" +
            "        <APDFLG>Y</APDFLG>\n" +
            "        <ATHFLG></ATHFLG>\n" +
            "        <BBKNBR>21</BBKNBR>\n" +
            "        <CHKNBR></CHKNBR>\n" +
            "        <C_ATHFLG>无</C_ATHFLG>\n" +
            "        <C_BBKNBR>上海</C_BBKNBR>\n" +
            "        <C_ETYDAT>2019年05月07日</C_ETYDAT>\n" +
            "        <C_GSBBBK></C_GSBBBK>\n" +
            "        <C_RPYBBK></C_RPYBBK>\n" +
            "        <C_TRSAMT>0.01</C_TRSAMT>\n" +
            "        <C_TRSAMTC>0.01</C_TRSAMTC>\n" +
            "        <C_TRSBLV>20,000.00</C_TRSBLV>\n" +
            "        <C_VLTDAT>2019年05月07日</C_VLTDAT>\n" +
            "        <ETYDAT>20190507</ETYDAT>\n" +
            "        <ETYTIM>154622</ETYTIM>\n" +
            "        <GSBBBK></GSBBBK>\n" +
            "        <NAREXT>00502883385</NAREXT>\n" +
            "        <NARYUR>转账</NARYUR>\n" +
            "        <REFNBR>KK780800176353C</REFNBR>\n" +
            "        <REFSUB></REFSUB>\n" +
            "        <RPYACC>6230663831001470940</RPYACC>\n" +
            "        <RPYBBK></RPYBBK>\n" +
            "        <RPYBNK>江苏省农村信用社联合社</RPYBNK>\n" +
            "        <RPYNAM>钱培元</RPYNAM>\n" +
            "        <RSV30Z>**</RSV30Z>\n" +
            "        <RSV31Z>10</RSV31Z>\n" +
            "        <RSV50Z></RSV50Z>\n" +
            "        <TRSAMT>0.01</TRSAMT>\n" +
            "        <TRSAMTC>0.01</TRSAMTC>\n" +
            "        <TRSANL>NPGATR</TRSANL>\n" +
            "        <TRSBLV>20000.00</TRSBLV>\n" +
            "        <TRSCOD>NPT2</TRSCOD>\n" +
            "        <VLTDAT>20190507</VLTDAT>\n" +
            "        <YURREF></YURREF>\n" +
            "    </NTQTSINFZ>\n" +
            "</CMBSDKPGK>";
    public static void main(String[] args) {
        try {
            Document doc = DocumentHelper.parseText(xml);
            Element rootele = doc.getRootElement();
            System.out.println(rootele.getName());
            Iterator ite = rootele.elementIterator("NTQTSINFZ");
            while (ite.hasNext()){
                Element recordEle = (Element) ite.next();
                System.out.println(recordEle.elementTextTrim("BUSNAM"));
                System.out.println(recordEle.elementTextTrim("RPYBNK"));
                System.out.println(recordEle.elementTextTrim("RPYADR"));
                System.out.println(recordEle.elementTextTrim("RPYACC"));
                System.out.println(recordEle.elementTextTrim("RPYNAM"));
                System.out.println(recordEle.elementTextTrim("VLTDAT"));
                System.out.println(recordEle.elementTextTrim("YURREF"));
                System.out.println(recordEle.elementTextTrim("ETYTIM"));
                System.out.println(recordEle.elementTextTrim("TRSAMT"));
                System.out.println(recordEle.elementTextTrim("TRSBLV"));
                System.out.println(new SimpleDateFormat("yyyyMMddhhmmss").parse(recordEle.elementTextTrim("VLTDAT")+recordEle.elementTextTrim("ETYTIM")));
                System.out.println("=====================================");
            }
        }catch (DocumentException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
}
