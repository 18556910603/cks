package sz.zp.cks.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;

public class ReadQRCode {
	
 public static String read(String qrcode) throws IOException {
	
	 String fileName="D:/code/"+qrcode;
	 
	 File file=new File(fileName);
	 
	 BufferedImage bufferedImage=ImageIO.read(file);
	 
	 QRCodeDecoder codeDecoder=new QRCodeDecoder();
	 
	 String result=new String (codeDecoder.decode(new MYQRCodeImage(bufferedImage)),"gb2312");
	 
	 System.out.println(result);
	  
	 return result;
}
}
