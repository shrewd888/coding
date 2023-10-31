package interview.asana;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/discuss/interview-question/550008/asana-software-engineer-design-a-jigsaw-puzzle-ood
 * Design a jigsaw puzzle algorithm, given a Piece class, a Side class containing sideTypes,
 * and a helper function which returns whether or not two pieces are a match
 */
//2 dimensional Board
public class PuzzleBoard
{
	Set<PuzzlePiece> pieces = new HashSet<>();
	int totalPieces;
	int row;
	int col;

	PuzzlePiece currentPuzzle;

	public PuzzleBoard(int row, int col, int totalPieces) {
		this.row = row;
		this.col = col;
		this.totalPieces = totalPieces;
	}

	public void addPiece(PuzzlePiece puzzlePiece)
	{
		this.pieces.add(puzzlePiece);
		this.currentPuzzle = puzzlePiece;
	}

	public boolean isComplete()
	{
		return pieces.size() == row * col;
	}

	//group pieces with the same characteristic of shape
	public void groupPieces(PuzzlePiece puzzlePiece)
	{
		Shape[] inputShape = puzzlePiece.puzzleShape.shapes;

		PuzzleShape currentShape = currentPuzzle.puzzleShape;
		Shape[] shapes = currentShape.shapes;
		for (Shape shape : shapes)
		{

		}

	}

	public boolean isMatch(PuzzlePiece puzzlePiece)
	{
		//start from [0,0] in the grid
		return true;
	}

}
