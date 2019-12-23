package ua.nure.cs.koshurnikov.usermanagement.gui;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ua.nure.cs.koshurnikov.usermanagement.domain.User;
import ua.nure.cs.koshurnikov.usermanagement.util.Messages;

public class UserTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7275718006176609008L;
	private static final String[] COLUMN_NAMES = {
			Messages.getString("UserTableModel.id"),
			Messages.getString("UserTableModel.first_name"),
			Messages.getString("UserTableModel.last_name")};//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	private static final Class[] COLUMN_CLASSES= {Long.class,String.class,String.class};
	private List<User> users = null;
	
	public UserTableModel(Collection<User> users) {
		this.users = new ArrayList<>(users);
	}
	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public int getRowCount() {
		return users.size();
	}
	public Class<?> getColumnClass(int column) {
		return COLUMN_CLASSES[column];
	}
	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = (User) users.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2:
			return user.getLastName();
		}
		return null;
	}
	public User getUser(int index) {
		return(User) users.get(index);
	}
	public void addUsers(Collection<User> users) {
		this.users.addAll(users);
	}
	public void clearUsers() {
		this.users = new ArrayList<User>();
	}
}
