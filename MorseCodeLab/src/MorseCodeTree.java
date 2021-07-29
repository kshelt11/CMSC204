/**
 * @author Kenneth Shelton
 */
import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {

	private TreeNode<String> root;
	
	public MorseCodeTree() {
		root = null;
		buildTree();
	}
	
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;
	}

	@Override
	public MorseCodeTree insert(String code, String letter) {
		//start recursion
		addNode(root,code,letter);
		return this;
	}

	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		//System.out.println("ADD CALL: "+code+" "+letter +" "+ root.getData());
		if(code.length()==1)	//base case
		{
			if(code.equals("."))	//go left
			{
				if(root.leftChild!=null)	//replace TEMP node
					root.leftChild.setData(letter);
				else
					root.leftChild = new TreeNode<String>(letter);
			}
			else if(code.equals("-"))	//go right
			{
				if(root.rightChild!=null)	//replace TEMP node
					root.rightChild.setData(letter);
				else
					root.rightChild = new TreeNode<String>(letter);
			}
		}
		else
		{
			if(code.charAt(0)=='.')
			{
				if(root.leftChild==null)	//TEMP node for flexible construction
					root.leftChild = new TreeNode<String>("TEMP");
				addNode(root.leftChild,code.substring(1),letter);
			}
			else if(code.charAt(0)=='-')
			{
				if(root.rightChild==null)	//TEMP node for flexible construction
					root.rightChild = new TreeNode<String>("TEMP");
				addNode(root.rightChild,code.substring(1),letter);
			}
		}
	}

	@Override
	public String fetch(String code) {
		//start recursion
		return fetchNode(root,code);
	}

	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		if(code.length()!=0)
			if(code.charAt(0)=='.')
				return fetchNode(root.leftChild,code.substring(1));
			else if(code.charAt(0)=='-')
				return fetchNode(root.rightChild,code.substring(1));
		if(root.getData()!=" ")
			return root.getData();	//base case if code is empty
		else
			return "";	//root is null, so return empty string
	}

	@Override
	public MorseCodeTree delete(String data) throws UnsupportedOperationException {
		return this;
	}

	@Override
	public MorseCodeTree update() throws UnsupportedOperationException {
		return this;
	}

	@Override
	public void buildTree() {
		String[] letters = {"a","b","c","d","e",
							"f","g","h","i","j",
							"k","l","m","n","o",
							"p","q","r","s","t",
							"u","v","w","x","y",
							"z"};
		String[] codes = {".-","-...","-.-.","-..",".",
							"..-.","--.","....","..",".---",
							"-.-",".-..","--","-.","---",
							".--.","--.-",".-.","...","-",
							"..-","...-",".--","-..-","-.--",
							"--.."};
		setRoot(new TreeNode<String>(""));
		
		for(int i=0;i<letters.length;i++)
			insert(codes[i],letters[i]);
	}

	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> list = new ArrayList<String>();
		LNRoutputTraversal(root,list);
		return list;
	}

	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if(root==null)
			return;
		LNRoutputTraversal(root.leftChild,list);
		list.add(root.getData());
		LNRoutputTraversal(root.rightChild,list);
	}

}
