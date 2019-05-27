import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;

import org.xml.sax.SAXException;

/**
 * 
 * ����XMLͨѶ������
 *
 */

public class XmlPacket{
	protected String FUNNAM;
	protected final String DATTYP="2";//�������͹̶�Ϊ2
	protected String LGNNAM;
	protected String RETCOD;
	protected String ERRMSG;
	protected Map data; //<String,Vector>
	
	public XmlPacket(){
		data = new Properties();
	}
	
	public XmlPacket(String sFUNNAM){
		FUNNAM = sFUNNAM;
		data = new Properties();
	}
	
	public XmlPacket(String sFUNNAM, String sLGNNAM){
		FUNNAM = sFUNNAM;
		LGNNAM = sLGNNAM;
		data = new Properties();
	}
	
	public String getFUNNAM() {
		return FUNNAM;
	}
	public void setFUNNAM(String fUNNAM) {
		FUNNAM = fUNNAM;
	}
	public String getLGNNAM() {
		return LGNNAM;
	}
	public void setLGNNAM(String lGNNAM) {
		LGNNAM = lGNNAM;
	}
	public String getRETCOD() {
		return RETCOD;
	}
	public void setRETCOD(String rETCOD) {
		RETCOD = rETCOD;
	}
	public String getERRMSG() {
		return ERRMSG;
	}
	public void setERRMSG(String eRRMSG) {
		ERRMSG = eRRMSG;
	}
	
	/**
	 * XML���ķ���ͷ�������Ƿ��ʾ�ɹ�
	 * @return
	 */
	public boolean isError(){
		if(RETCOD.equals("0")){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * �������ݼ�¼
	 * @param sSectionName
	 * @param mpData <String, String>
	 */
	public void putProperty(String sSectionName, Map mpData){
		if(data.containsKey(sSectionName)){
			Vector vt = (Vector)data.get(sSectionName);
			vt.add(mpData);
		}else{
			Vector vt = new Vector();
			vt.add(mpData);
			data.put(sSectionName, vt);	
		}		
	}
	
	/**
	 * ȡ��ָ���ӿڵ����ݼ�¼
	 * @param sSectionName
	 * @param index ��������0��ʼ
	 * @return Map<String,String>
	 */
	public Map getProperty(String sSectionName, int index){
		if(data.containsKey(sSectionName)){
			return (Map)((Vector)data.get(sSectionName)).get(index);
		}else{
			return null;
		}
	}
	
	/**
	 * ȡ���ƶ��ӿ����ݼ�¼��
	 * @param sSectionName
	 * @return
	 */
	public int getSectionSize(String sSectionName){
		if(data.containsKey(sSectionName)){
			Vector sec = (Vector)data.get(sSectionName);
			return sec.size();
		}
		return 0;
	}
	
	/**
	 * �ѱ���ת����XML�ַ���
	 * @return
	 */
	public String toXmlString(){
		StringBuffer sfData = new StringBuffer(
				"<?xml version='1.0' encoding = 'GBK'?>");
		sfData.append("<CMBSDKPGK>");
		sfData
				.append("<INFO><FUNNAM>"+FUNNAM+"</FUNNAM><DATTYP>"+DATTYP+"</DATTYP><LGNNAM>"+LGNNAM+"</LGNNAM></INFO>");
		int secSize = data.size();
		Iterator itr = data.keySet().iterator();
		while(itr.hasNext()){
			String secName = (String)itr.next();
			Vector vt = (Vector)data.get(secName);
			for(int i=0; i<vt.size(); i++){
				Map record = (Map)vt.get(i);
				Iterator itr2 = record.keySet().iterator();
				sfData.append("<"+secName+">");
				while(itr2.hasNext()){
					String datakey = (String)itr2.next();
					String dataValue = (String)record.get(datakey);
					sfData.append("<"+datakey+">");
					sfData.append(dataValue);
					sfData.append("</"+datakey+">");
				}
				sfData.append("</"+secName+">");
			}
		}
		sfData.append("</CMBSDKPGK>");		
		return sfData.toString();
	}
	
	/**
	 * ����xml�ַ�������ת��Ϊ���Ķ���
	 * @param message
	 */
	public static XmlPacket valueOf(String message) {
		SAXParserFactory saxfac = SAXParserFactory.newInstance();
		try {
			SAXParser saxparser = saxfac.newSAXParser();
			ByteArrayInputStream is = new ByteArrayInputStream(message.getBytes());
			XmlPacket xmlPkt= new XmlPacket();
			saxparser.parse(is, new SaxHandler(xmlPkt));
			is.close();
			return xmlPkt;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return null;
	}
}