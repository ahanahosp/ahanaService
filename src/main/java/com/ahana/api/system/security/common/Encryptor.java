package com.ahana.api.system.security.common;

import org.apache.commons.lang3.StringUtils;

import com.ahana.api.common.Constants;
import com.ahana.api.system.security.exception.AhanaApplicationException;
import com.ahana.api.system.security.exception.AhanaRuntimeException;
import com.ahana.api.system.security.exception.AhanaSystemException;

class Encryptor {

	private static final int MAP[] = { 0, 10, 20, 30, 1, 11, 21, 31, 2, 12, 22,
			3, 13, 23, 4, 14, 24, 5, 15, 25, 6, 16, 26, 7, 17, 27, 8, 18, 28,
			9, 19, 29 };

	private static int REMAP[];

	static {
		REMAP = new int[MAP.length];
		for (int i = 0; i < MAP.length; i++) {
			REMAP[MAP[i]] = i;
		}
	}

	private int rPosition = 0;

	private int wPosition = 0;

	private char[] data = null;

	public Encryptor() {
		data = new char[1024];
	}

	Encryptor(String coded) throws AhanaRuntimeException {
		data = new char[1024];
		parse(coded);
	}

	private Encryptor(char[] coded) throws AhanaRuntimeException {
		data = coded;
		wPosition = coded.length;
	}

	void append(String value) throws AhanaRuntimeException {
		if (value == null) {
			return;
		}
		StringBuffer sb = new StringBuffer(value);
		int l = sb.length();
		ensureCapacity(l + 5);
		int s = 0;
		for (int j = 0; j < l; j++) {
			s += sb.charAt(j);
		}
		s = s & 31;
		data[wPosition++] = (char) (s + 32);
		String len = Integer.toHexString(l);
		sb.insert(0, len);
		for (int llen = len.length(); llen < 4; llen++) {
			sb.insert(0, "0");
		}
		for (int j = 0; j < l + 4; j++) {
			data[wPosition++] = map(sb.charAt(j), s);
		}
	}

	public String next() throws AhanaRuntimeException {
		if (rPosition >= (wPosition - 1)) {
			return null;
		}
		int cs = data[rPosition++] - 32;
		if (cs < 0 || cs > 31 || (rPosition + 4) > wPosition) {
			throw new AhanaSystemException(Constants.ENCRYPTION_ERROR);
		}
		// Read length
		StringBuffer sb = new StringBuffer(4);
		for (int i = 0; i < 4; i++) {
			sb.append(remap(data[rPosition++], cs));
		}
		// Check lenegth
		int l = 0;
		try {
			l = Integer.parseInt(sb.toString(), 16);
		} catch (NumberFormatException ex) {
			throw new AhanaApplicationException(Constants.ENCRYPTION_ERROR);
		}
		if (l == 0 || ((rPosition + l) > wPosition)) {
			throw new AhanaApplicationException(Constants.ENCRYPTION_ERROR);
		}
		// Read it in
		sb.setLength(0);
		sb.ensureCapacity(l);
		for (int i = 0; i < l; i++) {
			sb.append(remap(data[rPosition++], cs));
		}
		return sb.toString();
	}

	public String toString() {
		if (wPosition == 0) {
			return null;
		}

		return new String(data, 0, wPosition);
	}

	private char[] toArray() {
		char b[] = new char[wPosition];
		for (int i = 0; i < wPosition; i++) {
			b[i] = data[i];
		}
		return b;
	}

	private void ensureCapacity(int i) {
		if (data.length - wPosition > i) {
			return;
		}

		// Create a new array
		if (i < 1024) {
			i = 1024;
		}
		char[] ndata = new char[wPosition + i];
		for (i = 0; i < wPosition; i++) {
			ndata[i] = data[i];
		}
		data = ndata;
	}

	public static void main(String[] args) {
		if (2 != args.length) {
			System.err.println("USAGE: Encryptor (-encrypt|-decrypt) <value>");
		}

		try {
			if (args[0].equals("-encrypt")) {
				Encryptor crypt = new Encryptor();
				crypt.append(args[1]);
				char[] cArray = crypt.toArray();
				System.out.println(Encryptor.hexEncode(cArray));
			} else if (args[0].equals("-decrypt")) {
				Encryptor crypt = new Encryptor(Encryptor.hexDecode(args[1]));
				System.out.println(crypt.next());
			} else {
				System.err
						.println("USAGE: Encryptor (-encrypt|-decrypt) <value>");
				System.exit(1);
			}
		} catch (AhanaSystemException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return a hex encoded string representing the binary data parameter.
	 * 
	 * @param binaryData
	 *            an array of characters to encode
	 * @return a String containing only hex characters [0-9a-f]
	 */
	private static String hexEncode(char[] binaryData) {
		StringBuffer rv = new StringBuffer();

		if (null == binaryData) {
			return rv.toString();
		}

		for (int i = 0; i < binaryData.length; i++) {
			int left = (binaryData[i] & 240) >>> 4;
			rv.append(encode(left));
			int right = binaryData[i] & 15;
			rv.append(encode(right));
		}
		return rv.toString();
	}

	/**
	 * Return a character array of data represented by the hex encoded string
	 * parameter.
	 * 
	 * @param string
	 *            hex encoded data containing only hex characters [0-9a-f]
	 * @return character array of data
	 */
	private static char[] hexDecode(String string) {
		if (null == string) {
			return new char[0];
		}

		if (0 != (string.length() % 2)) {
			return new char[0];
		}

		char[] rv = new char[string.length() / 2];
		int j = 0;
		for (int i = 0; i < string.length(); i += 2) {
			int left = decode(string.charAt(i)) << 4;
			int right = decode(string.charAt(i + 1));
			rv[j++] = (char) (left + right);
		}
		return rv;
	}

	public static String mask(String in) {
		return mask(in, 4);
	}

	private static String mask(String in, int length) {
		if (StringUtils.isEmpty(in)) {
			return "";
		}

		StringBuilder sb = new StringBuilder(in);
		for (int i = 0, n = sb.length() - length; i < n; i++) {
			if (Character.isLetterOrDigit(sb.charAt(i))) {
				sb.setCharAt(i, 'x');
			}
		}
		return sb.toString();
	}
	
	private void parse(String coded) throws AhanaRuntimeException {
		int l = coded.length();
		ensureCapacity(l);

		coded.getChars(0, l, data, wPosition);
		wPosition += l;
	}
	
	private static char map(char c, int cs) {
		int o = c & 31; // last 5 digits
		o += cs;
		if (o >= 32) {
			o -= 32;
		}
		char r = (char) ((c / 32 * 32) + MAP[o]);
		return r;
	}

	private static char remap(char c, int cs) {
		int o = REMAP[c & 31]; // last 5 digits
		if (o < cs) {
			o += 32;
		}
		o -= cs;
		return (char) ((c / 32 * 32) + o);
	}
	
	private static char encode(int i) {
		if (i < 10) {
			return (char) (i + 48);
		}

		if ((i >= 10) && (i <= 16)) {
			return (char) (i + 87);
		}

		return '?';
	}
	
	private static int decode(char i) {
		if (i < 58) {
			return i - 48;
		}

		if (i >= 97) {
			return i - 87;
		}
		return 0;
	}
}
