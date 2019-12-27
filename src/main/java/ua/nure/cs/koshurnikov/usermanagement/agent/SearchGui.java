package ua.nure.cs.koshurnikov.usermanagement.agent;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import ua.nure.cs.koshurnikov.usermanagement.gui.UserTableModel;



public class SearchGui extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4899930867248833923L;

	private SearchAgent agent;

    private JPanel contentPanel;

    private JPanel tablePanel;

    private JTable table;

   
    public SearchGui(SearchAgent agent) {
        this.agent = agent;
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("Title");
        this.setContentPane(getContentPanel());
    }

   
    private JPanel getContentPanel() {
        if (contentPanel == null) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getSearchPanel(), BorderLayout.NORTH);
            contentPanel.add(new JScrollPane(getTablePanel()), BorderLayout.CENTER);
        }
        return contentPanel;
    }

   
    private JPanel getTablePanel() {
        if (tablePanel == null) {
            tablePanel = new JPanel(new BorderLayout());
            tablePanel.add(getTable(), BorderLayout.CENTER);
        }
        return tablePanel;
    }

    
    private JTable getTable() {
        if (table == null) {
            table = new JTable(new UserTableModel(new LinkedList()));
        }
        return table;
    }

    
    private JPanel getSearchPanel() {
        return new SearchPanel(agent);
    }

    public static void main(String[] args) {
        SearchGui gui = new SearchGui(null);
        gui.setVisible(true);
    }
    
   class SearchPanel extends JPanel implements ActionListener {
	   
	private static final long serialVersionUID = 2285807931008107165L;

	   SearchAgent agent;

       private JPanel buttonPanel;

       private JPanel fieldPanel;

       private JButton cancelButton;

       private JButton searchButton;

       private JTextField firstNameField;

       private JTextField dateOfBirthField;

       private JTextField lastNameField;


       public SearchPanel(SearchAgent agent) {
           this.agent = agent;
           initialize();
       }

       private void initialize() {
           this.setName("AgentPanel"); //$NON-NLS-1$
           this.setLayout(new BorderLayout());
           this.add(getFieldPanel(), BorderLayout.NORTH);

       }

       private JPanel getButtonPanel() {
           if (buttonPanel == null) {
               buttonPanel = new JPanel();
               buttonPanel.add(getSearchButton(), null);
               buttonPanel.add(getCancelButton(), null);
           }
           return buttonPanel;
       }

       private JButton getCancelButton() {
           if (cancelButton == null) {
               cancelButton = new JButton();
               cancelButton.setText("Cancel"); //$NON-NLS-1$
               cancelButton.setName("cancelButton"); //$NON-NLS-1$
               cancelButton.setActionCommand("cancel"); //$NON-NLS-1$
               cancelButton.addActionListener(this);
           }
           return cancelButton;
       }

       private JButton getSearchButton() {
           if (searchButton == null) {
               searchButton = new JButton();
               searchButton.setText("Search"); //$NON-NLS-1$
               searchButton.setName("searchButton"); //$NON-NLS-1$
               searchButton.setActionCommand("seacrch"); //$NON-NLS-1$
               searchButton.addActionListener(this);
           }
           return searchButton;
       }

       private JPanel getFieldPanel() {
           if (fieldPanel == null) {
               fieldPanel = new JPanel();
               fieldPanel.setLayout(new GridLayout(2, 3));
               addLabeledField(fieldPanel, "First name", getFirstNameField()); //$NON-NLS-1$
               fieldPanel.add(getSearchButton());
               addLabeledField(fieldPanel, "LastName", getLastNameField()); //$NON-NLS-1$
               fieldPanel.add(getCancelButton());
           }
           return fieldPanel;
       }

       protected JTextField getLastNameField() {
           if (lastNameField == null) {
               lastNameField = new JTextField();
               lastNameField.setName("lastNameField"); //$NON-NLS-1$
           }
           return lastNameField;
       }
       protected JTextField getFirstNameField() {
           if (firstNameField == null) {
        	   firstNameField = new JTextField();
        	   firstNameField.setName("firstNameField"); //$NON-NLS-1$
           }
           return firstNameField;
       }
       private void addLabeledField(JPanel panel, String labelText,
               JTextField textField) {
           JLabel label = new JLabel(labelText);
           label.setLabelFor(textField);
           panel.add(label);
           panel.add(textField);
       }

       

       protected void doAction(ActionEvent e) throws ParseException {
           if ("seacrch".equalsIgnoreCase(e.getActionCommand())) {
               String firstName = getFirstNameField().getText();
               String lastName = getLastNameField().getText();
               try {
                   clearUsers();
                   agent.search(firstName, lastName);
               } catch (SearchException e1) {
                   throw new RuntimeException(e1);
               }
           }
           clearFields();
       }

       public void actionPerformed(ActionEvent e) {
           try {
               doAction(e);
           } catch (ParseException e1) {
               return;
           }
       }

       private void clearFields() {
    	   getLastNameField().setText("");
           getFirstNameField().setText("");
       }
}


public void addUsers(Collection  users) {
   System.out.println("addUsers : " + users);
   UserTableModel model = (UserTableModel) getTable().getModel();
   model.addUsers(users);
   this.repaint();
}

private void clearUsers() {
   System.out.println("clearUsers : ");
   UserTableModel model = (UserTableModel) getTable().getModel();
   model.clearUsers();
   this.repaint();
}
}