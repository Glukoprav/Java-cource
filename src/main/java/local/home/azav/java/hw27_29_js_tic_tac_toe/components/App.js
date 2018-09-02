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
        justifyContent: 'center',
        backgroundColor: 'lightgreen',
    },
    currentPlayer: {
        display: 'flex',
        justifyContent: 'center',
    },
    buttons: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        margin: 5,
    },
    button: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        margin: 5,
        border: 1,
        borderRadius: '20%',
        color: 'green',
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
        this.state = Object.assign({},initState,{cellsState: initState.cellsState.slice()});
        this.changeGameState = this.changeGameState.bind(this);
    }

    // Перезапуск игры после победы или ничьей
    changeGameState() {
        this.setState(Object.assign({},initState,{cellsState: initState.cellsState.slice()}));
    }

    // Проверяем ячейку и состояние, меняем знак в ячейке
    changeCellState(index) {
        //Проверяем пустая ли ячейка
        if (this.state.cellsState[index] != null) {
            this.setState({ message : 'Ячейка занята, выберите другую!' });
            return;
        }
        const newCellsState = this.state.cellsState.slice();
        //Меняем знак в ячейке
        newCellsState[index] = this.state.currentPlayer;
        //Проверяем на победу текущего игрока
        if (this.checkPlayerWin(this.state.currentPlayer, newCellsState)) {
            this.setState({ gameState : 'winner', gameWinner : this.state.currentPlayer });
        } else if(this.checkStandoff(newCellsState)) {           //Проверяем на ничью
            let message = 'Ничья. Перезапуск через 5 секунд!  8-)';
            this.setState({ message });
            setTimeout(this.changeGameState,5000);
        } else {
        //Смена игрока после хода
            let currP = '';
            if (String(this.state.currentPlayer) === 'X') {
                    currP = 'O';
                } else {
                    currP = 'X';
                }
            this.setState({ message: null, cellsState : newCellsState, currentPlayer : currP });
        }
    }

    // Перезапуск игры после выбора стартового символа
    changeCharPlay(charPlay) {
        const currentPlayer = (charPlay) ? charPlay : 'X';
        const state = Object.assign({},initState,{cellsState: initState.cellsState.slice(), currentPlayer })
        this.setState(state);
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
                value={this.state.cellsState[index]}
                onClick={() => this.changeCellState(index)}
            />
        );
    }

    render() {
        if (this.state.gameState === 'play') {
            return (
                <div>
                    <h1 style={style.h1}>Крестики-нолики</h1>
                    <p style={style.currentPlayer}>Сейчас ходит: {this.state.currentPlayer}</p>
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
                    <p style={style.message}>{this.state.message}</p>
                    <div style={style.buttons}>
                        <button style={style.button} onClick={() => this.changeCharPlay('X')}>{'Начать с X'}</button>
                        <button style={style.button} onClick={() => this.changeCharPlay('O')}>{'Начать с O'}</button>
                    </div>
                </div>
            );
        } else if (this.state.gameState === 'winner') {
            return <Win
                gameWinner={this.state.gameWinner}
                changeGameState={this.changeGameState}
            />;
        } else {
            return <p>Loading...</p>;
        }
    }
}

export default App;
