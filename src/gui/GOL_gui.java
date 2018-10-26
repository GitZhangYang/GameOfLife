package gui;

import javax.swing.*;
import java.awt.*;

import logic.GameModel;     //�����߼�������

public class GOL_gui extends JFrame{
	
	//��Ա����
	private JTextField[][] textMatrix;           
	private GameModel gmodel;
	
	//���湹��
	private JPanel gridPanel = new JPanel();     //������壬��ʾϸ������
	
	// ���캯��
	public GOL_gui(){
		this.setTitle("��ӭ����������Ϸ");       //�������
		this.setSize(1000,1000);               //���ڴ�С
		this.setVisible(true);                 //����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //�رմ���ʱ�˳�����
		this.add(gridPanel);                   //�������ӵ�����
		
		gmodel = new GameModel(30,30,300,20);   //�½�����
		InitGrid();                            //��ʼ������
	
	}
	// ��ʼ����������
	private void InitGrid(){
		int rows = gmodel.getGameWidth();                 //����
		int cols = gmodel.getGameHeight();                //����
		int[][] Matrix = gmodel.getMatrix();              //��ȡ״̬����
		gridPanel.setLayout(new GridLayout(rows,cols));
		textMatrix = new JTextField[rows][cols]; 
		for(int i = 0;i < rows;i++)                       //��������
			for(int j = 0;j < cols;j++)
			{
				JTextField text = new JTextField();
				//ϸ��Ϊ��ʱ��ʾΪ��ɫ
				if(Matrix[i][j]==1)           
				{
					text.setBackground(Color.black);
					textMatrix[i][j] = text;
					gridPanel.add(text);
				}
				//ϸ��Ϊ��ʱ��ʾΪ��ɫ
				else
				{
					text.setBackground(Color.white);
					textMatrix[i][j] = text;
					gridPanel.add(text);
				}
			}

		add("Center", gridPanel);
		gridPanel.updateUI();              //ˢ�½���
		Generate();                        //ϸ������
	}
	//��ʾÿһ��ϸ��״̬
	public void showMatrix()
	{
		gmodel.GenerateMatrix();                 //������һ��
		int rows = gmodel.getGameWidth();
		int cols = gmodel.getGameHeight();
		int[][] Matrix = gmodel.getMatrix();      //��ȡ��һ��ϸ��
		for(int i = 0;i < rows;i++)
			for(int j = 0;j < cols;j++)
			{
				if(Matrix[i][j]==1)
				{
					textMatrix[i][j].setBackground(Color.black);
				}
				else
				{
					textMatrix[i][j].setBackground(Color.white);
				}
			}
	}
	//��������ÿһ��ϸ��
	public void Generate(){
		for(int i = 0;i<gmodel.getIterations();i++)
		{
			showMatrix();
			//��ʱչʾ
			try{
				Thread.sleep(gmodel.getTimeGap());
			}catch(Exception e)
			{
				
			}
		}
	}
}
