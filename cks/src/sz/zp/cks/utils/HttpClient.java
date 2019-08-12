package sz.zp.cks.utils;

import java.io.*;
import java.net.*;

import javax.net.ssl.*;

/**
 * @author Lenovo
 *
 */
public class HttpClient {
	  private String authorization = null;
	  private URL url = null;
	  private String encoding = "UTF-8";
	  
	  public static final String ACTION_GET="GET";
	  public static final String ACTION_POST="POST";
	  public static final String ACTION_PUT="PUT";
	  
	 /**
	   * 构造方法，传入url
	 * @param url
	 */
	public HttpClient(URL url)
	  {
	    this.url = url;
	  }
	/**
	 * 创建连接，返回HttpURLConnection
	 * @param connectMethod
	 * @return
	 * @throws Exception
	 */
	private HttpURLConnection CreateConnection(String connectMethod) throws Exception
	{
	  //
	  // Create the HTTP connection
	  //
	  
	  //System.out.println("Attempting request");
	  
	  HttpURLConnection conn =(HttpURLConnection)url.openConnection(); 
	     
	  if (conn instanceof HttpsURLConnection)
	  {
	    //
	    // For HTTPS we want to accept an anonymous SSL connection without having to import any certificates
	    //
	    
	    // Create an empty host name verifier
	    HttpsURLConnection https = (HttpsURLConnection)conn;
	    
	    https.setHostnameVerifier(new HostnameVerifier()
	    {
	      public boolean verify(String name, SSLSession s) { return true; }
	    });
	    
	  }

	  conn.setInstanceFollowRedirects(false);
	  conn.setDoOutput(true);
	  conn.setDoInput(true); 
	  conn.setUseCaches(false);
	  conn.setAllowUserInteraction(true); 
	  conn.setRequestMethod(connectMethod);
	  
	  if (authorization != null)
	    conn.setRequestProperty("Authorization","Basic " + authorization);
	  
	conn.setRequestProperty("Accept-Language", "zh-CN");
	conn.setRequestProperty("Referer", url.toString());
	conn.setRequestProperty("Charset", "UTF-8");
	//conn.setRequestProperty("Range", "bytes=" + 0 + "-" + 99999999);
	conn.setRequestProperty("Connection", "Keep-Alive");
	conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	conn.addRequestProperty("User-Agent", "Mozilla/4.76");
//	System.out.println("-------------------------输出getRequestProperties和set_agent----------------------------------------");
//	System.out.println(conn.getRequestProperties());
//	System.out.println("set_agent");
//	System.out.println("--------------------------结束---------------------------------------------------------------");
	  if (connectMethod.equals(ACTION_GET))
	  {
	    conn.connect();
	    //System.out.println("Got response code: " + conn.getResponseCode());
	  }
	  
	  return conn;
	}
	/**
	 * 打开给定的URL并发送输入流的内容。
	 * @param outStr
	 * @param connectMethod
	 * @return
	 * @throws Exception
	 */
	public String run(String outStr, String connectMethod) throws Exception
	{
	  HttpURLConnection conn = CreateConnection(connectMethod);

	  //
	  // Write the data out (if post)
	  //
	      
	  if (connectMethod.equals(ACTION_POST) || connectMethod.equals(ACTION_PUT))
	  {    
	    OutputStream out = conn.getOutputStream();  
	    byte[] btSend = outStr.getBytes(encoding);
	    //write(byte[] b, int off, int len)  将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此输出流。
	    out.write(btSend,0,btSend.length);    
	    out.flush(); 
	    
	    try
	    {
	      out.close();
	    }
	    catch(Exception e) {}
	  } 
	  
	  ByteArrayOutputStream buf = new ByteArrayOutputStream(256);
	  
	  // Note when we get the input stream here, the write is made to the server if we are posting
	  InputStream in = conn.getInputStream();
	  try
	  {
	    //int r = in.read();
	    int r = 0;
	    while ((r = in.read()) >= 0)
	    {
	      buf.write(r);
	    }
	  }
	  catch (Exception x)
	  {
	    x.printStackTrace();
	    //System.out.println("responseCode=" + conn.getResponseCode()); 
	    if ((conn.getResponseCode() / 100) != 2)
	      throw new IOException(conn.getResponseMessage()); 
	    throw x;
	  }

	  try 
	  {
	    in.close();
	  } catch(Exception e){}
	  return new String(buf.toByteArray(),encoding);
	}
	/**
	 * 
	 * @param user
	 * @param pass
	 */
	public void setAuthorization(String user, String pass)
	{
	  if (user == null)
	  {
	    authorization = null;
	    return;
	  }
	  authorization = user + ":" + ((pass == null) ? "" : pass);
	  authorization = Base64.encode(authorization.getBytes());
//	  System.out.println("--------------------------开始输出auth--------------------------------");
//	  System.out.println(authorization);
//	  System.out.println("---------------------------结束----------------------------------------");
	}
	static 
	{
	  try
	  {
	    // Create a trust manager that does not validate certificate chains
	    TrustManager[] trustAllCerts = new TrustManager[]{ new X509TrustManager()
	                                   {
	                                     public java.security.cert.X509Certificate[] getAcceptedIssuers() {return null;}
	                      
	                                     public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
	                      
	                                     public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
	                                   }};   
	                                   
	    SSLContext sc = SSLContext.getInstance("SSL"); 

	    sc.init(null, trustAllCerts, new java.security.SecureRandom());
	    SSLSocketFactory sslSocketFactory = sc.getSocketFactory();

	    HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory); 
	  }
	  catch(Exception e)
	  {
	    //System.out.println("Error creating dummy trust manager...");
	    e.printStackTrace();
	  }
	}
}
