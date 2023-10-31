package interview.asana;

public class PuzzleShape
{
	boolean isEdge = false;
	boolean isCorner = false;
	Shape[] shapes = new Shape[4]; //4 edges

	public PuzzleShape() {
	}

	public PuzzleShape(Shape[] shapes)
	{
		this.shapes = shapes;
		int numOfEdge = 0;
		for (Shape shape : shapes)
		{
			if (Shape.FLAT.equals(shape))
			{
				numOfEdge = numOfEdge + 1;
			}
		}
		if (numOfEdge == 2)
		{
			isCorner = true;
		}
		else if (numOfEdge == 1)
		{
			isEdge = true;
		}
	}


}
