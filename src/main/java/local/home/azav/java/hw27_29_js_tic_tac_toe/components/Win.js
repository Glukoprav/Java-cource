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
            <h1>Примите поздравления!</h1>
            <h2>Победитель - {props.gameWinner} !</h2>
            <button onClick={props.changeGameState}>Новая игра</button>
        </div>
    );
};

export default Win;
