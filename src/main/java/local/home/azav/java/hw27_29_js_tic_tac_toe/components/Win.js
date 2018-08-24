import React from 'react';

const style = {
    isWinner: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
    }
};

const Win = (props) => {
    return (
        <div style={style.isWinner}>
            <h1>Сongratulations!</h1>
            <h2>{props.gameWinner} is winner!</h2>
            <button onClick={props.changeGameState}>New game</button>
        </div>
    );
};

export default Win;
