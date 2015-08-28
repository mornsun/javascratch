package topcoder;

import java.util.*;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
Hide Tags Hash Table Bit Manipulation

 * @author Chauncey
 *
 */
public class RepeatedDNASequences
{
	private static final byte[] _map = new byte[26];
	public RepeatedDNASequences() {
		_map['A'-'A'] = 0;
		_map['C'-'A'] = 1;
		_map['G'-'A'] = 2;
		_map['T'-'A'] = 3;
	}
    public List<String> findRepeatedDnaSequences(String s) {
        if (s==null || s.length()<11) return new ArrayList<String>();
    	HashSet<String> res = new HashSet<String>();
        int l = s.length();
        int bitseq = 0;
        int i = 0;
        for (; i<10; ++i) {
        	char ch = s.charAt(i);
        	bitseq <<= 2;
        	bitseq |= _map[ch-'A'];
        	bitseq &= 0x000fffff;
        }
        HashSet<Integer> set = new HashSet<Integer>();
        set.add(bitseq);
        for (; i<l; ++i) {
        	char ch = s.charAt(i);
        	bitseq <<= 2;
        	bitseq |= _map[ch-'A'];
        	bitseq &= 0x000fffff;
            if (set.contains(bitseq)) res.add(s.substring(i+1-10, i+1));
            else set.add(bitseq);
        }
        ArrayList<String> result = new ArrayList<String>(res.size());
        for (String key : res) {
        	result.add(key);
        }
        return result;
    }
	    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		RepeatedDNASequences solution = new RepeatedDNASequences();
		
		System.out.println(solution.findRepeatedDnaSequences("TGCTCCTGTCACAACTTCTTTACCAGCCTGTTTTTCTAGAGTCGGCTCAAAACCTGCCTTTATGCGCAGCTGTCCACGAGAATTTCATGTTATCGAGGACCGCGATATACCCAATCGCGCGCCCCAGAAAAAAGAGTCTTACCAGATGTATACGGTGACGACCCAGTGGGTAAGACCGCTCTGCTCAGCGACCCGTCCATACCCACAGTCAGCCATGTGTGACATATCAGCGTGCATTCTTGATCTGTATGGGTGCGCTGCCCCCGCACTTGATGGGGTATGTGATGACTCCGCTCGGTAAGCAAGACCCTGGGGGTTCGGACGTAGGGTATACCCGAACTTCACGTATGCGGACACCAACGCACGTGCCAATTTATCTAACGTATGTCTCCATGCCGCCCAGAAGGTTAAAGTGGACCGCCGTTCGTATACTGTTTCTGCAATTGTGTGCGGCAGCACCAGGTAGAGAGCATTCTATTTCGCTAGCTAGTAAATCTACTTCACCGAGTCTGGAAGCTCCAATCGCTGTTTACAAACTTTTTGCCCCTGCGTGGGTCAGGCCATGTCCCGTTCCCGAGGATTCTAGCACTGACCTAGCCCTATATCACGAGCCGGGTTTTCTTAAAATAGAGATCGGGACGTTAAGGTCTTATGAACGGCTTCAGCTATCTTCCGCTTACCAACTGAGCCGAACTATCTCCGGGTGTTACATGGATCCTAAAATGCTCTCCAATTTTGCCCCTGCATGGTATTTCTCTTGAGACTACTGGATCTACCTGGGTTGTGCATGTTTCGTGTCTCTTCCGACGTTCGACAATTGGGGGCGACGCTTTAAGTTCTACTACGGTGAGATGCACATCCCACGGACGCCCTTTTCCTTTGGCTCTTCCTACGTTCGCGAGCGGTCCTGTAGGACAGTTGCTTTATGCCAACTTTTACGAGGGTGGAATACAGTATCGCCATGACACTCTGAAAAAGGATGGAAGACCTGAGATTCACC"));
	}

}
