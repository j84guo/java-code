import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ArrayList;

public class BuildOrder{

  static class Node{
    int data;
    LinkedList<Node> adjacent;

    Node(int data){
      this.data = data;
      this.adjacent = new LinkedList<>();
    }
  }

  private static HashMap<Integer, Node> buildDependencyGraph(String fileName){
    HashMap<Integer, Node> nodeMap = new HashMap<>();
    String s;

    try(
      BufferedReader br = new BufferedReader(new FileReader(fileName));
    ){
      // todo : better validation
      s = br.readLine(); // headers
      s = br.readLine(); // edges
    }catch(Exception e){
      s = null;
    }

    HashSet<int[]> edges = extractEdges(s);
    Node nodeU;
    Node nodeV;

    for(int[] e : edges){
      int u = e[0];
      int v = e[1];

      if(!nodeMap.containsKey(u)){
        nodeU = new Node(u);
        nodeMap.put(u, nodeU);
      }else{
        nodeU = nodeMap.get(u);
      }

      if(!nodeMap.containsKey(v)){
        nodeV = new Node(v);
        nodeMap.put(v, nodeV);
      }else{
        nodeV = nodeMap.get(v);
      }

      nodeU.adjacent.add(nodeV);
    }

    return nodeMap;
  }

  private static HashSet<int[]> extractEdges(String s){
    HashSet<int[]> edges = new HashSet<>();
    String[] tokens = s.split("\\)");

    for(String t : tokens){
      if(t.length() <= 3) continue;
      if(t.charAt(0) == ','){
        t = t.substring(3, t.length());
      }else{
        t = t.substring(1, t.length());
      }
      t = t.replace(" ", "");
      t = t.replace("(", "");

      String[] pair = t.split(",");
      int u = Integer.parseInt(pair[0]);
      int v = Integer.parseInt(pair[1]);

      int[] e = {u, v};
      edges.add(e);
    }

    return edges;
  }

  // this is performing a depth first search repeatedly
  private static ArrayList<Node> computeBuildOrder(HashMap<Integer, Node> nodeMap){
    ArrayList<Node> order = new ArrayList<>();
    boolean[] visited = new boolean[nodeMap.size()];
    for(Integer id : nodeMap.keySet()){
      Node node = nodeMap.get(id);
      topologicalDepthFirstSearch(node, visited, order);
    }
    return order;
  }

  private static void topologicalDepthFirstSearch(Node node, boolean[] visited, ArrayList<Node> order){
    if(visited[node.data]) return;
    visited[node.data] = true;

    for(Node neighbour : node.adjacent){
      topologicalDepthFirstSearch(neighbour, visited, order);
    }

    order.add(node);
  }

  public static void main(String[] args){
    String fileName = "buildEdges";
    HashMap<Integer, Node> nodeMap = buildDependencyGraph(fileName);
    ArrayList<Node> order = computeBuildOrder(nodeMap);
    for(int i=0; i<order.size(); i++){
      System.out.println(order.get(i).data);
    }
  }
}
