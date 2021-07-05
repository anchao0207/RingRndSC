import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class NonRandom {
	private List<Vertex> vertices = new LinkedList<>();
	int n;
	public NonRandom(int n,int y) {
		this.n=n;
		//generate ring base
		if(n>0) {
			vertices.add(new Vertex(0));
			for(int i=1;i<n;i++) {
				vertices.add(new Vertex(i));
				vertices.get(i).addNeighbor(vertices.get(i-1));
			}
			vertices.get(n-1).addNeighbor(vertices.get(0));
		}
		for(int j=0;j<y;j++) {
			for(int i=0+j*n/(2*y);i<n/2+j*n/(2*y);i++) {
					if(vertices.get(i%n).getNeighbor().contains(vertices.get((2*i+2)%n))||i%n==(2*i+2)%n) {
						continue;
					} else {
						vertices.get(i%n).addNeighbor(vertices.get((2*i+2)%n));
					}
			}
			for(int i=n/2+j*n/(2*y);i<n+j*n/(2*y);i++) {
				if(vertices.get(i%n).getNeighbor().contains(vertices.get((2*i+2+n/2)%n))||i%n==(2*i+2+n/2)%n) {
					continue;
				} else {
					vertices.get(i%n).addNeighbor(vertices.get((2*i+2+n/2)%n));
				}
			}
		}
		
		
		
		try {
		      FileWriter myWriter = new FileWriter("C:\\Users\\An Chao\\eclipse-workspace\\RingRndSC\\src\\Ring.txt");
		      myWriter.write(toString());
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public String toString(){
		String result=""+n+"\r";
		for(Vertex v:vertices) {
			result+=v.toString();
		}
		return result;
	}
}
