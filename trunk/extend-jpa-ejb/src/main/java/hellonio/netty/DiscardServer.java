package hellonio.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class DiscardServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChannelFactory factory=new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),Executors.newCachedThreadPool());
		ServerBootstrap boot=new ServerBootstrap(factory);
		ChannelPipeline pipeline=boot.getPipeline();
		pipeline.addLast("handler", new DiscardServerHandler());
		boot.setOption("child.tcpNoDelay", true);
		boot.setOption("child.keepAlive", true);
		boot.bind(new InetSocketAddress(8765));
	}
}
