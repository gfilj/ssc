package redis;

public interface ExecutorBatchService<T> {

	public abstract boolean execute(T t);
}
