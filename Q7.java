import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Q7 {
	static ArrayList<ArrayList<String>> attributes;
	static int noOfAttributes, noOfData, totalPosData, totalNegData;

	public static void readFile(String fileName){

		String line;
		int rowIndex=0;
		try{
			BufferedReader br =new BufferedReader(new FileReader(fileName));
			
			if((line=br.readLine())!=null){
				if(rowIndex>=attributes.size()){
					attributes.add(new ArrayList<String>());
				}
				for(String lineParts:line.split("\\s+")){
					attributes.get(rowIndex).add(lineParts);
				}
				noOfAttributes=attributes.get(rowIndex).size()-1;
				rowIndex++;
			}
			do{
			if((line=br.readLine())==null)	break;
				//System.out.println(line);
				if(rowIndex>=attributes.size()){
					attributes.add(new ArrayList<String>());
				}
				for(String lineParts:line.split("\\s+")){
					attributes.get(rowIndex).add(lineParts);
				}
				rowIndex++;
			}while(true);
			noOfData=attributes.size()-1;
			//System.out.println( noOfAttributes + " "+ noOfData);
			br.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{}

	}
	
	public static int calcCondProb(int colIndex, String attriValue, String classValue){	//index acco. attribute list
		int index;
		int dataValue=0;
			
		for(index=1;index <= noOfData;index++){

			//calculate probability
			if(attributes.get(index).get(noOfAttributes).equals(classValue) && attributes.get(index).get(colIndex).equals(attriValue)){
				dataValue++;
			}
		}
		//System.out.println(Value+ " "+ dataValue);
		return dataValue;
	}

	public static void printClassifierData(){
		totalPosData= calcCondProb(5,"Yes" ,"Yes");
		totalNegData= calcCondProb(5,"No" ,"No");
		System.out.println("P(Play Tennis=Yes)=" + totalPosData+"/" +noOfData);
		System.out.println("P(Play Tennis=No)=" + totalNegData+"/" +noOfData);
		
		//Outlook
		System.out.println("\nP(Outlook=Sunny|Yes)=" + calcCondProb(1,"Sunny" ,"Yes")+"/" +totalPosData);
		System.out.println("P(Outlook=Sunny|No)=" + calcCondProb(1,"Sunny" ,"No")+"/" +totalNegData);
		
		System.out.println("P(Outlook=Rain|Yes)=" + calcCondProb(1,"Rain" ,"Yes")+"/" +totalPosData);
		System.out.println("P(Outlook=Rain|No)=" + calcCondProb(1,"Rain" ,"No")+"/" +totalNegData);
		
		System.out.println("P(Outlook=Overcast|Yes)=" + calcCondProb(1,"Overcast" ,"Yes")+"/" +totalPosData);
		System.out.println("P(Outlook=Overcast|No)=" + calcCondProb(1,"Overcast" ,"No")+"/" +totalNegData);
		
		//Temperature
		System.out.println("\nP(Temperature=Hot|Yes)=" + calcCondProb(2,"Hot" ,"Yes")+"/" +totalPosData);
		System.out.println("P(Temperature=Hot|No)=" + calcCondProb(2,"Hot" ,"No")+"/" +totalNegData);
		
		System.out.println("P(Temperature=Mild|Yes)=" + calcCondProb(2,"Mild" ,"Yes")+"/" +totalPosData);
		System.out.println("P(Temperature=Mild|No)=" + calcCondProb(2,"Mild" ,"No")+"/" +totalNegData);

		System.out.println("P(Temperature=Cool|Yes)=" + calcCondProb(2,"Cool" ,"Yes")+"/" +totalPosData);
		System.out.println("P(Temperature=Cool|No)=" + calcCondProb(2,"Cool" ,"No")+"/" +totalNegData);

		//Humidity
		System.out.println("\nP(Humidity=High|Yes)=" + calcCondProb(3,"High" ,"Yes")+"/" +totalPosData);
		System.out.println("P(Humidity=High|No)=" + calcCondProb(3,"High" ,"No")+"/" +totalNegData);

		System.out.println("P(Humidity=Normal|Yes)=" + calcCondProb(3,"Normal" ,"Yes")+"/" +totalPosData);
		System.out.println("P(Humidity=Normal|No)=" + calcCondProb(3,"Normal" ,"No")+"/" +totalNegData);

		//Wind
		System.out.println("\nP(Wind=Weak|Yes)=" + calcCondProb(4,"Weak" ,"Yes")+"/" +totalPosData);
		System.out.println("P(Wind=Weak|No)=" + calcCondProb(4,"Weak" ,"No")+"/" +totalNegData);

		System.out.println("P(Wind=Strong|Yes)=" + calcCondProb(4,"Strong" ,"Yes")+"/" +totalPosData);
		System.out.println("P(Wind=Strong|No)=" + calcCondProb(4,"Strong" ,"No")+"/" +totalNegData);

		
	}

	public static void calcProbClass(){
		double classNo=0, classYes=0, temp,rainProb, coolProb, highProb, strongProb;
		double negProb =totalNegData/(double)noOfData;
		double posProb= totalPosData/(double)noOfData;
		
		//System.out.println("\nposProb: " +posProb +" negProb: " +negProb);
		
		//for given input
		
		rainProb= calcCondProb(1,"Rain", "No")/(double)totalNegData;
		coolProb= calcCondProb(2,"Cool", "No")/(double)totalNegData;
		highProb= calcCondProb(3,"High", "No")/(double)totalNegData;
		strongProb= calcCondProb(4,"Strong", "No")/(double)totalNegData;
		classNo = rainProb*coolProb*highProb*strongProb*negProb;

		rainProb= calcCondProb(1,"Rain", "Yes")/(double)totalPosData;
		coolProb= calcCondProb(2,"Cool", "Yes")/(double)totalPosData;
		highProb= calcCondProb(3,"High", "Yes")/(double)totalPosData;
		strongProb= calcCondProb(4,"Strong", "Yes")/(double)totalPosData;
		classYes = rainProb*coolProb*highProb*strongProb*posProb;
		
		temp=classNo + classYes;
		
		//System.out.println("\ntemp: "+temp+" Yes: " +classYes +" No: " +classNo);
		
		classNo= classNo/temp;
		classYes= classYes/temp;
		
		//System.out.println("\nYes: " +classYes +" No: " +classNo);
		
		System.out.println("\n\nX=(Outlook: Rain, Temperature: Cool, Humidity: High, Wind: Strong)");
		if(classYes < classNo){
			System.out.println("Play Tennis: No");
		}
		else{
			System.out.println("Play Tennis: Yes");
		}
	}
	public static void main(String args []){
		attributes= new ArrayList<ArrayList<String>>();
		
		String inFile="input.txt";
		readFile(inFile);
	
		printClassifierData();		
		calcProbClass();
	}

}
