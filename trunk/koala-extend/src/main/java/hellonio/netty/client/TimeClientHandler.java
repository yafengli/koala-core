package hellonio.netty.client;

import hellonio.netty.decode.UnixTime;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

import java.nio.ByteOrder;
import java.util.Date;

/**
 * User: phoenixup
 * Date: 2010-7-12
 * Time: 13:57:58
 */
public class TimeClientHandler extends SimpleChannelHandler{
//    @Override
//    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//        ChannelBuffer buffer =(ChannelBuffer)e.getMessage();
//        long time=buffer.readInt()*1000L;
//        System.out.println(new Date(time));
//        e.getChannel().close();
//    }


    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        UnixTime time=(UnixTime)e.getMessage();
        System.out.println(new Date(time.getValue()*1000L));
        ChannelBuffer buffer= ChannelBuffers.buffer(ByteOrder.LITTLE_ENDIAN,4);
        buffer.writeBytes("quit".getBytes());
        ChannelFuture f = e.getChannel().write(buffer);                
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        Channel ch=e.getChannel();
        ch.close();
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("[Channel is closed.]");
    }
}
