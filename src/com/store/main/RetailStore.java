package com.store.main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RetailStore {
	public int groceryItemArray[];
	public int otherItemArray[];
	public int discountValue = 0;
	public float totalProductPrice = 0, totalGroceryPrice = 0, total = 0;
	public static boolean isCustForTwoYears = false;
	public static boolean isUserInvalid = false;

	public static void main(String args[]) {
		int userName;
		int itemNo;
		float custYear = 0;
		System.out
				.println("Enter the type of User.\n Press 1 for Employee.\n Press 2 for Affilated user.\n Press 3 for Other user");
		try {
			Scanner sc = new Scanner(System.in);
			userName = sc.nextInt();

			if (userName == 3) {
				System.out
						.println("Enter the number of years since user is our customer. ");
				custYear = sc.nextFloat();
				if (custYear >= 2)
					isCustForTwoYears = true;
			}
			RetailStore obj = new RetailStore();
			obj.checkUserType(userName);

			if (!isUserInvalid) {
				System.out
						.println("Enter the total number of items user purchased ");
				itemNo = sc.nextInt();
				if (itemNo > 0) {
					obj.getItemDetails(itemNo);
				} else
					System.out
							.println("Total number of items should be greater than 0.");
			}
		} catch (InputMismatchException e) {
			// TODO: handle exception
			System.out.println("Please enter integer number!!");
		}

	}

	/**
	 * Check the type of user
	 * 
	 * @param usrType
	 *            :Employee, Affilated user, Other user
	 */

	public void checkUserType(int usrType) {
		switch (usrType) {
		case 1:
			discountValue = 30;
			break;
		case 2:
			discountValue = 10;
			break;
		case 3:
			if (isCustForTwoYears)
				discountValue = 5;

			break;
		default:
			System.out.println("Invalid user");
			isUserInvalid = true;
			break;
		}
	}

	/**
	 * Get total number of items, type of item and price
	 * 
	 * @param totalitem
	 *            : Total number of items user purchased
	 */

	public void getItemDetails(int totalitem) {
		int itemType, price, j = 0, k = 0;
		Scanner sc = new Scanner(System.in);
		groceryItemArray = new int[totalitem];
		otherItemArray = new int[totalitem];
		System.out
				.println("Enter the item type and its price.\n Type 1 for grocery item and\n 2 for other items.\n For Example: 1 500 \n Its means item is grocery and its price is 500$");
		for (int i = 1; i <= totalitem; i++) {
			System.out.println("Enter item type and its price");
			itemType = sc.nextInt();
			price = sc.nextInt();

			if (itemType == 1) {
				groceryItemArray[j] = price;
				j++;
			} else if (itemType == 2) {
				otherItemArray[k] = price;
				k++;
			} else {
				System.out.println("Invalid item type. Please enter again");
				i = i - 1;
			}
		}
		gettotalAmount();
	}

	/**
	 * Calculate net payable amount by user.
	 */
	public void gettotalAmount() {
		float temp, netDiscount;
		for (int i = 0; i < otherItemArray.length; i++) {
			totalProductPrice = totalProductPrice + otherItemArray[i];
		}
		for (int i = 0; i < groceryItemArray.length; i++) {
			totalGroceryPrice = totalGroceryPrice + groceryItemArray[i];
		}
		if (discountValue != 0) {
			temp = (totalProductPrice * discountValue) / 100;
			totalProductPrice = totalProductPrice - temp;
		}
		total = totalProductPrice + totalGroceryPrice;
		System.out.println("After percentage discount: $" + total);

		/**
		 * Calculate net amount user have to pay.
		 */
		int ones, tens;
		ones = (int) (total / 10);
		tens = ones / 10;
		netDiscount = tens * 5;
		total = total - netDiscount;
		System.out.println("Net payable amount is: $" + total);
	}
}
