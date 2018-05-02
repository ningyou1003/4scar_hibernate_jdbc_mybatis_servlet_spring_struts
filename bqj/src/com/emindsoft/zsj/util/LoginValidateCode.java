package com.emindsoft.zsj.util;

import com.jfinal.kit.StrKit;
import com.jfinal.render.Render;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Random;
 
public class LoginValidateCode extends Render{

    private String randomCodeKey = "";
    private int img_width = 85;
    private int img_height = 20;
     
    public LoginValidateCode(String randomCodeKey, int width, int height, int count, boolean isCaseInsensitive) {
        if(width <=0 || height <=0 || count <=0)
        {
            throw new IllegalArgumentException("Image width or height or count must be > 0");
        }
        this.img_width = width;
        this.img_height = height;
        this.randomCodeKey = randomCodeKey;
    }
     
    public void render() {
        BufferedImage image = new BufferedImage(img_width, img_height, BufferedImage.TYPE_INT_RGB);
        drawGraphic(image);
        response.setContentType("image/jpeg");
         
        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            ImageIO.write(image, "jpeg",sos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if (sos != null)
                try {sos.close();} catch (IOException e) {e.printStackTrace();}
        }
    }
 
    private void drawGraphic(BufferedImage image){
        // 获取图形上下文
        Graphics g = image.createGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, img_width, img_height);
        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(img_width);
            int y = random.nextInt(img_height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String token = encrypt(randomCodeKey);
        int start = random.nextInt(token.length()-4);
        String finalCode = token.substring(start, start+4); //token随机截取四位
        for(int i=0; i<finalCode.length(); i++){
            String c = String.valueOf(finalCode.charAt(i));
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(c, 16 * i, 19);
        }

        // 图象生效
        g.dispose();
    }
     
    /*
     * 给定范围获得随机颜色
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
     
    private static final String encrypt(String srcStr) {
        try {
            String result = "";
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
            for(byte b:bytes){
                String hex = Integer.toHexString(b&0xFF).toUpperCase();
                result += ((hex.length() ==1 ) ? "0" : "") + hex;
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validate(String randomCodeKey, String captcha) {
        if (StrKit.isBlank(randomCodeKey) || StrKit.isBlank(captcha) || captcha.length()!=4)
            return false;

        String token = encrypt(randomCodeKey);
        return token.contains(captcha.toUpperCase());
    }
 
}
