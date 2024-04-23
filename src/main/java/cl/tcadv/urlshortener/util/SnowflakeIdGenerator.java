package cl.tcadv.urlshortener.util;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SnowflakeIdGenerator implements IdGenerator {

	private static final Logger logger = LoggerFactory.getLogger(SnowflakeIdGenerator.class);
	private static final int EPOCH_BITS = 41;
	private static final int NODE_ID_BITS = 10;
	private static final int SEQUENCE_BITS = 12;

	private static final long maxNodeId = (1L << NODE_ID_BITS) - 1;
	private static final long maxSequence = (1L << SEQUENCE_BITS) - 1;

	private static final long DEFAULT_CUSTOM_EPOCH = 1420070400000L;

	private final long nodeId;
	private final long customEpoch;

	private volatile long lastTimestamp = -1L;
	private volatile long sequence = 0L;

	public SnowflakeIdGenerator() {
		this.nodeId = createNodeId();
		this.customEpoch = DEFAULT_CUSTOM_EPOCH;
		logger.info("Snowflake Id generator successfully instantiated: " + this.toString());
	}

	@Override
	public synchronized long nextId() {
		long currentTimestamp = timestamp();

		if (currentTimestamp < lastTimestamp) {
			throw new IllegalStateException("Invalid System Clock!");
		}

		if (currentTimestamp == lastTimestamp) {
			sequence = (sequence + 1) & maxSequence;
			if (sequence == 0) {
				currentTimestamp = waitNextMillis(currentTimestamp);
			}
		} else {
			sequence = 0;
		}

		lastTimestamp = currentTimestamp;

		long id = currentTimestamp << (NODE_ID_BITS + SEQUENCE_BITS) | (nodeId << SEQUENCE_BITS) | sequence;

		return id;
	}

	private long timestamp() {
		return Instant.now().toEpochMilli() - customEpoch;
	}

	private long waitNextMillis(long currentTimestamp) {
		while (currentTimestamp == lastTimestamp) {
			currentTimestamp = timestamp();
		}
		return currentTimestamp;
	}

	private long createNodeId() {
		long nodeId;
		try {
			StringBuilder sb = new StringBuilder();
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				byte[] mac = networkInterface.getHardwareAddress();
				if (mac != null) {
					for (byte macPort : mac) {
						sb.append(String.format("%02X", macPort));
					}
				}
			}
			nodeId = sb.toString().hashCode();
		} catch (Exception ex) {
			nodeId = (new SecureRandom().nextInt());
		}
		nodeId = nodeId & maxNodeId;
		return nodeId;
	}

	@Override
	public String toString() {
		return "Snowflake Settings [EPOCH_BITS=" + EPOCH_BITS + ", NODE_ID_BITS=" + NODE_ID_BITS + ", SEQUENCE_BITS="
				+ SEQUENCE_BITS + ", CUSTOM_EPOCH=" + customEpoch + ", NodeId=" + nodeId + "]";
	}
}
