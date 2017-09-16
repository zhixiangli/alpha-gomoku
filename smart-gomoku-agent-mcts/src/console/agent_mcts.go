package console

import (
	"gomoku"
	"mcts"
)

type MonteCarloTreeAgent struct {
	board    *gomoku.Board
	Searcher mcts.MonteCarloTreeSearch
}

func (p *MonteCarloTreeAgent) Clear() {
	p.board = nil
}

func (p *MonteCarloTreeAgent) Next(chessType gomoku.ChessType) gomoku.Location {
	return p.Searcher.Next(p.board, chessType)
}

func (p *MonteCarloTreeAgent) Reset(board *gomoku.Board) {
	p.board = board
}

func (p *MonteCarloTreeAgent) Play(gomoku.ChessType, gomoku.Location) {
	return
}