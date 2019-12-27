package ua.nure.cs.koshurnikov.usermanagement.agent;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;


public class SearchRequestBehaviour extends Behaviour {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7183562418993720049L;
	private AID[] aids;
	private String firstName;
	private String lastName;
	
	public SearchRequestBehaviour(AID[] aids, String firstName, String lastName) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.aids=aids;
	}

	@Override
	public void action() {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		message.setContent(firstName +","+lastName);
		for (int i=0; i<aids.length;i++) {
			message.addReceiver(aids[i]);
		}
		myAgent.send(message);
	}

	@Override
	public boolean done() {
		return true;
	}

}
