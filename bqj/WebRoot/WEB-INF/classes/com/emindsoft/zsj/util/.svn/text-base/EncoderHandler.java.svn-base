package com.emindsoft.zsj.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class EncoderHandler {

	public void encoderQRCoder(String content, HttpServletResponse response) {
		try {
			int onColor = 0xFF000000;
			int offColor = 0xFFFFFFFF;
			int intX = 320;
			int intY = 320;
			String sErr = "L";
			String sEnc = "UTF-8";
//			String[] strarry = request.getQueryString().split("&");
//			for (String s : strarry) {
//				if (s.startsWith("chs=")) {
//					String[] sDemi = s.substring(4).split("x");
//					intX = Integer.parseInt(sDemi[0]);
//					intY = Integer.parseInt(sDemi[1]);
//				} else if (s.startsWith("chcl=")) {
//					onColor = Integer.parseInt(s.substring(5, 11), 16) | 0xFF000000;
//					offColor = Integer.parseInt(s.substring(11), 16) | 0xFF000000;
//				} else if (s.startsWith("chld=")) {
//					sErr = s.substring(5);
//				} else if (s.startsWith("choe=")) {
//					sEnc = s.substring(5);
//				} else if (s.startsWith("chl=")) {
//					content = URLDecoder.decode(s.substring(4), sEnc);
//				}
//			}

			Map hints = new HashMap();
			ErrorCorrectionLevel lErr;
			if (sErr.equals("H"))
				lErr = ErrorCorrectionLevel.H;
			else if (sErr.equals("Q"))
				lErr = ErrorCorrectionLevel.Q;
			else if (sErr.equals("M"))
				lErr = ErrorCorrectionLevel.M;
			else
				lErr = ErrorCorrectionLevel.L;
			hints.put(EncodeHintType.ERROR_CORRECTION, lErr);
			hints.put(EncodeHintType.CHARACTER_SET, sEnc);

			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Cache-Control", "no-store");
			ServletOutputStream rsp = response.getOutputStream();
			QRCodeWriter qrWriter = new QRCodeWriter();
			try {
				BitMatrix bitMatrix = qrWriter.encode(content,
						BarcodeFormat.QR_CODE, intX, intY, hints);
				MatrixToImageWriter.writeToStream(bitMatrix, "png", rsp,
						new MatrixToImageConfig(onColor, offColor));
			} catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rsp.flush();
			rsp.close();
			response.flushBuffer();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
