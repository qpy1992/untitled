import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.Properties;

/**
 *
 * HTTP通讯范例:
 *
 */
public class HttpRequest {

	/**
	 * 直接支付
	 *
	 * @return
	 */
	private String getRequestStr() {
		// 构造支付的请求报文
		XmlPacket xmlPkt = new XmlPacket("Payment", "鹰速01");
		Map mpPodInfo = new Properties();
		mpPodInfo.put("BUSCOD", "N02030");
		mpPodInfo.put("BUSMOD", "00001");
		xmlPkt.putProperty("SDKPAYRQX", mpPodInfo);
		Map mpPayInfo = new Properties();
		mpPayInfo.put("YURREF", "222701440002");
		mpPayInfo.put("EPTDAT", "20190507");
		mpPayInfo.put("EPTTIM", "");
		mpPayInfo.put("DBTACC", "121933148810301");
		mpPayInfo.put("C_DBTBBK", "上海");
		mpPayInfo.put("TRSAMT", "0.01");
		mpPayInfo.put("C_CCYNBR", "人民币");
		mpPayInfo.put("C_STLCHN", "普通");
		mpPayInfo.put("NUSAGE", "测试");
		mpPayInfo.put("CRTACC", "6230663831001470940");
		mpPayInfo.put("CRTNAM", "钱培元");
		mpPayInfo.put("CRTBNK", "N");
		mpPayInfo.put("CRTPVC", "江苏");
		mpPayInfo.put("CRTCTY", "南通");
		mpPayInfo.put("CRTSQN", "APP060928001255");
		mpPayInfo.put("TRSTYP", "100001");

		xmlPkt.putProperty("SDKPAYDTX", mpPayInfo);
		System.out.println(xmlPkt.toXmlString());
		return xmlPkt.toXmlString();
	}

	/**
	 * 查询明细
	 *
	 * @return
	 */
	private String getRequestStr1() {
		// 构造支付的请求报文
		XmlPacket xmlPkt = new XmlPacket("GetTransInfo", "鹰速01");
		Map mpPayInfo = new Properties();
		//分行号
		mpPayInfo.put("BBKNBR", "21");
		mpPayInfo.put("C_BBKNBR", "");
		//账号
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
	 * 支付结果列表查询
	 *
	 * @return
	 */
	private String getRequestStr2() {
		// 构造支付的请求报文
		XmlPacket xmlPkt = new XmlPacket("NTQRYSTN", "鹰速01");
		Map mpPayInfo = new Properties();
		//直接支付
		mpPayInfo.put("BUSCOD", "N02030");
		//单一模式
		mpPayInfo.put("BUSMOD", "00001");

		mpPayInfo.put("BGNDAT", "20190507");
		mpPayInfo.put("ENDDAT", "20190507");



		xmlPkt.putProperty("NTQRYSTNY1", mpPayInfo);
		System.out.println(xmlPkt.toXmlString());
		return xmlPkt.toXmlString();
	}

	/**
	 * 连接前置机，发送请求报文，获得返回报文
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
	 * 处理返回的结果
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
						System.out.println("支付失败："
								+ propPayResult.get("ERRTXT"));
					} else {
						System.out.println("支付已被银行受理（支付状态：" + sREQSTS + "）");
					}
				} else if (sRetCod.equals("-9")) {
					System.out.println("支付未知异常，请查询支付结果确认支付状态，错误信息："
							+ pktRsp.getERRMSG());
				} else {
					System.out.println("支付失败：" + pktRsp.getERRMSG());
				}
			} else {
				System.out.println("响应报文解析失败");
			}
		}
	}

	public static void main(String[] args) {
		try {
			HttpRequest request = new HttpRequest();

			// 生成请求报文
			String data = request.getRequestStr1();

			// 连接前置机，发送请求报文，获得返回报文
			String result = request.sendRequest(data);

			// 处理返回的结果
			request.processResult(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost1(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！"+e);
			e.printStackTrace();
		}
		//使用finally块来关闭输出流、输入流
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