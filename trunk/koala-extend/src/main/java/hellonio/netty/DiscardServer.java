package hellonio.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import hellonio.netty.server.TimeServerHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class DiscardServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChannelFactory factory=new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),Executors.newCachedThreadPool());
		ServerBootstrap boot=new ServerBootstrap(factory);
        boot.setPipelineFactory(new ChannelPipelineFactory(){
            public ChannelPipeline getPipeline() throws Exception {
//                return Channels.pipeline(new DiscardServerHandler());
                return Channels.pipeline(new TimeServerHandler());
            }

        });		
		boot.setOption("child.tcpNoDelay", true);
		boot.setOption("child.keepAlive", true);
        
		boot.bind(new InetSocketAddress(8765));
	}
}
