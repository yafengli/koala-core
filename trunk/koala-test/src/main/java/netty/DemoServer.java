package netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * User: Administrator
 * Date: 11-7-25
 * Time: 下午6:11
 */
public class DemoServer {
    public static void main(String args[]) {
        ServerBootstrap sbt = new ServerBootstrap(
                new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        sbt.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new DiscardServerHandler());
            }
        });
        sbt.setOption("child.tcpNoDelay", true);
        sbt.setOption("child.keepAlive", true);
        sbt.bind(new InetSocketAddress(8765));
    }
}
