/**
 * 
 */
package lintcode;

import java.util.*;

/**
 * Cosine similarity is a measure of similarity between two vectors of an inner product space that measures the cosine of the angle between them. The cosine of 0° is 1, and it is less than 1 for any other angle.

See wiki: Cosine Similarity

Here is the formula:

/media/problem/cosine-similarity.png

Given two vectors A and B with the same size, calculate the cosine similarity.

Return 2.0000 if cosine similarity is invalid (for example A = [0] and B = [0]).

Have you met this question in a real interview? Yes
Example
Given A = [1, 2, 3], B = [2, 3 ,4].

Return 0.9926.

Given A = [0], B = [0].

Return 2.0000

 * @author Chauncey
 *
 */
public class CosineSimilarity
{
    /**
     * @param A: An integer array.
     * @param B: An integer array.
     * @return: Cosine similarity.
     */
    public double cosineSimilarity(int[] A, int[] B) {
        if (null == A || A.length == 0 ||
                null == B || A.length != B.length) return 2.0;
        double numerator = 0;
        for (int i=0; i<A.length; ++i) {
            numerator += A[i]*B[i];
        }
        double denominator1 = 0;
        double denominator2 = 0;
        for (int i=0; i<A.length; ++i) {
            denominator1 += A[i]*A[i];
            denominator2 += B[i]*B[i];
        }
        if (denominator1 == 0 || denominator2 == 0) return 2.0;
        return numerator / (Math.sqrt(denominator1) * Math.sqrt(denominator2));
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        CosineSimilarity solution = new CosineSimilarity();
        System.out.println(solution.cosineSimilarity(new int[]{1,2,3}, new int[]{2,3,4}));
        System.out.println(solution.cosineSimilarity(new int[]{0}, new int[]{0}));
    }

}
