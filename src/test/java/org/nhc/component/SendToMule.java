package org.nhc.component;

import java.io.File;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.mule.api.MuleContext;
import org.mule.module.client.MuleClient;
import org.slf4j.Logger;


public class SendToMule implements JavaDelegate {
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String id = execution.getId();
		System.out.println("sending in process instance: "+id);
		try {
			MuleClient client = new MuleClient("test.xml");
			System.out.println("started: "+client.getMuleContext().isStarted());
			client.getMuleContext().start();
//			client.send("http://localhost:8081/activiti/"+id, "/activiti/"+id, null);
			client.dispatch("vm://receive", id, null);
			System.out.println("finish dispatch");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		
	}
}
