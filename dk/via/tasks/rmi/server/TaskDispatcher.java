package dk.via.tasks.rmi.server;

import java.io.IOException;

import dk.via.requestreply.Message;
import dk.via.requestreply.server.Recipient;
import dk.via.tasks.Task;
import dk.via.tasks.TaskList;
import dk.via.util.ByteConverter;

public class TaskDispatcher implements Recipient {
	TaskList taskList;

	public TaskDispatcher(TaskList taskList) {
		this.taskList = taskList;
	}

	@Override
	public byte[] interpret(Message message) {
		switch (message.getMethod()) {
		case "add":
			try {
				taskList.add((Task) ByteConverter.serializableFromByteArray(message.getArgs()[0]));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		case "getAndRemove":
			Task t = taskList.getAndRemoveNextTask();
			byte[] task = ByteConverter.toByteArray(t);
			return task;
		case "size":
			byte[] size = ByteConverter.toByteArray(taskList.size());
			return size;
		default:
			return null;
		}


	}

}
