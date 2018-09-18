package com.store.main.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.store.main.RetailStore;

public class RetailStoreTest {
	RetailStore test = new RetailStore();

	/**
	 * To check user is employee.
	 */
	@Test
	public void UserTypeEmployeeTest() {

		test.checkUserType(1);
		int result = test.discountValue;
		assertEquals(30, result);
	}

	/**
	 * To check user is affilated with company.
	 */

	@Test
	public void UserTypeAffilatedTest() {

		test.checkUserType(2);
		int result = test.discountValue;
		assertEquals(10, result);
	}

	/**
	 * To check user is customer of company for more than or equal to two years.
	 */
	@Test
	public void UserTypeOtherTest() {

		RetailStore.isCustForTwoYears = true;
		test.checkUserType(3);

		int result = test.discountValue;
		assertEquals(5, result);
	}

	/**
	 * To check user is customer of company for less than two years.
	 */
	@Test
	public void UserTypeOtherNotTwoYearsTest() {
		RetailStore.isCustForTwoYears = false;
		test.checkUserType(3);

		int result = test.discountValue;
		assertEquals(0, result);
	}

	/**
	 * To check invalid user, if someone entered value apart from 1, 2 and 3
	 * then it is invalid option.
	 */
	@Test
	public void InvalidUserType() {
		test.checkUserType(4);

		int result = test.discountValue;
		assertEquals("Invalid user", 0, result);
	}

	/**
	 * To test invalid item type entered by user
	 */
	@Test
	public void CheckInvalidItemTypeTest() {
		System.out
				.println("**TEST 6:\n FOR INVALID USERTYPE. ENTER VALUE OTHER THAN 1,2 AND 3**");
		test.getItemDetails(1);
		assertEquals("Invalid item type. Please enter again",
				"Invalid item type. Please enter again");
	}

	/**
	 * To test net payable by user; if user is other user and total item
	 * purchased by user is 3. 1. Grocery 500$ (Input should be: 1 500) 2.
	 * Grocery 1000$ (Input should be: 1 1000) 3. Other Product 150$ (Input
	 * should be:2 150) Expected output: 1525$
	 */
	@Test
	public void NetAmountByEmployeeTest() {
		System.out
				.println("**TEST 7:\n FOR EMPLOYEE USERTYPE. ENTER VALUE THE ITEM VALUE AS 1 500,1 1000, 2 150**");
		test.discountValue = 30;
		test.getItemDetails(3);
		int result = (int) test.total;
		assertEquals(1525, result);
	}

	/**
	 * To test net payable by user; if user is affilated user to company and
	 * total item purchased by user is 3. 1. Grocery 500$ (Input should be: 1
	 * 500) 2. Grocery 1000$ (Input should be: 1 1000) 3. Other Product 150$
	 * (Input should be:2 150) Expected output: 1555.0$
	 */
	@Test
	public void NetAmountByAffilatedTest() {
		System.out
				.println("**TEST 8:\n FOR AFFILATED USERTYPE. ENTER VALUE THE ITEM VALUE AS 1 500,1 1000, 2 150**");
		test.checkUserType(2);
		test.discountValue = 10;
		test.getItemDetails(3);
		int result = (int) test.total;
		assertEquals(1555, result);
	}

	/**
	 * To test net payable by user; if user is other customer and total item
	 * purchased by user is 3. 1. Grocery 500$ (Input should be: 1 500) 2.
	 * Grocery 1000$ (Input should be: 1 1000) 3. Other Product 150$ (Input
	 * should be:2 150) Expected output: 1562.0$
	 */
	@Test
	public void NetAmountByOtherUserTest() {
		System.out
				.println("**TEST 9:\n FOR OTHER USERTYPE. ENTER VALUE THE ITEM VALUE AS 1 500,1 1000, 2 150**");
		RetailStore.isCustForTwoYears = true;
		test.checkUserType(3);
		test.discountValue = 5;
		test.getItemDetails(3);
		int result = (int) test.total;
		assertEquals(1562, result);
	}
}
