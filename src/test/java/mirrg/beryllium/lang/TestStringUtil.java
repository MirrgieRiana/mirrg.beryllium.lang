package mirrg.beryllium.lang;

import static mirrg.beryllium.lang.StringUtil.*;
import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.junit.Test;

public class TestStringUtil
{

	@Test
	public void test_encode()
	{
		assertEquals("abc%2Fdef", encode("abc/def"));
		assertEquals("abc%E3%81%82def", encode("abcあdef"));
		assertEquals("abc%25def", encode("abc%def"));
		assertEquals("%20%21%22%23%24%25%26%27%28%29%2A%2B%2C%2D%2E%2F0123456789%3A%3B%3C%3D%3E%3F%40ABCDEFGHIJKLMNOPQRSTUVWXYZ%5B%5C%5D%5E%5F%60abcdefghijklmnopqrstuvwxyz%7B%7C%7D%7E",
			encode(" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~"));
		assertEquals("%E3%81%82%E3%81%84%E3%81%86%E3%81%88%E3%81%8A%E3%81%8B%E3%81%8D%E3%81%8F%E3%81%91%E3%81%93", encode("あいうえおかきくけこ"));
		assertEquals("%82%A0%82%A2%82%A4%82%A6%82%A8%82%A9%82%AB%82%AD%82%AF%82%B1", encode("あいうえおかきくけこ", Charset.forName("Shift_JIS")));

		assertEquals("abc/def", decode("abc%2Fdef"));
		assertEquals("abcあdef", decode("abc%E3%81%82def"));
		assertEquals("abc%def", decode("abc%25def"));
		assertEquals(" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~",
			decode("%20%21%22%23%24%25%26%27%28%29%2A%2B%2C%2D%2E%2F0123456789%3A%3B%3C%3D%3E%3F%40ABCDEFGHIJKLMNOPQRSTUVWXYZ%5B%5C%5D%5E%5F%60abcdefghijklmnopqrstuvwxyz%7B%7C%7D%7E"));
		assertEquals("あいうえおかきくけこ",
			decode("%E3%81%82%E3%81%84%E3%81%86%E3%81%88%E3%81%8A%E3%81%8B%E3%81%8D%E3%81%8F%E3%81%91%E3%81%93"));
		assertEquals("あいうえおかきくけこ",
			decode("%82%A0%82%A2%82%A4%82%A6%82%A8%82%A9%82%AB%82%AD%82%AF%82%B1", Charset.forName("Shift_JIS")));

		assertEquals("abcdef5%2", decode("abcdef5%2"));
		assertEquals("%あああ", decode("%あああ"));
		assertEquals("%g4%G4%4g%4G%%%%%", decode("%g4%G4%4g%4G%%%%%"));
		assertEquals("abcdef%", decode("abcdef%25"));
		assertEquals("abcde%f", decode("abcde%25f"));
		assertEquals("あいう", decode("あいう"));

		String string;

		string = "16ビットUnicode コード単位のシーケンスとバイト・シーケンス間の指定マップです。このクラスには、デコーダやエンコーダを作成するメソッドや、文字セットに関連付けられたさまざまな名前を取得するメソッドを定義します。このクラスのインスタンスは不変です。 ";
		assertEquals(string, decode(encode(string)));

	}

	@Test
	public void test_rept()
	{
		assertEquals("", rept("あいう", 0));
		assertEquals("あいう", rept("あいう", 1));
		assertEquals("あいうあいう", rept("あいう", 2));
		assertEquals("あいうあいうあいう", rept("あいう", 3));
		assertEquals("aiuaiuaiu", rept("aiu", 3));
	}

	@Test
	public void test_fill()
	{
		assertEquals("00013", fillLeft('0', "13", 5));
		assertEquals("13000", fillRight('0', "13", 5));
		assertEquals("134568", fillLeft('0', "134568", 5));
		assertEquals("134568", fillRight('0', "134568", 5));
		assertEquals("   13", fillLeft("13", 5));
		assertEquals("13   ", fillRight("13", 5));
		assertEquals("134568", fillLeft("134568", 5));
		assertEquals("134568", fillRight("134568", 5));
	}

}
