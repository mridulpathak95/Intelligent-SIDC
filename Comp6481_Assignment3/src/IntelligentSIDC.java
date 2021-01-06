/*Assignment 3
Part-2
Written By: Mridul Pathak
Student Id: 40078157

*/

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
/**
 * 
 * @author mridulpathak
 *
 */

public class IntelligentSIDC {

	static int thresholdValue = 0;
	static boolean isGreaterThanThreshold = false;
	static BinarySearchTree binarySearchTree = null;
	static CustomHashTable<Long, String> customHashTable = null;

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter through console or file ?");
		System.out.println("Input 1 for file and 2 for console.");
		int insertionType = Integer.parseInt(scanner.nextLine());
		if (insertionType == 2) {
			System.out.println("Please enter the size of data: ");
			long datasize = Long.parseLong(scanner.nextLine());
			System.out.println("Please enter the threshold: ");
			long threshold = Long.parseLong(scanner.nextLine());
			setSIDCThreshold((int) threshold);
			isGreaterThanThreshold = datasize > thresholdValue;
			if (isGreaterThanThreshold) {
				binarySearchTree = new BinarySearchTree();
			} else {
				customHashTable = new CustomHashTable<Long, String>();
			}

			for (int i = 0; i < datasize; i++) {
				if (isGreaterThanThreshold)
					binarySearchTree.insert(generate());
				else {
					long input = generate();
					customHashTable.add(input, "This record is " + input + " .");
				}
			}

		} else if (insertionType == 1) {
			System.out.println("Enter File name with extension");
			String path = "/Users/mridulpathak/eclipse-workspace/Comp6481_Assignment3";
	        Scanner sc=new Scanner(System.in);
			String fileName =sc.next();
			BufferedReader br = new BufferedReader(new FileReader(path + "/" + fileName));
			System.out.println("Please enter the size of data: ");
			long datasize = Long.parseLong(scanner.nextLine());
			System.out.println("Please enter the threshold: ");
			long threshold = Long.parseLong(scanner.nextLine());
			setSIDCThreshold((int) threshold);
			isGreaterThanThreshold = datasize > thresholdValue;
			if (isGreaterThanThreshold) {
				binarySearchTree = new BinarySearchTree();
			} else {
				customHashTable = new CustomHashTable<Long, String>();
			}

			String contentLine = br.readLine();
			int i = 0;
			while (contentLine != null && i < datasize) {

				long ID = Long.parseLong(contentLine.trim());
				if (isGreaterThanThreshold)
					binarySearchTree.insert(ID);
				else {

					customHashTable.add(ID, "This record is " + ID + " .");
				}
				contentLine = br.readLine();
				i++;
			}
			
		}
		int input = 1;
		while (input < 8) {
			System.out.println("Select operation from the Menu: ");
			System.out.println("1. Return all keys.");
			System.out.println("2. Add ");
			System.out.println("3. Remove ");
			System.out.println("4. Return Value");
			System.out.println("5. Return Next Key.");
			System.out.println("6. Return Prev Key.");
			System.out.println("7. Return Range Key.");
			System.out.println("8. Exit");
			input = Integer.parseInt(scanner.nextLine());
			switch (input) {
			case 1:
				if (isGreaterThanThreshold)
					binarySearchTree.inorder();
				else
					System.out.println("" + allKeys(customHashTable));
				break;
			case 2:
				System.out.println("Enter key: ");
				long key = Long.parseLong(scanner.nextLine());
				System.out.println("Enter Value: ");
				String v = scanner.nextLine();
				if (isGreaterThanThreshold)
					binarySearchTree.insert(key);
				else
					add(customHashTable, key, v);
				break;
			case 3:
				System.out.println("Enter key: ");
				long key1 = Long.parseLong(scanner.nextLine());
				if (isGreaterThanThreshold)
					binarySearchTree.remove(binarySearchTree.root, key1);
				else
					remove(customHashTable, key1);
				break;
			case 4:
				System.out.println("Enter Key: ");
				long key2 = Long.parseLong(scanner.nextLine());
				if (isGreaterThanThreshold) {
					binarySearchTree.containsNode(key2);
					System.out.println("This record is " + key2 + " .");
				} else
					System.out.println("" + getValues(customHashTable, key2));

				break;
			case 5:
				System.out.println("Enter Key: ");
				long key3 = Long.parseLong(scanner.nextLine());
				if (isGreaterThanThreshold) {
					System.out.println("" + binarySearchTree.nextKey(binarySearchTree.root, key3));
				} else
					System.out.println("" + nextKey(customHashTable, key3));
				break;
			case 6:
				System.out.println("Enter Key: ");
				long key4 = Long.parseLong(scanner.nextLine());
				if (isGreaterThanThreshold) {
					System.out.println("" + binarySearchTree.prevKey(binarySearchTree.root, key4));
				} else
					System.out.println("" + prevKey(customHashTable, key4));
				break;
			case 7:
				System.out.println("Enter Key1: ");
				long key5 = Long.parseLong(scanner.nextLine());
				System.out.println("Enter Key2: ");
				long key6 = Long.parseLong(scanner.nextLine());
				if (isGreaterThanThreshold) {
					System.out.println("" + binarySearchTree.rangeKeys(binarySearchTree.root, key5, key6));
				} else
					System.out.println("" + rangeKey(customHashTable, key5, key6));
				break;
			case 8:
				break;

			default:
				break;
			}
		}

	}
	/**
	 * 
	 * @return
	 */

	static long generate() {
		Random random = new Random();
		long value = 10000000 + random.nextInt(90000000);
		return value;
		//condition check
	}
	/**
	 * 
	 * @param size
	 */

	static void setSIDCThreshold(int size) {
		thresholdValue = size;

	}
	/**
	 * 
	 * @param customHashTable
	 * @return
	 */

	static List<Long> allKeys(CustomHashTable<Long, String> customHashTable) {

		return customHashTable.getAllKeys();
	}
	/**
	 * 
	 * @param customHashTable
	 * @param key
	 * @param value
	 */

	static void add(CustomHashTable<Long, String> customHashTable, Long key, String value) {
		customHashTable.add(key, value);
	}
	/**
	 * 
	 * @param customHashTable
	 * @param key
	 */

	static void remove(CustomHashTable<Long, String> customHashTable, Long key) {
		customHashTable.remove(key);
	}
	/**
	 * 
	 * @param customHashTable
	 * @param key
	 * @return
	 */

	static String getValues(CustomHashTable<Long, String> customHashTable, Long key) {
		return customHashTable.get(key);
	}
	/**
	 * 
	 * @param customHashTable
	 * @param key
	 * @return
	 */

	static Long nextKey(CustomHashTable<Long, String> customHashTable, Long key) {
		return customHashTable.nextKey(key);
	}
	/**
	 * 
	 * @param customHashTable
	 * @param key
	 * @return
	 */

	static Long prevKey(CustomHashTable<Long, String> customHashTable, Long key) {
		return customHashTable.prevKey(key);
	}
	/**
	 * 
	 * @param customHashTable
	 * @param key1
	 * @param key2
	 * @return
	 */

	static List<Long> rangeKey(CustomHashTable<Long, String> customHashTable, Long key1, long key2) {
		return customHashTable.rangeKey(key1, key2);
	}
	
	
}

