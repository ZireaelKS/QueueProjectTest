package BigInt;

public class TestClass {

	public static void main(String[] args) {
		
		// test of constructor + toString
		MyInt d = new MyInt(0,0, 7, 5, 2, 12345);
        System.out.println("d: " + d);

	    // test of valueOf
	    MyInt d2 = MyInt.valueOf("12345678901234567890");
	    System.out.println("d2: " +d2);

	    // test of toDecimalString
	    System.out.println("d: " + d.toDecimalString());
        System.out.println("d2: " + d2.toDecimalString());

	        // test of plus
	        MyInt sum = d2.plus(d2).plus(d2); 
	        System.out.println("sum: " + sum);

	        // test of times:
	        MyInt prod = d2.times(d2);
	        System.out.println("prod: " + prod);

	        // test of valueOf
	        MyInt d3 = MyInt.valueOf("12345678901234567890", 10);
	        System.out.println("d3: " + d3); // should be the same as d2
	        MyInt d4 = MyInt.valueOf("123456789A0123456789A0", 11);
	        System.out.println("d4: " + d4);
	        MyInt d5 = MyInt.valueOf("123456012345601234560", 7);
	        System.out.println("d5: " + d5);

	        MyInt d6 = MyInt.valueOf(Long.MAX_VALUE);
	        System.out.println("d6: " + d6);

	        MyInt d7 = MyInt.valueOf(new int[]{3, 5, 7}, 100);
	        System.out.println("d7: " + d7);

	        MyInt d3_by_100 = d3.divideBy(100);
	        System.out.println("d3/100 = " + d3_by_100);
	        System.out.println("d3%100 = " + d3.modulo(100));


	        // test of compareTo
	        System.out.println("d2 <=> d: " + d2.compareTo(d));
	        System.out.println("d2 <=> d3: " + d2.compareTo(d3));
	        System.out.println("d <=> d2: " + d.compareTo(d2));
	        System.out.println("sum <=> d: " + sum.compareTo(d));
	        System.out.println("prod <=> d: " + prod.compareTo(d));
	        System.out.println("d <=> prod: " + d.compareTo(prod));

	        // test of convertTo:
	        System.out.println("d in base 5: " + d.toString(5));
	        System.out.println("d in base 2: " + d.toString(2));
	        System.out.println("d4 in base 11: " + d4.toString(11));
	        System.out.println("d5 in base 7: " + d5.toString(7));

	        //        DecimalBigInt d8 = new DecimalBigInt(RADIX-1, RADIX-1);
	        MyInt d8 = new MyInt(1, 0, 0);
	        System.out.println(d8);
	        for(int i = 2; i < 1000; i++) {
	            try {
	                d8.convertTo(i);
	            }
	            catch(Exception e) {
	                System.err.println("Problem bei i = " + i);
	                e.printStackTrace();
	                break;
	            }
	        }
	}
}
