package mirrg.beryllium.lang;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStringUtil
{

	@Test
	public void test_encode()
	{
		assertEquals("abc%2Fdef", "" + StringUtil.encode("abc/def"));
		assertEquals("abc%E3%81%82def", "" + StringUtil.encode("abcあdef"));
		assertEquals("abc%25def", "" + StringUtil.encode("abc%def"));
	}

}
