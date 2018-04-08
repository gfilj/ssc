package share.util;

import java.security.SecureRandom;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SessionIdGenerator {

	private static final Queue<SecureRandom> randoms = new ConcurrentLinkedQueue<>();

	public static String generateSessionId() {
		byte random[] = new byte[16];

		StringBuilder buffer = new StringBuilder();

		int resultLenBytes = 0;
		while (resultLenBytes < 32) {
			getRandomBytes(random);
			for (int j = 0; j < random.length && resultLenBytes < 32; j++) {
				byte b1 = (byte) ((random[j] & 0xf0) >> 4);
				byte b2 = (byte) (random[j] & 0x0f);
				if (b1 < 10)
					buffer.append((char) ('0' + b1));
				else
					buffer.append((char) ('A' + (b1 - 10)));
				if (b2 < 10)
					buffer.append((char) ('0' + b2));
				else
					buffer.append((char) ('A' + (b2 - 10)));
				resultLenBytes++;
			}
		}

		return buffer.toString();
	}

	private static void getRandomBytes(byte bytes[]) {
		SecureRandom random = randoms.poll();
		if (random == null) {
			random = createSecureRandom();
		}
		random.nextBytes(bytes);
		randoms.add(random);
	}

	private static SecureRandom createSecureRandom() {
		SecureRandom result = null;
		if (result == null) {
			result = new SecureRandom();
		}

		result.nextInt();
		return result;
	}
}
