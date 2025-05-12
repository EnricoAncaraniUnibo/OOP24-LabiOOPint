package labioopint.labyrinth;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import labioopint.model.api.Coordinate;
import labioopint.model.block.api.Block;
import labioopint.model.block.api.BlockType;
import labioopint.model.block.api.Rotation;
import labioopint.model.block.impl.BlockImpl;
import labioopint.model.maze.api.Maze;
import labioopint.model.maze.impl.SimpleMazeImpl;

/**
 * The GenerateMazeTest class contains unit tests for verifying the correct
 * generation and behavior of mazes. It ensures that the maze is generated
 * correctly, blocks are placed within valid ranges, no duplicate blocks exist,
 * and the outside block is correctly identified.
 */
class GenerateMazeTest {
    private static final Integer SIZE1 = 7;
	private static final Integer SIZE2 = 5;
	private static Maze gridTest1;
	private static Maze gridTest2;
	private static Block outsideBlock1;
	private static Block outsideBlock2;

	/**
	 * Initializes the test mazes and their outside blocks before all tests are
	 * executed.
	 */
	@BeforeAll
	static void init() {
		gridTest1 = new SimpleMazeImpl(SIZE1);
		gridTest2 = new SimpleMazeImpl(SIZE2);
		outsideBlock1 = gridTest1.generate();
		outsideBlock2 = gridTest2.generate();
	}

	/**
	 * Tests that all blocks in the maze are correctly generated and not empty.
	 */
	@Test
	void correctGeneration() {
		boolean correctGeneration1 = true;
		boolean correctGeneration2 = true;
		for (int i = 0; i < SIZE1; i++) {
			for (int j = 0; j < SIZE1; j++) {
				final Optional<BlockImpl> b = gridTest1.getBlock(new Coordinate(i, j));
				if (b.isEmpty()) {
					correctGeneration1 = false;
				}
			}
		}
		assertTrue(correctGeneration1);
		for (int i = 0; i < SIZE2; i++) {
			for (int j = 0; j < SIZE2; j++) {
				final Optional<BlockImpl> b = gridTest2.getBlock(new Coordinate(i, j));
				if (b.isEmpty()) {
					correctGeneration2 = false;
				}
			}
		}
		assertTrue(correctGeneration2);
	}

	/**
	 * Tests that no blocks are placed outside the valid range of the maze.
	 */
	@Test
	void noBlockOutsideRange() {
		boolean correctGeneration1 = true;
		boolean correctGeneration2 = true;
		for (int i = 0; i < SIZE1; i++) {
			final Optional<BlockImpl> b = gridTest1.getBlock(new Coordinate(i, -1));
			if (b.isPresent()) {
				correctGeneration1 = false;
			}
		}
		for (int i = 0; i < SIZE1; i++) {
			final Optional<BlockImpl> b = gridTest1.getBlock(new Coordinate(i, SIZE1));
			if (b.isPresent()) {
				correctGeneration1 = false;
			}
		}
		for (int i = 0; i < SIZE1; i++) {
			final Optional<BlockImpl> b = gridTest1.getBlock(new Coordinate(-1, i));
			if (b.isPresent()) {
				correctGeneration1 = false;
			}
		}
		for (int i = 0; i < SIZE1; i++) {
			final Optional<BlockImpl> b = gridTest1.getBlock(new Coordinate(SIZE1, i));
			if (b.isPresent()) {
				correctGeneration1 = false;
			}
		}
		assertTrue(correctGeneration1);

		for (int i = 0; i < SIZE2; i++) {
			final Optional<BlockImpl> b = gridTest2.getBlock(new Coordinate(i, -1));
			if (b.isPresent()) {
				correctGeneration2 = false;
			}
		}
		for (int i = 0; i < SIZE2; i++) {
			final Optional<BlockImpl> b = gridTest2.getBlock(new Coordinate(i, SIZE1));
			if (b.isPresent()) {
				correctGeneration2 = false;
			}
		}
		for (int i = 0; i < SIZE2; i++) {
			final Optional<BlockImpl> b = gridTest2.getBlock(new Coordinate(-1, i));
			if (b.isPresent()) {
				correctGeneration2 = false;
			}
		}
		for (int i = 0; i < SIZE2; i++) {
			final Optional<BlockImpl> b = gridTest2.getBlock(new Coordinate(SIZE1, i));
			if (b.isPresent()) {
				correctGeneration2 = false;
			}
		}
		assertTrue(correctGeneration2);
	}

