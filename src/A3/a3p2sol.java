package A3;
//pseudo-code
public class a3p2sol {

	class Result{
		int height = 0;
	}

	int diameter(TreeNode p, Result result){
		Result leftResult = new Result();
		Result rightResult = new Result();
		
		if(isExternal(p) || p == null){
			r.height = 0;
			return 0;
		} 
		else{
			int leftDiameter = diameter(p.leftChild, leftResult);
			int rightDiameter = diameter(p.rightChild, rightResult);
			
			result.height = max(leftResult.height, rightResult.height) + 1;
			
			return max( max(leftDiameter, rightDiameter),  
						leftResult.height + rightResult.height + 2);
		}
	}

	int diameter(){
	     Height height = new Height();
	     return diameterOpt(root, height);
	 }



}
