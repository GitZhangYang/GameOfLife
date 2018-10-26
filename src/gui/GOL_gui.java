package gui;

import javax.swing.*;
import java.awt.*;

import logic.GameModel;     //导入逻辑处理类

public class GOL_gui extends JFrame{
	
	//成员变量
	private JTextField[][] textMatrix;           
	private GameModel gmodel;
	
	//界面构件
	private JPanel gridPanel = new JPanel();     //网格面板，显示细胞更迭
	
	// 构造函数
	public GOL_gui(){
		this.setTitle("欢迎来到生命游戏");       //界面标题
		this.setSize(1000,1000);               //窗口大小
		this.setVisible(true);                 //可视
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //关闭窗口时退出程序
		this.add(gridPanel);                   //将面板添加到窗口
		
		gmodel = new GameModel(30,30,300,20);   //新建对象
		InitGrid();                            //初始化网格
	
	}
	// 初始化网格区域
	private void InitGrid(){
		int rows = gmodel.getGameWidth();                 //行数
		int cols = gmodel.getGameHeight();                //列数
		int[][] Matrix = gmodel.getMatrix();              //获取状态矩阵
		gridPanel.setLayout(new GridLayout(rows,cols));
		textMatrix = new JTextField[rows][cols]; 
		for(int i = 0;i < rows;i++)                       //遍历矩阵
			for(int j = 0;j < cols;j++)
			{
				JTextField text = new JTextField();
				//细胞为生时表示为黑色
				if(Matrix[i][j]==1)           
				{
					text.setBackground(Color.black);
					textMatrix[i][j] = text;
					gridPanel.add(text);
				}
				//细胞为死时表示为白色
				else
				{
					text.setBackground(Color.white);
					textMatrix[i][j] = text;
					gridPanel.add(text);
				}
			}

		add("Center", gridPanel);
		gridPanel.updateUI();              //刷新界面
		Generate();                        //细胞繁衍
	}
	//显示每一代细胞状态
	public void showMatrix()
	{
		gmodel.GenerateMatrix();                 //产生下一代
		int rows = gmodel.getGameWidth();
		int cols = gmodel.getGameHeight();
		int[][] Matrix = gmodel.getMatrix();      //获取下一代细胞
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
	//迭代产生每一代细胞
	public void Generate(){
		for(int i = 0;i<gmodel.getIterations();i++)
		{
			showMatrix();
			//延时展示
			try{
				Thread.sleep(gmodel.getTimeGap());
			}catch(Exception e)
			{
				
			}
		}
	}
}
