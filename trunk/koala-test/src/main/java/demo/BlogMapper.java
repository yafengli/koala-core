package demo;

import java.util.*;

/**
 * User: Administrator Date: 11-9-13 Time: 上午10:18
 */
public interface BlogMapper {

    public Blog selectBlog(int id);

    public Blog selectBlogByTableName(Object blog);

    public List<Blog> selectAll(Object blog);
}
