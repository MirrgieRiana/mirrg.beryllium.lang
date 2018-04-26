package backup;

import static org.junit.Assert.*;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

import org.junit.Test;

import mirrg.beryllium.lang.NumberUtil;

public class TestHOptional
{

	@Test
	public void test_parse()
	{
		assertEquals(OptionalInt.of(198), NumberUtil.parseInt("198"));
		assertEquals(OptionalInt.of(198), NumberUtil.parseInt("+198"));
		assertEquals(OptionalInt.of(-198), NumberUtil.parseInt("-198"));
		assertEquals(OptionalInt.of(Integer.parseInt("0198")), NumberUtil.parseInt("0198"));
		assertEquals(OptionalInt.of(198), NumberUtil.parseInt("0198", 10));
		assertEquals(OptionalInt.empty(), NumberUtil.parseInt("53264738926553252", 10));
		assertEquals(OptionalInt.empty(), NumberUtil.parseInt("-53264738926553252", 10));

		assertEquals(OptionalLong.of(198), NumberUtil.parseLong("198"));
		assertEquals(OptionalLong.of(198), NumberUtil.parseLong("+198"));
		assertEquals(OptionalLong.of(-198), NumberUtil.parseLong("-198"));
		assertEquals(OptionalLong.of(Integer.parseInt("0198")), NumberUtil.parseLong("0198"));
		assertEquals(OptionalLong.of(198), NumberUtil.parseLong("0198", 10));
		assertEquals(OptionalLong.of(53264738926553252L), NumberUtil.parseLong("53264738926553252", 10));
		assertEquals(OptionalLong.of(-53264738926553252L), NumberUtil.parseLong("-53264738926553252", 10));

		assertEquals(OptionalInt.empty(), NumberUtil.parseInt(" 198"));
		assertEquals(OptionalInt.empty(), NumberUtil.parseInt("198 "));
		assertEquals(OptionalInt.empty(), NumberUtil.parseInt("198i"));
		assertEquals(OptionalInt.empty(), NumberUtil.parseInt("198.0"));

		assertEquals(OptionalDouble.of(1.1), NumberUtil.parseDouble("1.1"));
		assertEquals(OptionalDouble.of(.1), NumberUtil.parseDouble(".1"));
		assertEquals(OptionalDouble.of(1.), NumberUtil.parseDouble("1."));
		assertEquals(OptionalDouble.of(0), NumberUtil.parseDouble("0"));
		assertEquals(OptionalDouble.of(-0.0), NumberUtil.parseDouble("-0.0"));
		assertEquals(OptionalDouble.of(-0.0), NumberUtil.parseDouble("  -0.0  "));

		assertTrue(NumberUtil.parseDouble("NaN").isPresent());
		assertTrue(NumberUtil.parseDouble("+NaN").isPresent());
		assertTrue(NumberUtil.parseDouble("-NaN").isPresent());
		assertTrue(NumberUtil.parseDouble("Infinity").isPresent());
		assertTrue(NumberUtil.parseDouble("+Infinity").isPresent());
		assertTrue(NumberUtil.parseDouble("-Infinity").isPresent());

		assertEquals(OptionalDouble.of(-1.5E4), NumberUtil.parseDouble("-1.5E4"));
		assertEquals(OptionalDouble.of(-1.5e4), NumberUtil.parseDouble("-1.5e4"));
		assertEquals(OptionalDouble.of(-1.5e-4), NumberUtil.parseDouble("-1.5e-4"));

		assertFalse(NumberUtil.parseDouble("").isPresent());
		assertFalse(NumberUtil.parseDouble("a").isPresent());
		assertFalse(NumberUtil.parseDouble("5,4").isPresent());
	}

}
