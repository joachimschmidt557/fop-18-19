// Class SelfOrganizedTree
// Methods for H1 Tests	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (root != null) {
			if (root.right != null) {
				SelfOrganizedTree.print(sb, true, "", root.right);
			}

			if (root.value == null) {
				sb.append("<null>");
			} else {
				sb.append(root.value.toString());
				sb.append("\n");
			}
			if (root.left != null) {
				SelfOrganizedTree.print(sb, false, "", root.left);
			}
		}
		return sb.toString();
	}

	@SuppressWarnings("rawtypes")
	private static void print(StringBuilder sb, boolean isRight, String indent, BinaryTreeNode node) {
		if (node.right != null)
			SelfOrganizedTree.print(sb, true, indent + (isRight ? "        " : " │      "), node.right);
		sb.append(indent);
		if (isRight)
			sb.append(" ┌──");
		else
			sb.append(" └──");
		sb.append("─────");
		if (node.value == null)
			sb.append("<null>");
		else {
			sb.append(node.value.toString());
			sb.append("\n");
		}
		if (node.left != null)
			SelfOrganizedTree.print(sb, false, indent + (isRight ? " |      " : "        "), node.left);
	}