/**
 * @author Kenneth Shelton
 */

public class TreeNode<T> {
	private T data;
	public TreeNode<T> leftChild;
	public TreeNode<T> rightChild;

	
	public TreeNode(T dataNode)
	{
		data = dataNode;
		leftChild = null;
		rightChild = null;
	}
	
	public TreeNode(TreeNode<T> node)
	{
		this.data = node.getData();
		leftChild = node.leftChild;
		rightChild = node.rightChild;
	}
	
	public T getData()
	{
		return data;
	}
	
	public void setData(T data)
	{
		this.data = data;
	}
}
