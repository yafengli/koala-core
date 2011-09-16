package demo;

/**
 * User: Administrator
 * Date: 11-9-13
 * Time: 上午10:36
 */
public class Blog {
    private String tablename;
    private Long id;
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
