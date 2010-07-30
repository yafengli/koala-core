package hellonio.netty.client;

import hellonio.netty.decode.TimeDecoder;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * User: phoenixup
 * Date: 2010-7-12
 * Time: 14:01:22
 */
public class TimeClient {
    public static void main(String args[]) {
        ChannelFactory factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
        long start=System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            ClientBootstrap bootstrap = new ClientBootstrap(factory);
            bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
                public ChannelPipeline getPipeline() throws Exception {
                    return Channels.pipeline(new TimeDecoder(), new TimeClientHandler());
                }
            });
            bootstrap.setOption("tcpNoDelay", true);
            bootstrap.setOption("keepAlive", true);
            bootstrap.connect(new InetSocketAddress("localhost", 8765));
        }
        long end=System.currentTimeMillis();
        System.out.printf("[time=%d]\n",(end-start)/1000L);
    }
}
