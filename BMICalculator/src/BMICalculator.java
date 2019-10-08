import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;



public class BMICalculator {

	boolean sex = true;
	
	public static void main(String args[]) {
		
		new BMICalculator();
	}
	
	BMICalculator(){
		//SEtting the frame
		JFrame f = new JFrame("BMI Calculator");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLayout(new BorderLayout());
		
		//Upper bar
		JPanel upper = new JPanel();
		upper.setBackground(Color.GREEN);
		
		JTextArea up = new JTextArea();
		up.setText("Calculate your BMI!");
		up.setFont(new Font("DIALOG",Font.BOLD, 12));
		up.setBackground(Color.GREEN);
		upper.add(up);
		
		//Middle bar
		JPanel middle = new JPanel();
		middle.setLayout(new GridLayout(3,2,5,5));
		
		JTextField sexLabel = new JTextField();
		sexLabel.setText("Choose sex");
		sexLabel.setEditable(false);
		
		JTextField weightLabel = new JTextField();
		weightLabel.setText("Type your weight i.e: 74.1");
		weightLabel.setEditable(false);
		
		
		JTextField heightLabel = new JTextField();
		heightLabel.setText("Type your height i.e.: 1.75");
		heightLabel.setEditable(false);
		
		JRadioButton m = new JRadioButton("Male");
		m.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sex = true;
			}
		});
		JRadioButton k = new JRadioButton("Female");
		k.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sex = false;
			}
		});
		m.setSelected(true);
		
		ButtonGroup buttGroup = new ButtonGroup();
		buttGroup.add(m);
		buttGroup.add(k);
		
		JPanel chooseSex = new JPanel();		
		chooseSex.setLayout(new GridLayout(1,2));
		chooseSex.add(m);
		chooseSex.add(k);
		
		JTextField weight = new JTextField();
		JTextField height = new JTextField();
		
		middle.add(sexLabel);
		middle.add(chooseSex);		
		middle.add(weightLabel);
		middle.add(weight);		
		middle.add(heightLabel);
		middle.add(height);


		
		//Lower bar
		JPanel lower = new JPanel();
		lower.setLayout(new GridLayout(1,2,5,5));
		JTextField result = new JTextField();	
		result.setEditable(false);
		JButton calc = new JButton(new AbstractAction("Calculate:") {
			public void actionPerformed(ActionEvent e) {
				String w ="";
				String h ="";
				
				if(weight.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Enter weight");
				}else {
					Pattern p = Pattern.compile("\\d+\\.?\\d*");
					Matcher m = p.matcher(weight.getText());
					if(m.find()) {
					w = m.group();
				}else {
					JOptionPane.showMessageDialog(null, "Wrong pattern of weight");
				}
				};
				if(height.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Enter height");
				}else {	
					Pattern p = Pattern.compile("\\d\\.?\\d*");
					Matcher m = p.matcher(height.getText());
					if(m.find()) {
					h = m.group();
					}else {
						JOptionPane.showMessageDialog(null, "Wrong pattern of height");
					}
				};
				if(!(height.getText().isEmpty()) && !(weight.getText().isEmpty())) {
					result.setText(calculate(sex, w,h));
					if( changeColor(sex, w, h)== true){
						result.setBackground(Color.GREEN);
						}
						else {
							result.setBackground(Color.RED);
						}
				}
				}
				
			
		});

		
		lower.add(calc);
		lower.add(result);
		
		//Adding bars to the frame
		f.add(upper,BorderLayout.NORTH);
		f.add(middle, BorderLayout.CENTER);
		f.add(lower, BorderLayout.SOUTH);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				f.pack();
				f.setVisible(true);
			}
		});
	}
	public static String calculate(boolean s, String w, String h) {
		double wr = Double.parseDouble(w);
		double hr = Double.parseDouble(h);
		return Double.toString(wr/(hr*hr));
	}
	public static boolean changeColor(boolean s, String w, String h) {
		double wr = Double.parseDouble(w);
		double hr = Double.parseDouble(h);
		double res = (wr/(hr*hr));
		if(s==true && res >= 19 && res <= 25) {
			System.out.println("1: " +s  + res);
			return true;
		}
		else if(s==false && res >= 18 && res <= 25) {
			System.out.println("2: " +s  + res);
			return true;
		}
		else { 
			System.out.println("3: " +s + res);
			return false;
		}
	}
}
