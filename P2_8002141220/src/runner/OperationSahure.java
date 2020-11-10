package runner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import tools.ArrayIndexList;
import tools.Target;
import tree.LinkedTree;
/**
 * 
 * @author Alejandra Casanova
 *
 * @param <E>
 */
public class OperationSahure<E> {

	private static Scanner in;
	private static int totalCriminals;
	private  static ArrayIndexList<Target> listOfCriminals;
	private static LinkedTree<Target> scam;
	private static ArrayIndexList<Integer> maxArrests;
	/**
	 * 
	 */
	public OperationSahure() {
	}
	/**
	 * 
	 * @param args Main method to read the input files
	 * @throws FileNotFoundException if the file is not found it throws this exception
	 */
	public static void main(String[] args) throws FileNotFoundException {

		File fileText = new File(System.getProperty("user.dir")+ "/input.txt");
		ArrayIndexList<File> theFiles = new ArrayIndexList<File>();
		maxArrests = new ArrayIndexList<Integer>();

		try {
			in = new Scanner(fileText);
			while(in.hasNextLine()) {

				String[] params=in.nextLine().trim().split(" ");
				int integerCurrMax = Integer.parseInt(params[0]);
				maxArrests.add(integerCurrMax);
				String filename = params[1]; 

				File newFile = new File(filename);
				theFiles.add(newFile);

			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();{

			}
		}	

		for(int m = 0; m<= theFiles.size()-1;m++) {
			if(theFiles.get(m).length()==0) {
				try {
					String outputName = "output"+(m+1)+".txt";
					PrintWriter outputStream = new PrintWriter(outputName);
					outputStream.write("Maximum seized assets: "+0	
							+System.getProperty("line.separator"));
					outputStream.close();
				}
				catch(FileNotFoundException e) {
					e.printStackTrace();
				}
			}else {
				readFile(theFiles.get(m));
				scam.mentorAssigment();
				scam.setMaxArrest(maxArrests.get(m));
				scam.pyramidTraversal();
				int numberOfList;
				try {
					String outputName = "output"+(m+1)+".txt";
					PrintWriter outputStream = new PrintWriter(outputName);

					outputStream.write("Maximum seized assets: "+ scam.getMaxAsset()	
					+System.getProperty("line.separator"));
					for(int l = 0; l<=scam.getFinalRoutes().size()-1;l++) {
						numberOfList = l+1;
						outputStream.write("List: "+ numberOfList+":");
						for(int j = 0; j<=scam.getFinalRoutes().get(l).size()-1;j++) {
							outputStream.write(" \n"+scam.getFinalRoutes().get(l).get(j).getUserName()+", ");
						}
						outputStream.write(System.getProperty("line.separator"));
					}
					outputStream.close();
				}
				catch(FileNotFoundException e) {
					e.printStackTrace();
				}
			}	
		}

	}
	/**
	 * 
	 * @param file to be read, separates the data into target objects
	 */

	public static void readFile(File file)  {
		if(file.length()==0) {}
		else {
			try {



				Scanner in = new Scanner(file);
				listOfCriminals = new ArrayIndexList<>();
				scam = new LinkedTree<>();

				String dataRoot = in.nextLine();
				String[] separatedDataRoot = dataRoot.split("#");

				Target rootCriminal = new Target(separatedDataRoot[0].toString(),Integer.parseInt(separatedDataRoot[1]),null);

				scam.addRoot(rootCriminal);
				listOfCriminals.add(rootCriminal);

				while(in.hasNext()) {
					String data = in.nextLine();
					String[] separatedData = data.split("#");
					Target spns=scam.preOrder(rootCriminal,separatedData[2].toString());

					Target currentCriminal = new Target(separatedData[0].toString(),Integer.parseInt(separatedData[1]), spns);
					scam.addChild(spns, currentCriminal);
					listOfCriminals.add(currentCriminal);
					totalCriminals++;
				}

				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @return the list containing all the targets on that file
	 */
	public static ArrayIndexList<Target> getTheCriminals() {
		return listOfCriminals;
	}
	/**
	 * 
	 * @return maximum number of arrest in a determined route
	 */
	public static ArrayIndexList<Integer> getMaxArrests(){
		return maxArrests;
	}
}

