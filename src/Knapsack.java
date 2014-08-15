
public class Knapsack {
	
	public static int[][] valueTable;
	public static boolean[][] keepTable;
	public static int items[];
	public static int weights[];

	public static void main(String[] args) {

		int capacity = Integer.parseInt(args[0]);
		int maxItems = Integer.parseInt(args[1]);
		
		valueTable = new int[maxItems+1][maxItems+1];
		keepTable = new boolean[maxItems+1][maxItems+1];
		items = new int[maxItems+1];
		weights = new int[maxItems+1];
		
		initializeItems(capacity, maxItems);
		buildTables(capacity, maxItems);

	}
	
	public static void initializeItems(int capacity, int maxItems){
		items[0] = 0; weights[0] = 0;
				
		for(int i = 1; i < maxItems; i++){
			items[i] = (int) (Math.random()*100);
			weights[i] = (int) (Math.random()*capacity);
		}
	}
	
	
	public static void buildTables(int capacity, int maxItems){
		for(int i = 0; i < maxItems; i++){//initialize with 0s for weight of 0 and value of 0
			valueTable[i][0] = 0;
			keepTable[i][0] = false;
			valueTable[0][i] = 0;
			keepTable[0][i] = false;
		}
		
		for(int i = 1; i < maxItems; i++){//weights
			for(int j = 1; j < maxItems; j++){//item key
				if(i > weights[j] && valueTable[i-(weights[j])][j-1] + items[j] >= valueTable[i][j-1]){//if it fits. if its value + remaining weight from prev row > prev item.
					valueTable[i][j] = items[j] + valueTable[i-(weights[j])][j-1];//add the addition of the two
					keepTable[i][j] = true;
					}else{
						valueTable[i][j] = valueTable[i][j-1];
						keepTable[i][j] = false;
					}
			}
		}
	}


}
