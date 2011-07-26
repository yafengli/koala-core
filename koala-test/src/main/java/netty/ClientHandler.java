package netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

import java.nio.ByteBuffer;

/**
 * User: Administrator
 * Date: 11-7-25
 * Time: 下午6:12
 */
public class ClientHandler extends SimpleChannelUpstreamHandler {
    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("Open");
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("Connected");
        ByteBuffer buffer = ByteBuffer.allocate(256);
        for (char i = 'a'; i <= 'z'; i++) {
            buffer.putChar(i);
        }
        buffer.flip();
        e.getChannel().write(ChannelBuffers.copiedBuffer(buffer));
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        ChannelBuffer buf = (ChannelBuffer) e.getMessage();
        System.out.println((char) buf.readByte());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}