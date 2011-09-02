package hadoop.bytes;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

/**
 *
 * @author Administrator
 */
public class TextPair implements WritableComparable<TextPair> {

    private Text first;
    private Text second;

    public TextPair() {
        this.first = new Text();
        this.second = new Text();
    }

    public TextPair(Text first, Text second) {
        this.first = first;
        this.second = second;
    }

    public Text getFirst() {
        return first;
    }

    public void setFirst(Text first) {
        this.first = first;
    }

    public Text getSecond() {
        return second;
    }

    public void setSecond(Text second) {
        this.second = second;
    }

    @Override
    public void readFields(DataInput di) throws IOException {
        first.readFields(di);
        second.readFields(di);
    }

    @Override
    public void write(DataOutput d) throws IOException {
        first.write(d);
        second.write(d);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof TextPair) {
            TextPair tp = (TextPair) o;
            return first.equals(tp.first) && second.equals(tp.second);
        }
        return false;
    }

    @Override
    public String toString() {
        return first.toString() + "\t" + second.toString();
    }

    @Override
    public int compareTo(TextPair t) {
        int cmp = first.compareTo(t.first);
        if (cmp != 0) {
            return cmp;
        }
        return second.compareTo(t.second);
    }
}
