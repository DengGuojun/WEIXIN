package com.lpmas.weixin.api.receive.util;

import java.util.ArrayList;

public class ByteList {

	ArrayList<Byte> list = new ArrayList<Byte>();

	public byte[] toBytes() {
		byte[] bytes = new byte[list.size()];
		for (int i = 0; i < list.size(); i++) {
			bytes[i] = list.get(i);
		}
		return bytes;
	}

	public ByteList addBytes(byte[] bytes) {
		for (byte b : bytes) {
			list.add(b);
		}
		return this;
	}
}
