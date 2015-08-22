package topcoder;

import java.util.*;

/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file. 
The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file. 
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file. 
Note:
The read function may be called multiple times.
 * 
 * @author Chauncey
 *
 */
public class ReadNCharactersGivenRead4II {
	private char[] buffer = new char[4];
	int offset = 0, bufsize = 0;

	private char[] _content = null;
	private int _ofs_content = 0;
	ReadNCharactersGivenRead4II(char[] content) {
		_content = content;
	}
	public int read4(char[] buf) {
		if (null == _content || null == buf || buf.length < 4) return 0;
		int sz = _ofs_content+4>_content.length ? _content.length-_ofs_content : 4;
		System.arraycopy(_content /* src */, _ofs_content /* srcPos */, buf /* dest */,
				0 /* destPos */, sz /* length */);
		_ofs_content += sz;
		return sz;
	}
	/**
	 * @param buf Destination buffer
	 * @param n Maximum number of characters to read
	 * @return The number of characters read
	 */
	public int read(char[] buf, int n) {
		int readBytes = 0;
		boolean eof = false;
		while (!eof && readBytes < n) {
			int sz = (bufsize > 0) ? bufsize : read4(buffer);
			if (bufsize == 0 && sz < 4)
				eof = true;
			int bytes = Math.min(n - readBytes, sz);
			System.arraycopy(buffer /* src */, offset /* srcPos */, buf /* dest */,
					readBytes /* destPos */, bytes /* length */);
			offset = (offset + bytes) % 4;
			bufsize = sz - bytes;
			readBytes += bytes;
		}
		return readBytes;
	}
	/**
	 * @param buf Destination buffer
	 * @param n Maximum number of characters to read
	 * @return The number of characters read
	 */
	public int singleread(char[] buf, int n) {
	    char[] buffer = new char[4];
		int readBytes = 0;
		boolean eof = false;
		while (!eof && readBytes < n) {
			int sz = read4(buffer);
			if (sz < 4)
				eof = true;
			int bytes = Math.min(n - readBytes, sz);
			System.arraycopy(buffer /* src */, 0 /* srcPos */, buf /* dest */,
					readBytes /* destPos */, bytes /* length */);
			readBytes += bytes;
		}
		return readBytes;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReadNCharactersGivenRead4II solution = new ReadNCharactersGivenRead4II("abcdefghijklmn".toCharArray());

		char[] buf = new char[1024];
		System.out.println(solution.read(buf,5) + ":" + String.valueOf(buf));
		System.out.println(solution.read(buf,6) + ":" + String.valueOf(buf));
	}

}