	/**
	 * Tests that no duplicate blocks exist within the maze.
	 */
	@Test
	void checkNoDoubleBlocks() {
		boolean correctGeneration1 = true;
		boolean correctGeneration2 = true;
		for (int i = 0; i < SIZE1; i++) {
			for (int j = 0; j < SIZE1; j++) {
				final Optional<BlockImpl> b = gridTest1.getBlock(new Coordinate(i, j));
				for (int x = 0; x < SIZE1; x++) {
					for (int y = 0; y < SIZE1; y++) {
						final Optional<BlockImpl> test = gridTest1.getBlock(new Coordinate(x, y));
						if (b.equals(test) && i != x && j != y) {
							correctGeneration1 = false;
						}
					}
				}
			}
		}
		assertTrue(correctGeneration1);

		for (int i = 0; i < SIZE2; i++) {
			for (int j = 0; j < SIZE2; j++) {
				final Optional<BlockImpl> b = gridTest2.getBlock(new Coordinate(i, j));
				for (int x = 0; x < SIZE2; x++) {
					for (int y = 0; y < SIZE2; y++) {
						final Optional<BlockImpl> test = gridTest2.getBlock(new Coordinate(x, y));
						if (b.equals(test) && i != x && j != y) {
							correctGeneration2 = false;
						}
					}
				}
			}
		}
		assertTrue(correctGeneration2);
	}

	/**
	 * Tests that the block outside the maze is not present within the maze.
	 */
	@Test
	void correctOutsideBlock() {
		boolean correctOutside1 = true;
		boolean correctOutside2 = true;
		for (int i = 0; i < SIZE1; i++) {
			for (int j = 0; j < SIZE1; j++) {
				final Optional<BlockImpl> b = gridTest1.getBlock(new Coordinate(i, j));
				if (b.get().equals(outsideBlock1)) {
					correctOutside1 = false;
				}
			}
		}
		assertTrue(correctOutside1);

		for (int i = 0; i < SIZE2; i++) {
			for (int j = 0; j < SIZE2; j++) {
				final Optional<BlockImpl> b = gridTest2.getBlock(new Coordinate(i, j));
				if (b.get().equals(outsideBlock2)) {
					correctOutside2 = false;
				}
			}
		}
		assertTrue(correctOutside2);
	}

	/**
	 * Tests that the starting blocks of the maze have the correct rotation and
	 * type.
	 */
	@Test
	void correctStartingBlockRotation() {
		boolean correctGeneration1 = true;
		boolean correctGeneration2 = true;
		Optional<BlockImpl> b = gridTest1.getBlock(new Coordinate(0, 0));
		if (!b.get().getRotation().equals(Rotation.ZERO) || !b.get().getType().equals(BlockType.CORNER)) {
			correctGeneration1 = false;
		}
		b = gridTest1.getBlock(new Coordinate(0, SIZE1 - 1));
		if (!b.get().getRotation().equals(Rotation.TWO_HUNDRED_SEVENTY)
				|| !b.get().getType().equals(BlockType.CORNER)) {
			correctGeneration1 = false;
		}
		b = gridTest1.getBlock(new Coordinate(SIZE1 - 1, SIZE1 - 1));
		if (!b.get().getRotation().equals(Rotation.ONE_HUNDRED_EIGHTY) || !b.get().getType().equals(BlockType.CORNER)) {
			correctGeneration1 = false;
		}
		b = gridTest1.getBlock(new Coordinate(SIZE1 - 1, 0));
		if (!b.get().getRotation().equals(Rotation.NINETY) || !b.get().getType().equals(BlockType.CORNER)) {
			correctGeneration1 = false;
		}
		assertTrue(correctGeneration1);

		b = gridTest2.getBlock(new Coordinate(0, 0));
		if (!b.get().getRotation().equals(Rotation.ZERO) || !b.get().getType().equals(BlockType.CORNER)) {
			correctGeneration2 = false;
		}
		b = gridTest2.getBlock(new Coordinate(0, SIZE2 - 1));
		if (!b.get().getRotation().equals(Rotation.TWO_HUNDRED_SEVENTY)
				|| !b.get().getType().equals(BlockType.CORNER)) {
			correctGeneration2 = false;
		}
		b = gridTest2.getBlock(new Coordinate(SIZE2 - 1, SIZE2 - 1));
		if (!b.get().getRotation().equals(Rotation.ONE_HUNDRED_EIGHTY) || !b.get().getType().equals(BlockType.CORNER)) {
			correctGeneration2 = false;
		}
		b = gridTest2.getBlock(new Coordinate(SIZE2 - 1, 0));
		if (!b.get().getRotation().equals(Rotation.NINETY) || !b.get().getType().equals(BlockType.CORNER)) {
			correctGeneration2 = false;
		}
		assertTrue(correctGeneration2);
	}
}
