import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.Properties;

/**
 *
 * HTTPͨѶ����:
 *
 */
public class HttpRequest {

	/**
	 * ֱ��֧��
	 *
	 * @return
	 */
	private String getRequestStr() {
		// ����֧����������
		XmlPacket xmlPkt = new XmlPacket("Payment", "ӥ��01");
		Map mpPodInfo = new Properties();
		mpPodInfo.put("BUSCOD", "N02030");
		mpPodInfo.put("BUSMOD", "00001");
		xmlPkt.putProperty("SDKPAYRQX", mpPodInfo);
		Map mpPayInfo = new Properties();
		mpPayInfo.put("YURREF", "222701440002");
		mpPayInfo.put("EPTDAT", "20190507");
		mpPayInfo.put("EPTTIM", "");
		mpPayInfo.put("DBTACC", "121933148810301");
		mpPayInfo.put("C_DBTBBK", "�Ϻ�");
		mpPayInfo.put("TRSAMT", "0.01");
		mpPayInfo.put("C_CCYNBR", "�����");
		mpPayInfo.put("C_STLCHN", "��ͨ");
		mpPayInfo.put("NUSAGE", "����");
		mpPayInfo.put("CRTACC", "6230663831001470940");
		mpPayInfo.put("CRTNAM", "Ǯ��Ԫ");
		mpPayInfo.put("CRTBNK", "N");
		mpPayInfo.put("CRTPVC", "����");
		mpPayInfo.put("CRTCTY", "��ͨ");
		mpPayInfo.put("CRTSQN", "APP060928001255");
		mpPayInfo.put("TRSTYP", "100001");

		xmlPkt.putProperty("SDKPAYDTX", mpPayInfo);
		System.out.println(xmlPkt.toXmlString());
		return xmlPkt.toXmlString();
	}

	/**
	 * ��ѯ��ϸ
	 *
	 * @return
	 */
	private String getRequestStr1() {
		// ����֧����������
		XmlPacket xmlPkt = new XmlPacket("GetTransInfo", "ӥ��01");
		Map mpPayInfo = new Properties();
		//���к�
		mpPayInfo.put("BBKNBR", "21");
		mpPayInfo.put("C_BBKNBR", "");
		//�˺�
		mpPayInfo.put("ACCNBR", "121933148810301");
		mpPayInfo.put("BGNDAT", "20190507");
		mpPayInfo.put("ENDDAT", "20190507");
		mpPayInfo.put("LOWAMT", "");
		mpPayInfo.put("HGHAMT", "");
		mpPayInfo.put("AMTCDR", "");


		xmlPkt.putProperty("SDKTSINFX", mpPayInfo);
		System.out.println(xmlPkt.toXmlString());
		return xmlPkt.toXmlString();
	}


	/**
	 * ֧������б��ѯ
	 *
	 * @return
	 */
	private String getRequestStr2() {
		// ����֧����������
		XmlPacket xmlPkt = new XmlPacket("NTQRYSTN", "ӥ��01");
		Map mpPayInfo = new Properties();
		//ֱ��֧��
		mpPayInfo.put("BUSCOD", "N02030");
		//��һģʽ
		mpPayInfo.put("BUSMOD", "00001");

		mpPayInfo.put("BGNDAT", "20190507");
		mpPayInfo.put("ENDDAT", "20190507");



		xmlPkt.putProperty("NTQRYSTNY1", mpPayInfo);
		System.out.println(xmlPkt.toXmlString());
		return xmlPkt.toXmlString();
	}

	/**
	 * ����ǰ�û������������ģ���÷��ر���
	 *
	 * @param data
	 * @return
	 * @throws MalformedURLException
	 */
	private String sendRequest(String data) {
		String result = "";
		try {
			URL url;
			url = new URL("http://localhost:8089");

			HttpURLConnection conn;
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			OutputStream os;
			os = conn.getOutputStream();
			os.write(data.toString().getBytes("gbk"));
			os.close();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn
					.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				result += line;
			}

			System.out.println(result);
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * �����صĽ��
	 *
	 * @param result
	 */
	private void processResult(String result) {
		if (result != null && result.length() > 0) {
			XmlPacket pktRsp = XmlPacket.valueOf(result);
			if (pktRsp != null) {
				String sRetCod = pktRsp.getRETCOD();
				if (sRetCod.equals("0")) {
					Map propPayResult = pktRsp.getProperty("NTQPAYRQZ", 0);
					String sREQSTS = (String) propPayResult.get("REQSTS");
					String sRTNFLG = (String) propPayResult.get("RTNFLG");
					if (sREQSTS.equals("FIN") && sRTNFLG.equals("F")) {
						System.out.println("֧��ʧ�ܣ�"
								+ propPayResult.get("ERRTXT"));
					} else {
						System.out.println("֧���ѱ���������֧��״̬��" + sREQSTS + "��");
					}
				} else if (sRetCod.equals("-9")) {
					System.out.println("֧��δ֪�쳣�����ѯ֧�����ȷ��֧��״̬��������Ϣ��"
							+ pktRsp.getERRMSG());
				} else {
					System.out.println("֧��ʧ�ܣ�" + pktRsp.getERRMSG());
				}
			} else {
				System.out.println("��Ӧ���Ľ���ʧ��");
			}
		}
	}

	public static void main(String[] args) {
		try {
			HttpRequest request = new HttpRequest();

			// ����������
			String data = request.getRequestStr1();

			// ����ǰ�û������������ģ���÷��ر���
			String result = request.sendRequest(data);

			// �����صĽ��
			request.processResult(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * ��ָ�� URL ����POST����������
	 *
	 * @param url
	 *            ��������� URL
	 * @param param
	 *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
	 * @return ������Զ����Դ����Ӧ���
	 */
	public static String sendPost1(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// �򿪺�URL֮�������
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(conn.getOutputStream());
			// �����������
			out.print(param);
			// flush������Ļ���
			out.flush();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("���� POST ��������쳣��"+e);
			e.printStackTrace();
		}
		//ʹ��finally�����ر��������������
		finally{
			try{
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return result;
	}

}