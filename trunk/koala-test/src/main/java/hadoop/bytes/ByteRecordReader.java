package hadoop.bytes;

import java.io.IOException;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

/**
 *
 * @author Administrator
 */
public class ByteRecordReader  extends RecordReader<LongWritable, BytesWritable> {

    @Override
    public  void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LongWritable getCurrentKey() throws IOException, InterruptedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void initialize(InputSplit is, TaskAttemptContext tac) throws IOException, InterruptedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
