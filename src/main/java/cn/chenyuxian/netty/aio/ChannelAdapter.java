package cn.chenyuxian.netty.aio;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;

public abstract class ChannelAdapter implements CompletionHandler<Integer, Object>{
	
	private AsynchronousSocketChannel channel;
	
	private Charset charset;
	
	public ChannelAdapter(AsynchronousSocketChannel channel, Charset charset) {
		this.channel = channel;
		this.charset = charset;
		if(channel.isOpen()) {
			channel
		}
	}
	
}
