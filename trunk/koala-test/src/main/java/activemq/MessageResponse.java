package activemq;

public interface MessageResponse<T> {
    public T getResponse();

    public void setResponse(T t);
}
