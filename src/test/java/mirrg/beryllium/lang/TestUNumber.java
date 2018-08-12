package mirrg.beryllium.lang;

import static org.junit.jupiter.api.Assertions.*;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

import org.junit.jupiter.api.Test;

import mirrg.beryllium.lang.UNumber;

public class TestUNumber
{

	@Test
	public void test_parse()
	{
		assertEquals(OptionalInt.of(198), UNumber.parseInt("198"));
		assertEquals(OptionalInt.of(198), UNumber.parseInt("+198"));
		assertEquals(OptionalInt.of(-198), UNumber.parseInt("-198"));
		assertEquals(OptionalInt.of(Integer.parseInt("0198")), UNumber.parseInt("0198"));
		assertEquals(OptionalInt.of(198), UNumber.parseInt("0198", 10));
		assertEquals(OptionalInt.empty(), UNumber.parseInt("53264738926553252", 10));
		assertEquals(OptionalInt.empty(), UNumber.parseInt("-53264738926553252", 10));

		assertEquals(OptionalLong.of(198), UNumber.parseLong("198"));
		assertEquals(OptionalLong.of(198), UNumber.parseLong("+198"));
		assertEquals(OptionalLong.of(-198), UNumber.parseLong("-198"));
		assertEquals(OptionalLong.of(Integer.parseInt("0198")), UNumber.parseLong("0198"));
		assertEquals(OptionalLong.of(198), UNumber.parseLong("0198", 10));
		assertEquals(OptionalLong.of(53264738926553252L), UNumber.parseLong("53264738926553252", 10));
		assertEquals(OptionalLong.of(-53264738926553252L), UNumber.parseLong("-53264738926553252", 10));

		assertEquals(OptionalInt.empty(), UNumber.parseInt(" 198"));
		assertEquals(OptionalInt.empty(), UNumber.parseInt("198 "));
		assertEquals(OptionalInt.empty(), UNumber.parseInt("198i"));
		assertEquals(OptionalInt.empty(), UNumber.parseInt("198.0"));

		assertEquals(OptionalDouble.of(1.1), UNumber.parseDouble("1.1"));
		assertEquals(OptionalDouble.of(.1), UNumber.parseDouble(".1"));
		assertEquals(OptionalDouble.of(1.), UNumber.parseDouble("1."));
		assertEquals(OptionalDouble.of(0), UNumber.parseDouble("0"));
		assertEquals(OptionalDouble.of(-0.0), UNumber.parseDouble("-0.0"));
		assertEquals(OptionalDouble.of(-0.0), UNumber.parseDouble("  -0.0  "));

		assertTrue(UNumber.parseDouble("NaN").isPresent());
		assertTrue(UNumber.parseDouble("+NaN").isPresent());
		assertTrue(UNumber.parseDouble("-NaN").isPresent());
		assertTrue(UNumber.parseDouble("Infinity").isPresent());
		assertTrue(UNumber.parseDouble("+Infinity").isPresent());
		assertTrue(UNumber.parseDouble("-Infinity").isPresent());

		assertEquals(OptionalDouble.of(-1.5E4), UNumber.parseDouble("-1.5E4"));
		assertEquals(OptionalDouble.of(-1.5e4), UNumber.parseDouble("-1.5e4"));
		assertEquals(OptionalDouble.of(-1.5e-4), UNumber.parseDouble("-1.5e-4"));

		assertFalse(UNumber.parseDouble("").isPresent());
		assertFalse(UNumber.parseDouble("a").isPresent());
		assertFalse(UNumber.parseDouble("5,4").isPresent());
	}

}
