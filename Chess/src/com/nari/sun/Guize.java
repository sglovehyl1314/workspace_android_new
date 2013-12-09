package com.nari.sun;

import java.util.ArrayList;
import java.util.List;

public class Guize {
	boolean isRedGo = false;

	public boolean canMove(int[][] qizi, int fromX, int fromY, int toX, int toY) {
		int i = 0;
		int j = 0;
		int moveChessId;
		int targetId;
		if (toX < 0) {
			return false;
		}
		if (toX > 8) {
			return false;
		}
		if (toY < 0) {
			return false;
		}
		if (toY > 8) {
			return false;
		}
		if (fromX == toX && fromY == toY) {
			return false;
		}
		moveChessId = qizi[fromX][fromY];
		targetId = qizi[toX][toY];
		if (isSameSide(moveChessId, targetId)) {
			return false;
		}
		switch (moveChessId) {
		case 1:
			if (toY > 2 || toX < 3 || toX > 5) {
				return false;
			}
			if ((Math.abs(fromY - toY) + Math.abs(toX - fromX)) > 1) {
				return false;
			}
			break;
		case 5:
			if (toY > 2 || toX < 3 || toX > 5) {
				return false;
			}
			if (Math.abs(fromY - toY) != 1 || Math.abs(toX - fromX) != 1) {
				return false;
			}
			break;
		case 6:
			if (toY > 4) {
				return false;
			}
			if (Math.abs(fromY - toY) != 2 || Math.abs(fromX - toX) != 2) {
				return false;
			}
			if (qizi[(fromY + toY) / 2][(fromX + toX) / 2] != 0) {
				return false;
			}
			break;
		case 7:
			if (toY < fromY) {
				return false;
			}
			if (fromY < 5 && fromY == toY) {
				return false;
			}
			if (toY - fromY + Math.abs(toX - fromX) > 1) {
				return false;
			}
			break;
		case 8:
			if (toY < 7 || toX < 3 || toX > 5) {
				return false;
			}
			if ((Math.abs(fromY - toY) + Math.abs(toX - fromX)) > 1) {
				return false;
			}
			break;
		case 2:
		case 9:
			if (fromY != toY && fromX != toX) {
				return false;
			}
			if (fromY == toY) {
				if (fromX < toX) {
					for (i = fromX + 1; i < toX; i++) {
						if (qizi[fromY][i] != 0) {
							return false;
						}
					}
				} else {
					for (i = toX + 1; i < fromX; i++) {
						if (qizi[fromY][i] != 0) {
							return false;
						}
					}
				}
			} else {
				if (fromY < toY) {
					for (j = fromY + 1; j < toY; j++) {
						if (qizi[j][fromX] != 0) {
							return false;
						}
					}
				} else {
					for (j = toY + 1; j < fromY; j++) {
						if (qizi[j][fromX] != 0) {
							return false;
						}
					}
				}
			}
			break;
		case 10:
		case 3:
			if (!((Math.abs(toX - fromX) == 1 && Math.abs(toY - fromY) == 2) || (Math.abs(toX - fromX) == 2 && Math.abs(toY - fromY) == 1))) {
				return false;
			}
			if (toX - fromX == 2) {
				i = fromX + 1;
				j = fromY;
			} else if (fromX - toX == 2) {
				i = fromX - 1;
				j = fromY;
			} else if (toY - fromY == 2) {
				i = fromX;
				j = fromY + 1;
			} else if (fromY - toY == 2) {
				i = fromX;
				j = fromY - 1;
			}
			if (qizi[j][i] != 0) {
				return false;
			}
			break;
		case 11:
		case 4:
			if (fromY != toY && fromX != toX) {
				return false;
			}
			if (qizi[toY][toX] == 0) {
				if (fromY == toY) {
					if (fromX < toX) {
						for (i = fromX + 1; i < toX; i++) {
							if (qizi[i][fromY] != 0) {
								return false;
							}
						}
					} else {
						for (i = toX + 1; i < fromX; i++) {
							if (qizi[i][fromY] != 0) {
								return false;
							}
						}
					}
				} else {
					if (fromY < toY) {
						for (j = fromY + 1; j < toY; j++) {
							if (qizi[fromX][j] != 0) {
								return false;
							}
						}
					} else {
						for (j = toY + 1; j < fromY; j++) {
							if (qizi[fromX][j] != 0) {
								return false;
							}
						}
					}
				}
			} else {
				int count = 0;
				if (fromY == toY) {
					if (fromX < toX) {
						for (i = fromX + 1; i < toX; i++) {
							if (qizi[fromY][i] != 0) {
								count++;
							}
						}
						if (count != 1) {
							return false;
						}
					} else {
						for (i = toX + 1; i < fromX; i++) {
							if (qizi[fromY][i] != 0) {
								count++;
							}
						}
						if (count != 1) {
							return false;
						}
					}
				} else {
					if (fromY < toY) {
						for (j = fromY + 1; j < toY; j++) {
							if (qizi[j][fromX] != 0) {
								count++;
							}
						}
						if (count != 1) {
							return false;
						}
					} else {
						for (j = toY + 1; j < fromY; j++) {
							if (qizi[j][fromX] != 0) {
								count++;
							}
						}
						if (count != 1) {
							return false;
						}
					}
				}
			}
			break;
		case 12:
			if (toY < 7 || toX < 3 || toX > 5) {
				return false;
			}
			if (Math.abs(fromY - toY) != 1 || Math.abs(toX - fromX) != 1) {
				return false;
			}
			break;
		case 13:
			if (toY < 5) {
				return false;
			}
			if (Math.abs(fromY - toY) != 2 || Math.abs(fromX - toX) != 2) {
				return false;
			}
			if (qizi[(fromY + toY) / 2][(fromX + toX) / 2] != 0) {
				return false;
			}
			break;
		case 14:
			if (toY > fromY) {
				return false;
			}
			if (fromY > 4 && fromY == toY) {
				return false;
			}
			if (fromY - toY + Math.abs(toX - fromX) > 1) {
				return false;
			}
			break;
		default:
			return false;
		}

		return true;
	}

