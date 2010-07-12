package hellonio.netty.server;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

public class DiscardServerHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
            throws Exception {
        Channel ch = e.getChannel();
        isClose(ch, e);
        ch.write(e.getMessage());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
            throws Exception {
        e.getCause().printStackTrace();
        Channel ch = e.getChannel();
        ch.close();
    }

    private boolean isClose(Channel ch, MessageEvent e) {
        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        while (buffer.readable()) {
            System.out.println(buffer.readByte());
            System.out.flush();
        }
        return false;
    }
}
