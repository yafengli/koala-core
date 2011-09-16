package demo;

/**
 * User: Administrator
 * Date: 11-9-13
 * Time: 上午10:18
 */
public interface BlogMapper {
    public Blog selectBlog(int id);

    public Blog selectBlogByTableName(Blog blog);
}
