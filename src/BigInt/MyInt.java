package BigInt;

import java.util.Arrays;
import java.util.Formatter;

public class MyInt {
	
    public final static int RADIX = 1000000000;

    private final static int RADIX_DECIMAL_DIGITS = 9;
    
    private int[] digits;
    
    public final static MyInt ZERO = new MyInt();
    public final static MyInt ONE = new MyInt(1);
    
    public MyInt(int... digits) {
        int zeroCount = 0;
        boolean zero = true;
        for(int digit : digits) {
            if(digit < 0 ||  RADIX <= digit) {
                throw new IllegalArgumentException("digit " + digit +
                                                   " out of range!");
            }
            if(zero) {
                if (digit != 0) {
                    zero = false;
                }
                else {
                    zeroCount ++;
                }
            }
        }

        this.digits = Arrays.copyOfRange(digits, zeroCount, digits.length);
    }

    public String toString() {
        return "Big" + Arrays.toString(digits);
    }
    
    public static MyInt valueOf(String decimal) {
        int decLen = decimal.length();
        int bigLen = (decLen-1) / RADIX_DECIMAL_DIGITS + 1;
        int firstSome = decLen - (bigLen-1) * RADIX_DECIMAL_DIGITS;
        int[] digits = new int[bigLen];
        for(int i = 0; i < bigLen ; i++) {
            String block =
                decimal.substring(Math.max(firstSome + (i-1)*RADIX_DECIMAL_DIGITS, 0),
                                  firstSome +   i  *RADIX_DECIMAL_DIGITS);
            digits[i] = Integer.parseInt(block);
        }
        return new MyInt(digits);
    }
    
    public static MyInt valueOf(long number) {
        if(number < 0) {
            throw new IllegalArgumentException("negative number: " +number);
        }
        if(number == 0L)
            return ZERO;
        if(number == 1L)
            return ONE;

        int[] digits = new int[3];
        int index = 2;
        while(number > 0) {
            digits[index] = (int)(number % RADIX);
            number = number / RADIX;
            index-- ;
        }
        return new MyInt(digits);
    }

    public static MyInt valueOf(String text, int radix) {
        if(radix < Character.MIN_RADIX || Character.MAX_RADIX < radix) {
            throw new IllegalArgumentException("radix out of range: " + radix);
        }
        MyInt bigRadix = new MyInt(radix);
        MyInt value = ZERO;
        for(char digit : text.toCharArray()) {
            int iDigit = Character.digit(digit, radix);
            if(iDigit < 0) {
                throw new NumberFormatException("digit " + digit +
                                                " is not a valid base-"+radix+
                                                "-digit.");
            }
            MyInt bigDigit = new MyInt(iDigit);
            value = value.times(bigRadix).plus(bigDigit);
        }
        return value;
    }	
    
    
    public static MyInt valueOf(int[] digits, int radix) {
        if(radix < 2) {
            throw new IllegalArgumentException("illegal radix: " + radix);
        }
        MyInt bigRadix = valueOf(radix);
        MyInt value = ZERO;
        for(int digit : digits) {
            if(digit < 0 || radix <= digit) {
                throw new IllegalArgumentException("digit " + digit +
                                                   " out of range");
            }
            MyInt bigDigit = MyInt.valueOf(digit);
            value = value.times(bigRadix).plus(bigDigit);
        }
        return value;
    }
    
    public String toDecimalString() {
        StringBuilder b =
            new StringBuilder(RADIX_DECIMAL_DIGITS * digits.length);
        Formatter f = new Formatter(b);
        f.format("%d", digits[0]);
        for(int i = 1 ; i < digits.length; i++) {
            f.format("%09d", digits[i]);
        }
        return b.toString();
    }
    
    public String toString(int radix) {
        if(radix < Character.MIN_RADIX || Character.MAX_RADIX < radix) {
            throw new IllegalArgumentException("radix out of range: " + radix);
        }
        if(digits.length == 0)
            return "0";
        int[] rdigits = convertTo(radix);
        StringBuilder b = new StringBuilder(rdigits.length);
        for(int dig : rdigits) {
            b.append(Character.forDigit(dig, radix));
        }
        return b.toString();
    }
    
    public MyInt plus(MyInt that) {
        int[] result = new int[Math.max(this.digits.length,
                                        that.digits.length)+ 1];

        addDigits(result, result.length-1, this.digits);
        addDigits(result, result.length-1, that.digits);

        return new MyInt(result);
    }

    private void addDigits(int[] result, int resultIndex, int... addend) {
		int addendIndex = addend.length - 1;
		while(addendIndex >= 0) {
			addDigit(result, resultIndex, addend[addendIndex]);
			addendIndex--;
			resultIndex--;
		}
    }
    
