package mirrg.beryllium.lang;

import java.io.PrintWriter;
import java.io.StringWriter;

import mirrg.beryllium.lang.string.PercentEncoding;

public class UString
{

	/**
	 * @see PercentEncoding#encode(String)
	 */
	public static String encode(String string)
	{
		return PercentEncoding.encode(string);
	}

	/**
	 * @see PercentEncoding#decode(String)
	 */
	public static String decode(String string)
	{
		return PercentEncoding.decode(string);
	}

	//

	public static String rept(char ch, int t)
	{
		StringBuffer sb = new StringBuffer(t);
		for (int i = 0; i < t; i++) {
			sb.append(ch);
		}
		return sb.toString();
	}

	public static String rept(String string, int t)
	{
		StringBuffer sb = new StringBuffer(string.length() * t);
		for (int i = 0; i < t; i++) {
			sb.append(string);
		}
		return sb.toString();
	}

	//

	public static String fillLeft(char ch, String string, int length)
	{
		if (string.length() >= length) return string;
		return rept(ch, length - string.length()) + string;
	}

	public static String fillLeft(String string, int length)
	{
		return fillLeft(' ', string, length);
	}

	public static String fillRight(char ch, String string, int length)
	{
		if (string.length() >= length) return string;
		return string + rept(ch, length - string.length());
	}

	public static String fillRight(String string, int length)
	{
		return fillRight(' ', string, length);
	}

	//

	public static String getStackTrace(Exception e)
	{
		StringWriter out = new StringWriter();
		e.printStackTrace(new PrintWriter(out));
		return out.toString();
	}

}
