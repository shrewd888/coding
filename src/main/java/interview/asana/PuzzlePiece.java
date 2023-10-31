package interview.asana;

public class PuzzlePiece
{
	PuzzleShape puzzleShape; //has 4 sides, each one with a unique outline which will only connect to one other piece.

	public PuzzlePiece() {}

	public PuzzlePiece(PuzzleShape puzzleShape)
	{
		this.puzzleShape = puzzleShape;
	}

}
