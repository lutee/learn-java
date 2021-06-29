package cn.chenyuxian.netty.aio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

public class AioClient {
	
	public static void main(String[] args) throws IOException {
		AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
		Future<Void> future = socketChannel.connect(new InetSocketAddress("127.0.0.1", 7387));
		System.out.println("client start....");
		future.get();
		socketChannel.read(ByteBuffer.allocate(1024), null, new Aioclie, 0, null, null, null);
	}
}
