import netty.ClientHandler;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * User: Administrator
 * Date: 11-7-25
 * Time: 下午6:04
 */
public class NettyServerTest {
    @Test
    public void testServer() {
        ClientBootstrap cbt = new ClientBootstrap(
                new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        cbt.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new ClientHandler());
            }
        });
        ChannelFuture cf = cbt.connect(new InetSocketAddress("127.0.0.1", 8765));
        cf.getChannel().getCloseFuture().awaitUninterruptibly();
        cbt.releaseExternalResources();
    }
}


