package dissertation;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class MainMethod extends JFrame implements ActionListener{
	
	private JButton KnasackButton = new JButton("Knapsack");
	private JButton ThreeMaxSatButton = new JButton("MAX-3SAT");
	private JButton PartitionButton  = new JButton("Partition");
	
	private JButton KnapButtonFitEvalu = new JButton("Knapsack");
	private JButton ThreeMaxSatFitEvalu = new JButton("MAX-3SAT");
	private JButton PartionButtFitEvalu = new JButton("Partition");
	private JButton submitLambdaValueButton = new JButton("Submit");
	private JButton clearComboBox = new JButton("Reset");
	
	private JPanel buttonPanel = new JPanel();
	private JPanel buttonPanelEvaluationFitness = new JPanel();
	private JTextArea t = new JTextArea(20,40);
	private JLabel generationLable = new JLabel("Generations (1-500) :   ");
	private JLabel fitEvaluationLable = new JLabel("Fitness Evaluations :   ");
	private JLabel lambdaValueLable = new JLabel("Lambda Value: ");
	
	private JTextField generationSampleDataText = new JTextField();
	private JLabel GenerationsampleDataPanel = new JLabel("Fitness for Generation: ");
	private JLabel GenerationNullfitnessLabel = new JLabel("* Optional");
	
	private JLabel nullgenerationLabel = new JLabel("");
	private JLabel nullfitnessLabel = new JLabel("* Optional");
	
	private JLabel sampleDataPanel = new JLabel("Fitness for Generation: ");
	private JTextField sampleDataText = new JTextField();
	
	private JComboBox fitnessEvalu = new JComboBox();
	private JTextField generationText = new JTextField();
	private JTextField lambdaText = new JTextField();
	
	private static Algorithms algorithms = new Algorithms();
	private int inputGeneration;
	private int inputFitnessEvaluation;

	private float[] standardKnapsack = new float[inputGeneration];
	private float[] lambdaKnapsak =  new float[inputGeneration];
	private float[] oneKnapsack =  new float[inputGeneration];
	
	private float[] standardKnapsackFitnessEvaluation = new float[inputGeneration];
	private float[] lambdaKnapsakFitnessEvaluation =  new float[inputGeneration];
	private float[] oneKnapsackFitnessEvaluation =  new float[inputGeneration];
	
//	Algorithms algorithms2 = new Algorithms();
	
	//generation against fitness, the number of offspring is 20
	private int offspringScaleGeneratonAgainstFitness = 20;
	
	//fitness evaluation against fitness, the fitness evaluations is 40,thus
	//standard GA and lambda EA has 40 offspring and lambda GA has 20 offspring
	
	private int OneLambdaScale;
	private int offspringScaleFitnessEvaluationAgainstFitness;
	private int sampleData;
	private int generationSampleData;
	
	public static void main(String[] args) {
		run(new MainMethod());	
//		algorithms.callStandarGA(50, new ThreeMaxSatProblem(),20,50);
//		algorithms.callLamdaGAKnasack(50, new KnapsackProblem());
//		algorithms.callOneEA(50, new KnapsackProblem());
	}
	
	public MainMethod() {
		add(buttonPanel);
		add(buttonPanelEvaluationFitness);
		buttonPanel.setBorder(new TitledBorder("Generations Versus Fitness Value"));
		buttonPanel.setLayout(new GridLayout(3, 3));
		buttonPanelEvaluationFitness.setBorder(new TitledBorder("Fitness Evaluations Versus Fitness Value"));
		buttonPanelEvaluationFitness.setLayout(new GridLayout(4, 3));
		setLayout(new FlowLayout());
		addActionListener();		
		addComponent();
//		add(t);
		t.append("\n"+"                                                   Instruction"+"\n"+"\n"+
		"This Java diagram aims to compare the power of three different evolutionary "+"\n"+
		"algorithms,inlcuding standard genetic algorithm,(1+ λ) λ genetic algorithm"+"\n"+
		"and (1+1) evolutionary algorithm.These three algorithm are applied to three"+"\n"+ 
		"different combinatorial problems,namely knapsack problem,pratition"+"\n"+
		"problem and Three maximum satisfiability problem, as you can see their"+"\n"+
		"names on the above buttons. A line chart will be illustrated after clicking"+"\n"+
		"one specific button. According to fitness value in this chart, the power of "+"\n"+
		"these three algorithms can be identified explicitly."+ "\n"
		+"\n"+"The generation of the line is dependent on the real data.The fitness value"+"\n"+
		"for each genration in this graph is produced via calculating average value"+"\n"+"from fifty offsprings.Addtionally Therefore, these generated data and line is "+"\n"+
		"reliable and accurate."+"\n"+"\n"+"If you have any question,please contact: yge5@sheffield.ac.uk"
				);
		t.setEditable(false);
	}
	
	public void addComponent(){	
		buttonPanel.add(generationLable);
		buttonPanel.add(generationText);
		buttonPanel.add(nullgenerationLabel);
		buttonPanel.add(GenerationsampleDataPanel);
		buttonPanel.add(generationSampleDataText);
		buttonPanel.add(GenerationNullfitnessLabel);
		buttonPanel.add(KnasackButton);
		buttonPanel.add(ThreeMaxSatButton);
		buttonPanel.add(PartitionButton);	
			
		buttonPanelEvaluationFitness.add(lambdaValueLable);
		buttonPanelEvaluationFitness.add(lambdaText);
		buttonPanelEvaluationFitness.add(submitLambdaValueButton);
		buttonPanelEvaluationFitness.add(fitEvaluationLable);
		buttonPanelEvaluationFitness.add(fitnessEvalu);
		buttonPanelEvaluationFitness.add(clearComboBox);
		buttonPanelEvaluationFitness.add(sampleDataPanel);
		buttonPanelEvaluationFitness.add(sampleDataText);
		buttonPanelEvaluationFitness.add(nullfitnessLabel);
		buttonPanelEvaluationFitness.add(KnapButtonFitEvalu);
		buttonPanelEvaluationFitness.add(ThreeMaxSatFitEvalu);
		buttonPanelEvaluationFitness.add(PartionButtFitEvalu);
	}
	
	public void addItemToCombo(){
		for(int i = 1;i<=500;i++){
			fitnessEvalu.addItem(i*offspringScaleFitnessEvaluationAgainstFitness);
		}
	}
	
	public void addActionListener(){
		KnasackButton.addActionListener(this);
		ThreeMaxSatButton.addActionListener(this);
		PartitionButton.addActionListener(this);
		
		KnapButtonFitEvalu.addActionListener(this);
		ThreeMaxSatFitEvalu.addActionListener(this);
		PartionButtFitEvalu.addActionListener(this);
		
		lambdaText.addActionListener(this);
		submitLambdaValueButton.addActionListener(this);
		
		generationText.addActionListener(this);
		fitnessEvalu.addActionListener(this);
		clearComboBox.addActionListener(this);

	}
	public static void run(final JFrame f) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				f.setTitle("Combinatorial Problems");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setBounds(330, 130, 500, 300);
				f.setVisible(true);
			} 
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();		
		if(source == submitLambdaValueButton ){
			submitLambdaValue();
		}
		if(source == clearComboBox){
			fitnessEvalu.removeAllItems();
			lambdaText.setText("");
		}
		if(source == PartitionButton){
			textMethod(new PartitionProblem(),"Partition Problem");
		}
		if(source == ThreeMaxSatButton){
			textMethod(new ThreeMaxSatProblem(),"MAX-3SAT Problem");
		}
		if(source == KnasackButton){
			 textMethod(new KnapsackProblem(),"Knapsack Problem");			
		}
		if(source==PartionButtFitEvalu){
			if(fitnessEvalu.getSelectedItem()!=null){
				inputFitnessEvaluation = (int) fitnessEvalu.getSelectedItem();
				if(sampleDataText.getText().trim().isEmpty()){
					sampleData = 0;
				}else{
				if(sampleDataText.getText().matches("^([1-4][0-9]{0,2})$|500|5|6|7|8|9|50|51|52|53|54|55|56|57|58|59|60|61"
						+ "|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|"
						+ "95|96|97|98|99")&&Integer.parseInt(sampleDataText.getText())<=inputFitnessEvaluation/offspringScaleFitnessEvaluationAgainstFitness){
						sampleData = Integer.parseInt(sampleDataText.getText());
					}
					else{
						JOptionPane.showMessageDialog(null, "The value of sample data should be less than "+inputFitnessEvaluation/offspringScaleFitnessEvaluationAgainstFitness);	
						sampleDataText.setText("");
						return;
					}
				}
				callAlgrithmsFitnessEvaluation(new PartitionProblem());
				JFrame fm = new GrapgicFrame(new FitnessEvaluationGraphic(offspringScaleFitnessEvaluationAgainstFitness,inputFitnessEvaluation,standardKnapsackFitnessEvaluation,lambdaKnapsakFitnessEvaluation,oneKnapsackFitnessEvaluation,new PartitionProblem(),"Partition Problem",OneLambdaScale));
			}else{
				JOptionPane.showMessageDialog(null, "Please Input a figure between 1 and 500");
			}			
		}
		if(source==ThreeMaxSatFitEvalu){
			if(fitnessEvalu.getSelectedItem()!=null){
			inputFitnessEvaluation = (int) fitnessEvalu.getSelectedItem();	
			if(sampleDataText.getText().trim().isEmpty()){
				sampleData = 0;
			}else{
			if(sampleDataText.getText().matches("^([1-4][0-9]{0,2})$|500|5|6|7|8|9|50|51|52|53|54|55|56|57|58|59|60|61"
					+ "|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|"
					+ "95|96|97|98|99")&&Integer.parseInt(sampleDataText.getText())<=inputFitnessEvaluation/offspringScaleFitnessEvaluationAgainstFitness){
					sampleData = Integer.parseInt(sampleDataText.getText());
				}
				else{
					JOptionPane.showMessageDialog(null, "The value of sample data should be less than "+inputFitnessEvaluation/offspringScaleFitnessEvaluationAgainstFitness);	
					sampleDataText.setText("");
					return;
				}
			}
			callAlgrithmsFitnessEvaluation(new ThreeMaxSatProblem());
			JFrame fm = new GrapgicFrame(new FitnessEvaluationGraphic(offspringScaleFitnessEvaluationAgainstFitness,inputFitnessEvaluation,standardKnapsackFitnessEvaluation,lambdaKnapsakFitnessEvaluation,oneKnapsackFitnessEvaluation,new ThreeMaxSatProblem(),"MAX-3SAT Problem",OneLambdaScale));
			}else{
				JOptionPane.showMessageDialog(null, "Please Input a figure between 1 and 500");
			}
		}
		if(source==KnapButtonFitEvalu){
			if(fitnessEvalu.getSelectedItem()!=null){
			inputFitnessEvaluation = (int) fitnessEvalu.getSelectedItem();
			if(sampleDataText.getText().trim().isEmpty()){
				sampleData = 0;
			}else{
			if(sampleDataText.getText().matches("^([1-4][0-9]{0,2})$|500|5|6|7|8|9|50|51|52|53|54|55|56|57|58|59|60|61"
					+ "|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|"
					+ "95|96|97|98|99")&&Integer.parseInt(sampleDataText.getText())<=inputFitnessEvaluation/offspringScaleFitnessEvaluationAgainstFitness){
					sampleData = Integer.parseInt(sampleDataText.getText());
				}
				else{
					JOptionPane.showMessageDialog(null, "The value of sample data should be less than "+inputFitnessEvaluation/offspringScaleFitnessEvaluationAgainstFitness);	
					sampleDataText.setText("");
					return;
				}
			}
			callAlgrithmsFitnessEvaluation(new KnapsackProblem());
			JFrame fm = new GrapgicFrame(new FitnessEvaluationGraphic(offspringScaleFitnessEvaluationAgainstFitness,inputFitnessEvaluation,standardKnapsackFitnessEvaluation,lambdaKnapsakFitnessEvaluation,oneKnapsackFitnessEvaluation,new KnapsackProblem(),"Knapsack Problem",OneLambdaScale));
			}else{
				JOptionPane.showMessageDialog(null, "Please Input a figure between 1 and 500");
			}
		}
	}

	public void submitLambdaValue(){
		if(lambdaText.getText().matches("^([1-9][0-9]{0,1}|100)$")){	
			OneLambdaScale = Integer.parseInt(lambdaText.getText());
			this.offspringScaleFitnessEvaluationAgainstFitness = OneLambdaScale * 2;
			addItemToCombo();
		}else{
			JOptionPane.showMessageDialog(null, "Please Input a lambda value from 1 to 100");
			lambdaText.setText("");
		}
		
	}
	
	public void textMethod(FitnessFunctions fitnessFunctions,String problemName){
		if(generationText.getText().matches("^([1-4][0-9]{0,2})$|500|5|6|7|8|9|50|51|52|53|54|55|56|57|58|59|60|61"
				+ "|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|"
				+ "95|96|97|98|99")){
			inputGeneration = Integer.parseInt(generationText.getText());
			if(generationSampleDataText.getText().trim().isEmpty()){
				generationSampleData = 0;
			}else{
				if(generationSampleDataText.getText().matches("^([1-4][0-9]{0,2})$|500|5|6|7|8|9|50|51|52|53|54|55|56|57|58|59|60|61"
					+ "|62|63|64|65|66|67|68|69|70|71|72|73|74|75|76|77|78|79|80|81|82|83|84|85|86|87|88|89|90|91|92|93|94|"
					+ "95|96|97|98|99")&&Integer.parseInt(generationSampleDataText.getText())<=inputGeneration){
					generationSampleData = Integer.parseInt(generationSampleDataText.getText());
				}else{
					JOptionPane.showMessageDialog(null, "The value of sample data should be less than or equal with "+inputGeneration);	
					generationSampleDataText.setText("");
					return;
				}
			}
			callAlgrithms(fitnessFunctions);
			JFrame fm = new GrapgicFrame(new GenerationProblemGraphic(inputGeneration,standardKnapsack,lambdaKnapsak,oneKnapsack,fitnessFunctions,problemName));
		}else{		
			JOptionPane.showMessageDialog(null, "Please Input a figure between 1 and 500");	
			generationText.setText("");
		}
	}
	
	public void callAlgrithms(FitnessFunctions fitnessFunctions) {
		standardKnapsack = algorithms.callStandarGA(inputGeneration,fitnessFunctions,offspringScaleGeneratonAgainstFitness,generationSampleData);	
		lambdaKnapsak = algorithms.callLamdaGA(inputGeneration,fitnessFunctions,offspringScaleGeneratonAgainstFitness,generationSampleData);
		oneKnapsack = algorithms.callOneEA(inputGeneration,fitnessFunctions,generationSampleData);
		System.out.println("Best values of Standard GA: " + Arrays.toString(standardKnapsack));
		System.out.println("(1+λ) λ :" + Arrays.toString(lambdaKnapsak));
		System.out.println("(1+1)EA: " + Arrays.toString(oneKnapsack));	
	}
	
	public void callAlgrithmsFitnessEvaluation(FitnessFunctions fitnessFunctions) {		
		standardKnapsackFitnessEvaluation = algorithms.callStandarGA(inputFitnessEvaluation/offspringScaleFitnessEvaluationAgainstFitness,fitnessFunctions,offspringScaleFitnessEvaluationAgainstFitness,sampleData);
		lambdaKnapsakFitnessEvaluation = algorithms.callLamdaGA(inputFitnessEvaluation/offspringScaleFitnessEvaluationAgainstFitness,fitnessFunctions,OneLambdaScale,sampleData);
		oneKnapsackFitnessEvaluation = algorithms.callOneEAFitnessEvaluation(inputFitnessEvaluation/offspringScaleFitnessEvaluationAgainstFitness,fitnessFunctions,offspringScaleFitnessEvaluationAgainstFitness,sampleData);
		System.out.println();
		System.out.println("Lambda = "+ OneLambdaScale);
		System.out.println("Best values of Standard GA" + Arrays.toString(standardKnapsackFitnessEvaluation));
		System.out.println("(1+λ) λ :" + Arrays.toString(lambdaKnapsakFitnessEvaluation));
		System.out.println("(1+λ)EA" + Arrays.toString(oneKnapsackFitnessEvaluation));
	}
	
}
