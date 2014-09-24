public class Knapsack {
	
	static int[][] valueTable;
	static boolean[][] keepTable;
	static int values[];
	static int weights[];
	static int totalItems;
	static int maxWeight;

	public static void main(String[] args) {
		totalItems = Integer.parseInt(args[0]);
		maxWeight = Integer.parseInt(args[1]);
		
		values = new int[maxWeight+1];//rename to values?
		weights = new int[maxWeight+1];
		
		valueTable = new int[maxWeight+1][maxWeight+1];
		keepTable = new boolean[maxWeight+1][maxWeight+1];
		
		initializeItems();
		buildTables();
		getMax();

	}
	
	public static void initializeItems(){
		values[0] = 0; weights[0] = 0;
				
		for(int i = 1; i <= totalItems; i++){
			values[i] = (int) (Math.random()*10);//value
			System.out.print("Value: " + values[i] + "\t");
			weights[i] = (int) (Math.random()*maxWeight);//weight
			System.out.println("Weight: " + weights[i]);
			//if(weights[i] == 0){weights[i] = 1;} //make sure nothing is weight 0
		}
	}
	
	
	public static void buildTables(){
		
		for(int i = 1; i < totalItems; i++){//items
			for(int j = 1; j < maxWeight; j++){//weights
				int prev = valueTable[i-1][j];
				int curr = 0;
				
				if(weights[j] <= maxWeight){ 
					if(j-(weights[i]) >= 0){ curr = valueTable[i-1][j-(weights[i])] + values[i]; }
				}
				
				valueTable[i][j] = Math.max(prev,  curr);
				
				keepTable[i][j] = (curr > prev);
				if(curr == 0) keepTable[i][j] = false;
			}
		}
	}//buildTables
	
	public static void getMax(){
		
		//DEBUGGING
		for(int i = 0; i < totalItems; i++){
			for(int j = 0; j < maxWeight; j++){
				System.out.print(valueTable[i][j] + " ");
			}
			System.out.println();
		}
		
		for(int i = 0; i < totalItems; i++){
			for(int j = 0; j < maxWeight; j++){
				System.out.print(keepTable[i][j] + " ");
			}
			System.out.println();
		}
		
		
		
		
		//DEBUGGING
		
		int j = maxWeight;
		boolean[] take = new boolean[totalItems+1];
		
		for(int i = totalItems; i > 0; i--){
			if(j-weights[i] > 0){ 
				if(keepTable[i][j] = true){ take[i] = true; j = j-weights[i]; }
				else				{ take[i] = false; }
			}else break;
		}
		
		System.out.println("Item" + "\t" + "Value" + "\t" + "Weight" + "\t" + "Take");
		for (int i = 1; i <= totalItems; i++){
			System.out.println(i + "\t" + values[i] + "\t" + weights[i] + "\t" + take[i]);
		}
		
	}


}
