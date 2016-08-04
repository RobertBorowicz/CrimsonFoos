import React from 'react';
import DeepEqual from '../../utils/DeepEqual.jsx';
import {Grid, Row, Col} from 'react-bootstrap';
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
        let game = this.props.game;
        return (
            <div className='game-group'>
                <div className='group-pair'>
                    <div>
                        <div>{game.red.players[0] + ' & ' + game.red.players[1]}</div>
                        <div>{game.red.goals}</div>
                    </div>
                    <div>
                        <div>{game.blue.players[0] + ' & ' + game.blue.players[1]}</div>
                        <div>{game.blue.goals}</div>
                    </div>
                </div>
                <div className={'group-element group-element-sep-' + winner}>{'<'}</div>
                <div className='group-element'></div>
            </div>
        );
    }
}