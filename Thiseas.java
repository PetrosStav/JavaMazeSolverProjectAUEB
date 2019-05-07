import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * This class makes use of the StringStackImpl class to find the way out of a maze.
 * @author Stavropoulos Petros(3150230), Savvidis Konstantinos(3150229) 
 */
public class Thiseas {
	
	public static void main(String[] args) {
		
		//if the file given as an argument is empty
		if(args.length<1){
			System.err.println("Not enough arguments!\nUsage: java Thiseas path_to_file/filename.txt");
			return;
		}
		
		//if the file given as an argument is not a text file
		if(!args[0].endsWith(".txt")){
			System.err.println("You must enter a txt file!");
			return;
		}
		
		File f = new File(args[0]);	//pass the file to the program using command-line argument
		
		//if there is no file with the name given in the command-line
		if(!f.exists()){
			System.err.println("File: " + f.getAbsolutePath() + " doesn't exist!");
			return;
		}
		
		//Initialize the reader for the file
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(f));
			int n;	//number of rows in the maze
			int m;	//number of columns in the maze
			int Erow;	//the row coordinate of the entrance
			int Ecol;	//the columns coordinate of the entrance

			try{	//read the first couple of lines in the text file
				String[] head1 = reader.readLine().split("\\s+");	//read the first line, split it into parts according to the spaces between them, using regular expression which means one or more whitespace characters (spaces/tabs/etc) and assign them to a string array
				n = Integer.parseInt(head1[0]);	//the first line in the text file contains a pair of digits
				m = Integer.parseInt(head1[1]);	//representing the number of rows and columns in the maze				
				
				String[] head2 = reader.readLine().split("\\s+");	//read the first line, split it into parts according to the spaces between them, using regular expression which means one or more whitespace characters (spaces/tabs/etc) and assign them to a string array
				Erow = Integer.parseInt(head2[0]);	//the second line in the text file contains a pair of digits
				Ecol = Integer.parseInt(head2[1]);	//representing the coordinates of the entrance in the maze
			}catch(NumberFormatException e){
				System.err.println("Your input is invalid!\nExiting...");
				return;
			}
			
			String line;	//a temporary buffer for reading each line
			int linec=0;	//a line counter
			char[][] maze = new char[n][m];	//array of type char to store the maze
			boolean foundEntrance = false;
			while((line=reader.readLine())!=null){	//loop until end of text file
				
				//checking validity of maze input
				if(linec>=n){	//if the number of lines counted is more than the number declared at the beginning
					System.err.println("Input is invalid!\nThe number of rows in the maze input is not the same as the number given!");
					return;
				}
				String[] line_parts = line.split(" ");	//split the line into string parts and assign them to a string array
				if(line_parts.length!=m){	//if the array size is not equal to the declared number of columns
					System.err.println("Input is invalid!\nThe number of columns in the maze input is not the same as the number given!");
					return;
				}				
				
				for(int i=0;i<line_parts.length;i++){	//for each string part in the array
					if(!line_parts[i].equals("0") && !line_parts[i].equals("1") && !line_parts[i].equals("E")){	//if they are not all equal to 0, 1 or E
						System.err.println("Input is invalid!\nThere are values in the maze input other than [0,1,E] !");
						return;
					}
					if(line_parts[i].equals("E")){	//if a string part is equal to E
						if(!foundEntrance){	//if the entrance has not been found again before
							foundEntrance = true;	//declare that the entrance is found
						}else{	//if this is the second time we find the entrance
							System.err.println("Input is invalid!\nMaze input has more than one entrances!");
							return;
						}
					}
					maze[linec][i] = line_parts[i].charAt(0);	//fill the char array with the maze
				}
				linec++;	//increase the line counter
			}
			
			if(maze[Erow][Ecol] != 'E'){	//if the character at the entrance coordinates is not equal to E
				System.err.println("Input is invalid!\nThe point of entrance is not the same in the maze input as it is in the coordinates given!");
				return;
			}
			
			//last validity check for less lines given
			if(linec!=n){	//if the number of lines counted is not equal to those declared at the beginning
				System.err.println("Input is invalid!\nThe number of rows in the maze input is not the same as the number given!");
				return;
			}

