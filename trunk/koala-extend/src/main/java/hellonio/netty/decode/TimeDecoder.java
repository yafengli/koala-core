package hellonio.netty.decode;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 * User: phoenixup
 * Date: 2010-7-5
 * Time: 10:27:36
 */
public class TimeDecoder extends FrameDecoder {
    @Override
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {
        if (channelBuffer.readableBytes() < 4) {
            return null;
        } else {
            int value = channelBuffer.readInt();
            return new UnixTime(value, value);
        }
    }
}