	public ChessMove searchAGoodMove(int[][] qizi) {
		List<ChessMove> ret = allPossibleMoves(qizi);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ret.get((int) (Math.random() * ret.size()));

	}

	public List<ChessMove> allPossibleMoves(int[][] qizi) {
		List<ChessMove> ret = new ArrayList<ChessMove>();
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 10; y++) {
				int chessman = qizi[x][y];
				if (chessman != 0) {
					if (chessman > 7) {
						continue;
					}
					switch (chessman) {
					case 1:
						if (canMove(qizi, x, y, x, y + 1)) {
							ret.add(new ChessMove(chessman, x, y, x, y + 1, 0));
						}
						if (canMove(qizi, x, y, x, y - 1)) {
							ret.add(new ChessMove(chessman, x, y, x, y - 1, 0));
						}
						if (canMove(qizi, x, y, x + 1, y)) {
							ret.add(new ChessMove(chessman, x, y, x + 1, y, 0));
						}
						if (canMove(qizi, x, y, x - 1, y)) {
							ret.add(new ChessMove(chessman, x, y, x - 1, y, 0));
						}
						break;
					case 5:
					case 12:
						if (canMove(qizi, x, y, x - 1, y + 1)) {
							ret.add(new ChessMove(chessman, x, y, x - 1, y + 1, 1));
						}
						if (canMove(qizi, x, y, x - 1, y - 1)) {
							ret.add(new ChessMove(chessman, x, y, x - 1, y - 1, 1));
						}
						if (canMove(qizi, x, y, x + 1, y + 1)) {
							ret.add(new ChessMove(chessman, x, y, x + 1, y + 1, 1));
						}
						if (canMove(qizi, x, y, x + 1, y - 1)) {
							ret.add(new ChessMove(chessman, x, y, x + 1, y - 1, 1));
						}
						break;
					case 6:
					case 13:
						if (canMove(qizi, x, y, x - 2, y + 2)) {
							ret.add(new ChessMove(chessman, x, y, x - 2, y + 2, 1));
						}
						if (canMove(qizi, x, y, x - 2, y - 2)) {
							ret.add(new ChessMove(chessman, x, y, x - 2, y - 2, 1));
						}
						if (canMove(qizi, x, y, x + 2, y + 2)) {
							ret.add(new ChessMove(chessman, x, y, x + 2, y + 2, 1));
						}
						if (canMove(qizi, x, y, x + 2, y - 2)) {
							ret.add(new ChessMove(chessman, x, y, x + 2, y - 2, 1));
						}
						break;
					case 7:
						if (canMove(qizi, x, y, x, y + 1)) {
							ret.add(new ChessMove(chessman, x, y, x, y + 1, 2));
						}
						if (y >= 5) {
							if (canMove(qizi, x, y, x - 1, y)) {
								ret.add(new ChessMove(chessman, x, y, x - 1, y, 2));
							}
							if (canMove(qizi, x, y, x + 1, y)) {
								ret.add(new ChessMove(chessman, x, y, x + 1, y, 2));
							}
						}
						break;
					case 14:
						if (canMove(qizi, x, y, x, y - 1)) {
							ret.add(new ChessMove(chessman, x, y, x, y - 1, 2));
						}
						if (y <= 4) {
							if (canMove(qizi, x, y, x - 1, y)) {
								ret.add(new ChessMove(chessman, x, y, x - 1, y, 2));
							}
							if (canMove(qizi, x, y, x + 1, y)) {
								ret.add(new ChessMove(chessman, x, y, x + 1, y, 2));
							}
						}
						break;
					case 8:
						if (canMove(qizi, x, y, x, y + 1)) {
							ret.add(new ChessMove(chessman, x, y, x, y + 1, 0));
						}
						if (canMove(qizi, x, y, x, y - 1)) {
							ret.add(new ChessMove(chessman, x, y, x, y - 1, 0));
						}
						if (canMove(qizi, x, y, x + 1, y)) {
							ret.add(new ChessMove(chessman, x, y, x + 1, y, 0));
						}
						if (canMove(qizi, x, y, x - 1, y)) {
							ret.add(new ChessMove(chessman, x, y, x - 1, y, 0));
						}
						break;
					case 2:
					case 9:
						for (int i = y + 1; i < 10; i++) {
							if (canMove(qizi, x, y, x, i)) {
								ret.add(new ChessMove(chessman, x, y, x, i, 0));
							} else {
								break;
							}
						}
						for (int i = y - 1; i > -1; i--) {
							if (canMove(qizi, x, y, x, i)) {
								ret.add(new ChessMove(chessman, x, y, x, i, 0));
							} else {
								break;
							}
						}
						for (int j = x - 1; j > -1; j--) {
							if (canMove(qizi, x, y, j, y)) {
								ret.add(new ChessMove(chessman, x, y, j, y, 0));
							} else {
								break;
							}
						}
						for (int j = x + 1; j < 9; j++) {
							if (canMove(qizi, x, y, j, y)) {
								ret.add(new ChessMove(chessman, x, y, j, y, 0));
							} else {
								break;
							}
						}
						break;
					case 10:
					case 3:
						if (canMove(qizi, x, y, x - 1, y - 2)) {
							ret.add(new ChessMove(chessman, x, y, x - 1, y - 2, 0));
						}
						if (canMove(qizi, x, y, x - 1, y + 2)) {
							ret.add(new ChessMove(chessman, x, y, x - 1, y + 2, 0));
						}
						if (canMove(qizi, x, y, x + 1, y - 2)) {
							ret.add(new ChessMove(chessman, x, y, x + 1, y - 2, 0));
						}
						if (canMove(qizi, x, y, x + 1, y + 2)) {
							ret.add(new ChessMove(chessman, x, y, x + 1, y + 2, 0));
						}

						if (canMove(qizi, x, y, x - 2, y - 1)) {
							ret.add(new ChessMove(chessman, x, y, x - 2, y - 1, 0));
						}
						if (canMove(qizi, x, y, x - 2, y + 1)) {
							ret.add(new ChessMove(chessman, x, y, x - 2, y + 1, 0));
						}
						if (canMove(qizi, x, y, x + 2, y - 1)) {
							ret.add(new ChessMove(chessman, x, y, x + 2, y - 1, 0));
						}
						if (canMove(qizi, x, y, x + 2, y + 1)) {
							ret.add(new ChessMove(chessman, x, y, x + 2, y + 1, 0));
						}
						break;
					case 11:
					case 4:
						for (int i = y + 1; i < 10; i++) {
							if (canMove(qizi, x, y, x, i)) {
								ret.add(new ChessMove(chessman, x, y, x, i, 0));
							}
						}
						for (int i = y - 1; i > -1; i--) {
							if (canMove(qizi, x, y, x, i)) {
								ret.add(new ChessMove(chessman, x, y, x, i, 0));
							} 
						}
						for (int j = x - 1; j > -1; j++) {
							if (canMove(qizi, x, y, j, y)) {
								ret.add(new ChessMove(chessman, x, y, j, y, 0));
							} 
						}
						for (int j = x + 1; j < 9; j++) {
							if (canMove(qizi, x, y, j, y)) {
								ret.add(new ChessMove(chessman, x, y, j, y, 0));
							} 
						}
						break;
					}
				}
			}
		}
		return ret.isEmpty() ? null : ret;
	}

	public boolean isSameSide(int moveChessId, int targetId) {
		if (targetId == 0) {
			return false;
		}
		if (moveChessId > 7 && targetId > 7) {
			return true;
		} else if (moveChessId <= 7 && targetId <= 7) {
			return true;
		} else {
			return false;
		}
	}
}