			//maze is valid -- continue			
			StringStackImpl stack = new StringStackImpl();	//declare and initialize a stack to store the current path
			StringStackImpl solutionPaths = new StringStackImpl();	//declare and initialize a stack to store the paths leading to an exit
			stack.push("{ " + Erow + "," + Ecol + " }"); //the starting point is the entrance
			int r = Erow; //current row is the entry row
			int c = Ecol; //current column is the entry column

			do{
//				FOR DEBUGGING - SHOWS MAZE AND PATH ANIMATION
//				
//				for(int i=0;i<n;i++){
//					for(int j=0;j<m;j++){
//						if(i==r && j==c){
//							System.out.print("C ");
//						}else{
//							System.out.print(maze[i][j] + " ");
//						}
//					}
//					System.out.println();
//				}
//				try {
//					Thread.sleep(300);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println();
//				
				//check for possible directions to follow
				if(c+1<m && maze[r][c+1] == '0'){				//check EAST
					stack.push("{ " + r + "," + (c+1) + " }");
				}else if(r+1 < n && maze[r+1][c] == '0'){		//check SOUTH
					stack.push("{ " + (r+1) + "," + c + " }");
				}else if(c-1>=0 && maze[r][c-1] == '0'){		//check WEST
					stack.push("{ " + r + "," + (c-1) + " }");
				}else if(r-1>=0 && maze[r-1][c] == '0'){		//check NORTH
					stack.push("{ " + (r-1) + "," + c + " }");
				}else{ 											//if there is no path available
					if(stack.size()>1){	//if the stack for the current path contains maze points apart from the entry E
						stack.pop();	//backtrack to the previous maze point
					}else{
						//if size==1 then we are on the entrance again
						//but we can't go to any direction so we have no more solutions
						break;
					}
				}
				
				//update a maze point as "visited"
				String current = stack.peek();	//get the maze point at top of the stack
				current = current.substring(2,current.length()-2);	//returns the substring that contains 2 digits and a comma between them, e.g. { 6,3 } --> 6,3
				String[] parts = current.split(",");	//store the digits-coordinates in an array of strings
				r = Integer.parseInt(parts[0]);	//update row
				c = Integer.parseInt(parts[1]);	//update column
				maze[r][c] = 'V'; //V for Visited
				
				//check if we have found a path that exits the maze
				if(r==n-1 || r==0 || c==0 || c==m-1){	// if we have reached the maze borers
					if(r!=Erow || c!=Ecol){	//if the exit found is not the entry point
						solutionPaths.push(stack.peek()); //we found a successful path, so push it to the stack of solutions
						stack.printStack(System.out); //print the path of the solution
						
//						FOR DEBUGGING - SHOWS MAZE AND PATH ANIMATION
//						
//						for(int i=0;i<n;i++){
//							for(int j=0;j<m;j++){
//								if(i==r && j==c){
//									System.out.print("C ");
//								}else{
//									System.out.print(maze[i][j] + " ");
//								}
//							}
//							System.out.println();
//						}
//						try {
//							Thread.sleep(300);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//						System.out.println();
//						
						//repeat the process to  seek for more solutions
						stack.pop();	//remove a maze point from our current path
						current = stack.peek();	//get maze point at top of the stack
						current = current.substring(2,current.length()-2);	//returns the substring that contains 2 digits and a comma between them, e.g. { 6,3 } --> 6,3
						parts = current.split(",");	//store the digits-coordinates in an array of strings
						r = Integer.parseInt(parts[0]); //update row
						c = Integer.parseInt(parts[1]); //update column
						maze[r][c] = 'V';	//V for Visited
					}
				}
			}while(true);

			//print the results to the console
			if(solutionPaths.size()==0){	//if we have not found any paths leading to an exit
				System.out.println("The Maze has no solution!");
				return;
			}else{							//print the
				System.out.println();
				System.out.println("Solutions: " + solutionPaths.size());	//print the number of exits
				System.out.println();
				solutionPaths.printStack(System.out);	//print the coordinates of each exit
			}	
		}catch(IOException e){
			e.printStackTrace();
			return;
		}finally{
			if(reader!=null){
				try{	//closing readers
					reader.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
}