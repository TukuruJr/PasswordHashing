/**
 * @author Framc
 * @about salting and hashing using sha256 algorithm
 */
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Icontroller;
import controller.controller;
import database.Backend;
import model.User;
import view.Iview;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Register implements Iview.Register, ActionListener{

	private JFrame frame;
	private JTextField tfname;
	private JTextField tfemail;
	private JPasswordField tfpass;
    private JButton btnRegister;
    Backend bk;
    private JLabel lblLogin;
    private JLabel showhash;
    private JLabel lblShaAlgorithm;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 652, 322);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblJavaCryptographyArchitecturejca = new JLabel("JAVA CRYPTOGRAPHY ARCHITECTURE(JCA) IMPLEMENTATION DEMO");
		lblJavaCryptographyArchitecturejca.setForeground(Color.CYAN);
		lblJavaCryptographyArchitecturejca.setBounds(64, 12, 513, 15);
		frame.getContentPane().add(lblJavaCryptographyArchitecturejca);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setForeground(Color.GREEN);
		lblUsername.setBounds(110, 55, 99, 15);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setForeground(Color.GREEN);
		lblEmail.setBounds(110, 102, 89, 15);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(Color.GREEN);
		lblPassword.setBounds(89, 175, 99, 15);
		frame.getContentPane().add(lblPassword);
		
		tfname = new JTextField();
		tfname.setBounds(227, 53, 259, 27);
		frame.getContentPane().add(tfname);
		tfname.setColumns(10);
		
		tfemail = new JTextField();
		tfemail.setColumns(10);
		tfemail.setBounds(227, 100, 259, 27);
		frame.getContentPane().add(tfemail);
		
		tfpass = new JPasswordField();
		tfpass.setBounds(227, 163, 259, 27);
		frame.getContentPane().add(tfpass);
		
		btnRegister = new JButton("REGISTER");
		btnRegister.setForeground(Color.BLACK);
		btnRegister.setBackground(Color.ORANGE);
		btnRegister.setBounds(369, 220, 117, 25);
		btnRegister.addActionListener(this);
		frame.getContentPane().add(btnRegister);
		
		lblLogin = new JLabel("Login?");
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login lg = new Login();
				lg.frame.setVisible(true);
			}
		});
		lblLogin.setForeground(Color.GREEN);
		lblLogin.setBounds(524, 225, 99, 15);
		frame.getContentPane().add(lblLogin);
		
		showhash = new JLabel("");
		showhash.setForeground(Color.CYAN);
		showhash.setBounds(12, 269, 611, 15);
		frame.getContentPane().add(showhash);
		
		lblShaAlgorithm = new JLabel("SHA-256 ALGORITHM ");
		lblShaAlgorithm.setForeground(Color.MAGENTA);
		lblShaAlgorithm.setBounds(250, 36, 236, 15);
		frame.getContentPane().add(lblShaAlgorithm);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		if(ev.getSource().equals(btnRegister)) {
			try {
				Register();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	//a method to generate a  hashed password from user input
	private String SafePass(String pass) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");  //using Safe Hashing Algorithm 256.
		
		md.update(pass.getBytes()); //pass the salt bytes
		
		byte[] bytes =md.digest(); 
		
		//Generate the hash 
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<bytes.length;i++) {
			sb.append(Integer.toString((bytes[i]& 0xff)+0*100, 16).substring(1));
		}
		return sb.toString();
	}
	
	private void Register() throws NoSuchAlgorithmException {
		String name = tfname.getText();
		String email = tfemail.getText();
		String password = tfpass.getText().toString();
		String safepass = SafePass(password);
		controller cr = new controller();
		cr.setRview(new Register());
		cr.onRegister(name, email, password, safepass);
	}

	@Override
	public void FieldsError(String error) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(frame, error,"warning",JOptionPane.WARNING_MESSAGE);
		
	}

	@Override
	public void RegistrationSuccess(String message) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(frame, message);
	}

	@Override
	public void RegistrationFailed(String error) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(frame, error,"warning",JOptionPane.WARNING_MESSAGE);
		
	}

	@Override
	public void ShowHash(String message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

}
