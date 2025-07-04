package net.xmlizer.permutation;

import java.math.BigInteger;

/**
 * Copyright (C) 2010 Bernhard Wagner
 * 
 * This file is part of permutation.
 * 
 * permutation is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program. If not, see
 * {@literal <http://www.gnu.org/licenses/>}.
 */

// Initial version of this code from:
// http://www.merriampark.com/perm.htm
// Kenneth H. Rosen, Discrete Mathematics and Its Applications
// 2nd edition (NY: McGraw-Hill, 1991), pp. 282-284.

public class PermutationGenerator {

	private final int[] a;
	private BigInteger numLeft;
	private final BigInteger total;

	// -----------------------------------------------------------
	// Constructor. WARNING: Don't make n too large.
	// Recall that the number of permutations is n!
	// which can be very large, even when n is as small as 20 --
	// 20! = 2,432,902,008,176,640,000 and
	// 21! is too big to fit into a Java long, which is
	// why we use BigInteger instead.
	// ----------------------------------------------------------

	public PermutationGenerator(int n) {
		if (n < 1) {
			throw new IllegalArgumentException("Min 1");
		}
		a = new int[n];
		total = getFactorial(n);
		reset();
	}

	/**
	 * Reset
	 */
	public void reset() {
		for (int i = 0; i < a.length; i++) {
			a[i] = i;
		}
		numLeft = new BigInteger(total.toString());
	}

	/**
	 * @return number of permutations not yet generated
	 */
	public BigInteger getNumLeft() {
		return numLeft;
	}

	/**
	 * @return total number of permutations
	 */
	public BigInteger getTotal() {
		return total;
	}

	/**
	 * @return true if there more permutations
	 */
	public boolean hasMore() {
		return numLeft.compareTo(BigInteger.ZERO) == 1;
	}

	/**
	 * Computes factorial for given n
	 * 
	 * @param n
	 * @return factorial for given n
	 */
	private static BigInteger getFactorial(int n) {
		BigInteger fact = BigInteger.ONE;
		for (int i = n; i > 1; i--) {
			fact = fact.multiply(new BigInteger(Integer.toString(i)));
		}
		return fact;
	}

	/**
	 * Generate next permutation (algorithm from Rosen p. 284)
	 * 
	 * @return next permutation
	 */
	public int[] getNext() {

		if (numLeft.equals(total)) {
			numLeft = numLeft.subtract(BigInteger.ONE);
			return a;
		}

		int temp;

		// Find largest index j with a[j] < a[j+1]

		int j = a.length - 2;
		while (a[j] > a[j + 1]) {
			j--;
		}

		// Find index k such that a[k] is smallest integer
		// greater than a[j] to the right of a[j]

		int k = a.length - 1;
		while (a[j] > a[k]) {
			k--;
		}

		// Interchange a[j] and a[k]

		temp = a[k];
		a[k] = a[j];
		a[j] = temp;

		// Put tail end of permutation after jth position in increasing order

		int r = a.length - 1;
		int s = j + 1;

		while (r > s) {
			temp = a[s];
			a[s] = a[r];
			a[r] = temp;
			r--;
			s++;
		}

		numLeft = numLeft.subtract(BigInteger.ONE);
		return a;

	}
}
