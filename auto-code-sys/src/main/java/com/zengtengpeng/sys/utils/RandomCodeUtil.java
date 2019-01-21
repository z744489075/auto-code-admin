package com.zengtengpeng.sys.utils;

import com.zengtengpeng.sys.constant.SessionConstant;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;


/**
 * 随机码工具类，包括生成验证码和验证码图片
 * 
 */
public abstract class RandomCodeUtil {

	/***
	 * 随机产生的字符串
	 */
	private static final String randString = "13456789ABCDEFGHIJKLMNPQRSTUVWXY";

	/**
	 * 返回指定长度的随机字符串
	 * 
	 * @since
	 * @param length
	 * @return
	 */
	public static String getVerifyCode(int length) {
		return getString(randString, length);
	}

	/**
	 * 生成包含指定字符的验证码图片
	 * 
	 * @since
	 * @param request
	 * @param response
	 * @param value 待显示的验证码
	 */
	public static void createValidateCode( HttpServletResponse response, String value) {
		Random random = new Random();
		int width = 80;// 图片宽
		int height = 26;// 图片高
		int lineSize = 40;// 干扰线数量
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "no-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		// BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		g.setColor(getRandColor(110, 133));
		// 绘制干扰线
		for (int i = 0; i <= lineSize; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(13);
			int yl = random.nextInt(15);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 绘制随机字符
		for (int i = 1; i <= value.length(); i++) {
			drowString(g, value.charAt(i - 1), i);
		}
		g.dispose();
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());// 将内存中的图片通过流动形式输出到客户端
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * 判断用户输入的验证码是否有效
     * @param request 对象
     * @param paramName 验证码的请求参数名称
     * @return
     */
    public static boolean isValidImageVerifyCode(HttpServletRequest request, String paramName) {
        String inVerifyCode = request.getParameter(paramName);
        HttpSession session = request.getSession();
        String verifyCode = (String) session.getAttribute(SessionConstant.imageLgoinCode);
        if (verifyCode != null) {
            session.removeAttribute(SessionConstant.imageLgoinCode);
        }
        return !StringUtils.isEmpty(inVerifyCode) && inVerifyCode.equalsIgnoreCase(verifyCode);
    }



	/*
	 * 获得颜色
	 */
	private static Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		Random random = new Random();
		int r = fc + random.nextInt(bc - fc - 16);
		int g = fc + random.nextInt(bc - fc - 14);
		int b = fc + random.nextInt(bc - fc - 18);
		return new Color(r, g, b);
	}

	/*
	 * 绘制字符串
	 */
	private static void drowString(Graphics g, char c, int i) {
		Random random = new Random();
		g.setFont(new Font("Fixedsys", Font.CENTER_BASELINE, 18));
		g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
		g.translate(random.nextInt(3), random.nextInt(3));
		g.drawString(c + "", 13 * i, 16);
	}

    /**
     * 返回min(包括)和max(包括)之间的一个随机整数
     *
     * @param min 返回的最小值
     * @param max 返回的最大值
     * @return
     */
    public static final int getInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    /**
     * 随机生成指定长度的数字字符串
     *
     * @param length
     */
    public static final String getIntString(int length) {
        if (length <= 0) {
            return "";
        }
        String str = Double.toString(Math.random()).substring(2);
        int realLen = str.length();
        if (length > realLen) {
            StringBuilder sb = new StringBuilder(length).append(str);
            int remain = length - realLen;
            while (remain > 0) {
                str = Double.toString(Math.random()).substring(2);
                realLen = str.length();
                if (remain >= realLen) {
                    sb.append(str);
                } else {
                    sb.append(str.substring(0, remain));
                }
                remain -= realLen;
            }
            return sb.toString();
        } else {
            return str.substring(0, length);
        }
    }

    /**
     * 随机返回0 ~ 1之间(不包括1)的双精度浮点数
     *
     * @return
     */
    public static final double getDouble() {
        return Math.random();
    }

    /**
     * 随机返回0 ~ 1之间(不包括1)的单精度浮点数
     *
     * @return
     */
    public static final float getFloat() {
        return new Random().nextFloat();
    }

    /**
     * 随机返回一个long值<br>
     * 因为 <code>java.util.Random</code> 类使用只以 48 位表示的种子，所以此算法不会返回所有可能的 long值。
     *
     * @return
     */
    public static final long getLong() {
        return new Random().nextLong();
    }

    /**
     * 随机返回一个boolean值
     *
     * @return
     */
    public static final boolean getBoolean() {
        return new Random().nextBoolean();
    }

    /**
     * 根据指定的字符数组，随机返回其中的一个字符<br>
     * 如果字符数组为空，则默认返回一个空格字符<code>' '</code>
     *
     * @param chars
     * @return
     */
    public static final char getChar(char[] chars) {
        switch (chars.length) {
            case 0:
                return ' ';
            case 1:
                return chars[0];
            default:
                return chars[new Random().nextInt(chars.length)];
        }
    }

    /**
     * 根据指定的字符串，随机返回其中的一个字符<br>
     * 如果为空字符串，则默认返回一个空格字符<code>' '</code>
     *
     * @param str
     * @return
     */
    public static final char getChar(String str) {
        return getChar(str.toCharArray());
    }

    /**
     * 随机抽取指定字符数组中的字符，生成指定长度的字符串
     *
     * @param str 用于提供字符来源的字符数组
     * @param length 生成的字符串的长度。如果长度小于1，则返回空字符串
     * @return
     */
    public static final String getString(char[] chars, int length) {
        if (length < 1) {
            return "";
        }
        char[] newChars = new char[length];
        if (chars.length == 1) {
            Arrays.fill(newChars, chars[0]);
        } else {
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                newChars[i] = chars[random.nextInt(chars.length)];
            }
        }
        return new String(newChars);
    }

    /**
     * 随机抽取指定字符串中的字符，生成指定长度的字符串
     *
     * @param str 用于提供字符来源的字符串
     * @param length 生成的字符串的长度。如果长度小于1，则返回空字符串
     * @return
     */
    public static final String getString(String str, int length) {
        return getString(str.toCharArray(), length);
    }
}
