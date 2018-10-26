package logic;

import javax.sound.midi.Soundbank;
import java.sql.Time;

public class GameModel {

    //游戏界面长
    int GameHeight;

    //游戏界面宽
    int GameWidth;

    //游戏界面
    int [][]matrix;

    //游戏更新时间 单位：ms
    int timeGap;

    //游戏迭代次数
    int iterations;


    //构造函数
    public GameModel(int GameHeight,int GameWidth,int timeGap,int iterations){
        this.GameHeight=GameHeight;
        this.GameWidth=GameWidth;
        this.timeGap=timeGap;
        this.iterations=iterations;
        this.matrix=new int[GameWidth][GameHeight];

        //初始化矩阵
        this.InitMatrix();
    }


    /*生成下一代矩阵
        结果保存在matrix中
    */
    public void GenerateMatrix(){
        int [][]StatusMatrix=new int[GameWidth][GameHeight];
        for(int i=0;i<GameWidth;i++){
            for(int j=0;j<GameHeight;j++){
                //先初始化为0
                StatusMatrix[i][j]=0;

                //判断原矩阵中是否存活
                if(this.If_Survive(j,i)){
                    StatusMatrix[i][j]=1;
                }
            }
        }
        this.matrix=StatusMatrix;
    }

    /*判断细胞生死
        True:生
        False:死
        x:细胞横坐标
        y:细胞纵坐标
    */
    public boolean If_Survive(int x,int y){
        int AroundNum=0;

        if(x-1>=0) AroundNum+=matrix[y][x-1];
        if(y-1>=0) AroundNum+=matrix[y-1][x];

        if(x+1<GameHeight) AroundNum+=matrix[y][x+1];
        if(y+1<GameWidth)  AroundNum+=matrix[y+1][x];

        if(x+1<GameHeight && y+1<GameWidth)  AroundNum+=matrix[y+1][x+1];
        if(x+1<GameHeight && y-1>=0)          AroundNum+=matrix[y-1][x+1];
        if(y-1>=0 && x-1>=0)                   AroundNum+=matrix[y-1][x-1];
        if(x-1>=0 && y+1<GameWidth)           AroundNum+=matrix[y+1][x-1];


        boolean ret=(matrix[y][x]==1)?true:false;

        if(AroundNum==3) return true;
        if(AroundNum==2) return ret;

        return false;
    }

    //随机数初始化矩阵
    public void InitMatrix(){
        int [][]StatusMatrix=new int[GameWidth][GameHeight];

        for(int i=0;i<GameWidth;i++){
            for(int j=0;j<GameHeight;j++){
                if(Math.random()<0.5){
                    StatusMatrix[i][j]=0;
                }else{
                    StatusMatrix[i][j]=1;
                }
            }
        }
        this.matrix=StatusMatrix;

    }

    //测试打印函数
    public void PrintMatrix(){
        for(int i=0;i<GameWidth;i++){
            for(int j=0;j<GameHeight;j++){
                if(matrix[i][j]==1) System.out.print('*');
                else System.out.print('0');
            }
            System.out.print('\n');
        }
        System.out.println("--------------------------------");
    }


    public int getGameHeight() {
        return GameHeight;
    }

    public int getGameWidth() {
        return GameWidth;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getTimeGap() {
        return timeGap;
    }

    public int getIterations() {
        return iterations;
    }


    public static void main(String[] args) {
        GameModel g=new GameModel(50,50,300,50);
        for(int i=0;i<g.getIterations();i++){
            g.GenerateMatrix();
            g.PrintMatrix();
            try{
                Thread.sleep(300);
            }catch (Exception e){
                System.out.println("error");
            }
        }
    }

}
