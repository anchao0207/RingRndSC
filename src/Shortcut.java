import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Shortcut {
	private List<Vertex> vertices = new LinkedList<>();
	int n;
	public Shortcut(int n,int y) {
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
		//adding random shortcuts
		Random rand = new Random();
		
		for(int i=0;i<n;i++) {
			if(vertices.get(i).getNeighbor().size()<y+2) {
				int temp=vertices.get(i).getNeighbor().size();
				for(int j=0;j<y+2-temp;j++) {
					int r = rand.nextInt(n);
					if(r==i||vertices.get(i).getNeighbor().contains(vertices.get(r))||vertices.get(r).getNeighbor().size()==y+2) {
						j--;
					} else {
						vertices.get(i).addNeighbor(vertices.get(r));
					}
				}
			}
		}
		
		//create a file for this RSC
//		try {
//			File RSC = new File("C:\\Users\\An Chao\\eclipse-workspace\\RingRndSC\\src\\RCS.txt");
//			if(RSC.createNewFile()) {
//				System.out.println("File created:"+RSC.getName());
//			} else {
//				System.out.println("File already exist.");
//			}
//		} catch(IOException e) {
//			System.out.println("Error occurred!");
//			e.printStackTrace();
//		}
		
		try {
		      FileWriter myWriter = new FileWriter("C:\\Users\\An Chao\\eclipse-workspace\\RingRndSC\\src\\RSC.txt");
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
