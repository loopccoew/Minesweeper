package minsweeper;
import java.io.*;
import java.util.*;
public class minesweeper 
{
	Random r=new Random();//Used to decide number of mines and their locations
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	int size;
    int board[][]; 
    int score;
    String name;
    int start[];
    int end[];
    int time[];
    minesweeper()//constructor
    {
        Calendar c= Calendar.getInstance();
    	score=0;
    	name="";
    	size=0;
    	start=new int [3];
    	end=new int [3];
    	time=new int [3];
    	for(int i=0;i<3;i++)end[i]=-1;
    	start[0]=c.get(Calendar.HOUR_OF_DAY);
    	start[1]=c.get(Calendar.MINUTE);
    	start[2]=c.get(Calendar.SECOND);
    }
    void acceptStat()throws IOException
    {
    	System.out.println("Enter your name");
    	name=br.readLine();
    }
    void makeBoard()//Uses random class to create board
    {
    	while(size<10)//generates a number between 9 and 32 this number is the number of mines on the board
    		size=r.nextInt(32);
    	board=new int[2][size];
    	for(int i=0;i<size;i++)//Decides locations of these mines
    	{   
    		 board[0][i]=r.nextInt(8);
		     board[1][i]=r.nextInt(8);
		     for(int j=0;j<i;j++)//this loop ensures locations are not repeated
		     {
		    	while((board[0][i]==board[0][j])&&(board[1][i]==board[1][j]))
		    	{
		    		board[0][i]=r.nextInt(8);
				    board[1][i]=r.nextInt(8);
		    	}
		     }

    	}
    }
    void displayBoard()//used in displaystat prints board
    {
    	System.out.println("'!' Represent Mines");
    	int c=0;
    	for(int i=0;i<8;i++) 
    	{
    		System.out.println();
    		for(int j=0;j<8;j++)
    		{
    			c=0;
    			int flag=0;
    			while(c<size)
    			{
    				if((board[0][c]==i)&&(board[1][c]==j))
    					flag=1;
    				c++;
        		
    			}
    			if(flag==1)
    			{
    				System.out.print(" ! ");
    				
    			}
    			else
    				System.out.print(" * ");
    		}
    		System.out.println();
    	}
    }
    void userEntry()throws IOException
    {
    	int row[]=new int[64];
    	int col[]=new int[64];
    	for(int i=0;i<64;i++)
    	{   int flag=0;
    		do
    		{	
    		if(flag==1)
    			System.out.println("Repeated entries are prohibted");
    		flag=0;
    		int range=0;
    		do
    		{
    			range=0;
        	System.out.println("Enter row coordinate(between 0 to 7 both inclusive)");
        	row[i]=Integer.parseInt(br.readLine());
        	System.out.println("Enter column coordinate(between 0 to 7 both inclusive)");
        	col[i]=Integer.parseInt(br.readLine());
        	if((row[i]>=8)||(row[i]<0)||(col[i]>=8)||(col[i]<0))//out of range condition
        	{
        		range=1;
        		System.out.println("Invalid entry");
        	}
    		}while(range==1);//checks whether user input is within range 
           	for(int k=0;k<i;k++)
        		if((row[k]==row[i])&&(col[k]==col[i]))//checks for repeated entries 
        				flag=1;
    	    }while(flag==1);
        	boolean prez=checkPrescence(row[i],col[i]);
        	if(prez==true)
        	{
            	Calendar c1= Calendar.getInstance();//holds the time at which user enters location of a mine
        		end[0]=c1.get(Calendar.HOUR_OF_DAY);
        		end[1]=c1.get(Calendar.MINUTE);
        		end[2]=c1.get(Calendar.SECOND);
        		System.out.println("GAMEOVER");
        		break;
        	}
        	else
                score=score+10;
        }
    	if(end[0]==-1)//If player has completed game without choosing a mine
    	{
        	Calendar c1= Calendar.getInstance();
    		end[0]=c1.get(Calendar.HOUR_OF_DAY);
    		end[1]=c1.get(Calendar.MINUTE);
    		end[2]=c1.get(Calendar.SECOND);
    		score=1000;//Optional if player beats the game
     	}
        displayStat();
    }
    boolean checkPrescence(int row,int col)//Checks if user has chosen a mine
    {
    	boolean flag=false;
    	for(int c=0;c<size;c++)
    	{
    		if((board[0][c]==row)&&(board[1][c]==col))
    			flag=true;
    	}
    	return flag;
    }
    
