import java.util.*; 
import java.lang.*; 
import java.io.*; 
  
class MST 
{ 
    static int V; 
    static int graph[][] =new int[V][V];
    int minKey(int key[], Boolean mstSet[]) 
    { 
        int min = Integer.MAX_VALUE, min_index=-1; 
  
        for (int v = 0; v < V; v++) 
            if (mstSet[v] == false && key[v] < min) 
            { 
                min = key[v]; 
                min_index = v; 
            } 
  
        return min_index; 
    } 
    void printMST(int parent[], int n, int graph[][]) 
    { 
        System.out.println("Edge \tWeight"); 
        for (int i = 1; i < V; i++) 
            System.out.println(parent[i]+" - "+ i+"\t"+ 
                            graph[i][parent[i]]); 
    } 
    void primMST(int graph[][]) 
    { 
        int parent[] = new int[V]; 
        int key[] = new int [V]; 
        Boolean mstSet[] = new Boolean[V]; 
        for (int i = 0; i < V; i++) 
        { 
            key[i] = Integer.MAX_VALUE; 
            mstSet[i] = false; 
        } 
        key[0] = 0;
        parent[0] = -1;
        for (int count = 0; count < V-1; count++) 
        {  
            int u = minKey(key, mstSet); 
            mstSet[u] = true; 
            for (int v = 0; v < V; v++) 
                if (graph[u][v]!=0 && mstSet[v] == false && 
                    graph[u][v] < key[v]) 
                { 
                    parent[v] = u; 
                    key[v] = graph[u][v]; 
                } 
        } 
        printMST(parent, V, graph); 
    } 
    public void readFile(String a){
        BufferedReader br = null;
        
        try {   
            br = new BufferedReader(new FileReader(a));       
            String textInALine;
            V= Integer.parseInt(br.readLine());
            String[] linetext = new String[V];
            String line;
            int i =0;
            while((line = br.readLine())!= null){
                linetext[i] = line;
                i++;
            }
            graph= new int[V][V];         
            for(int b=0;b<V;b++){              
                String[] words=linetext[b].split(",");
            
               for(int c=0;c<V;c++){ 
                graph[b][c]=Integer.parseInt(words[c]);
               }                
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       
    }
    public void out(int V)
    {
        for(int i =0;i < V ; i++)
        {
            for(int j =0 ; j< V ; j++)
            {
                System.out.print(graph[i][j]+" ");
            }
            System.out.println();
        } 
    }
    public static void main (String[] args) 
    { 
        MST t = new MST(); 
        t.readFile("MST.txt");
        t.out(V);
        t.primMST(graph); 
    } 
} 