    private void addDigit(int[] result, int resultIndex, int addendDigit) {
		int sum = result[resultIndex] + addendDigit;
		result[resultIndex] = sum % RADIX;
		int carry = sum / RADIX;
		if(carry > 0) {
			addDigit(result, resultIndex - 1, carry);
		}
    }
    
    private void multiplyDigit(int[] result, int resultIndex, int firstFactor, int secondFactor) {
		long prod = (long)firstFactor * (long)secondFactor;
		int prodDigit = (int)(prod % RADIX);
		int carry = (int)(prod / RADIX);
		addDigits(result, resultIndex, carry, prodDigit);
    }
    
    private void multiplyDigits(int[] result, int resultIndex, int[] leftFactor, int[] rightFactor) {
		for(int i = 0; i < leftFactor.length; i++) {
			for(int j = 0; j < rightFactor.length; j++) {
				multiplyDigit(result, resultIndex - (i + j), 
				leftFactor[leftFactor.length-i-1],
				rightFactor[rightFactor.length-j-1]);
			}
		}
    }
	
	public MyInt times(MyInt that) {
        int[] result = new int[this.digits.length + that.digits.length];
        multiplyDigits(result, result.length-1, this.digits, that.digits);
        return new MyInt(result);
    }
    
	private int divideDigit(int[] result, int resultIndex, 
			int divident, int lastRemainder, int divisor) {
		
		assert divisor < RADIX;
		assert lastRemainder < divisor;
		
		long ent = divident + (long)RADIX * lastRemainder;
		
		long quot = ent / divisor;
		long rem = ent % divisor;
		
		assert quot < RADIX;
		assert rem < divisor;
		
		result[resultIndex] = (int)quot;
		return (int)rem;
	}

	private int divideDigits(int[] result, int resultIndex,
            int[] divident, int dividentIndex, int divisor) {
		int remainder = 0;
		for(; dividentIndex < divident.length; dividentIndex++, resultIndex++) {
		remainder = divideDigit(result, resultIndex,
		                   divident[dividentIndex],
		                   remainder, divisor);
		}
		return remainder;
	}
    
	public MyInt divideBy(int divisor) {
        if(divisor <= 0 || RADIX <= divisor) {
            throw new IllegalArgumentException("divisor " + divisor +
                                               " out of range!");
        }

        int[] result = new int[digits.length];
        divideDigits(result, 0, digits, 0, divisor);
        return new MyInt(result);
    }
	
	public int modulo(int divisor) {
        if(divisor <= 0 || RADIX <= divisor) {
            throw new IllegalArgumentException("divisor " + divisor +
                                               " out of range!"); }
        int[] result = new int[digits.length];
        return divideDigits(result, 0, digits, 0, divisor);
    }
	
	
	public int[] convertTo(int radix) {
        if(radix <= 1 || RADIX <= radix) {
            throw new IllegalArgumentException("radix " + radix +
                                               " out of range!");
        }
        if(digits.length == 0)
            return new int[0];
        int len = (int) (Math.log(RADIX) / Math.log(radix) * digits.length)+1;
        int[] rDigits = new int[len];
        int rIndex = len-1;
        int[] current = digits;
        int quotLen = digits.length;

        while(quotLen > 0)  {
            int[] quot = new int[quotLen];
            int rem = divideDigits(quot, 0, current, 
            		current.length - quotLen, radix);
            rDigits[rIndex] = rem;
            rIndex --;
            
            current = quot;

            if(current[0] == 0) {
                quotLen--;
            }
        }

        while(rIndex < 0 || rDigits[rIndex] == 0) {
            rIndex++;
        }
        return Arrays.copyOfRange(rDigits, rIndex, rDigits.length);
    }
	
	public int hashCode() {
        int hash = 0;
        for(int digit : digits) {
            hash = hash * 13 + digit;
        }
        return hash;
    }
	
	public boolean equals(Object o) {
        return o instanceof MyInt &&
            this.compareTo((MyInt)o) == 0;
    }
	
	public int compareTo(MyInt that) {
        if(this.digits.length < that.digits.length) {
            return -1;
        }
        if (that.digits.length < this.digits.length) {
            return 1;
        }
        for(int i = 0; i < this.digits.length; i++) {
            if(this.digits[i] < that.digits[i]) {
                return -1;
            }
            if(that.digits[i] < this.digits[i]) {
                return 1;
            }
        }
        return 0;
    }
	
	public static MyInt factorial(int n) {
		MyInt fac = MyInt.ONE;
        for(int i = 2; i <= n; i++) {
            fac = fac.times(new MyInt(i));
            if(i % 1000 == 0) {
                System.err.println("log_RADIX(fac("+i+")) = " +
                                   fac.digits.length);
            }
        }
        return fac;
    }
	
	
	
	
    	
}
