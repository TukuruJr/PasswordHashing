import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.controller;
import view.Iview;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.awt.event.ActionEvent;

public class Login implements Iview.Login {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;


	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(128, 0, 0));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLoginModule = new JLabel("LOGIN MODULE");
		lblLoginModule.setForeground(new Color(255, 255, 0));
		lblLoginModule.setBounds(157, 12, 185, 15);
		frame.getContentPane().add(lblLoginModule);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setForeground(Color.YELLOW);
		lblUsername.setBounds(52, 67, 131, 15);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblLoginModule_1_1 = new JLabel("PASSWORD");
		lblLoginModule_1_1.setForeground(Color.YELLOW);
		lblLoginModule_1_1.setBounds(52, 156, 131, 15);
		frame.getContentPane().add(lblLoginModule_1_1);
		
		textField = new JTextField();
		textField.setBounds(181, 70, 231, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(181, 154, 231, 31);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DoLogin();
				} catch (HeadlessException | NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(295, 216, 117, 25);
		frame.getContentPane().add(btnLogin);
	}
	
	private void DoLogin() throws NoSuchAlgorithmException {
		controller cr = new controller();
		cr.setLview(new Login());
		cr.onLogin(textField.getText(), HashInput(passwordField.getText()));
	    
	}
	

	
	//a method to hash user input for comparing to the hashValue in the db
	private String HashInput(String pass) throws NoSuchAlgorithmException {
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

	@Override
	public void LoginSuccess(String message) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(frame, message);
	}

	@Override
	public void LoginFailed(String error) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(frame, error,"warning",JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void ShowHash(String message) {
		// TODO Auto-generated method stub
		System.out.println("\n"+"\n"+message);
	}

}
