/*
 * @author Kenneth Shelton
 * 6/26/2021
 * CMSC204
 * Prof. Thai
 */

public class ArraySumDriver {
	private final static int ARRAY_SIZE = 6;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int index = 0;

		Integer[] myArray = new Integer[ARRAY_SIZE];
		
		myArray[index++] = 13;
		myArray[index++] = 5;
		myArray[index++] = 12;
		myArray[index++] = 6;
		
		int sum = sumOfArray(myArray, 3);
		System.out.println(sum);
		
		myArray[index++] = 7;
		myArray[index++] = 1;
		
		sum = sumOfArray(myArray, 5);
		System.out.println(sum);
		
		int fib = 20;
		System.out.println("The "+fib+"th Fibonacci number is: "+getFibonacci(fib));
	}
	
	/**
	 * Recursive method for generating sum of values in array
	 * @param arr array of Integers
	 * @param num index of array to sum all previous index values (including num)
	 * @return sum of array values
	 */
	public static <T> int sumOfArray(Integer[] arr, int num) {
		if(num>0)
			return arr[num] + sumOfArray(arr,num-1);
		else
			return arr[num];
	}
	
	private static final int max = 100000;
	private static int[] fibNums = new int[max];

	public static int getFibonacci(int num)
	{
		if(num<=1)
			return num;
		else if(fibNums[num]!=0)
			return fibNums[num];
		else
		{
			fibNums[num] = getFibonacci(num-1) + getFibonacci(num-2);
			return fibNums[num];
		}
	}
	
}