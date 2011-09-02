package hadoop;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

/**
 *
 * @author Administrator
 */
public class ByteFindMapper extends Mapper<Object, Text, Object, Object> {

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        
    }
    
}
