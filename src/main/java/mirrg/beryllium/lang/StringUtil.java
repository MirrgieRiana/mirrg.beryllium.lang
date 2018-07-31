package mirrg.beryllium.lang;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringUtil
{

	/**
	 * URLエンコードに似た方式により、文字列を[a-zA-Z0-9%]からなる文字列にエンコードします。
	 * 通常のURLエンコードでは異なる扱いになる一部の記号や空白も"%"表記で置換されます。
	 * このメソッドは軽量化のために {@link StringBuilder} で返します。
	 * そのため、同じ内容の文字列との {@link String#equals(Object)} は真になりません。
	 */
	public static CharSequence encode(CharSequence string, Charset charset)
	{
		StringBuilder sb = new StringBuilder();

		int length = string.length();
		for (int i = 0; i < length; i++) {
			char c = string.charAt(i);

			if ('0' <= c && c <= '9') {
				sb.append(c);
			} else if ('A' <= c && c <= 'Z') {
				sb.append(c);
			} else if ('a' <= c && c <= 'z') {
				sb.append(c);
			} else {
				byte[] bytes = String.valueOf(c).getBytes(charset);
				for (int j = 0; j < bytes.length; j++) {
					sb.append('%');
					sb.append(String.format("%02X", bytes[j]));
				}
			}

		}

		return sb;
	}

	/**
	 * UTF-8によって {@link #encode(CharSequence, Charset)} を呼び出します。
	 */
	public static CharSequence encode(CharSequence string)
	{
		return encode(string, StandardCharsets.UTF_8);
	}

}
