/** Сделал Андрей 29.08.2018 */
import React, { Component } from 'react';
import axios from 'axios';

import Cell from './Cell';
import Win from './Win';

const style = {
    gameGrid: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center'
    },
    gameRow: {
        display: 'flex',
        flexDirection: 'row'
    },
    h1: {
        display: 'flex',
        justifyContent: 'center',
    },
    message: {
        display: 'flex',
        justifyContent: 'center'
    },
    currentPlayer: {
        display: 'flex',
        justifyContent: 'center',
    },
    button:{
        display: 'flex',
        justifyContent: 'center',
        margin: 5,
    }
};

/*const */
var initState = {
    gameState: 'play',
    gameWinner: null,
    currentPlayer: 'X',
    message: null,
    cellsState: Array(9).fill(null)
};

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {initState};
        this.changeGameState = this.changeGameState.bind(this);
    }

    // Перезапуск игры после победы или ничьей
    changeGameState() {
        initState.gameState = 'play';
        initState.cellsState = Array(9).fill(null);
        initState.gameWinner = null;
        initState.currentPlayer = 'X';
        initState.message = null;
        this.setState({initState});
    }

    // смена игрока
    changePlayerState() {
        if (String(this.state.initState.currentPlayer) === 'X') {
            initState.currentPlayer = 'O';
            this.setState({initState});
        } else {
            initState.currentPlayer = 'X';
            this.setState({initState});
        }
    }

    // Меняем знак в ячейке и проверяем состояние
    changeCellState(index) {
        //Проверяем пустая ли ячейка
        if (this.state.initState.cellsState[index] != null) {
            this.setState({ message: 'Ячейка занята, выберите другую!' });
            return;
        }
        this.setState({ message: null });
        const newCellsState = this.state.initState.cellsState.slice();
        //Меняем знак в ячейке
        initState.cellsState[index] = this.state.initState.currentPlayer;
        newCellsState[index] = this.state.initState.currentPlayer;
        //Проверяем на победу текущего игрока
        if (this.checkPlayerWin(this.state.initState.currentPlayer, newCellsState)) {
            initState.gameState = 'winner';
            initState.gameWinner = this.state.initState.currentPlayer;
            initState.message = 'Есть победитель!';
            this.setState({ initState });
        } else if(this.checkStandoff(newCellsState)) {           //Проверяем на ничью
            initState.message = 'Ничья. Перезапуск через 5 секунд!  8-)';
            this.setState({ initState });
            setTimeout(this.changeGameState,5000);
        }
        //Смена игрока после хода
        this.changePlayerState();
    }

    //Проверяем на ничью
    checkStandoff(newCellsState) {
        if (newCellsState.every((item) => item != null)) {
            return true;
        }
        return false;
    }

    //Проверяем на победу текущего игрока
    checkPlayerWin(player, newCellsState) {
        //Выйгрышные комбинации
        const winsCells = [
            //вертикали
            [0, 1, 2],
            [3, 4, 5],
            [6, 7, 8],
            //горизонтали
            [0, 3, 6],
            [1, 4, 7],
            [2, 5, 8],
            //диагонали
            [0, 4, 8],
            [2, 4, 6]
        ];
        //Проверка комбинаций
        let win = false;
        for (let i = 0; i < winsCells.length; i++) {
            const [a, b, c] = winsCells[i];
            if (newCellsState[a] && newCellsState[a] === newCellsState[b] && newCellsState[a] === newCellsState[c]) {
              win = true;
              break;
            }
          }
        return win;
    }

    renderCell(index) {
        return (
            <Cell
                value={this.state.initState.cellsState[index]}
                onClick={() => this.changeCellState(index)}
            />
        );
    }

    render() {
        if (this.state.initState.gameState === 'play') {
            return (
                <div>
                    <h1 style={style.h1}>Крестики-нолики</h1>
                    <p style={style.currentPlayer}>Сейчас ходит: {this.state.initState.currentPlayer}</p>
                    <div style={style.gameGrid}>
                        <div style={style.gameRow}>
                            {this.renderCell(0)}
                            {this.renderCell(1)}
                            {this.renderCell(2)}
                        </div>
                        <div style={style.gameRow}>
                            {this.renderCell(3)}
                            {this.renderCell(4)}
                            {this.renderCell(5)}
                        </div>
                        <div style={style.gameRow}>
                            {this.renderCell(6)}
                            {this.renderCell(7)}
                            {this.renderCell(8)}
                        </div>
                    </div>
                    <p style={style.message}>{this.state.initState.message}</p>
                </div>
            );
        } else if (this.state.initState.gameState === 'winner') {
            return <Win
                gameWinner={this.state.initState.gameWinner}
                changeGameState={this.changeGameState}
            />;
        } else {
            return <p>Loading...</p>;
        }
    }
}

export default App;