    void displayStat()//Displays user details
    {
    	System.out.println("NAME="+name);
    	System.out.println("SCORE="+score);
    	caltime();
    	System.out.print("START TIME:");
    	for(int i=0;i<3;i++)
    	{   
    		if(i!=2)
    		System.out.print(start[i]+":");
    		else
    		{
    	    	System.out.print(start[i]);
    		    System.out.println();
    		}
    		
    	}
    	System.out.print("END TIME:");

    	for(int i=0;i<3;i++)
    	{   
    		if(i!=2)
    		System.out.print(end[i]+":");
    		else
    		{
    	    	System.out.print(end[i]);
    		    System.out.println();
    		}
    		
    	}
    	System.out.print("TIME DURATION:");
    	for(int i=0;i<3;i++)
    	{   
    		if(i!=2)
    		System.out.print(time[i]+":");
    		else
    		{
    	    	System.out.print(time[i]);
    		    System.out.println();
    		}
    	}
    		System.out.println("YOUR GAME BOARD");
    		displayBoard();		
}
    void caltime()//Calculates time duration of play
    {
    	long ssec=(start[0]*3600)+(start[1]*60)+start[2];
    	long esec=(end[0]*3600)+(end[1]*60)+end[2];
    	int dif=(int)(esec-ssec);
    	time[0]=dif/3600;
    	time[1]=(dif%3600)/60;
    	time[2]=dif-((time[0]*3600)+(time[1]*60));
    }
    void leaderboard(minesweeper obj [])
    {
    	int len=obj.length;
    	for(int i=0;i<len;i++)//Sorting players by scores in descending order using selection sort
    	{
    		int max=i;
    		for(int j=(i+1);j<len;j++)
    		{
    			if(obj[j].score>obj[max].score)
    				max=j;
      		}
    		swap(obj[i],obj[max]);
    	} 
    	int highscore=obj[0].score;
    	System.out.println("Players according to score");
    	System.out.println("Highscore is:"+highscore);
    	for(int i=0;i<len;i++)
    	{
    		System.out.println("___________________________________________________________________________________________________________________________________________________________________");
    		System.out.println("Player "+(i+1));
    		obj[i].displayStat();
    		System.out.println("___________________________________________________________________________________________________________________________________________________________________");

    	}
    	

    }
    void swap(minesweeper a,minesweeper b)
    {
    	minesweeper temp=new minesweeper();
    	temp.board=a.board;
    	temp.end=a.end;
    	temp.start=a.start;
    	temp.name=a.name;
    	temp.score=a.score;
    	temp.size=a.size;
    	temp.time=a.time;
    	
    	a.board=b.board;
    	a.end=b.end;
    	a.start=b.start;
    	a.name=b.name;
    	a.score=b.score;
    	a.size=b.size;
    	a.time=b.time;
    	
    	b.board=temp.board;
    	b.end=temp.end;
    	b.start=temp.start;
    	b.name=temp.name;
    	b.score=temp.score;
    	b.size=temp.size;
    	b.time=temp.time;
    	
    	
    }
public static void main(String args[])throws IOException
{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	minesweeper obj=new minesweeper();
	System.out.println("How many users are going to be playing?");
	int n=Integer.parseInt(br.readLine());
	minesweeper user[]=new minesweeper[n];
	for(int i=0;i<n;i++)
	{
		user[i]=new minesweeper();
		user[i].makeBoard();
    	user[i].acceptStat();
    	user[i].userEntry();
	}
	obj.leaderboard(user);
	
	}
}
