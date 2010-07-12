package hellonio.netty.server;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

/**
 * User: phoenixup
 * Date: 2010-7-5
 * Time: 9:59:19
 */
public class TimeServerHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        System.out.println("[can read count: ]"+buffer.readableBytes());
        if (buffer.readableBytes() >= 4) {
            byte msgb[]=new byte[4];
            buffer.readBytes(msgb);
            String message = new String(msgb, "UTF-8");
            System.out.println("[msg=]"+message);
            if("quit".equalsIgnoreCase(message)){                       
                e.getChannel().close();
            }
        }
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        Channel ch = e.getChannel();
        ChannelBuffer time = ChannelBuffers.buffer(4);
        int value = (int) (System.currentTimeMillis() / 1000);
        time.writeInt(value);
        ChannelFuture f = ch.write(time);
        System.out.printf("[time=%s][%d]\n", Integer.toHexString(value), value);
        /*
        f.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                Channel ch = channelFuture.getChannel();
                ch.close();
            }
        });
        */
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        Channel ch = e.getChannel();
        ch.close();
    }
}
