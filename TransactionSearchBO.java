import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import com.shah.*;
import javax.swing.table.DefaultTableModel;


class TransactionSearchPanel extends JPanel implements ActionListener {

	private JButton SearchTransaction;
	private JTextField StartDateField, EndDateField;
	private String StartDate, EndDate;
	private String UName;
	private Vector TransInfo = new Vector();
	private Vector column = new Vector();
	private final JTable jt;

	public TransactionSearchPanel(String UName, String CustName) {

		this.UName = UName;

		SearchTransaction = new JButton("Search Transaction");

		StartDateField = new JTextField(15);
       	EndDateField = new JTextField(15);

       	JLabel InstructionLabel = new JLabel("Please Enter Start Date and End Date in YYYY-MM-DD Format");
		JLabel StartDateLabel = new JLabel("Start Date: ");
        JLabel EndDateLabel = new JLabel("End Date: ");

        JPanel StartDatePanel = new JPanel();
		JPanel EndDatePanel = new JPanel();
		JPanel InstructionPanel = new JPanel();

		InstructionPanel.add(InstructionLabel);
		StartDatePanel.add(StartDateLabel);
		StartDatePanel.add(StartDateField);
		EndDatePanel.add(EndDateLabel);
		EndDatePanel.add(EndDateField);

		add(InstructionPanel);
		add(StartDatePanel);
		add(EndDatePanel);

		add(SearchTransaction);

		column.add(0, "TransactionNumber");
		column.add(1, "TransactionAmount");
		column.add(2, "TransactionType");
		column.add(3, "TransactionTime");
		column.add(4, "TransactionDate");
		column.add(5, "FromAccount");
		column.add(6, "ToAccount");
		jt = new JTable(TransInfo, column);
		JScrollPane sp = new JScrollPane(jt);
		add(sp);
		System.out.println("At Transaction Search BO");
        System.out.println(TransInfo);

		SearchTransaction.addActionListener(this);

	}

	public void actionPerformed(ActionEvent evt) {
		//Object source = evt.getSource(); //get who generates this event
        String arg = evt.getActionCommand();
        if (arg.equals("Search Transaction")) { //determine which button is clicked
            StartDate = StartDateField.getText(); //take actions
            EndDate = EndDateField.getText();
            if (!StartDate.equals("") && !EndDate.equals("")) {
            	Transaction TZ = new Transaction(StartDate, EndDate);
            	TransInfo = TZ.SearchTransection(UName);
				DefaultTableModel model = (DefaultTableModel) jt.getModel();
				for(Object column : TransInfo)
					model.addRow((Vector)column);

			} else
				JOptionPane.showMessageDialog(null, "Please Enter a Start Date and End Date!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}
    }
}

public class TransactionSearchBO extends JFrame {

	public TransactionSearchBO(String UName, String CustName) {

		setTitle("Transfer Money");
		setSize(350, 260);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width;
		setLocation(screenWidth / 3, screenHeight / 4);

		addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
				System.exit(0);
			}
		});

		Container contentPane = getContentPane();
		TransactionSearchPanel TSP = new TransactionSearchPanel(UName, CustName);
		contentPane.add(TSP);
		show();
	}
}