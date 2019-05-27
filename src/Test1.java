import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

public class Test1 {
    public static void main(String[] args) {
        String xml = "<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <device_info><![CDATA[1000]]></device_info>\n" +
                "   <nonce_str><![CDATA[TN55wO9Pba5yENl8]]></nonce_str>\n" +
                "   <sign><![CDATA[BDF0099C15FF7BC6B1585FBB110AB635]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <openid><![CDATA[oUpF8uN95-Ptaags6E_roPHg7AG0]]></openid>\n" +
                "   <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "   <trade_type><![CDATA[APP]]></trade_type>\n" +
                "   <bank_type><![CDATA[CCB_DEBIT]]></bank_type>\n" +
                "   <total_fee>1</total_fee>\n" +
                "   <fee_type><![CDATA[CNY]]></fee_type>\n" +
                "   <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>\n" +
                "   <out_trade_no><![CDATA[1415757673]]></out_trade_no>\n" +
                "   <attach><![CDATA[¶©µ¥¶îÍâÃèÊö]]></attach>\n" +
                "   <time_end><![CDATA[20141111170043]]></time_end>\n" +
                "   <trade_state><![CDATA[SUCCESS]]></trade_state>\n" +
                "</xml>";
        try {
            Document doc = DocumentHelper.parseText(xml);
            Element rootele = doc.getRootElement();
            System.out.println(rootele.getName());
            String return_code = rootele.elementTextTrim("return_code");
            System.out.println(return_code);
//            Iterator ite = rootele.elementIterator("NTQTSINFZ");
//            while (ite.hasNext()){
//                Element recordEle = (Element) ite.next();
//                System.out.println(recordEle.elementTextTrim("BUSNAM"));
//                System.out.println(recordEle.elementTextTrim("RPYBNK"));
//                System.out.println(recordEle.elementTextTrim("RPYADR"));
//                System.out.println(recordEle.elementTextTrim("RPYACC"));
//                System.out.println(recordEle.elementTextTrim("RPYNAM"));
//                System.out.println(recordEle.elementTextTrim("VLTDAT"));
//                System.out.println(recordEle.elementTextTrim("YURREF"));
//                System.out.println(recordEle.elementTextTrim("ETYTIM"));
//                System.out.println(recordEle.elementTextTrim("TRSAMT"));
//                System.out.println(recordEle.elementTextTrim("TRSBLV"));
//                System.out.println(new SimpleDateFormat("yyyyMMddhhmmss").parse(recordEle.elementTextTrim("VLTDAT")+recordEle.elementTextTrim("ETYTIM")));
//                System.out.println("=====================================");
//            }
        }catch (DocumentException e){
            e.printStackTrace();
        }
    }
}
