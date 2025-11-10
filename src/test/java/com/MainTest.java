package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTest {

	/**
	 * 测试条件 1: “快乐路径”
	 * 验证两个非负整数相加时，是否能返回正确的和。
	 */
	@Test
	void testAdd_Success_ShouldReturnCorrectSum() {
		Main main = new Main();
		int result = main.add(10, 5);
		assertEquals(15, result, "Adding 10 and 5 should result in 15");
	}

	/**
	 * 测试条件 2: 输入为负数
	 * 验证当任一输入为负数时，是否会按预期抛出 IllegalArgumentException。
	 */
	@Test
	void testAdd_WhenInputIsNegative_ShouldThrowIllegalArgumentException() {
		Main main = new Main();
		
		// 使用 assertThrows 来捕获并验证异常
		// 它会执行 lambda 表达式中的代码，并检查它是否抛出了指定的异常类型
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			main.add(-1, 5); // 尝试使用一个负数调用
		});

		// （可选但推荐）我们还可以验证异常信息是否与代码中定义的一致
		String expectedMessage = "Both numbers must be non-negative.";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

		// 同样可以测试另一个输入为负数或两者都为负数的情况
		assertThrows(IllegalArgumentException.class, () -> {
			main.add(5, -1);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			main.add(-5, -1);
		});
	}

	/**
	 * 测试条件 3: 整数溢出
	 * 验证当两个数的和超过 Integer.MAX_VALUE 时，是否会按预期抛出 ArithmeticException。
	 */
	@Test
	void testAdd_WhenSumOverflows_ShouldThrowArithmeticException() {
		Main main = new Main();

		// Integer.MAX_VALUE 是 int 类型的最大值 (2,147,483,647)
		// 尝试将最大值与 1 相加，这必然会导致溢出
		Exception exception = assertThrows(ArithmeticException.class, () -> {
			main.add(Integer.MAX_VALUE, 1);
		});

		// （可选）验证异常信息
		String expectedMessage = "Integer overflow when trying to add";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

		// 也可以测试其他会导致溢出的组合
		assertThrows(ArithmeticException.class, () -> {
			main.add(1, Integer.MAX_VALUE);
		});
		assertThrows(ArithmeticException.class, () -> {
			main.add(Integer.MAX_VALUE - 10, 11);
		});



}
