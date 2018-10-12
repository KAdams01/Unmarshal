package dk.via.tasks.rmi.client;

import java.io.IOException;
import java.rmi.RemoteException;
import dk.via.requestreply.Message;
import dk.via.requestreply.client.CommunicationModule;
import dk.via.tasks.Task;
import dk.via.tasks.rmi.TaskListInterface;
import dk.via.util.ByteConverter;

public class Proxy implements TaskListInterface {

	@Override
	public void add(Task t) {
		Message m = new Message("add", ByteConverter.toByteArray(t));
		try {
			CommunicationModule.doOperation(m);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Task getAndRemoveNextTask() {
		byte[] args = {};
		Message m = new Message("getAndRemove", args);
		byte[] returnedTask;
		Task t = null;
		try {
			returnedTask = CommunicationModule.doOperation(m);
			t = (Task)ByteConverter.serializableFromByteArray(returnedTask);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public int size() {
		byte[] args = {};
		Message m = new Message("size", args);
		byte[] returnedSize;
		int size = 0;
		try {
			returnedSize = CommunicationModule.doOperation(m);
			size = ByteConverter.intFromByteArray(returnedSize);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return size;
	}

}
