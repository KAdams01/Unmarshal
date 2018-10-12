package dk.via.tasks.rmi;

import dk.via.tasks.Task;

public interface TaskListInterface {
	public void add(Task t);
	public Task getAndRemoveNextTask();
	public int size();

}
