import React from 'react';
import DeepEqual from '../../utils/DeepEqual.jsx';
import {ListGroupItem} from 'react-bootstrap';
import './scores-view.scss';

export default class GameScore extends React.Component {

    static propTypes = {
        game: React.PropTypes.shape({
            red: React.PropTypes.shape({
                players: React.PropTypes.arrayOf(React.PropTypes.string),
                goals: React.PropTypes.number
            }),
            blue: React.PropTypes.shape({
                players: React.PropTypes.arrayOf(React.PropTypes.string),
                goals: React.PropTypes.number
            })
        }).isRequired
    };

    shouldComponentUpdate = DeepEqual.updateIfPropsOrStateChanged;

    render() {
        let winner = (this.props.game.red.goals > this.props.game.blue.goals)
                     ? 'red' : 'blue';
        let redStyle = (winner === 'red') ? 'winner' : 'loser';
        let blueStyle = (winner === 'blue') ? 'winner' : 'loser';
        let game = this.props.game;
        return (
            <div>
                <hr/>
                <div className='inline-container'>
                    <div className={'inline-left'}>
                        <p className={redStyle}>{game.red.players[0] + ' + ' + game.red.players[1]}</p>
                        <p className={blueStyle}>{game.blue.players[0] + ' + ' + game.blue.players[1]}</p>
                    </div>
                    <div className={'inline-right'}>
                        <p className={redStyle}>{game.red.goals}</p>
                        <p className={blueStyle}>{game.blue.goals}</p>
                    </div>
                    <div className='clear-both'/>
                </div>
            </div>
        );
    }
}